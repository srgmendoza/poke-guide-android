# **Poke Guide by. Sergio Mendoza**

A simple project to show a list of Pokemons, show a brief detail, and search for them using name keyword.

---

## **Table of Contents**
1. [Features](#features)
2. [Tech Stack](#tech-stack)
4. [Setup & Installation](#setup--installation)
5. [Architecture](#architecture)
6. [Future Improvements](#future-improvements)
7. [Contact](#contact)

---

## **Features**
- 📋 **Listing Feature**: Displays a paginated list of Pokémon with images and basic details.
- 🔍 **Search Feature**: Quickly search for Pokémon by their name using a keyword filter.
- 🚀 **Detail Feature**: View detailed stats, height, weight, and an official image of each Pokémon.

---

## **Tech Stack**
- **Data API**: [PokeApi](https://pokeapi.co/)
- **Programming Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: MVI / Clean Architecture
- **Libraries**:
    - Dependency Injection: [Koin](https://insert-koin.io/)
    - Networking: [Retrofit](https://square.github.io/retrofit/)
    - Asynchronous tasks: [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)
    - Image Loading: [Coil](https://coil-kt.github.io/coil/)
    - Navigation: [Jetpack Navigation Component](https://developer.android.com/guide/navigation)
    - Testing: [JUnit](https://junit.org/junit5/), [Mockk](https://mockk.io/)

---

## **Setup & Installation**

### Prerequisites
- Android Studio version **2022.2.1 (or above)**.
- Gradle version **7.x**.
- Minimum SDK level **24**.

### Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/srgmendoza/poke-guide-android
   ```
2. Open the project in Android Studio.
3. Sync the Gradle files.
4. Build and run the project on an emulator or device.

---

## **Architecture**
This project follows the **MVI (Model-View-Intent)** pattern.

### Module explanation:
1. **:app**: Module where application is running by default.
2. **:core:navigation**: Contains the common logic to navigate within the app.
3. **:core:network**: Act as a Retrofit wrapper, to give flexibility for remote data usage.
4. **:core:ui**: Transversal module, that serves all feature modules, with common UI components and logic.
5. **:poke:data**: Where data layer lays, using repository pattern, its main duty is to provide data sources.
6. **:poke:domain:models**: All domain models are declared within this module.
7. **:poke:domain:uses-cases**: Where domain rules are, there is a Use Case for every single data need within the app.
8. **:poke:features**: Here we can find the UI and presentation code for every feature in the app.
9. **:poke:theme**: Where composable theme is declared.

---

## **Future Improvements**
- Add offline support for Pokémon data.
- Implement filters (e.g., Pokémon type or generation).
- Enhance detail view with stats like abilities and base stats.
- Add unit tests for domain and UI layers.
---

## **Contact**
If you have questions or suggestions, feel free to reach out:

- **GitHub**: [srgmendoza](https://github.com/srgmendoza)
- **Email**: srgmendoza@gmail.com