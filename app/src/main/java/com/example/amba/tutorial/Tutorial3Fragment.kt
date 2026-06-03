package com.example.amba.tutorial

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.amba.AuthActivity
import com.example.amba.BaseActivity
import com.example.amba.databinding.FragmentTutorial3Binding

class Tutorial3Fragment : Fragment() {

    private var _binding: FragmentTutorial3Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTutorial3Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnStart.setOnClickListener {
            // Berpindah ke BaseActivity
            val intent = Intent(requireContext(), AuthActivity::class.java)
            startActivity(intent)
            
            // Tutup activity tutorial agar tidak bisa kembali dengan tombol back
            requireActivity().finish()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
