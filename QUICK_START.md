# Quick Start - PlantaLivre Android App

## ⚡ 5 Minutos para Testar

### 1️⃣ Clone/Pull do Projeto

```bash
cd C:\Users\SIPC018\StudioProjects\website-app-android
git pull
```

### 2️⃣ Abre no Android Studio

1. **Android Studio** → `Open`
2. Seleciona: `C:\Users\SIPC018\StudioProjects\website-app-android\android`
3. Aguarda **Gradle Sync** (~1-2 min primeira vez)
4. Verifica que não há erros no "Build" tab

### 3️⃣ Run na App

**Opção A: Emulador**
```
1. Tools → Device Manager
2. Create Virtual Device (se não tiveres)
3. Escolhe: Pixel 5, API 34 (Android 14)
4. Click ▶️ Run (Shift+F10)
```

**Opção B: Device Físico**
```
1. Ativa "Developer Options" no telemóvel
2. Ativa "USB Debugging"
3. Liga via USB
4. Seleciona device no dropdown
5. Click ▶️ Run
```

### 4️⃣ Testa as Features

✅ **WebView carrega**: https://plantalivre.pt deve aparecer  
✅ **Zoom desativado**: Tentar zoom não funciona  
✅ **Pull to refresh**: Arrastar para baixo recarrega  
✅ **Links externos**: Abrem no browser nativo  
✅ **Back button**: Navega para trás no WebView  

---

## 🧪 Testar JavaScript Bridge

Abre o Chrome DevTools ligado ao WebView:

```bash
# No Chrome desktop:
chrome://inspect

# Seleciona teu device/emulator
# Console JavaScript:
window.AndroidBridge.showToast('Hello from WebView!');
window.AndroidBridge.getAppVersion(); // "1.0.0"
window.AndroidBridge.share('Título', 'Texto', 'https://plantalivre.pt');
```

---

## 📦 Build APK (Teste)

### Via Android Studio
```
Build → Build Bundle(s) / APK(s) → Build APK(s)
```

Output: `android/app/build/outputs/apk/debug/app-debug.apk`

### Via Command Line
```bash
cd android
.\gradlew assembleDebug
```

---

## 🐛 Troubleshooting

### "Gradle sync failed"
```bash
cd android
.\gradlew clean
.\gradlew --refresh-dependencies
# Restart Android Studio
```

### "SDK not found"
```
File → Project Structure → SDK Location
Verifica Android SDK path (ex: C:\Users\...\AppData\Local\Android\Sdk)
```

### "WebView não carrega"
- Verifica internet no emulador/device
- Check Logcat: `adb logcat | findstr WebView`
- Testa URL no browser primeiro

### "JavaScript Bridge não funciona"
- Verifica Logcat: `adb logcat | findstr PlantaLivre`
- Confirma que `@JavascriptInterface` está em AppBridge.kt
- Testa em device real (não só emulador)

---

## ✅ Checklist de Funcionalidade

- [ ] Gradle sync completo sem erros
- [ ] App compila e roda
- [ ] WebView carrega plantalivre.pt
- [ ] Zoom desativado funciona
- [ ] Pull to refresh funciona
- [ ] Links externos abrem browser
- [ ] Back button navega no histórico
- [ ] JavaScript Bridge responde (toast test)
- [ ] App reinstala sem problemas

---

## 🎯 Próximos Passos

1. **Adicionar Ícones**  
   Ver: [android/ICON_INSTRUCTIONS.md](android/ICON_INSTRUCTIONS.md)

2. **Customizar app-theme.js**  
   Edita: `android/app/src/main/assets/app-theme.js`

3. **Implementar Push Notifications**  
   Ver: [android/FCM_SETUP.md](android/FCM_SETUP.md)

4. **Build Release**  
   Ver: [docs/playstore-guide.md](docs/playstore-guide.md)

---

## 📚 Documentação Completa

- [README.md](README.md) - Visão geral
- [PROJECT_VALIDATION.md](PROJECT_VALIDATION.md) - Validação completa
- [docs/development-log.md](docs/development-log.md) - Histórico desenvolvimento
- [android/FCM_SETUP.md](android/FCM_SETUP.md) - Push notifications
- [android/ICON_INSTRUCTIONS.md](android/ICON_INSTRUCTIONS.md) - App icons

---

**Qualquer problema?** Abre uma issue no GitHub!
