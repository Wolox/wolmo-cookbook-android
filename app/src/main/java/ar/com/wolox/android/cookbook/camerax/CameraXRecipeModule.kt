package ar.com.wolox.android.cookbook.camerax

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CameraXRecipeModule {

    @ContributesAndroidInjector
    abstract fun cameraXRecipeActivity(): CameraXRecipeActivity

    @ContributesAndroidInjector
    abstract fun cameraXRecipeFragment(): CameraXRecipeFragment

    @ContributesAndroidInjector
    abstract fun pictureActivity(): PictureActivity

    @ContributesAndroidInjector
    abstract fun pictureFragment(): PictureFragment
}