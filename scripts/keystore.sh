#!/bin/sh
if [ -f app/keystore.gradle ]; then
    echo "app/keystore.gradle file already exists!"
    exit 1
fi
cat > app/keystore.gradle << EOF
ext.release_keystore=file('keystore/debug.keystore')
ext.key_alias='androiddebugkey'
ext.key_password='android'
ext.store_password='android'
ext.mercadopago_public_key='"YOUR_PUBLIC_KEY"'
ext.mercadopago_public_key_sandbox='"YOUR_SANDBOX_PUBLIC_KEY"'
EOF
