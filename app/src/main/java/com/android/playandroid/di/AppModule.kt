package com.android.playandroid.di
import com.android.playandroid.repository.LoginRepository
import com.android.playandroid.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
}

val repositoryModule = module {
    single { LoginRepository() }
    //single { HomeRepository() }
}

val appModule = listOf(viewModelModule, repositoryModule)