package com.mobileoak.rickandmorty

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ApplicationComponent::class)
class NetworkManagerModule {

    @Provides
    fun getNetworkManager(@ApplicationContext appContext: Context): NetworkManager {
        return NetworkManager(appContext)
    }
}