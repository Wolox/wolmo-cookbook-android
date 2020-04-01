package ar.com.wolox.android.cookbook.coroutines.football.networking.responses

import ar.com.wolox.android.cookbook.coroutines.football.model.Team
import com.google.gson.annotations.SerializedName

data class CompetitionResponse(@SerializedName("competition") val data: CompetitionData, val teams: List<Team>)

data class CompetitionData(val id: Long, val name: String)
