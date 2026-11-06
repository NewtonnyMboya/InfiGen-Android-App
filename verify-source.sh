#!/usr/bin/env bash
set -euo pipefail
node --check backend/server.js
node --check backend/auth.js
grep -q 'https://infigen-cloud-api.onrender.com' frontend/index.html
grep -q 'https://appassets.androidplatform.net' backend/server.js
grep -q 'livePayments: false' backend/server.js
echo "InfiGen source checks passed."
