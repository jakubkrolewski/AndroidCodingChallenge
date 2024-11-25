# The Challenge:

The challenge is to create a simple Android app that exercises a REST-ful API. The API endpoint `https://api.nytimes.com/svc/books/v3/lists/current/hardcover-fiction.json?api-key=KoRB4K5LRHygfjCL2AH6iQ7NeUqDAGAB&offset=0` returns a JSON object which is a list of different books published by the New York Times. 

Using this endpoint, show a list of these items, with each row displaying at least the following data:

- Image
- Title
- Description 

### Technical Instructions:
- MVP architecture, no ViewModel
- XML Layouts (no Compose)
- Demonstrate use of Dagger, Retrofit and Glide (for the images)
- No database needed
- Feel free to make any assumptions you want along the way or use any third party libraries as needed and document why you used them.

### General Instructions:
- This isn't a visual design exercise. For example, if you set random background colors to clearly differentiate the views when debugging, pick Comic Sans or Papyrus, we won't hold that against you. Well, maybe a little bit if you use Comic Sans :)
- This is also most of the code you'll be showing us – don't understimate the difficulty of the task, or the importance of this exercise in our process, and rush your PR. Put up your best professional game.
- This isn't just about handling the happy path. Think slow network (or no network at all), supporting different device sizes, ease of build and run of the project. If we can't check out and click the run button in Android Studio, you're off to a bad start (we've had PRs without a graddle for instance).
- Explanations on any choice you've made are welcome.
- We appreciate there's a lot that is asked in this exercise. If you need more time, feel free to ask. If you need to de-prioritize something, apply the same judgement you would on a professional project, argument your decision. 

Bonus Points:
  - Unit tests

# The Solution:
## Project configuration
The project was refactored to a multi-module structure that can be used for a big, scalable application.
Currently it contains the following modules:
- app - the application module that holds the main Dagger graph, main activity and navigation graph
- component/base - module that can be shared with other modules that contains base application configuration, e.g. the network layer
- component/common - reusable general utilities, not coupled to the app business logic
- feature/books-list - Fragment that contains the books list

Changes in the project Gradle file were done only when necessary to support the new project structure.
When I create a project setup from scratch, I usually do a lot of things differently, e.g.:
- configure detekt
- configure code coverage measurement and enforcement
- update dependencies version
- use KSP instead of KAPT
- use src/main/kotlin and src/test/kotlin directories
- use Junit5 instead of Junit4

The Gradle files still may contain some code that should be cleaned up, e.g. unused dependencies should be removed and the order of dependencies should be updated (e.g. by sorting the alphabetically).
The project currently supports only debug build type. The release build type and code obfuscation have not been configured.

## MVP architecture
The presenter was created from scratch in a very simple way. It has a lifecycle connected to the Fragment lifecycle, so doesn't survive screen rotation.
It would be possible to make it live on the extended lifecycle scope, e.g. with using the 'https://github.com/konmik/nucleus' library that I used in the past, but it has been deprecated 5 years ago.
The presenter also currently doesn't support saving the state in a Bundle or SavedStateHandle.

## Libraries
I have used some additional "AndroidX" libraries, "Kluent" and "Mockito" for testing, "Moshi" for JSON parsing and kotlin coroutines for asynchronous operations. Using coroutines instead of RxJava should be recommended for new projects for better work with Android frameworks (including Compose), support for nullable values in streams and better error handling.

## UI
A very minimal time was spent on UI. It was designed just to be functional.
The whole list is loaded at once without pagination support.

## Tests
Due to the limited time spent on the project, only unit tests were created (but still some tests missing) and no integration tests.