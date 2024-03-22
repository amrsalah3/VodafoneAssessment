## Vodafone Technical Assessment Solution - Amr Salah Abdelhady

The technical assessment is about building a weather application for Android called **Weather Now And Later** with a set of instructions and requirements for the project.

### Screenshots

<img src="https://github.com/amrsalah3/VodafoneAssessment/assets/52531091/34934f2d-9b88-484e-8943-686e2aa79414" width="217" height="470"> <img src="https://github.com/amrsalah3/VodafoneAssessment/assets/52531091/e31f044f-8d49-43e8-9585-9814dbe36709" width="217" height="470">
<img src="https://github.com/amrsalah3/VodafoneAssessment/assets/52531091/2f72467d-cf29-4c9e-aea7-87484b4ee164" width="217" height="470"> <img src="https://github.com/amrsalah3/VodafoneAssessment/assets/52531091/f0df0fab-ec59-466c-89f4-d7e5d8393ce4" width="217" height="470">
<img src="https://github.com/amrsalah3/VodafoneAssessment/assets/52531091/e8e983aa-90b9-4e36-a53f-03040555c29f" width="217" height="470"> <img src="https://github.com/amrsalah3/VodafoneAssessment/assets/52531091/26a418fa-b9ee-42d7-99a9-d223188b1a6c" width="217" height="470">

### Architecture
- Clean Architecture principles are applied in the project:
  
  <img src="https://github.com/amrsalah3/VodafoneAssessment/assets/52531091/b86f763e-7636-4b6e-8bb7-60f2b0b9c59c" width=300>

- **Project Modules** 

  - **domain**: Contains business rules and does not depend on any other layers. Thus, it can be used with different data layer implementations or different presentation layer implementations (e.g. Kotlin and Compose Multiplatform.). The module contains:
    - Weather and city models
    - Repository interfaces
    - Use cases
  - **data**: Contains data sources and repositories' implementations (Repository business rules/contracts (interfaces) are defined in the domain layer). This module contains:
    - Data sources
    - Data Transfer Objects (response models of data sources)
    - Repository implementations
  - **feature**: Contains the presentation layer including Composables and the ViewModels for each feature. This module contains these sub-modules:
    - City feature (Pick City screen). This feature is implemented using MVVM architecture.
    - Current Weather feature module (Current Weather screen). This feature is implemented using MVVM architecture.
    - 7-Day Weather Forecast feature (Daily Forecast screen). This feature is implemented using MVI architecture.
  - **core**: Contains shared and common components among the project. This module contains:
    - Common dependency injection using Dagger-Hilt
    - Common composable functions used in the UI
  - **weatherdatatools-library**: A library for formatting weather data. This library is published using Maven Local as specified by the assessment requirements, however,
    to use it on other PC than mine (e.g. on GitHub repo), we need to add the module itself as a dependency because Maven Local publishes this module as a local library, not online (central).

### Other Requirements

- UI: Jetpack Compose is used for the user interface. 
- Dependency Injection: All DI is done using Dagger-Hilt.
- Testing: Unit tests are added for the domain, data, and weatherdatatools-library modules.
- CI/CD Pipeline: A workflow is set up using GitHub Actions [[link](https://github.com/amrsalah3/VodafoneAssessment/blob/master/.github/workflows/Build.yaml)] containing the following work: 
  - Linting the code
  - Running tests
  - Generate app APK
- Dark mode is supported.

### Download
Different APKs can be found in the WorkFlow CI/CD builds [here](https://github.com/amrsalah3/VodafoneAssessment/actions/workflows/Build.yaml).   
Or you can download the latest APK directly from [here](https://github.com/amrsalah3/VodafoneAssessment/actions/runs/8396214030/artifacts/1351387806).

### Notes and Further Improvements
There are some improvements to the project that I wanted to make but due to the limited time for submitting the assessment, I will just mention them below:
- Encapsulate and abstract common gradle module dependencies and their versions that are used in multiple modules.
  This will reduce repeating the dependencies in multiple modules and will guarantee providing one shared version of each library for all the sharing modules.
- Add unit tests for the ViewModels
- Implement checks for internet connection while using the app, displaying an error when no connection, and provide a swipe-to-refresh functionality.
  Please make sure you have an internet connection while using the app.
- Displaying the latest fetched weather when using the app offline.
- Add city name on current weather screen.
- Use string resources instead of hardcoded texts.
- Build a more fancy UI with responsive screen to large screen sizes.
- Display more weather data.
- Adding full documentation for each class, object, and function.

