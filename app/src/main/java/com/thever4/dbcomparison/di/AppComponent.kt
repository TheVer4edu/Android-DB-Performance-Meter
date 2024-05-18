package com.thever4.dbcomparison.di

import android.app.Application
import com.thever4.dbcomparison.presenter.MainFragment
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {

    fun inject(fragment: MainFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder
        fun build(): AppComponent
    }

}

@Module(
    includes = [
        ViewModelModule::class,
        FlavorModule::class,
        AppBindsModule::class,
    ]
)
class AppModule