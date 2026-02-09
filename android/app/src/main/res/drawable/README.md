# Splash Logo

## Para substituir o logo do splash screen:

1. **Adiciona o ficheiro:**
   - Nome: `splash_logo.png`
   - Formato: PNG com transparência
   - Tamanho: 512×512px ou maior
   - Localização: `android/app/src/main/res/drawable/splash_logo.png`

2. **Atualiza o layout:**
   - Ficheiro: `android/app/src/main/res/layout/activity_main.xml`
   - Linha: `android:src="@mipmap/ic_launcher"`
   - Mudar para: `android:src="@drawable/splash_logo"`

3. **Rebuild:**
   ```
   Build → Clean Project
   Build → Rebuild Project
   ```

## Nota:
Enquanto não adicionares `splash_logo.png`, a app usa `ic_launcher` como fallback.
