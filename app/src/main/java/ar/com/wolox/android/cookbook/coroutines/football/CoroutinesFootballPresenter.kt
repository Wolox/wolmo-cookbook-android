package ar.com.wolox.android.cookbook.coroutines.football

import ar.com.wolox.android.cookbook.coroutines.core.unit
import ar.com.wolox.android.cookbook.coroutines.football.networking.CallbackToCoroutineFootballRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject
import kotlin.system.measureTimeMillis

class CoroutinesFootballPresenter @Inject constructor(
    private val footballRepository: CallbackToCoroutineFootballRepository
) : FootballPresenter() {

    override fun onRandomPlayerMatchesButtonClicked() = launch {
        showRandomPlayerMatches()
    }.unit

    override fun onRandomTeamsSquadsSequentialButtonClicked() = launch {
        measureTimeAndShow { showCompetitionSequential() }
    }.unit

    override fun onRandomTeamsSquadsAsyncButtonClicked() = launch {
        measureTimeAndShow { showCompetitionAsync() }
    }.unit

    /** Gets a random player from a random team from spain and shows the matches he played. */
    private suspend fun showRandomPlayerMatches() {
        try {
            val competition = footballRepository.getCompetition(SPAIN_COMPETITION_ID)
            val team = footballRepository.getTeam(competition.teams.random().id)
            val player = team.squad.random()
            val matches = footballRepository.getMatches(player.id)
            view.showPlayerMatches(team, player, matches)
        } catch (exception: HttpException) {
            handleError(null, exception.code())
        } catch (exception: Exception) {
            view.showUnexpectedError()
        }
    }

    /**
     * Gets [MAX_REQUESTS] teams from Spain, fetches sequentially the squads and shows them.
     * Then, it returns the elapsed time of the task.
     */
    private suspend fun showCompetitionSequential() {
        try {
            val competition = footballRepository.getCompetition(SPAIN_COMPETITION_ID).run {
                copy(teams = teams
                        .shuffled()
                        .take(MAX_REQUESTS)
                        .map { footballRepository.getTeam(it.id) })
            }

            view.showCompetition(competition)
        } catch (exception: HttpException) {
            handleError(null, exception.code())
        } catch (exception: Exception) {
            view.showUnexpectedError()
        }
    }

    /**
     * Gets [MAX_REQUESTS] teams from Spain, fetches async the squads and shows them.
     * Then, it returns the elapsed time of the task.
     */
    private suspend fun showCompetitionAsync() {
        try {
            val competition = footballRepository.getCompetition(SPAIN_COMPETITION_ID).run {
                copy(teams = teams
                        .shuffled()
                        .take(MAX_REQUESTS)
                        .map { async { footballRepository.getTeam(it.id) } }
                        .awaitAll())
            }

            view.showCompetition(competition)
        } catch (exception: HttpException) {
            handleError(null, exception.code())
        } catch (exception: Exception) {
            view.showUnexpectedError()
        }
    }

    private inline fun measureTimeAndShow(block: () -> Unit) {
        val elapsedTime = measureTimeMillis { block() }
        view.showElapsedTime(elapsedTime)
    }
}
