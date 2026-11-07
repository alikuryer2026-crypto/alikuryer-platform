# Ali Kuryer — production integration contract

The public customer website is a static demo. It does not authenticate users, send SMS, charge money, or accept live orders. Do not enable those actions until backend services are deployed and tested.

## Customer API (proposed)

- `POST /api/auth/google`: accept Google OIDC credential, validate ID token on server using Google's JWKS and audience/client ID, issuer, expiry and nonce as applicable; create or link customer by stable `sub`; issue HttpOnly Secure SameSite session cookie. Never trust email alone for account linking.
- `POST /api/auth/otp/request`: normalize +998XXXXXXXXX, rate-limit by IP and phone, generate cryptographically secure short-lived OTP, store only a salted hash and expiry, call a configured SMS provider. Return generic responses to prevent enumeration.
- `POST /api/auth/otp/verify`: atomic one-time code consumption, max attempts, 5-minute expiry, server session creation, abuse controls.
- `GET /api/me`, `PATCH /api/me`, `POST /api/logout`: authenticated customer profile and session revocation.
- `GET/POST/PATCH/DELETE /api/me/addresses`: owned addresses; verify access control.
- `GET /api/me/orders`, `GET /api/me/orders/:id`: only the authenticated customer's orders, status from server; never invent history.
- `GET/POST/DELETE /api/me/favorites`: customer's favorites.
- `GET /api/restaurants?lat=...&lng=...`: return only available restaurants inside delivery zones; enforce service zones on server when accepting orders.

## Server environment (never publish secrets)

```dotenv
DATABASE_URL=
SESSION_SECRET=
GOOGLE_OIDC_CLIENT_ID=
SMS_PROVIDER=
SMS_API_KEY=
SMS_SENDER_ID=
GEOCODING_PROVIDER=
GEOCODING_API_KEY=
```

Public client configuration may contain only non-secret values such as published store links and public map style identifiers. Use HTTPS, CSRF protection for cookie sessions, CSP, validation, log redaction, database migrations, and audited role-based access control. Do not expose admin/courier/restaurant controls in customer UI; keep necessary protected backend workflows.

## Maps and consent

The static demo loads Leaflet/OpenStreetMap tiles only after the user clicks the map button. Reverse geocoding via public Nominatim is intended for occasional manual testing, not production traffic. Before production use, select a licensed geocoding provider, comply with quotas and privacy policy, use a server proxy where required, and obtain consent before transmitting coordinates. GPS is requested only after explicit click; denied permission falls back to manual address entry.

## Current state

The live static UI includes a Google/SMS sign-in *presentation only*. OTP inputs and verification are disabled until a server and provider are configured. The profile data saved in localStorage is an unverified demo and must be migrated to authenticated server records before production. Notifications are a preference only; no push infrastructure. Store buttons remain marked "Tez orada" until official URLs are configured. All sample restaurant information must be replaced by authorized, verified data before launch.

## Recommended migration

Keep the current single-file storefront as a reference during a gradual migration to React + TypeScript + Tailwind. Build and test the new client in a separate folder, then change Render's static build command/publish path only after the React version reaches feature parity and passes mobile/desktop testing. Do not overwrite the existing storefront prematurely.
