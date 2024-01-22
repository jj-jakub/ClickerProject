package com.jj.clickerproject.di

import com.jj.clickerproject.data.google.DefaultClickerRepository
import com.jj.clickerproject.data.google.network.ClickerNetwork
import com.jj.clickerproject.data.google.service.ClickerService
import com.jj.clickerproject.domain.google.ClickerRepository
import org.koin.dsl.module
import retrofit2.Retrofit

val networkingModule = module {
    single { get<Retrofit>().create(ClickerService::class.java) }

    single { ClickerNetwork(clickerService = get()) }
    single<ClickerRepository> { DefaultClickerRepository(clickerNetwork = get()) }
}