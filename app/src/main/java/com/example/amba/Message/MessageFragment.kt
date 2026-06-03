package com.example.amba.Message

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.amba.R
import com.example.amba.databinding.FragmentMessageBinding
import com.example.amba.tutorial.TutorialMessageActivity

class MessageFragment : Fragment() {

    private var _binding: FragmentMessageBinding? = null
    private val binding get() = _binding!!

    // Data dummy untuk pesan
    private val messageList = listOf(
        MessageModel(
            "Arsyad Ronald",
            "Mclaren lu warna apa bos?",
            "https://randomuser.me/api/portraits/women/20.jpg"
        ),
        MessageModel(
            "Syabil Muchkin",
            "Futsal futsall?",
            "https://randomuser.me/api/portraits/men/33.jpg"
        ),
        MessageModel(
            "Haji Thariq",
            "Tolonggg tolonggg",
            "https://randomuser.me/api/portraits/women/16.jpg"
        ),
        MessageModel(
            "Rudiono Jawa Timur",
            "Serlok tak parani! Iyo lat silat",
            "https://randomuser.me/api/portraits/women/12.jpg"
        ),
        MessageModel(
            "Fathur Koto",
            "SENG SENG SENGGG!",
            "https://randomuser.me/api/portraits/men/32.jpg"
        ),
        MessageModel(
            "Givo Brainrot",
            "SIX SEVEN 67!!!!!",
            "https://randomuser.me/api/portraits/men/35.jpg"
        ),
        MessageModel(
            "Fila Botaks",
            "Awak di dumai!!!",
            "https://randomuser.me/api/portraits/men/10.jpg"
        ),
        MessageModel(
            "Ilman Bucin",
            "Ya lah tu",
            "https://randomuser.me/api/portraits/women/13.jpg"
        ),
        MessageModel(
            "Farras Vario",
            "Motor ku di bengkel kamprettt",
            "https://randomuser.me/api/portraits/women/30.jpg"
        ),
        MessageModel("Kkdal", "Izin bertanya", "https://randomuser.me/api/portraits/men/32.jpg")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Memberitahu fragment bahwa ia memiliki menu di toolbar
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMessageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup Toolbar melalui Activity agar Title muncul dengan benar
        val appCompatActivity = requireActivity() as AppCompatActivity
        appCompatActivity.setSupportActionBar(binding.toolbar)
        appCompatActivity.supportActionBar?.title = "Messages"

        // Inisialisasi Adapter dan hubungkan ke ListView
        val adapter = MessageAdapter(requireContext(), messageList)
        binding.listMessageItems.adapter = adapter

        // Tambahkan interaksi klik pada item list
        binding.listMessageItems.setOnItemClickListener { _, _, position, _ ->
            val sender = messageList[position].senderName
            Toast.makeText(
                requireContext(),
                "Membuka percakapan dengan $sender",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        // Inflate menu toolbar
        inflater.inflate(R.menu.message_toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_tutorial -> {
                // Aksi saat menu tutorial diklik
                val intent = Intent(requireContext(), TutorialMessageActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
