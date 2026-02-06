# PlantaLivre.pt - Android App

**WebView nativo em Kotlin** para https://plantalivre.pt

[![Status](https://img.shields.io/badge/status-funcional-brightgreen)]() 
[![Kotlin](https://img.shields.io/badge/kotlin-1.9.20-purple)]() 
[![Min SDK](https://img.shields.io/badge/minSdk-24-blue)]() 
[![Target SDK](https://img.shields.io/badge/targetSdk-34-blue)]()

---

## 🎯 Configurações Fixas

```kotlin
SITE_URL = "https://plantalivre.pt"
DISABLE_ZOOM = true
USER_AGENT = "PlantalivreApp/1.0"
MIN_ANDROID_SDK = 24 (Android 7.0)
TARGET_SDK = 34 (Android 14)
```

---

## ✅ Funcionalidades Implementadas

### Core WebView
- ✅ WebView com https://plantalivre.pt
- ✅ Zoom desativado
- ✅ User Agent customizado
- ✅ Swipe to refresh
- ✅ Progress bar
- ✅ Back button navigation

### JavaScript Bridge (Kotlin ↔ JS)
- ✅ `window.AndroidBridge.openExternal(url)`
- ✅ `window.AndroidBridge.share(title, text, url)`
- ✅ `window.AndroidBridge.showToast(message)`
- ✅ `window.AndroidBridge.getAppVersion()`
- ✅ `window.AndroidBridge.hasPermission(permission)`

### URL Interception
- ✅ Links `plantalivre.pt` → WebView
- ✅ Links externos → Browser nativo
- ✅ Blacklist de domínios

### JavaScript Injection
- ✅ `app-theme.js` injetado automaticamente
- ✅ `window.PLANTALIVRE_APP = true`
- ✅ `window.PLANTALIVRE_PLATFORM = 'android'`
- ✅ API `window.PlantaLivreApp` simplificada

### Documentação
- ✅ 7 ficheiros Markdown completos
- ✅ Guias passo-a-passo
- ✅ Troubleshooting

### Pronto para Implementar
- 🟡 Push Notifications (FCM) - [Guia](android/FCM_SETUP.md)
- 🟡 Ícones da app - [Guia](android/ICON_INSTRUCTIONS.md)

---

## 🚀 Quick Start (5 minutos)

Ver guia completo: **[QUICK_START.md](QUICK_START.md)**

```bash
# 1. Clone/Pull
cd C:\Users\SIPC018\StudioProjects\website-app-android
git pull

# 2. Abre no Android Studio
# Seleciona pasta: android/

# 3. Aguarda Gradle Sync

# 4. Run (Shift+F10)
```

---

## 📁 Estrutura do Projeto

```
website-app-android/
├── android/                          # Projeto Android Studio
│   ├── app/
│   │   ├── src/main/
│   │   │   ├── kotlin/pt/plantalivre/app/
│   │   │   │   ├── MainActivity.kt        ← WebView principal
│   │   │   │   └── AppBridge.kt           ← JavaScript Bridge
│   │   │   ├── assets/
│   │   │   │   └── app-theme.js           ← JS injetado
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   │   └── activity_main.xml
│   │   │   │   ├── values/
│   │   │   │   │   ├── strings.xml
│   │   │   │   │   ├── colors.xml
│   │   │   │   │   └── themes.xml
│   │   │   │   └── mipmap-*/              ← App icons (aguarda)
│   │   │   └── AndroidManifest.xml
│   │   └── build.gradle                   ← Dependencies
│   ├── build.gradle                       ← Project config
│   ├── settings.gradle
│   ├── FCM_SETUP.md
│   ├── ICON_INSTRUCTIONS.md
│   └── README.md
├── docs/
│   ├── development-log.md                 ← Status e roadmap
│   ├── playstore-guide.md                 ← Play Store checklist
│   └── backend-api.yaml                   ← API spec
├── shared/js/
│   └── app-theme.js                       ← JS original
├── README.md                              ← Este ficheiro
├── QUICK_START.md                         ← Guia rápido
├── PROJECT_VALIDATION.md                  ← Validação completa
└── setup-git.sh                           ← Git helper script
```

---

## 🔧 Tecnologias

| Componente | Tecnologia | Versão |
|------------|------------|--------|
| **Linguagem** | Kotlin | 1.9.20 |
| **Build System** | Gradle | 8.2 |
| **Min Android** | API 24 | Android 7.0 |
| **Target Android** | API 34 | Android 14 |
| **WebView** | Android WebView | System |
| **UI** | XML Layouts | - |
| **Dependencies** | AndroidX | Latest |

---

## 📊 Código

```
Linhas de Código:
- MainActivity.kt:  ~175 linhas Kotlin
- AppBridge.kt:     ~65 linhas Kotlin
- app-theme.js:     ~120 linhas JavaScript

Total: ~360 linhas (sem contar XML/docs)
```

---

## 🧪 Como Testar

### Teste Básico
```bash
# Abre Android Studio
# Run no emulador/device
# Verifica que plantalivre.pt carrega
```

### Teste JavaScript Bridge
```javascript
// Chrome DevTools: chrome://inspect
window.AndroidBridge.showToast('Hello!');
window.AndroidBridge.getAppVersion();
window.PlantaLivreApp.share('Título', 'Texto', 'https://plantalivre.pt');
```

### Build APK
```bash
cd android
.\gradlew assembleDebug
# Output: app/build/outputs/apk/debug/app-debug.apk
```

---

## 📚 Documentação Completa

| Documento | Descrição |
|-----------|----------|
| [QUICK_START.md](QUICK_START.md) | Guia rápido 5 minutos |
| [PROJECT_VALIDATION.md](PROJECT_VALIDATION.md) | Validação completa do projeto |
| [docs/development-log.md](docs/development-log.md) | Status e histórico |
| [docs/playstore-guide.md](docs/playstore-guide.md) | Checklist Play Store |
| [docs/backend-api.yaml](docs/backend-api.yaml) | API specification |
| [android/FCM_SETUP.md](android/FCM_SETUP.md) | Push notifications setup |
| [android/ICON_INSTRUCTIONS.md](android/ICON_INSTRUCTIONS.md) | Como adicionar ícones |

---

## 🎯 Próximos Passos

1. ✅ **Código funcional** - COMPLETO
2. ✅ **Documentação** - COMPLETO
3. 🟡 **Adicionar ícones** - [Ver guia](android/ICON_INSTRUCTIONS.md)
4. 🟡 **Testar em device real**
5. 🟡 **Implementar FCM** (opcional) - [Ver guia](android/FCM_SETUP.md)
6. 🟡 **Play Store submission** - [Ver checklist](docs/playstore-guide.md)

---

## 🐛 Troubleshooting

Ver [QUICK_START.md](QUICK_START.md) secção Troubleshooting para:
- Gradle sync issues
- SDK not found
- WebView não carrega
- JavaScript Bridge não responde

---

## 📞 Suporte

- **Issues**: [GitHub Issues](https://github.com/pedroalvarezpt/website-app-android/issues)
- **Docs**: Ver pasta `docs/`
- **Quick Help**: Ver [QUICK_START.md](QUICK_START.md)

---

## 📄 Licença

Proprietário - PlantaLivre.pt

---

**Status Atual**: 🟢 **FUNCIONAL E PRONTO PARA TESTAR**

**Última Atualização**: 06-Fev-2026 22:23 WET
