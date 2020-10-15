package com.debin.gitterandroid.di

import com.google.firebase.auth.FirebaseAuth
import com.debin.gitterandroid.persistence.DatabaseFactory
import com.debin.gitterandroid.network.GitRetrofitInstance
import com.debin.gitterandroid.repositories.AuthGitRepository
import com.debin.gitterandroid.repositories.IAuthGitRepository
import com.debin.gitterandroid.repositories.ISearchGitRepo
import com.debin.gitterandroid.repositories.SearchGitRepoRepository
import com.debin.gitterandroid.ui.addUser.AddUserRepositoryGithubViewModel
import com.debin.gitterandroid.ui.commits.CommitsViewModel
import com.debin.gitterandroid.ui.repo.SearchRepoViewModel
import com.debin.gitterandroid.ui.users.UserViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val databaseModule = module {
    single { DatabaseFactory.getDBInstance(get()) }
}

val firebaseModule = module {
    single { FirebaseAuth.getInstance() }
}


val networkModule = module {
    single { GitRetrofitInstance().retrofitPupil() }
}
val repositoriesModule = module {
    single<IAuthGitRepository> { AuthGitRepository() }
    single<ISearchGitRepo> { SearchGitRepoRepository(get(), get()) }
}

val viewModelModule = module {
    viewModel { AddUserRepositoryGithubViewModel(get(),get()) }
    viewModel { UserViewModel(get()) }
    viewModel { SearchRepoViewModel(get()) }
    viewModel  { CommitsViewModel(get()) }
}
