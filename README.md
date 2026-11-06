# InfiGen Cloud-connected Android project

This package combines the existing InfiGen web frontend and Express/PostgreSQL authentication backend with a reproducible Android WebView shell.

## Android
- Android application ID: `com.infinity.infigen`
- App version: `1.2-cloud`
- Uses AndroidX WebKit asset loading at `https://appassets.androidplatform.net`, not `file://`.
- The frontend calls `https://infigen-cloud-api.onrender.com` by default. Change `window.INFIGEN_API_BASE` in `frontend/index.html` only if the deployed InfiGen API URL changes.
- Build with GitHub Actions: push to `main`, then open Actions → Build InfiGen Android APK → Run workflow. Download the `InfiGen-debug-apk` artifact.

## Backend deployment
Deploy `backend/` as the service root. Build command: `npm install`; start command: `npm start`.
Configure `DATABASE_URL`, `JWT_SECRET` (at least 64 characters), `NODE_ENV=staging`, and `APP_ORIGIN=https://appassets.androidplatform.net` plus any trusted web frontend origin as a comma-separated value.
Apply exactly one initial schema migration (`migrations/001_initial.sql`) to the Supabase/PostgreSQL database. Do not run both similarly named initial SQL files.

## Verification
- `GET /api/health` confirms the API process is responding.
- `GET /api/health/database` confirms database connectivity.
- `GET /api/status` reports service configuration; it deliberately reports `production: false` and `mpesa: not_configured`.
- Test registration, login, and `/api/auth/me` against a staging database before distributing the APK.

## Important operational limits
The app is cloud-connected for account registration/login/profile only. The backend explicitly keeps live payments disabled. No M-PESA, Airtel Money, bank transfer, customer funds, or regulated financial service is activated by this build. Provider contracts, credentials, security review, compliance/legal approvals, production controls, and independent testing are still required. The current auth registration response also says email verification is not enabled; do not treat a registered account as identity-verified.

Never commit `.env`, production credentials, signing keys, customer records, or real payment data. Use GitHub Actions secrets for future release signing; the uploaded debug APK is not suitable for production distribution.
