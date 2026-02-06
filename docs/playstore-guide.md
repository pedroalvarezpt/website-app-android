# Google Play Store - Guia de Publicação

## 📋 Checklist Pré-Submissão

### 1. App Assets
- [ ] Ícone da app (512x512 PNG)
- [ ] Screenshots (mínimo 2, recomendado 8)
  - Phones: 1080x1920 ou 1080x2340
  - Tablets (opcional): 1920x1200
- [ ] Feature graphic (1024x500)
- [ ] Vídeo promo (opcional)

### 2. Descrições
- [ ] Título (máx 50 caracteres)
- [ ] Descrição curta (máx 80 caracteres)
- [ ] Descrição completa (máx 4000 caracteres)
- [ ] Categoria: Shopping ou Lifestyle

### 3. Build
- [ ] Version code incrementado
- [ ] Signed release build (AAB)
- [ ] Testado em múltiplos devices
- [ ] ProGuard configurado

### 4. Play Console Setup
- [ ] Conta Google Play Console criada
- [ ] App criada no console
- [ ] Privacy policy URL
- [ ] Contact email

## 🔐 Assinar Release Build

### Gerar Keystore
```bash
keytool -genkey -v -keystore plantalivre-release.jks \
  -keyalg RSA -keysize 2048 -validity 10000 \
  -alias plantalivre
```

### build.gradle
```gradle
android {
    signingConfigs {
        release {
            storeFile file('plantalivre-release.jks')
            storePassword 'xxx'
            keyAlias 'plantalivre'
            keyPassword 'xxx'
        }
    }
    buildTypes {
        release {
            signingConfig signingConfigs.release
        }
    }
}
```

### Build AAB
```bash
./gradlew bundleRelease
```

Output: `app/build/outputs/bundle/release/app-release.aab`

## 📤 Upload

1. Play Console → App → Release → Production
2. Create new release
3. Upload AAB
4. Release notes
5. Review & rollout

## ⏱️ Timeline

- **Review**: 1-7 dias (geralmente 24-48h)
- **Updates**: ~2-4 horas após aprovação

---

✅ **Ready to publish!**
