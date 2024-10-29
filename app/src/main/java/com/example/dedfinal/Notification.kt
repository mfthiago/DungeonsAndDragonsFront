import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.dedfinal.R

fun showCustomNotification(context: Context) {
    val channelId = "movie_night_channel"
    val notificationId = 101

    // Create the notification channel (required for Android Oreo or higher)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = "Movie Night Channel"
        val descriptionText = "Notifications for movie night events"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelId, name, importance).apply {
            description = descriptionText
        }
        // Register the channel with the system
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    // Build the notification
    val notificationBuilder = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(R.drawable.ic_maisum)  // Notification icon
        .setContentTitle("Justin Rhyss")
        .setContentText("Hey, do you have any plans for tonight? I was thinking a few of us could go watch a movie...")
        .setStyle(
            NotificationCompat.BigTextStyle()
                .bigText("Hey, do you have any plans for tonight? I was thinking a few of us could go watch a movie at the theater nearby since there won’t be much going on for the next couple of weeks. There are some great options at 6 and 7pm, but whatever works best for you. If you have any suggestions for dinner beforehand hit reply!")
        )
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .addAction(R.drawable.ic_menosum, "Reply", null)  // Reply action (can add Intent)
        .addAction(R.drawable.ic_maisum, "Archive", null)  // Archive action (can add Intent)
        .setAutoCancel(true)  // Cancel the notification when clicked

    // Display the notification
    with(NotificationManagerCompat.from(context)) {
        notify(notificationId, notificationBuilder.build())
    }
}