package ar.com.wolox.android.cookbook.coroutines.football

import ar.com.wolox.android.cookbook.coroutines.football.model.Competition
import ar.com.wolox.android.cookbook.coroutines.football.model.Match
import ar.com.wolox.android.cookbook.coroutines.football.model.Player
import ar.com.wolox.android.cookbook.coroutines.football.model.Team

interface FootballView {

    fun showCompetition(competition: Competition)

    fun showPlayerMatches(team: Team, player: Player, matches: List<Match>)

    fun showElapsedTime(elapsedTime: Long)

    fun showTooManyRequestsError()

    fun showUnexpectedError()

    fun showNotAvailableError()
}
