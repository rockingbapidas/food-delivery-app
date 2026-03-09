# Zomato Sample Project

A multi-module Android project demonstrating Clean Architecture, Jetpack Compose, and modern Android development practices. This repository simulates a food delivery ecosystem with separate applications for customers, restaurants, and delivery partners.

## 🏗 Architecture

The project follows **Clean Architecture** principles and is divided into several modules to ensure separation of concerns and scalability.

### Modules

- **`:app:customer`**: The consumer-facing application where users can browse restaurants and order food.
- **`:app:restaurant`**: An application for restaurant owners to manage their menus and incoming orders.
- **`:app:delivery`**: An application for delivery partners to manage pickups and drop-offs.
- **`:domain`**: The core business logic layer containing Entities, Repository Interfaces, and Use Cases. This is a pure Kotlin/Java module.
- **`:data`**: The infrastructure layer that implements Repository interfaces defined in the domain. It handles networking (Retrofit) and local persistence (Room).
- **`:core`**: A shared module containing reusable UI components (Compose), utilities, and base classes used across different app modules.

## 🛠 Tech Stack

- **Language**: [Kotlin](https://kotlinlang.org/)
- **UI Framework**: [Jetpack Compose](https://developer.android.com/jetpack/compose)
- **Dependency Injection**: [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
- **Networking**: [Retrofit](https://square.github.io/retrofit/) & [OkHttp](https://square.github.io/okhttp/)
- **Database**: [Room](https://developer.android.com/training/data-storage/room)
- **Asynchronous Programming**: [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) & [Flow](https://kotlinlang.org/docs/flow.html)
- **Navigation**: [Compose Navigation](https://developer.android.com/jetpack/compose/navigation)
- **Image Loading**: [Coil](https://coil-kt.github.io/coil/)
- **Build System**: Gradle Kotlin DSL with Version Catalogs

## 🚀 Getting Started

### Prerequisites

- Android Studio Ladybug or newer.
- JDK 17.
- Android SDK 36 (Compile SDK).

### Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/zomato-sample.git
   ```
2. Open the project in Android Studio.
3. Sync the project with Gradle files.
4. Select the desired run configuration (`customer`, `restaurant`, or `delivery`) and press **Run**.

## 🧪 Testing

The project includes unit tests for business logic in the `domain` module and instrumentation tests for UI components.

To run unit tests:
```bash
./gradlew test
```

To run instrumentation tests:
```bash
./gradlew connectedAndroidTest
```
