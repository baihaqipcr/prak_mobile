package com.example.amba

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.amba.databinding.ActivityAuthBinding
import com.example.amba.tutorial.TutorialMessageActivity

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val sharedPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        val isLogin = sharedPref.getBoolean("isLogin", false)

        if (isLogin) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnLogin.setOnClickListener {
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()

            if (username.isNotEmpty() && username == password) {
                // Simpan status login
                val editor = sharedPref.edit()
                editor.putBoolean("isLogin", true)
                editor.putString("username", username)
                editor.apply()

                val intent = Intent(this, BaseActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                AlertDialog.Builder(this)
                    .setTitle("Login Gagal")
                    .setMessage("Silahkan coba lagi")
                    .setPositiveButton("OK", null)
                    .show()
            }
        }

    }
    private fun playEntranceAnimations() {
        val scaleIn     = AnimationUtils.loadAnimation(this, R.anim.scale_in)
        val fadeIn      = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        val slideUp     = AnimationUtils.loadAnimation(this, R.anim.fade_slide_up)
        val slideUpSlow = AnimationUtils.loadAnimation(this, R.anim.fade_slide_up)

        // Semua mulai tidak terlihat
        binding.imageView2.alpha               = 0f
        binding.layoutCard.alpha               = 0f
        binding.textInputLayoutUsername.alpha  = 0f
        binding.textInputLayoutPassword.alpha  = 0f
        binding.btnLogin.alpha                 = 0f

        // imageView2 — muncul dengan efek scale + bounce
        binding.imageView2.postDelayed({
            binding.imageView2.alpha = 1f
            binding.imageView2.startAnimation(scaleIn)
        }, 100)

        // Card putih — slide up dari bawah
        binding.layoutCard.postDelayed({
            binding.layoutCard.alpha = 1f
            binding.layoutCard.startAnimation(fadeIn)
        }, 300)

        // Username field — slide up dengan delay
        binding.textInputLayoutUsername.postDelayed({
            binding.textInputLayoutUsername.alpha = 1f
            binding.textInputLayoutUsername.startAnimation(slideUp)
        }, 450)

        // Password field — slide up sedikit lebih lambat
        binding.textInputLayoutPassword.postDelayed({
            binding.textInputLayoutPassword.alpha = 1f
            binding.textInputLayoutPassword.startAnimation(slideUpSlow)
        }, 550)

        // Tombol login — muncul terakhir
        binding.btnLogin.postDelayed({
            binding.btnLogin.alpha = 1f
            binding.btnLogin.startAnimation(
                AnimationUtils.loadAnimation(this, R.anim.fade_slide_up)
            )
        }, 650)
    }
}
