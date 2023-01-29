package com.clevertec.task7.dagger.component;

import com.clevertec.task7.api.provider.RemoteApiProvider;
import com.clevertec.task7.dagger.module.RemoteApiProviderModule;
import dagger.Component;

@Component(modules = RemoteApiProviderModule.class)
public interface ApiComponent {

    void inject(RemoteApiProvider apiProvider);
}
