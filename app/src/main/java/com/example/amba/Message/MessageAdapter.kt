package com.example.amba.Message

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.amba.databinding.ItemMessageBinding
import com.github.bumptech.glide.Glide

class MessageAdapter(
    context: Context,
    private val  Messages: List<MessageModel>
) : ArrayAdapter<MessageModel>(context, 0, Messages) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding: ItemMessageBinding
        val view: View

        // 1. Cek apakah ada view yang bisa didaur ulang
        if (convertView == null) {
            // Jika tidak ada, buat baru (inflate)
            binding = ItemMessageBinding.inflate(LayoutInflater.from(context), parent, false)
            view = binding.root
            // Simpan binding ke dalam tag view agar bisa dipanggil lagi nanti
            view.tag = binding
        } else {
            // Jika ada, pakai ulang view yang sudah ada
            view = convertView
            binding = view.tag as ItemMessageBinding
        }

        // 2. Masukkan data ke dalam view
        val data = Messages[position]

        Glide.with(context)
            .load(data.avatarUrl)
            .into(binding.avatarImg)

        binding.textSender.text = data.senderName
        binding.textMessage.text = data.messageText

        return view
    }
}