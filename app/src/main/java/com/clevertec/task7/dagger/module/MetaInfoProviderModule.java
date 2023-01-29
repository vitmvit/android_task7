package com.clevertec.task7.dagger.module;

import com.clevertec.task7.api.provider.MetaInfoApiProvider;
import dagger.Module;
import dagger.Provides;

@Module
public class MetaInfoProviderModule {

    @Provides
    MetaInfoApiProvider providesApiProvider() {
        return new MetaInfoApiProvider();
    }
}
