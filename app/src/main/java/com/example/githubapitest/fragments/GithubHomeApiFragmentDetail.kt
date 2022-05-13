package com.example.githubapitest.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.githubapitest.R
import com.example.githubapitest.databinding.FragmentGithubHomeApiDetailBinding

class GithubHomeApiFragmentDetail : Fragment() {

    private lateinit var binding: FragmentGithubHomeApiDetailBinding
    private val args: GithubHomeApiFragmentDetailArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGithubHomeApiDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(requireContext())
            .load(args.data.avatarUrl)
            .placeholder(R.drawable.no_image)
            .into(binding.iconImageView)

        binding.loginTextView.text = "Username: ${args.data.login}"
        binding.htmlUrlTextView.text = "Profile link: ${args.data.htmlUrl}"
        binding.typeTextView.text = "Account Type: ${args.data.type}"
    }
}
