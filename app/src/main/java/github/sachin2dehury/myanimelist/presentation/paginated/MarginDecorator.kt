package github.sachin2dehury.myanimelist.presentation.paginated

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MarginDecorator(private val space: Int = 20) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val orientation =
            (parent.layoutManager as? LinearLayoutManager)?.orientation ?: RecyclerView.VERTICAL
        val span = (parent.layoutManager as? GridLayoutManager)?.spanCount ?: 1
        with(outRect) {
            if (orientation == RecyclerView.VERTICAL) {
                if (parent.getChildAdapterPosition(view) < span) {
                    top = space
                }
                if (parent.getChildAdapterPosition(view) % span == 0) {
                    left = space
                }
            } else {
                if (parent.getChildAdapterPosition(view) < span) {
                    left = space
                }
                if (parent.getChildAdapterPosition(view) % span == 0) {
                    top = space
                }
            }
            right = space
            bottom = space
        }
    }
}