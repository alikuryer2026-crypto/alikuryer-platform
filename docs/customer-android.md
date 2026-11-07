# Ali Kuryer Customer Android

This Android app packages the existing `customer-react` Vite/React interface using Capacitor. It is distinct from the older native Kotlin demo under `app/`.

## Build locally

Prerequisites: Node.js 22, Java 21, Android SDK.

```bash
cd customer-react
npm install
npm run build
npx cap add android
npx cap sync android
cd android
./gradlew assembleDebug
```

The resulting debug APK is `customer-react/android/app/build/outputs/apk/debug/app-debug.apk`.

## CI download

GitHub Actions → **Ali Kuryer Customer Android APK** → latest successful run → **AliKuryer-Customer-Android-APK** artifact. GitHub sign-in may be required.

## Current limitations

- Restaurant and food listings are demonstrations, not connected to a production backend.
- Payments, SMS authentication, order submission and courier tracking are not live.
- OpenStreetMap tiles and Nominatim lookup need internet access; production geocoding requires a provider suitable for expected traffic.
- The Capacitor wrapper includes the built React assets and uses the Android WebView runtime; it is not a fully native rewrite.
- This is a debug build for testing, not a Play Store release. A signed release, app icon, splash screen and device QA remain necessary.
