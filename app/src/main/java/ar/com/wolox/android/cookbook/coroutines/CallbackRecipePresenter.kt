package ar.com.wolox.android.cookbook.coroutines

import ar.com.wolox.android.cookbook.coroutines.core.CoroutineBasePresenter
import ar.com.wolox.android.cookbook.coroutines.model.Competition
import ar.com.wolox.android.cookbook.coroutines.model.Team
import ar.com.wolox.android.cookbook.coroutines.networking.CallbackFootballRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CallbackRecipePresenter @Inject constructor(
        private val footballRepository: CallbackFootballRepository
) : CoroutineBasePresenter<CoroutinesRecipeView>() {

    // FIRST APPROACH
    //    private fun fetchCompetition() {
    //        footballRepository.getCompetition(SPAIN_COMPETITION_ID, object : Callback<Competition> {
    //            override fun onResponse(call: Call<Competition>, response: Response<Competition>) {
    //                val competition = response.body()
    //                if (competition != null) {
    //                    val teams = mutableListOf<Team>()
    //                    var teamsLeft = MAX_REQUESTS
    //                    competition.teams.shuffled().take(MAX_REQUESTS).forEach {
    //                        footballRepository.getTeam(it.id, object : Callback<Team> {
    //                            override fun onResponse(call: Call<Team>, response: Response<Team>) {
    //                                response.body()?.let { team -> teams.add(team) }
    //                                teamsLeft--
    //
    //                                if (teamsLeft == 0) {
    //                                    view.showCompetition(competition.copy(teams = teams))
    //                                }
    //                            }
    //
    //                            override fun onFailure(call: Call<Team>, t: Throwable) {
    //                            }
    //                        })
    //                    }
    //                }
    //            }
    //
    //            override fun onFailure(call: Call<Competition>, t: Throwable) {}
    //        })
    //    }

    override fun onViewAttached() {
        fetchCompetition()
    }

    private fun fetchCompetition() {
        footballRepository.getCompetition(SPAIN_COMPETITION_ID, object : Callback<Competition> {

            override fun onResponse(call: Call<Competition>, response: Response<Competition>) {
                response.body()?.let { fetchTeams(it) }
            }

            override fun onFailure(call: Call<Competition>, t: Throwable) {}
        })
    }

    private fun fetchTeams(competition: Competition) {
        val teams = mutableListOf<Team>()
        var teamsLeft = MAX_REQUESTS
        competition.teams.shuffled().take(MAX_REQUESTS).forEach {
            footballRepository.getTeam(it.id, object : Callback<Team> {
                override fun onResponse(call: Call<Team>, response: Response<Team>) {
                    response.body()?.let { team -> teams.add(team) }
                    teamsLeft--

                    if (teamsLeft == 0) {
                        view.showCompetition(competition.copy(teams = teams))
                    }
                }

                override fun onFailure(call: Call<Team>, t: Throwable) {
                }
            })
        }
    }

    companion object {
        private const val SPAIN_COMPETITION_ID: Long = 2014
        private const val MAX_REQUESTS = 2
    }
}