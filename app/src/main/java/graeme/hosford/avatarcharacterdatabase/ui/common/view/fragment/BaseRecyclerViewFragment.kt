package graeme.hosford.avatarcharacterdatabase.ui.common.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import graeme.hosford.avatarcharacterdatabase.databinding.FragmentBaseRecyclerViewLayoutBinding
import graeme.hosford.avatarcharacterdatabase.ui.common.uimodel.BaseUiModel
import graeme.hosford.avatarcharacterdatabase.ui.common.view.recyclerview.BaseRecyclerViewAdapter
import graeme.hosford.avatarcharacterdatabase.ui.common.view.recyclerview.BaseViewHolder
import graeme.hosford.avatarcharacterdatabase.ui.common.view.viewmodel.BaseViewModel

/**
 * Base class for handling common functionality in a [Fragment] which has a [RecyclerView].
 *
 * Fragments which do not have a [RecyclerView] should inherit from [BaseFragment] instead.
 */
abstract class BaseRecyclerViewFragment<
        UiModel : BaseUiModel,
        ViewHolder : BaseViewHolder<UiModel>,
        Adapter : BaseRecyclerViewAdapter<UiModel, ViewHolder>,
        VM : BaseViewModel<*>
        > : BaseFragment<VM>() {

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
        if (!::binding.isInitialized) {
            if (getBaseRecyclerViewLayout() == null) {
                throw IllegalStateException(
                    "OnCreateView has been overridden in a child " +
                            "class of BaseRecyclerViewFragment but #getBaseRecyclerViewLayout must " +
                            "also be overridden to provide the base layout to the superclass."
                )
            }

            binding = getBaseRecyclerViewLayout()!!
        }

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

        viewmodel.errorLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                true -> {
                    binding.recyclerView.visibility = View.GONE
                    binding.defaultLoadingLayout.root.visibility = View.GONE
                    binding.defaultErrorLayout.root.visibility = View.VISIBLE
                }
                false -> binding.defaultErrorLayout.root.visibility = View.GONE
            }
        })

        viewmodel.loadingLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                true -> {
                    binding.defaultLoadingLayout.root.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                    binding.defaultErrorLayout.root.visibility = View.GONE
                }
                false -> binding.defaultLoadingLayout.root.visibility = View.GONE
            }
        })

        viewmodel.completedLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                true -> {
                    binding.recyclerView.visibility = View.VISIBLE
                    binding.defaultErrorLayout.root.visibility = View.GONE
                    binding.defaultLoadingLayout.root.visibility = View.GONE
                }
                false -> binding.recyclerView.visibility = View.GONE
            }
        })
    }

    abstract fun recyclerViewAdapter(): Adapter

    protected open fun getBaseRecyclerViewLayout(): FragmentBaseRecyclerViewLayoutBinding? = null
}