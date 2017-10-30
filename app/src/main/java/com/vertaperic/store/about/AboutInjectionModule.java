package com.vertaperic.store.about;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class AboutInjectionModule {

    @ContributesAndroidInjector(modules = Declarations.class)
    abstract AboutFragment contributeAboutFragmentInjector();

    @Module
    abstract class Declarations {

        @Binds
        abstract AboutContract.Presenter bindsAboutPresenter(AboutPresenter presenter);
    }
}
