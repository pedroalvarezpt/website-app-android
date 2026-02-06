# Gradle Wrapper JAR

## ⚠️ Missing File: gradle-wrapper.jar

O ficheiro `gradle-wrapper.jar` **NÃO PODE** ser commitado diretamente via GitHub API porque é um ficheiro **binário**.

---

## ✅ Como Resolver

### Opção 1: Android Studio Gera Automaticamente

Quando abres o projeto no Android Studio, ele vai:
1. Detectar que falta o wrapper JAR
2. **Download automático** do Gradle 8.2
3. Gerar o ficheiro `gradle-wrapper.jar`

**Não precisas fazer nada!** ✅

---

### Opção 2: Gerar Manualmente (se necessário)

```bash
cd "C:\Git\Planta Livre\website-app-android\android"

# Windows
gradlew.bat wrapper

# Ou se tiveres Gradle instalado globalmente:
gradle wrapper --gradle-version 8.2
```

Isto cria automaticamente:
- `gradle/wrapper/gradle-wrapper.jar`
- `gradle/wrapper/gradle-wrapper.properties`

---

### Opção 3: Download Manual

1. Vai a: https://services.gradle.org/distributions/gradle-8.2-bin.zip
2. Extrai o ZIP
3. Copia `gradle-8.2/lib/gradle-wrapper.jar` para:
   ```
   android/gradle/wrapper/gradle-wrapper.jar
   ```

---

## 🐛 Se Android Studio Não Gerar

```bash
cd android

# Tentar sync
.\gradlew.bat tasks

# Se falhar, instalar Gradle:
# https://gradle.org/install/
```

---

## ✅ Verificar Se Está Correto

Depois de o Android Studio gerar:

```bash
cd android
.\gradlew.bat tasks
# Deve mostrar lista de tasks disponíveis
```

---

**RESUMO**: Abre no Android Studio e ele resolve automaticamente! 🚀
