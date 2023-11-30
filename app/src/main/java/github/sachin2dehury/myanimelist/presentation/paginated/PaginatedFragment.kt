package github.sachin2dehury.myanimelist.presentation.paginated

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import dagger.hilt.android.AndroidEntryPoint
import github.sachin2dehury.myanimelist.databinding.FragmentPaginatedBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PaginatedFragment : Fragment() {

    private var binding: FragmentPaginatedBinding? = null

    private val adapter = PaginatedAdapter()

    private val viewModel by viewModels<PaginatedViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentPaginatedBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        collectData()
    }

    private fun collectData() {
        lifecycleScope.launch {
            viewModel.pager.collectLatest {
                adapter.submitData(it)
            }
        }
        adapter.addLoadStateListener {
            binding?.loader?.isVisible =
                it.append is LoadState.Loading || it.refresh is LoadState.Loading
        }
    }

    private fun setupUi() {
        binding?.recyclerView?.adapter = adapter
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}