package com.jj.clickerproject.presentation.activities

import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.ComposeView
import androidx.lifecycle.setViewTreeLifecycleOwner
import androidx.navigation.compose.rememberNavController
import androidx.savedstate.setViewTreeSavedStateRegistryOwner
import com.jj.clickerproject.click.ClickOverlayView
import com.jj.clickerproject.presentation.MainRoot
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private var clickOverlayView: View? = null

    private val mainActivityViewModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(getOverlaySettingsIntent(context = this), null)
        setContent {
            val navHostController = rememberNavController()
            MainRoot(
                navController = navHostController,
                viewModel = getViewModel(),
            )
            val state = mainActivityViewModel.viewState.collectAsState()

            removeClickOverlayView()
            addOverlayView(
                overlayView = prepareClickOverlayView(
                    isActive = state.value.isClickSequenceRunning,
                    onStartClicking = mainActivityViewModel::onStartClicking,
                    onStopClicking = mainActivityViewModel::onStopClicking,
                ),
                overlayViewParams = prepareCallFloatingOverlayViewParams(),
            )
        }
    }

    private fun prepareClickOverlayView(
        isActive: Boolean,
        onStartClicking: () -> Unit,
        onStopClicking: () -> Unit,
    ) =
        ComposeView(this).apply {
            setContent {
                ClickOverlayView(
                    isActive = isActive,
                    onStartClicking = onStartClicking,
                    onStopClicking = onStopClicking,
                )
            }
            setViewTreeLifecycleOwner(this@MainActivity)
            setViewTreeSavedStateRegistryOwner(this@MainActivity)
        }

    private fun addOverlayView(
        overlayView: View?,
        overlayViewParams: WindowManager.LayoutParams,
    ) {
        clickOverlayView = overlayView
        kotlin.runCatching {
            val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
            windowManager.addView(overlayView, overlayViewParams)
        }
    }

    private fun prepareCallFloatingOverlayViewParams(): WindowManager.LayoutParams {
        val layoutParams = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        )
        layoutParams.gravity = Gravity.TOP or Gravity.CENTER
        return layoutParams
    }

    private fun removeClickOverlayView() {
        removeOverlayView(clickOverlayView)
        clickOverlayView = null
    }

    private fun removeOverlayView(
        overlayView: View?,
    ) {
        overlayView ?: return
        kotlin.runCatching {
            val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
            windowManager.removeView(overlayView)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun getOverlaySettingsIntent(context: Context) = Intent(
        Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
        Uri.parse("package:${context.packageName}"))
}