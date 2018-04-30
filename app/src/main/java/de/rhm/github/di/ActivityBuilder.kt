package de.rhm.github.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import de.rhm.github.RepoListActivity

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindRepoListActivity(): RepoListActivity

}