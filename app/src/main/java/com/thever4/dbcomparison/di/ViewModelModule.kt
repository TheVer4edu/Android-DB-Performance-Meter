package com.thever4.dbcomparison.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.thever4.dbcomparison.presenter.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel

}