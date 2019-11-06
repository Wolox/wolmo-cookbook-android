package ar.com.wolox.android.cookbook.coroutines.football

import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.coroutines.core.CoroutinesRecipeItemFragment
import ar.com.wolox.android.cookbook.coroutines.football.model.Competition
import ar.com.wolox.android.cookbook.coroutines.football.model.Match
import ar.com.wolox.android.cookbook.coroutines.football.model.Player
import ar.com.wolox.android.cookbook.coroutines.football.model.Team
import kotlinx.android.synthetic.main.fragment_coroutines_football.*
import javax.inject.Inject

class FootballFragment @Inject constructor() : CoroutinesRecipeItemFragment<CoroutinesFootballPresenter>(), FootballView {

    override val childrenLayout = R.layout.fragment_coroutines_football

    override val titleRes = R.string.football_title

    override val descriptionRes = R.string.football_description

    override fun setListeners() {
        randomPlayerMatchesButton.setOnClickListener {
            presenter.onRandomPlayerMatchesButtonClicked()
        }
        randomTeamsSquadsSequentialButton.setOnClickListener {
            presenter.onRandomTeamsSquadsSequentialButtonClicked()
        }
        randomTeamsSquadsAsyncButton.setOnClickListener {
            presenter.onRandomTeamsSquadsAsyncButtonClicked()
        }
    }

    override fun showCompetition(competition: Competition) {
        footballTitle.text = competition.name
        footballDescription.text = competition.teams.joinToString("\n\n") {
            it.name + ":\n-" + it.squad.joinToString("\n-") { player -> "${player.name} - ${player.position}" }
        }
    }

    override fun showPlayerMatches(team: Team, player: Player, matches: List<Match>) {
        footballTitle.text = "%s: %s - %s".format(team.name, player.name, player.position)
        footballDescription.text = matches.joinToString("\n") {
            "%s - %s\n%s %d - %d %s".format(
                    it.date.toString(),
                    it.competition.name,
                    it.homeTeam.name,
                    it.score.fulltime.home,
                    it.score.fulltime.away,
                    it.awayTeam.name)
        }
    }

    override fun showElapsedTime(elapsedTime: Long) = toastFactory.show("It takes $elapsedTime ms to perform the task")

    override fun showTooManyRequestsError() = toastFactory.show(R.string.football_too_many_requests_error)

    override fun showUnexpectedError() = toastFactory.show(R.string.football_unexpected_error)

    override fun showNotAvailableError() = toastFactory.show(R.string.football_not_available_error)

    companion object {
        fun newInstance() = FootballFragment()
    }
}