# Android App Icon Instructions

## 🎨 Requisitos

- **Formato**: PNG
- **Fundo**: Sólido (sem transparência)
- **Design**: Simples, reconhecível

## 📐 Tamanhos Necessários

| Densidade | Tamanho | Pasta |
|-----------|---------|-------|
| mdpi | 48x48 | mipmap-mdpi/ |
| hdpi | 72x72 | mipmap-hdpi/ |
| xhdpi | 96x96 | mipmap-xhdpi/ |
| xxhdpi | 144x144 | mipmap-xxhdpi/ |
| xxxhdpi | 192x192 | mipmap-xxxhdpi/ |

## 📂 Onde Colocar

```
android/app/src/main/res/
├── mipmap-mdpi/
│   ├── ic_launcher.png (48x48)
│   └── ic_launcher_round.png (48x48)
├── mipmap-hdpi/
│   ├── ic_launcher.png (72x72)
│   └── ic_launcher_round.png (72x72)
├── mipmap-xhdpi/
│   ├── ic_launcher.png (96x96)
│   └── ic_launcher_round.png (96x96)
├── mipmap-xxhdpi/
│   ├── ic_launcher.png (144x144)
│   └── ic_launcher_round.png (144x144)
└── mipmap-xxxhdpi/
    ├── ic_launcher.png (192x192)
    └── ic_launcher_round.png (192x192)
```

## ⚡ Opção Rápida: Ferramenta Online

### Android Asset Studio
1. Vai a: https://romannurik.github.io/AndroidAssetStudio/
2. Image Asset Generator → Launcher icons
3. Upload logo (512x512 recomendado)
4. Ajusta padding/cor de fundo
5. Download ZIP
6. Extrai para `android/app/src/main/res/`

### Alternative: appicon.co
1. https://www.appicon.co
2. Upload logo
3. Seleciona "Android"
4. Download e extrai

## 🛠️ Método Manual: ImageMagick

```bash
# Instalar
brew install imagemagick

# Gerar todos os tamanhos
convert logo.png -resize 48x48 mipmap-mdpi/ic_launcher.png
convert logo.png -resize 72x72 mipmap-hdpi/ic_launcher.png
convert logo.png -resize 96x96 mipmap-xhdpi/ic_launcher.png
convert logo.png -resize 144x144 mipmap-xxhdpi/ic_launcher.png
convert logo.png -resize 192x192 mipmap-xxxhdpi/ic_launcher.png
```

## ✅ Testar

1. Android Studio → Run
2. Verifica ícone no launcher
3. Testa em diferentes devices/emuladores

---

**Tip**: Para versão redonda (`ic_launcher_round.png`), usa a mesma imagem mas com cantos arredondados ou deixa Android fazer automaticamente.
