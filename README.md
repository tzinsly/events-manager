# events-manager

* [Description](#description)
* [How to compile and run](#how-to-compile-and-run)
* [Versions](#versions)

## Description
Java application to read a log file containing records of events, process each one and 
register it, with additional information, into a database.

## How to compile and run
1. Inside the project root directory (events-manager), run:
   $ gradlew clean
   $ gradlew build

2. A jar file will be generated on ./build/libs

3. To run, execute:
   $ java -jar build\\libs\\events-manager-1.0-SNAPSHOT.jar <log-event-file>
   i.e: log-event-file can be: ".\\src\\main\\resources\\events-log"

## Versions
* 1.0-SNAPSHOT Release containing:
  ### Features:
  * Read and parse of input text file 
  * Parse each line of the file to -> Json -> Entity
  * Process all events, marking the long ones with more than 4ms
  * List all event summary registered on database
  * Open for extension using Threads 
  ### Tools:
  * Use Java 1.8
  * Use log4j 1.2.17 to register info and debug messages
  * Use jUnit 4.12 for tests
  * Use Gradle 4.0 for build
  * Use HSQLDB 2.0.0
