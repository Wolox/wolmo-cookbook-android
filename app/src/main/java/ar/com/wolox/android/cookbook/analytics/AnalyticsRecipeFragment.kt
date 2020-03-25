package ar.com.wolox.android.cookbook.analytics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.databinding.FragmentAnalyticsBinding
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.util.ToastFactory
import ar.com.wolox.wolmo.core.util.openBrowser
import javax.inject.Inject

class AnalyticsRecipeFragment : WolmoFragment<AnalyticsRecipePresenter>(), AnalyticsRecipeView {

    @Inject
    lateinit var toastFactory: ToastFactory

    private var _binding: FragmentAnalyticsBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding
        get() = _binding!!

    override fun layout() = R.layout.fragment_analytics

    override fun init() {
    }

    override fun setListeners() {
        binding.ageRequestButton.setOnClickListener {
            presenter.onAgeRequestButtonClicked(binding.emailInput.text.toString(), binding.passwordInput.text.toString())
        }
        binding.helpButton.setOnClickListener {
            presenter.onHelpButtonClicked()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentAnalyticsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun openHelp() = requireContext().openBrowser(HELP_URL)

    override fun showAge(name: String, age: Int) = toastFactory.show(resources.getString(R.string.analytics_age, name, age))

    override fun showEmailEmptyError() = toastFactory.show(R.string.analytics_empty_email_error)

    override fun showPasswordEmptyError() = toastFactory.show(R.string.analytics_empty_password_error)

    override fun showInvalidUserError() = toastFactory.show(R.string.analytics_invalid_user_error)

    override fun showServeUnavailableError() = toastFactory.show(R.string.analytics_service_unavailable_error)

    companion object {

        const val HELP_URL = "https://www.google.com"

        fun newInstance() = AnalyticsRecipeFragment()
    }
}