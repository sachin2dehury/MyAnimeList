package github.sachin2dehury.myanimelist.presentation.paginated

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import dagger.hilt.android.AndroidEntryPoint
import github.sachin2dehury.myanimelist.R
import github.sachin2dehury.myanimelist.databinding.FragmentPaginatedBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PaginatedFragment : Fragment(), PaginatedClickListener {

    private var binding: FragmentPaginatedBinding? = null

    private val adapter = PaginatedAdapter(this)

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
                (it.append is LoadState.Loading) || (it.refresh is LoadState.Loading)

            (it.refresh as? LoadState.Error)?.error?.let {
                Toast.makeText(requireContext(), it.localizedMessage, Toast.LENGTH_LONG).show()
            }
            (it.append as? LoadState.Error)?.error?.let {
                Toast.makeText(requireContext(), it.localizedMessage, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setupUi() {
        binding?.recyclerView?.adapter = adapter
        binding?.searchBar?.isSubmitButtonEnabled = true

        binding?.searchBar?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.updateState(query = query)
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                return true
            }
        })
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun onClick(id: Int) {
        if (findNavController().currentDestination?.id == R.id.paginatedFragment) {
            findNavController().navigate(
                PaginatedFragmentDirections.actionListingFragmentToDetailFragment(
                    id,
                ),
            )
        }
    }
}
