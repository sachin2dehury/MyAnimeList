package github.sachin2dehury.myanimelist.presentation.paginated

import androidx.recyclerview.widget.DiffUtil
import github.sachin2dehury.myanimelist.domain.model.PaginatedModel

class PaginatedDiffUtil : DiffUtil.ItemCallback<PaginatedModel>() {
    override fun areItemsTheSame(oldItem: PaginatedModel, newItem: PaginatedModel): Boolean {
        return oldItem.malId == newItem.malId
    }

    override fun areContentsTheSame(oldItem: PaginatedModel, newItem: PaginatedModel): Boolean {
        return oldItem == newItem
    }
}