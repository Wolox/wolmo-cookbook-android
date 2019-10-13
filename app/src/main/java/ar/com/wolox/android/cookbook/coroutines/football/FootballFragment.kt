package ar.com.wolox.android.cookbook.coroutines.football

import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.coroutines.core.fromHtml
import ar.com.wolox.android.cookbook.coroutines.football.model.Competition
import ar.com.wolox.android.cookbook.coroutines.football.model.Match
import ar.com.wolox.android.cookbook.coroutines.football.model.Player
import ar.com.wolox.android.cookbook.coroutines.football.model.Team
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.util.ToastFactory
import kotlinx.android.synthetic.main.fragment_coroutines_football.*
import javax.inject.Inject

class FootballFragment : WolmoFragment<CoroutinesFootballPresenter>(), FootballView {

    @Inject
    internal lateinit var toastFactory: ToastFactory

    override fun layout() = R.layout.fragment_coroutines_football

    override fun init() {}

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
        title.text = competition.name
        description.text = competition.teams.joinToString("<br><br>") {
            it.name + ":<br>-" + it.squad.joinToString("<br>-") { player -> "${player.name} - ${player.position}" }
        }.fromHtml()
    }

    override fun showPlayerMatches(team: Team, player: Player, matches: List<Match>) {
        title.text = "%s: %s - %s".format(team.name, player.name, player.position)
        description.text = matches.joinToString("<br><br>") {
            "%s - %s<br>%s %d - %d %s".format(
                    it.date.toString(),
                    it.competition.name,
                    it.homeTeam.name,
                    it.score.fulltime.home,
                    it.score.fulltime.away,
                    it.awayTeam.name)
        }.fromHtml()
    }

    override fun showElapsedTime(elapsedTime: Long) = toastFactory.show("It takes $elapsedTime ms to perform the task")

    override fun showTooManyRequestsError() = toastFactory.show(R.string.football_too_many_requests_error)

    override fun showUnexpectedError() = toastFactory.show(R.string.football_unexpected_error)

    override fun showNotAvailableError() = toastFactory.show(R.string.football_not_available_error)

    companion object {
        fun newInstance() = FootballFragment()
    }
}