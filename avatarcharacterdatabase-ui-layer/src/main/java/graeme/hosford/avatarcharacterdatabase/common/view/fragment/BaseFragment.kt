package graeme.hosford.avatarcharacterdatabase.common.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import graeme.hosford.avatarcharacterdatabase.common.view.viewmodel.BaseViewModel

/**
 * Base class for handling common [Fragment] functionality.
 *
 * Fragments which need to handle a [RecyclerView] should inherit from
 * [BaseRecyclerViewFragment] instead.
 */
abstract class BaseFragment<VM : BaseViewModel<*>> : Fragment() {
    protected lateinit var viewmodel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodel = registerViewModel()
    }

    abstract fun registerViewModel(): VM
}