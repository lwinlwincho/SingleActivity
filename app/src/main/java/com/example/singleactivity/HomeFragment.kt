package com.example.singleactivity

import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkBuilder
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.singleactivity.databinding.FragmentHomeBinding
import com.example.singleactivity.databinding.FragmentSplashBinding
import java.lang.reflect.Method

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegister.setOnClickListener {
            showNotification()
        }

        /*  val request = NavDeepLinkRequest.Builder
              .fromUri("android-app://LiveFragment".toUri())
              .build()
          findNavController().navigate(request)*/

    }

    private fun showNotification() {
        Log.e("Single", "show notification")
        val notificationBuilder =
            NotificationCompat.Builder(requireContext(), "new_id")
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setColor(0x00000000)
                .setContentTitle("Single Activity")
                .setContentText("this is description")
                .setLights(Color.RED, 1000, 1000)
                .setVibrate(longArrayOf(0, 400, 250, 400))
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE))

        val pendingIntent = NavDeepLinkBuilder(requireContext())
            .setGraph(R.navigation.nav_graph)
            .setDestination(R.id.registerFragment)
            .createPendingIntent()
        notificationBuilder.setContentIntent(pendingIntent)

        val notificationManager =
            requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channelId = "Single"
        val channelName = "Single Notifications"
        val channelImportance: Byte = 4

        try {
            val notificationChannelClass = Class.forName("android.app.NotificationChannel")

            val notificationChannelConstructor =
                notificationChannelClass.getDeclaredConstructor(
                    String::class.java,
                    CharSequence::class.java,
                    Integer.TYPE
                )

            val notificationChannel =
                notificationChannelConstructor.newInstance(
                    channelId,
                    channelName,
                    Integer.valueOf(channelImportance.toInt())
                )

            val createNotificationChannelMethod =
                notificationManager?.javaClass?.getDeclaredMethod(
                    "createNotificationChannel",
                    notificationChannelClass
                )
            createNotificationChannelMethod?.invoke(notificationManager, notificationChannel)

            val setChannelIdMethod: Method? =
                notificationBuilder?.javaClass?.getDeclaredMethod(
                    "setChannelId",
                    String::class.java
                )
            setChannelIdMethod?.invoke(notificationBuilder, channelId)

            Log.d("Single", "Notification channel set successfully")
        } catch (var11: Exception) {
            Log.e("Single", "Creating/setting notification channel failed", var11)
        }

        notificationManager.notify(1, notificationBuilder.build())
    }
}