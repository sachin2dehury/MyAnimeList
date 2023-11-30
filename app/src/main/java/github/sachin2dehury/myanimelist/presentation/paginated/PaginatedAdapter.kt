package github.sachin2dehury.myanimelist.presentation.paginated

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import coil.load
import coil.transform.RoundedCornersTransformation
import github.sachin2dehury.myanimelist.databinding.ItemListingBinding
import github.sachin2dehury.myanimelist.domain.model.PaginatedModel

class PaginatedAdapter : PagingDataAdapter<PaginatedModel, PaginatedViewHolder>(PaginatedDiffUtil()) {
    override fun onBindViewHolder(holder: PaginatedViewHolder, position: Int) {
        val item = getItem(position) ?: return
        with(holder.binding) {
            image.load(item.images) {
                transformations(RoundedCornersTransformation(20f))
                crossfade(true)
            }
            tvAired.text = "Air : ${item.aired}"
            tvScore.text = "Rating : ${item.rating}"
            tvRank.text = "Rank : ${item.rank}"
            tvEpisodes.text = "Episodes : ${item.episodes}"
            tvRating.text = item.rating
            tvDuration.text = item.duration
            titleEng.text = item.titleEnglish
            titleJap.text = "${item.titleJapanese}(${item.title})"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaginatedViewHolder {
        val binding = ItemListingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PaginatedViewHolder(binding)
    }
}
