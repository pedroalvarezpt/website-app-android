# PlantaLivre.pt - Android App

WebView wrapper nativo para https://plantalivre.pt

## 🎯 Configurações Fixas

```
SITE_URL=https://plantalivre.pt
DISABLE_ZOOM=true
USER_AGENT="PlantalivreApp/1.0"
MIN_ANDROID_SDK=24
```

## 📱 Funcionalidades Implementadas

- ✅ WebView básico com plantalivre.pt
- ✅ Zoom desativado
- ✅ User Agent customizado
- ✅ Interceptação de URLs (links externos abrem no browser)
- ✅ Injeção completa de JS (app-theme.js)
- ✅ JavaScript Bridge (AndroidBridge)
- ✅ Swipe to refresh
- ✅ Progress bar
- ✅ Back button navigation
- 🟡 Push notifications (FCM) - Pronto para implementar
- 🟡 Ícones da app - Aguarda assets

## 🚀 Quick Start

### 1. Clonar Repositório
```bash
git clone https://github.com/pedroalvarezpt/website-app-android.git
cd website-app-android
```

### 2. Abrir no Android Studio
1. Open Android Studio
2. File → Open
3. Selecionar pasta `android/`
4. Aguardar Gradle sync
5. Run (Shift+F10)

### 3. Testar no Emulador/Device
- Emulador: API 24+ (Android 7.0+)
- Device físico: USB debugging ativado

## 📚 Documentação

- [Development Log](docs/development-log.md) - Status e roadmap
- [Play Store Guide](docs/playstore-guide.md) - Checklist para publicação
- [Backend API](docs/backend-api.yaml) - Especificação da API
- [FCM Setup](android/FCM_SETUP.md) - Push notifications
- [Icon Instructions](android/ICON_INSTRUCTIONS.md) - Como adicionar ícones

## 🔧 Estrutura do Projeto

```
website-app-android/
├── android/                    # Projeto Android nativo
│   ├── app/
│   │   ├── src/main/
│   │   │   ├── kotlin/pt/plantalivre/app/
│   │   │   │   ├── MainActivity.kt
│   │   │   │   └── AppBridge.kt       # JavaScript Bridge
│   │   │   ├── assets/
│   │   │   │   └── app-theme.js       # JS injection
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   ├── values/
│   │   │   │   └── mipmap-*/          # App icons
│   │   │   └── AndroidManifest.xml
│   │   └── build.gradle
│   ├── build.gradle
│   ├── settings.gradle
│   ├── FCM_SETUP.md
│   ├── ICON_INSTRUCTIONS.md
│   └── README.md
├── docs/
│   ├── development-log.md
│   ├── playstore-guide.md
│   └── backend-api.yaml
├── shared/
│   └── js/
│       └── app-theme.js           # Original (reference)
├── .gitignore
└── README.md
```

## 🔗 JavaScript Bridge API

Funções disponíveis no WebView:

```javascript
// Verificar se está na app
if (window.PLANTALIVRE_APP) {
  console.log('Running in app!');
  console.log('Platform:', window.PLANTALIVRE_PLATFORM); // 'android'
}

// Abrir URL externa
window.AndroidBridge.openExternal('https://example.com');

// Partilhar conteúdo
window.AndroidBridge.share('Título', 'Texto', 'https://url.com');

// Mostrar toast
window.AndroidBridge.showToast('Mensagem!');

// Obter versão da app
var version = window.AndroidBridge.getAppVersion();

// API simplificada via PlantaLivreApp
window.PlantaLivreApp.openExternal('https://example.com');
window.PlantaLivreApp.share('Title', 'Text', 'URL');
window.PlantaLivreApp.toast('Message');
```

## 📦 Build para Produção

### APK (teste)
```bash
cd android
./gradlew assembleRelease
```

### AAB (Google Play)
```bash
./gradlew bundleRelease
```

## 🎉 Próximos Passos

1. **Adicionar ícones** - Ver [ICON_INSTRUCTIONS.md](android/ICON_INSTRUCTIONS.md)
2. **Implementar FCM** - Ver [FCM_SETUP.md](android/FCM_SETUP.md)
3. **Customizar app-theme.js** - Ajustar seletores CSS
4. **Testar em device real**
5. **Seguir Play Store Guide** para publicação

---

**Versão**: 1.0.0  
**Package**: pt.plantalivre.app  
**Min SDK**: 24 (Android 7.0)  
**Target SDK**: 34 (Android 14)
