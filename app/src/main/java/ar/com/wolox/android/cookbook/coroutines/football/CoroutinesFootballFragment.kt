package ar.com.wolox.android.cookbook.coroutines.football

import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.coroutines.core.fromHtml
import ar.com.wolox.android.cookbook.coroutines.football.model.Competition
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.util.ToastFactory
import kotlinx.android.synthetic.main.fragment_coroutines_football.*
import javax.inject.Inject

class CoroutinesFootballFragment : WolmoFragment<CoroutinesFootballPresenter>(), CoroutinesFootballView {

    @Inject
    internal lateinit var toastFactory: ToastFactory

    override fun layout() = R.layout.fragment_coroutines_football

    override fun init() {}

    override fun showCompetition(competition: Competition) {
        competitionName.text = competition.name
        teamsList.text = competition.teams.joinToString("<br><br>") {
            it.name + ":<br>-" + it.squad?.joinToString("<br>-") { player -> "${player.name} - ${player.position}" }
        }.fromHtml()
    }

    companion object {
        fun newInstance() = CoroutinesFootballFragment()
    }
}