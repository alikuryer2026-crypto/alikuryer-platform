package uz.alikuryer.app

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setBackgroundColor(Color.WHITE)
        }

        val title = TextView(this).apply {
            text = "ALI KURYER"
            textSize = 32f
            setTextColor(Color.rgb(217, 11, 32))
            gravity = Gravity.CENTER
        }

        val subtitle = TextView(this).apply {
            text = "Namangan • Taom yetkazib berish"
            textSize = 16f
            setTextColor(Color.BLACK)
            gravity = Gravity.CENTER
        }

        layout.addView(title)
        layout.addView(subtitle)
        setContentView(layout)
    }
}l
