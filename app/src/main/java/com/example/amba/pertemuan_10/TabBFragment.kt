package com.example.amba.pertemuan_10

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.amba.R
import com.example.amba.databinding.FragmentTabABinding
import com.example.amba.databinding.FragmentTabBBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TabBFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TabBFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentTabBBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTabBBinding.inflate(inflater, container, false)
        return binding.root
    }
}