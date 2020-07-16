package graeme.hosford.avatarcharacterdatabase.ui.common.view.fragment

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import graeme.hosford.avatarcharacterdatabase.ui.common.view.viewmodel.BaseViewModel

/**
 * Base class for handling common [Fragment] functionality.
 *
 * Fragments which need to handle a [RecyclerView] should inherit from
 * [BaseRecyclerViewFragment] instead.
 */
abstract class BaseFragment<VM : BaseViewModel> : Fragment() {
    abstract fun registerViewModel(): VM
}