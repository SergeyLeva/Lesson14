package ua.sergeylevcenko.lesson14

import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {


    companion object {
        const val NOTIFICATION_ID = 101
        const val CHANNEL_ID = "channelID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, MainActivity::class.java)
        intent.apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }


        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {


            val builder = NotificationCompat.Builder(this, CHANNEL_ID)


                .setSmallIcon(android.R.drawable.stat_sys_upload)
                .setContentTitle("Напоминание")
                .setContentText("Пора покормить кота")
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setLargeIcon(
                    BitmapFactory.decodeResource(
                        getResources(),
                        R.drawable.ic_action_cat
                    )
                )


                .setStyle(
                    NotificationCompat.BigPictureStyle()
                        .bigPicture(BitmapFactory.decodeResource(resources, R.drawable.pikcha))
                        .bigLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.pikcha))
                        .setBigContentTitle("Beautiful Cat")
                        .setSummaryText("Голодный кот")
                )

                .addAction(R.drawable.ic_action_cat, "Открыть", pendingIntent)
                .addAction(R.drawable.ic_pets_24dp, "Отказаться", pendingIntent)
                .addAction(R.drawable.ic_launcher_foreground, "Другой вариант", pendingIntent)
                .setColor(Color.GREEN)
                .setTicker("Последнее китайское предупреждение!")
                .setAutoCancel(true)
                .setProgress(100, 86, false)
                .setStyle(
                    NotificationCompat.BigTextStyle()
                        .bigText("Когда кормить будут? Далее идёт очень длинный текст про бедного котика, которого морят голодом уже целых три минуты")
                )
                .setStyle(
                    NotificationCompat.InboxStyle()
                        .addLine("This is first line")
                        .addLine("This is second line")
                        .addLine("This is third line")
                        .addLine("This is fourth line")
                        .addLine("This is fifth line")
                        .setBigContentTitle("This is Content Title.")
                        .setSummaryText("This is summary text.")
                )
            with(NotificationManagerCompat.from(this)) {
                notify(
                    NOTIFICATION_ID, builder.build()

                )
            }
        }
    }
}
