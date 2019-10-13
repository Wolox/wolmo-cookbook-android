package ar.com.wolox.android.cookbook.coroutines.football

import ar.com.wolox.android.cookbook.common.network.networkCallback
import ar.com.wolox.android.cookbook.coroutines.football.networking.CallbackFootballRepository
import okhttp3.ResponseBody
import javax.inject.Inject

class CallbackFootballPresenter @Inject constructor(
    private val footballRepository: CallbackFootballRepository
) : FootballPresenter() {

    override fun onRandomPlayerMatchesButtonClicked() {
        showRandomPlayerMatches()
    }

    override fun onRandomTeamsSquadsSequentialButtonClicked() = view.showNotAvailableError()

    override fun onRandomTeamsSquadsAsyncButtonClicked() = view.showNotAvailableError()

    private fun showRandomPlayerMatches() {
        footballRepository.getCompetition(SPAIN_COMPETITION_ID, networkCallback {

            onResponseSuccessful competitionCallback@{ competition ->
                if (competition == null) {
                    return@competitionCallback
                }

                footballRepository.getTeam(competition.teams.random().id, networkCallback {

                    onResponseSuccessful teamCallback@{ team ->
                        if (team == null) {
                            return@teamCallback
                        }

                        val player = team.squad.random()

                        footballRepository.getMatches(player.id, networkCallback {

                            onResponseSuccessful matchesCallback@{ matches ->
                                if (matches == null) {
                                    return@matchesCallback
                                }

                                view.showPlayerMatches(team, player, matches)
                            }

                            onResponseFailed(::handleError)
                        })
                    }

                    onResponseFailed(::handleError)
                })
            }

            onResponseFailed(::handleError)
        })
    }

    private fun handleError(body: ResponseBody?, code: Int) {
        if (code == TOO_MANY_REQUESTS_ERROR_CODE) {
            view.showTooManyRequestsError()
        } else {
            view.showUnexpectedError()
        }
    }
}