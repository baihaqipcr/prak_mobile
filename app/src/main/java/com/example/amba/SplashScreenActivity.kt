package com.example.amba

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.amba.databinding.ActivitySplashScreenBinding

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Sembunyikan status bar & navigation bar untuk efek full screen
        @Suppress("DEPRECATION")
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                )

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        playAnimations()
    }

    private fun playAnimations() {
        // Load semua animasi (pastikan file-file ini ada di res/anim)
        try {
            val zoomIn     = AnimationUtils.loadAnimation(this, R.anim.splash_logo_zoom)
            val slideUp    = AnimationUtils.loadAnimation(this, R.anim.splash_title_slide)
            val fadeIn     = AnimationUtils.loadAnimation(this, R.anim.splash_tagline_fade)
            val barExpand  = AnimationUtils.loadAnimation(this, R.anim.splash_bar_expand)

            // ===== STEP 1: Glow muncul (delay 200ms) =====
            handler.postDelayed({
                binding.viewGlow.animate()
                    .alpha(1f)
                    .scaleX(1.1f)
                    .scaleY(1.1f)
                    .setDuration(800)
                    .withEndAction {
                        binding.viewGlow.animate()
                            .scaleX(1f).scaleY(1f)
                            .setDuration(400)
                            .start()
                    }
                    .start()
            }, 200)

            // ===== STEP 2: Logo zoom in dengan bounce (delay 300ms) =====
            handler.postDelayed({
                binding.imgLogo.alpha = 1f
                binding.imgLogo.startAnimation(zoomIn)
            }, 300)

            // ===== STEP 3: Nama app slide up (delay 700ms) =====
            handler.postDelayed({
                binding.tvAppName.alpha = 1f
                binding.tvAppName.startAnimation(slideUp)
            }, 700)

            // ===== STEP 4: Tagline fade in (delay 900ms) =====
            handler.postDelayed({
                binding.tvTagline.alpha = 1f
                binding.tvTagline.startAnimation(fadeIn)
            }, 900)

            // ===== STEP 5: Loading bar + teks muncul (delay 1100ms) =====
            handler.postDelayed({
                binding.tvLoading.animate().alpha(0.6f).setDuration(300).start()
                binding.tvVersion.animate().alpha(0.4f).setDuration(300).start()
                binding.viewProgressFill.alpha = 1f
                binding.viewProgressFill.startAnimation(barExpand)
            }, 1100)

            // ===== STEP 6: Loading teks berubah (delay 1700ms) =====
            handler.postDelayed({
                binding.tvLoading.animate()
                    .alpha(0f).setDuration(150)
                    .withEndAction {
                        binding.tvLoading.text = "Siap!"
                        binding.tvLoading.animate().alpha(0.6f).setDuration(150).start()
                    }.start()
            }, 1700)

            // ===== STEP 7: Exit + pindah ke tujuan (delay 2400ms) =====
            handler.postDelayed({
                val exitAnim = AnimationUtils.loadAnimation(this, R.anim.splash_exit)
                binding.root.startAnimation(exitAnim)

                handler.postDelayed({
                    checkLoginAndNavigate()
                }, 350)
            }, 2400)
        } catch (e: Exception) {
            // Jika animasi gagal/tidak ada, langsung navigasi setelah delay standar
            handler.postDelayed({
                checkLoginAndNavigate()
            }, 2000)
        }
    }

    private fun checkLoginAndNavigate() {
        val sharedPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        val isLogin = sharedPref.getBoolean("isLogin", false)

        val intent = if (isLogin) {
            Intent(this, MainActivity::class.java)
        } else {
            Intent(this, AuthActivity::class.java)
        }

        startActivity(intent)

        // Animasi transisi halus antar Activity
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

        finish() // Tutup SplashScreen agar tidak bisa kembali
    }

    override fun onDestroy() {
        super.onDestroy()
        // Bersihkan semua handler agar tidak memory leak
        handler.removeCallbacksAndMessages(null)
    }
}
