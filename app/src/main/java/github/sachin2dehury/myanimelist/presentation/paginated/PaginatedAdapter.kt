package github.sachin2dehury.myanimelist.presentation.paginated

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.drawable.toBitmap
import androidx.paging.PagingDataAdapter
import androidx.palette.graphics.Palette
import coil.load
import coil.transform.RoundedCornersTransformation
import github.sachin2dehury.myanimelist.databinding.ItemListingBinding
import github.sachin2dehury.myanimelist.domain.model.PaginatedModel

class PaginatedAdapter(private val listener: PaginatedClickListener) :
    PagingDataAdapter<PaginatedModel, PaginatedViewHolder>(PaginatedDiffUtil()) {
    override fun onBindViewHolder(holder: PaginatedViewHolder, position: Int) {
        val item = getItem(position) ?: return
        with(holder.binding) {
            root.setOnClickListener {
                listener.onClick(item.malId)
            }
            image.load(item.images) {
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
                    }
                }
            }
            tvScore.text = "Rating : ${item.score}"
            tvRank.text = "Rank : ${item.rank}"
            tvEpisodes.text = "Episodes : ${item.episodes}"
            tvDuration.text = item.duration
            titleEng.text = if (item.titleEnglish.isNullOrEmpty()) item.title else item.titleEnglish
            titleJap.text = item.titleJapanese
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaginatedViewHolder {
        val binding = ItemListingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PaginatedViewHolder(binding)
    }
}
