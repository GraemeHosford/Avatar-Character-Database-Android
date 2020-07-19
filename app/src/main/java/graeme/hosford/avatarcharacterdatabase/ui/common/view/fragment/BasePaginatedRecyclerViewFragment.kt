package graeme.hosford.avatarcharacterdatabase.ui.common.view.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import graeme.hosford.avatarcharacterdatabase.ui.common.uimodel.BaseUiModel
import graeme.hosford.avatarcharacterdatabase.ui.common.view.recyclerview.BaseRecyclerViewAdapter
import graeme.hosford.avatarcharacterdatabase.ui.common.view.recyclerview.BaseViewHolder
import graeme.hosford.avatarcharacterdatabase.ui.common.view.viewmodel.BasePaginatedViewModel

abstract class BasePaginatedRecyclerViewFragment<UiModel : BaseUiModel, ViewHolder : BaseViewHolder<UiModel>,
        Adapter : BaseRecyclerViewAdapter<UiModel, ViewHolder>, VM : BasePaginatedViewModel<*, *>>
    : BaseRecyclerViewFragment<UiModel, ViewHolder, Adapter, VM>() {

    private lateinit var scrollListener: RecyclerView.OnScrollListener

    abstract fun registerPaginatedViewModel(): VM

    override fun registerViewModel() = registerPaginatedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val visibleItems = recyclerViewLayoutManager.childCount
                val totalItems = recyclerViewLayoutManager.itemCount
                val firstVisibleItemPos = recyclerViewLayoutManager.findFirstVisibleItemPosition()

                if (firstVisibleItemPos + visibleItems >= totalItems) {
                    registerPaginatedViewModel().requestNextPage()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        recyclerview.addOnScrollListener(scrollListener)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        recyclerview.removeOnScrollListener(scrollListener)
    }
}