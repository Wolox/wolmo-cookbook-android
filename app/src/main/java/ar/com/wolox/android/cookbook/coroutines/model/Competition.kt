package ar.com.wolox.android.cookbook.coroutines.model

import com.google.gson.annotations.SerializedName

data class Competition(@SerializedName("competition") private val data: CompetitionData, val teams: List<Team>) {

    val name: String
        get() = data.name
}

data class CompetitionData(val name: String)
