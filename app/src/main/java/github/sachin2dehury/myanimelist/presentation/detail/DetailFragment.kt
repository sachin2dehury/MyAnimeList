package github.sachin2dehury.myanimelist.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.palette.graphics.Palette
import coil.load
import coil.transform.RoundedCornersTransformation
import dagger.hilt.android.AndroidEntryPoint
import github.sachin2dehury.myanimelist.databinding.FragmentDetailBinding
import github.sachin2dehury.myanimelist.domain.model.DetailModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var binding: FragmentDetailBinding? = null

    private val viewModel by viewModels<DetailViewModel>()

    private val args by navArgs<DetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getDetails(args.id)
        collectData()
    }

    private fun collectData() {
        lifecycleScope.launch {
            viewModel.data.collectLatest {
                binding?.loader?.isVisible = it.isLoading
                if (!it.error.isNullOrEmpty()) {
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_LONG).show()
                }
                setUiData(it.data)
            }
        }
    }

    private fun setUiData(data: DetailModel?) {
        data ?: return
        binding ?: return
        with(binding!!) {
            image.load(data.images) {
                transformations(RoundedCornersTransformation(20f))
                crossfade(true)
                listener { _, result ->
                    val palette = Palette.from(result.drawable.toBitmap()).generate()
                    val dominant = palette.dominantSwatch
                    val muted = palette.mutedSwatch
                    muted?.rgb?.let { root.setBackgroundColor(it) }
                    dominant?.rgb?.let { titleJap.setBackgroundColor(it) }
                    dominant?.bodyTextColor?.let {
                        titleJap.setTextColor(it)
                    }
                    muted?.bodyTextColor?.let {
                        titleEng.setTextColor(it)
                        tvDuration.setTextColor(it)
                        tvRank.setTextColor(it)
                        tvEpisodes.setTextColor(it)
                        tvScore.setTextColor(it)
                        tvRating.setTextColor(it)
                        tvAired.setTextColor(it)
                        tvGenre.setTextColor(it)
                        tvBackground.setTextColor(it)
                        tvSynopsis.setTextColor(it)
                        tvOpenings.setTextColor(it)
                        tvEndings.setTextColor(it)
                    }
                }
            }
            tvScore.text = "Rating : ${data.score}"
            tvRank.text = "Rank : ${data.rank}"
            tvEpisodes.text = "Episodes : ${data.episodes}"
            tvDuration.text = data.duration
            titleEng.text = if (data.titleEnglish.isNullOrEmpty()) data.title else data.titleEnglish
            titleJap.text = data.titleJapanese
            tvRating.text = "Rating : ${data.rating}"
            tvAired.text = "Air : ${data.aired}"
            tvGenre.text = "Genres : ${data.genres.joinToString()}"
            tvBackground.text = "Background :\n\n\t${data.titleJapanese}"
            tvSynopsis.text = "Synopsis :\n\n\t${data.synopsis}"
            tvOpenings.text = "Opening theme :\n${data.openingTheme.joinToString("\n")}"
            tvEndings.text = "Ending theme :\n${data.endingTheme.joinToString("\n")}"
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}
