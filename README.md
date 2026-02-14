# Mobile Automation Test Project

Appium + Cucumber + TestNG ile mobil otomasyon test projesi.

## �️ Gereksinimler

- Java 11+
- Maven 3.6+
- Appium Server (çalışır durumda)
- Android Emulator veya iOS Simulator

## ⚙️ Kurulum

### 1. Projeyi klonla

```bash
git clone <repository-url>
cd mobileAutomationTest
```

### 2. Konfigürasyon dosyalarını oluştur

```bash
# Android config
cp src/test/resources/config/config-android.properties.example src/test/resources/config/config-android.properties

# iOS config
cp src/test/resources/config/config-ios.properties.example src/test/resources/config/config-ios.properties
```

### 3. Config dosyalarını düzenle

`config-android.properties` ve `config-ios.properties` dosyalarındaki placeholder değerleri kendi değerlerinizle değiştirin:

- `YOUR_APP.apk` → Uygulamanızın yolu
- `YOUR_DEVICE_NAME` → Cihaz adı
- `YOUR_USERNAME` → Test kullanıcı adı
- vb.

### 4. Bağımlılıkları yükle

```bash
mvn clean install -DskipTests
```

## �🚀 Testleri Çalıştırma

### Tüm login testleri

```bash
mvn clean test -Dcucumber.filter.tags="@login" -Dplatform=android
```

### Sadece pozitif testler

```bash
mvn clean test -Dcucumber.filter.tags="@positive" -Dplatform=android
```

### Sadece negatif testler

```bash
mvn clean test -Dcucumber.filter.tags="@negative" -Dplatform=android
```

### iOS için testler

```bash
mvn clean test -Dcucumber.filter.tags="@login" -Dplatform=ios
```

## 📊 Test Raporları

Test koşulduktan sonra terminalde rapor linkleri otomatik olarak gösterilecektir.

### Raporları Otomatik Açma

```bash
./open-reports.sh
```

### Manuel Rapor Açma

#### 1. Extent Report (En Detaylı)

```bash
open target/extent-reports/ExtentReport.html
```

#### 2. Cucumber HTML Report

```bash
open target/cucumber-reports/cucumber-html-reports/overview-features.html
```

#### 3. Allure Report (İnteraktif)

```bash
mvn allure:serve
```

## 🛠️ Gereksinimler

- Java 11+
- Maven 3.6+
- Appium Server (çalışır durumda)
- Android Emulator veya iOS Simulator

## 📁 Proje Yapısı

```
├── src/test/java/
│   ├── pages/          # Page Object Model sınıfları
│   ├── stepdefinitions/# Cucumber step tanımlamaları
│   ├── runners/        # Test runner sınıfları
│   └── utils/          # Yardımcı sınıflar
├── src/test/resources/
│   ├── features/       # Cucumber feature dosyaları
│   └── config/         # Konfigürasyon dosyaları
└── target/
    ├── cucumber-reports/    # Cucumber raporları
    ├── extent-reports/      # Extent raporları
    └── allure-results/      # Allure sonuçları
```

## ⚙️ Konfigürasyon

Platform-specific config dosyaları:

- `src/test/resources/config/config-android.properties` (Git'te yok - local)
- `src/test/resources/config/config-ios.properties` (Git'te yok - local)

Example dosyalar repository'de mevcut:

- `config-android.properties.example`
- `config-ios.properties.example`

**Not:** Gerçek config dosyaları `.gitignore` ile ignore edilmiştir ve repository'ye push edilmez.

## 🏷️ Tag'lar

- `@login` - Login testleri
- `@positive` - Pozitif senaryolar
- `@negative` - Negatif senaryolar
- `@smoke` - Smoke testleri
- `@regression` - Regression testleri

## 📸 Screenshots

Failed testlerde otomatik screenshot alınır:

- Lokasyon: `target/screenshots/`
- Cucumber ve Allure raporlarına otomatik eklenir

## 🤝 Katkıda Bulunma

1. Feature branch oluştur
2. Değişiklikleri commit et
3. Pull request aç

## 📝 Lisans

MIT License
