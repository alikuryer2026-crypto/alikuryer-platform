package uz.alikuryer.app

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView

class MainActivity : Activity() {
    private val red = Color.rgb(217, 11, 32)
    private val black = Color.rgb(18, 18, 18)
    private val white = Color.WHITE
    private var language = "O‘zbekcha"
    private var cartCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showScreen("welcome")
    }

    private fun label(text: String, size: Float = 18f, color: Int = black, bold: Boolean = false): TextView {
        return TextView(this).apply {
            this.text = text
            textSize = size
            setTextColor(color)
            setPadding(12, 14, 12, 14)
            if (bold) setTypeface(null, Typeface.BOLD)
        }
    }

    private fun button(text: String, action: () -> Unit): Button {
        return Button(this).apply {
            this.text = text
            setTextColor(white)
            setBackgroundColor(red)
            setOnClickListener { action() }
        }
    }

    private fun showScreen(screen: String) {
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(white)
            setPadding(24, 24, 24, 24)
        }
        val scroller = ScrollView(this)
        val content = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL }
        scroller.addView(content)
        root.addView(scroller)
        setContentView(root)

        fun heading(title: String, description: String = "") {
            content.addView(label("ALI KURYER", 20f, red, true))
            content.addView(label(title, 30f, black, true))
            if (description.isNotEmpty()) content.addView(label(description, 16f))
        }
        fun nav(text: String, destination: String) {
            content.addView(button(text) { showScreen(destination) })
        }
        fun item(name: String, detail: String, onTap: () -> Unit) {
            content.addView(label(name, 20f, black, true))
            content.addView(label(detail, 15f))
            content.addView(button("Tanlash") { onTap() })
        }

        when (screen) {
            "welcome" -> {
                root.setBackgroundColor(black)
                content.addView(label("ALI KURYER", 38f, red, true))
                content.addView(label("Namangan • Taom yetkazib berish", 19f, white))
                content.addView(label("Sevimli taomlaringiz bir joyda", 23f, white, true))
                nav("Boshlash", "language")
            }
            "language" -> {
                heading("Tilni tanlang")
                listOf("O‘zbekcha", "Русский", "English").forEach { lang ->
                    content.addView(button(lang) { language = lang; showScreen("login") })
                }
            }
            "login" -> {
                heading("Telefon raqam", "Davom etish uchun raqamingizni kiriting")
                val phone = EditText(this).apply {
                    hint = "+998 90 123 45 67"
                    inputType = android.text.InputType.TYPE_CLASS_PHONE
                }
                content.addView(phone)
                content.addView(label("SMS tasdiqlash xizmati hali ulanmagan.", 14f))
                nav("Demo rejimida davom etish", "home")
            }
            "otp" -> {
                heading("SMS kod", "Tasdiqlash xizmati ishlab chiqilmoqda")
                nav("Bosh sahifa (demo)", "home")
            }
            "home" -> {
                heading("Namangan", "Bugun nima yeysiz?")
                content.addView(label("🍔 Burger     🍕 Pitsa     🍣 Sushi", 19f))
                content.addView(label("Restoranlar", 24f, black, true))
                nav("Restoranlarni ko‘rish", "restaurants")
                nav("Savat ($cartCount)", "cart")
                nav("Profil", "profile")
            }
            "restaurants" -> {
                heading("Restoranlar", "Namangan • Demo katalog")
                item("Restoran namunasi", "Menyu va narxlar namuna sifatida ko‘rsatilgan") { showScreen("restaurant") }
                nav("Orqaga", "home")
            }
            "restaurant" -> {
                heading("Restoran namunasi", "Taomlar menyusi • Demo")
                item("Lavash", "35 000 so‘m") { cartCount++; showScreen("cart") }
                item("Burger", "42 000 so‘m") { cartCount++; showScreen("cart") }
                item("Pitsa", "75 000 so‘m") { cartCount++; showScreen("cart") }
                nav("Restoranlar", "restaurants")
            }
            "cart" -> {
                heading("Savat", "Tanlangan mahsulotlar: $cartCount")
                if (cartCount == 0) content.addView(label("Savat bo‘sh"))
                if (cartCount > 0) nav("Buyurtmani rasmiylashtirish", "checkout")
                content.addView(button("Savatni tozalash") { cartCount = 0; showScreen("cart") })
                nav("Taom tanlash", "restaurants")
            }
            "checkout" -> {
                heading("Buyurtmani rasmiylashtirish", "Demo: haqiqiy buyurtma yuborilmaydi")
                val address = EditText(this).apply { hint = "Yetkazib berish manzili" }
                content.addView(address)
                content.addView(label("To‘lov va yetkazib berish xizmati hali ulanmagan.", 15f))
                nav("Demo kuzatuv ekranini ko‘rish", "tracking")
            }
            "tracking" -> {
                heading("Buyurtma kuzatuvi", "Xarita va jonli kuryer kuzatuvi ishlab chiqilmoqda")
                nav("Bosh sahifa", "home")
            }
            "profile" -> {
                heading("Profil", "Ali Kuryer • $language")
                nav("Tilni o‘zgartirish", "language")
                nav("Buyurtmalar (demo)", "tracking")
                nav("Bosh sahifa", "home")
            }
            else -> showScreen("welcome")
        }
    }
}
