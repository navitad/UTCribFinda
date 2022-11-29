package com.orange.utcribfinda.ui.saved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.orange.utcribfinda.databinding.FragmentHomeBinding
import com.orange.utcribfinda.databinding.FragmentRvBinding
import com.orange.utcribfinda.databinding.FragmentSavedBinding
import com.orange.utcribfinda.ui.PostRowAdapter

class SavedFragment : Fragment() {

    //private var _binding: FragmentSavedBinding? = null
    private var _binding: FragmentSavedBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SavedViewModel by viewModels()

    companion object {
        fun newInstance(): SavedFragment {
            return SavedFragment()
        }
    }

    private fun initAdapter(binding: FragmentSavedBinding): PostRowAdapter {
        val rv = binding.recyclerView
        val adapter = PostRowAdapter(viewModel)
        rv.layoutManager = LinearLayoutManager(activity)
        rv.adapter = adapter
        return adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val savedViewModel =
//            ViewModelProvider(this)[SavedViewModel::class.java]
        _binding = FragmentSavedBinding.inflate(inflater, container, false)
        //_binding = FragmentSavedBinding.inflate(inflater, container, false)
        //val root: View = binding.root
        return binding.root
        //return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = initAdapter(binding)
        adapter.submitList(viewModel.observeNetPosts())
//        viewModel.observeNetPosts().observe(viewLifecycleOwner) {
//            viewModel.fetchDone.postValue(true)
//            // do more here
//            adapter.submitList(it)
//        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}