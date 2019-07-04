package ar.com.wolox.android.cookbook.koin.core

interface BasePresenter<T> {
    val view: T
}