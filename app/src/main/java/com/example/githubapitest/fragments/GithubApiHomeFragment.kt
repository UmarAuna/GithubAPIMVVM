package com.example.githubapitest.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubapitest.adapter.GithubApiAdapter
import com.example.githubapitest.databinding.FragmentGithuApiHomeBinding
import com.example.githubapitest.model.Item
import com.example.githubapitest.viewmodel.GithubApiViewModel
import com.example.punchandroidtest.extension.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class GithubApiHomeFragment : Fragment(), GithubApiAdapter.OpenDetailClickListener {

    private lateinit var binding: FragmentGithuApiHomeBinding
    private val viewModel: GithubApiViewModel by viewModel()
    private lateinit var adapter: GithubApiAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGithuApiHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.init()

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = GithubApiAdapter(arrayListOf(), this)
        binding.recyclerView.adapter = adapter

        viewModel.observeGithubApi.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressBar.isVisible = false
                    it.data?.let { fetchApi ->
                        renderList(fetchApi)
                    }
                    binding.recyclerView.isVisible = true
                }
                Status.LOADING -> {
                    binding.progressBar.isVisible = true
                    binding.recyclerView.isVisible = false
                }
                Status.ERROR -> {
                    binding.progressBar.isVisible = false
                    getCachedData()
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun openDetailClickListener(item: Item) {
        findNavController().navigate(GithubApiHomeFragmentDirections.actionGithuApiHomeFragmentToGithubHomeApiFragmentDetail(item))
    }

    private fun getCachedData() {
        viewModel.getCachedApi.observe(viewLifecycleOwner) {
            renderList(it)
        }
    }

    private fun renderList(fetchApi: List<Item>) {
        adapter.addData(fetchApi)
        adapter.submitList(fetchApi)
    }
}
