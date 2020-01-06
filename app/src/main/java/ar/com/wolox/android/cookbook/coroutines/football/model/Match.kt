package ar.com.wolox.android.cookbook.coroutines.football.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class Match(
    val id: Long,
    val competition: Competition,
    @SerializedName("homeTeam") val homeTeam: Team,
    @SerializedName("awayTeam") val awayTeam: Team,
    @SerializedName("utcDate") val date: Date,
    val score: Score,
    val attendance: String,
    val matchday: String,
    val stage: String,
    val group: String
)
