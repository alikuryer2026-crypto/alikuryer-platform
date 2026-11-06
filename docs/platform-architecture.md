# Ali Kuryer — platforma arxitekturasi (v1)

## Mahsulot chegaralari
- Mijozlar: ommaviy veb-sayt, faqat taom yetkazib berish.
- Restoran: alohida kabinet (kelajakda restaurant.alikuryer.uz).
- Kuryer: alohida kabinet (kelajakda courier.alikuryer.uz).
- Administrator: alohida kabinet (kelajakda admin.alikuryer.uz).
- Domenlar rejalashtirilgan, DNS va server hali sozlanmagan.
- Birinchi ishga tushirish: Namangan viloyati; boshqa hududlar keyin bosqichma-bosqich ochiladi.

## Muhim xavfsizlik qoidalari
1. Kabinet havolasini ommaviy navigatsiyadan yashirish xavfsizlik chegarasi EMAS. Har bir API so‘rovi serverda autentifikatsiya va avtorizatsiyadan o‘tadi.
2. Rollar: customer, restaurant_staff, courier, dispatcher, admin. Har bir restoran/kuryer foydalanuvchisi faqat o‘ziga tegishli ma’lumotlarni ko‘radi (object-level authorization).
3. Admin va restoran xodimlari uchun MFA; sessiyalar HttpOnly, Secure, SameSite cookie orqali; CSRF himoyasi; kirish urinishlarini cheklash.
4. HTTPS, audit log, serverda kiritmalarni tekshirish, parollarni Argon2id/bcrypt bilan xeshlash, maxfiy kalitlarni faqat server muhitida saqlash.
5. To‘lov ma’lumotlari to‘lov provayderi orqali; karta ma’lumotlarini o‘z bazamizda saqlamaslik.
6. Demo restoranlar, chegirmalar, vaqtlar va reytinglarni real ma’lumot sifatida ko‘rsatmaslik.

## Asosiy ma’lumotlar modeli
- regions: id, name, enabled
- cities: id, region_id, name, enabled
- users: id, phone, role, status, created_at
- restaurants: id, region_id, city_id, name, status, owner_user_id
- menu_categories: id, restaurant_id, name, sort_order
- menu_items: id, restaurant_id, category_id, name, price_uzs, available
- couriers: id, user_id, region_id, vehicle_id, status
- vehicles: id, type, asset_number, status
- orders: id, customer_id, restaurant_id, region_id, delivery_address, status, subtotal_uzs, delivery_fee_uzs, total_uzs, created_at
- order_items: id, order_id, menu_item_id, quantity, unit_price_uzs
- order_events: id, order_id, actor_id, event_type, created_at
- courier_assignments: id, order_id, courier_id, status, assigned_at

## Buyurtma holatlari
draft -> placed -> accepted -> preparing -> ready_for_pickup -> picked_up -> delivered.
Rad etish/bekor qilish alohida status va sabab bilan audit logda qayd qilinadi.

## Ishga tushirish tartibi
1. Mijozlar uchun responsive dizayn va hudud tanlash (demo mavjud).
2. Backend, ma’lumotlar bazasi, autentifikatsiya, restoran CRUD va menyu.
3. Buyurtma yaratish va serverda narxlarni qayta hisoblash.
4. Restoran kabineti va buyurtmalar oqimi.
5. Kuryer kabineti, buyurtma biriktirish va yetkazish holatlari.
6. Admin kabineti, audit, monitoring, hududlarni faollashtirish.
7. To‘lov integratsiyasi, test, xavfsizlik tekshiruvi va pilot.

## Hozirgi holat
Repo ichidagi index.html statik demo. Backend, haqiqiy buyurtmalar, autentifikatsiya va production xavfsizlik nazoratlari hali amalga oshirilmagan.
