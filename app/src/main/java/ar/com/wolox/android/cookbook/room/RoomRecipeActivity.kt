package ar.com.wolox.android.cookbook.room

import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.activity.WolmoActivity

/**
 * To implement DB example:
 * 1. Add dependencies (See "Notes" at bottom)
 * 2. Define Entities (single table row)
 * 3. Define DAOs (DataAccessObject)
 * 4. Define DataBase
 * (Example defined in "ar.com.wolox.android.cookbook.room.database")
 */
class RoomRecipeActivity : WolmoActivity() {

    override fun layout(): Int = R.layout.activity_base

    override fun init() = replaceFragment(R.id.vActivityBaseContent, RoomRecipeFragment())
}

/**
 * Notes: // Room
 *   implementation "androidx.room:room-runtime:2.1.0-alpha01"
 *   implementation "androidx.legacy:legacy-support-v4:1.0.0"
 *   implementation 'androidx.lifecycle:lifecycle-extensions:2.0.0'
 *   implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.0.0'
 *   kapt "androidx.room:room-compiler:2.1.0-alpha01"
 */