package ar.com.wolox.android.cookbook.koin.core

interface BasePresenter<T> {
    var view: T
}