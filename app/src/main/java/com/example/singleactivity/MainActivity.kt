package com.example.singleactivity

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.BounceInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.activity.viewModels
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.singleactivity.databinding.ActivityMainBinding
import kotlinx.coroutines.delay

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val navController by lazy {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navHostFragment.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        val splashScreen = installSplashScreen()
        setContentView(binding.root)

        /*splashScreen.setOnExitAnimationListener() { splashScreenProvider ->
            val splashScreenView = splashScreenProvider.view
            ObjectAnimator.ofFloat(
                splashScreenView,
                View.TRANSLATION_Y,
                0f,
                splashScreenView.height.toFloat()
            ).apply {
                //interpolator = BounceInterpolator()
                //duration = 1000L
                doOnEnd { splashScreenProvider.remove() }
                start()
            }
        }
*/
        splashScreen.setOnExitAnimationListener { splashScreenView ->
            val slideBack = ObjectAnimator.ofFloat(
                splashScreenView.view,
                View.TRANSLATION_X,
                0f,
                -splashScreenView.view.width.toFloat()
            ).apply {
               // interpolator = DecelerateInterpolator()
               // duration = 800L
                doOnEnd { splashScreenView.remove() }
            }

            slideBack.start()
        }


        var isReady = false

       // Simulate some request
        lifecycleScope.launchWhenCreated {
            delay(3000L)
            isReady = true
        }

        //splashScreen.setKeepOnScreenCondition() { !isReady }

    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp()
    }
}