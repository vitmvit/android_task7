package com.clevertec.task7.dagger.module;

import com.clevertec.task7.api.provider.RemoteApiProvider;
import dagger.Module;
import dagger.Provides;

@Module
public class RemoteApiProviderModule {

    @Provides
    RemoteApiProvider providesApiProvider() {
        return new RemoteApiProvider();
    }
}
