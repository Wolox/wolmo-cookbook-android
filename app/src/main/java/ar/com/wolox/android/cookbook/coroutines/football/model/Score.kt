package ar.com.wolox.android.cookbook.coroutines.football.model

import com.google.gson.annotations.SerializedName

class Score(@SerializedName("fullTime") val fulltime: TimeScore)

class TimeScore(
    @SerializedName("homeTeam") val home: Int,
    @SerializedName("awayTeam") val away: Int
)