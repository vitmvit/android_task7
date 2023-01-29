package com.clevertec.task7.dagger.component;

import com.clevertec.task7.api.provider.MetaInfoApiProvider;
import com.clevertec.task7.dagger.module.MetaInfoProviderModule;
import dagger.Component;

@Component(modules = MetaInfoProviderModule.class)
public interface ApiComponent {

    void inject(MetaInfoApiProvider apiProvider);
}
