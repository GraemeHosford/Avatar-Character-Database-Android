package graeme.hosford.avatarcharacterdatabase.ui.common.view.fragment

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

/**
 * Base class for handling common functionality in a [Fragment] which has a [RecyclerView].
 *
 * Fragments which do not have a [RecyclerView] should inherit from [BaseFragment] instead.
 */
abstract class BaseRecyclerViewFragment : BaseFragment()