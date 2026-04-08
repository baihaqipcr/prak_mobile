package com.example.amba


import android.content.Intent
import com.example.amba.databinding.ActivityThirdBinding

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.amba.databinding.ActivityMainBinding
import com.example.amba.pertemuan_3.ThirdResultActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            // Inisialisasi komponen

            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnSubmit1.setOnClickListener {
            val intent = Intent(this, ThirdResultActivity::class.java)
            startActivity(intent)
        }
        binding.btnSubmit2.setOnClickListener {
            val intent = Intent(this, FourthActivity::class.java)
            /*tambahkan bagian berikut*/
            intent.putExtra("name", "Politeknik Caltex Riau")
            intent.putExtra("from", "Rumbai")
            intent.putExtra("age", 25)

            startActivity(intent)
        }
    }
    override fun onStart() {
        super.onStart()
        Log.e("-- onStart --", "onStart: FourthActivity terlihat di layar")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.e("-- onDestroy --", "FourthActivity dihapus dari stack")
    }
}