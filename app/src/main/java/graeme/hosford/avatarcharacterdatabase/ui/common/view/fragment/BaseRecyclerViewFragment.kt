package graeme.hosford.avatarcharacterdatabase.ui.common.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import graeme.hosford.avatarcharacterdatabase.databinding.FragmentBaseRecyclerViewLayoutBinding
import graeme.hosford.avatarcharacterdatabase.ui.common.uimodel.BaseUiModel
import graeme.hosford.avatarcharacterdatabase.ui.common.view.recyclerview.BaseRecyclerViewAdapter
import graeme.hosford.avatarcharacterdatabase.ui.common.view.recyclerview.BaseViewHolder

/**
 * Base class for handling common functionality in a [Fragment] which has a [RecyclerView].
 *
 * Fragments which do not have a [RecyclerView] should inherit from [BaseFragment] instead.
 */
abstract class BaseRecyclerViewFragment<
        UiModel : BaseUiModel,
        ViewHolder : BaseViewHolder<UiModel>,
        Adapter : BaseRecyclerViewAdapter<UiModel, ViewHolder>
        > : BaseFragment() {

    private lateinit var binding: FragmentBaseRecyclerViewLayoutBinding
    protected lateinit var recyclerview: RecyclerView
    protected lateinit var recyclerViewAdapter: Adapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBaseRecyclerViewLayoutBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerview = binding.recyclerView

        val recyclerViewLayoutManager = LinearLayoutManager(context)
        recyclerViewAdapter = recyclerViewAdapter()

        recyclerview.apply {
            layoutManager = recyclerViewLayoutManager
            adapter = recyclerViewAdapter
            setHasFixedSize(true)

            addItemDecoration(
                DividerItemDecoration(context, recyclerViewLayoutManager.orientation)
            )
        }
    }

    abstract fun recyclerViewAdapter(): Adapter
}