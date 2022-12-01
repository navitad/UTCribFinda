package com.orange.utcribfinda.ui.home


import com.orange.utcribfinda.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asFlow
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.orange.utcribfinda.MainActivity
import com.orange.utcribfinda.api.ListingPost
import com.orange.utcribfinda.databinding.FragmentHomeBinding
import com.orange.utcribfinda.ui.saved.ResultsFragment
import com.orange.utcribfinda.ui.saved.SavedFragment
import com.orange.utcribfinda.ui.saved.SavedViewModel

class HomeFragment : Fragment(R.layout.fragment_home), AdapterView.OnItemSelectedListener {

    private var _binding: FragmentHomeBinding? = null
    private val viewModel: SavedViewModel by activityViewModels()
    private var _viewModel: ViewModel? = null
    private var location: String = "West Campus"
    private var numRooms: Int = 0
    private var numBath: Int = 1
    private var minPrice: Int = 0
    private var maxPrice: Int = 2500
    private var minSqFt: Int = 0
    private var maxSqFt : Int = 2000

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    //private val viewModel get() = _viewModel!!

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //_viewModel = ViewModelProvider(this)[SavedViewModel::class.java]
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        setUpLocation()
        setUpRooms()
        setUpBath()
        setUpPrice()
        setUpSize()
        submit()
        return binding.root
    }

    private fun submit(){
        binding.submit.setOnClickListener{
            var posts = mutableListOf<ListingPost>()
            viewModel.observeNetPosts().observe(viewLifecycleOwner) {
                for(i in it){
                    if(numRooms == i.numBeds && numBath == i.numBaths && minPrice <= i.price &&
                        maxPrice >= i.price && minSqFt <= i.sqFT!! && maxSqFt >= i.sqFT){
                        posts.add(i)
                        //Log.d("in home", posts.toString())
                    }
                }
            }

            if(posts.isEmpty()){
                Toast.makeText(activity, "Try again with different attributes.", Toast.LENGTH_LONG).show()
            } else {
                viewModel.selectedPosts = posts
                viewModel.fetchSelected()
//                Log.d("selec", viewModel.selectedPosts.toString())
//                viewModel.observePostsPicked().observe(viewLifecycleOwner) {
//                    Log.d("idk", it.toString())
//                }
                    findNavController().navigate(
                    R.id.action_navigation_home_to_navigation_results
                )
            }

        }
    }

    private fun setUpLocation(){
        val data = arrayOf("West Campus", "Riverside")
        val aa = activity?.let {
            ArrayAdapter(
                it,
                android.R.layout.simple_spinner_item,
                data
            )
        }
        aa?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        with(binding.locations)
        {
            adapter = aa

            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
//                    Toast.makeText(activity,
//                        parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show()
                    location =  parent.getItemAtPosition(position).toString()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {

                }
            }
        }
    }

    private fun setUpRooms(){
        val data = arrayOf(0, 1, 2, 3, 4, 5)
        val aa = activity?.let {
            ArrayAdapter(
                it,
                android.R.layout.simple_spinner_item,
                data
            )
        }
        aa?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        with(binding.rooms)
        {
            adapter = aa

            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
//                    Toast.makeText(activity,
//                        parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show()
                    numRooms =  position
                }

                override fun onNothingSelected(parent: AdapterView<*>) {

                }
            }
        }
    }

    private fun setUpBath(){
        val data = arrayOf(1, 2, 3, 4, 5)
        val aa = activity?.let {
            ArrayAdapter(
                it,
                android.R.layout.simple_spinner_item,
                data
            )
        }
        aa?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        with(binding.bathrooms)
        {
            adapter = aa

            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
//                    Toast.makeText(activity,
//                        parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show()
                    numBath =  position + 1
                }

                override fun onNothingSelected(parent: AdapterView<*>) {

                }
            }
        }
    }

    private fun setUpPrice(){
        binding.price.setValues(
            listOf(0f, 2500f),
            0f,
            2500f
        )
        binding.price.addOnValueChangeListener{ from, to, _ ->
            val text = "$${from.toInt()}-$${to.toInt()} "
            minPrice = from.toInt()
            maxPrice = to.toInt()
            binding.priceRange.text = text
        }
    }

    private fun setUpSize(){
        binding.sqft.setValues(
            listOf(0f, 2000f),
            0f,
            2500f,
        )
        binding.sqft.addOnValueChangeListener{ from, to, _ ->
            val text = "[${from.toInt()} ~ ${to.toInt()}] "
            minSqFt = from.toInt()
            maxSqFt = to.toInt()
            binding.sqftRange.text = text
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }
}

