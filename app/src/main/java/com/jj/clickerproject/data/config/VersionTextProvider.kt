package com.jj.clickerproject.data.config

import com.jj.clickerproject.BuildConfig

class VersionTextProvider {

    fun getAboutVersionText(): String = "Revision: ${BuildConfig.currentRevisionHash}, " +
            "Build number: ${BuildConfig.ciBuildNumber}, Version: ${BuildConfig.VERSION_NAME}"
}