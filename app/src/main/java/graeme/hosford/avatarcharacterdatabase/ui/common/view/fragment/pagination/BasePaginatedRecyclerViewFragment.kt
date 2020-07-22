package graeme.hosford.avatarcharacterdatabase.ui.common.view.fragment.pagination

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import graeme.hosford.avatarcharacterdatabase.ui.common.uimodel.BaseUiModel
import graeme.hosford.avatarcharacterdatabase.ui.common.view.fragment.BaseRecyclerViewFragment
import graeme.hosford.avatarcharacterdatabase.ui.common.view.recyclerview.BaseViewHolder
import graeme.hosford.avatarcharacterdatabase.ui.common.view.recyclerview.pagination.BasePaginatedRecyclerViewAdapter
import graeme.hosford.avatarcharacterdatabase.ui.common.view.viewmodel.pagination.BasePaginatedViewModel

abstract class BasePaginatedRecyclerViewFragment<UiModel : BaseUiModel, ViewHolder : BaseViewHolder<UiModel>,
        Adapter : BasePaginatedRecyclerViewAdapter<UiModel, ViewHolder>, VM : BasePaginatedViewModel<*, *>>
    : BaseRecyclerViewFragment<UiModel, ViewHolder, Adapter, VM>() {

    private lateinit var scrollListener: RecyclerView.OnScrollListener

    abstract fun registerPaginatedViewModel(): VM

    override fun registerViewModel() = registerPaginatedViewModel()

    override fun recyclerViewAdapter() = paginatedRecyclerViewAdapter()

    protected abstract fun paginatedRecyclerViewAdapter(): Adapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (loading) {
                    return
                }

                if (!recyclerView.canScrollVertically(1)) {
                    viewmodel.requestNextPage()
                }
            }
        }

        recyclerview.addOnScrollListener(scrollListener)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        recyclerview.removeOnScrollListener(scrollListener)
    }
}