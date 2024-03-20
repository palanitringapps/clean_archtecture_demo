## Clean Architecture
The core principles of the clean approach can be summarized as followed:

1. The application code is separated into layers.
   These layers define the separation of concerns inside the code base.

2. The layers follow a strict dependency rule.
   Each layer can only interact with the layers below it.

3. As we move toward the bottom layer — the code becomes generic.
   The bottom layers dictate policies and rules, and the upper layers dictate implementation details such as the database, networking manager, and UI.

## Build with
1. Kotlin - First class and official programming language for Android development.
2. Coroutines - For asynchronous and more..
3. Flow - A cold asynchronous data stream that sequentially emits values and completes normally or with an exception.
4. Android Architecture Components 
   4.1 ViewModel - Stores UI-related data that isn't destroyed on UI changes.
   4.2 Navigation Components - Navigate fragments easier.
   4.3 Room - Persistence library provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite
5. Dependency Injection
   5.1 Hilt - Easier way to incorporate Dagger DI into Android application.
6. Retrofit - A type-safe HTTP client for Android and Java.
