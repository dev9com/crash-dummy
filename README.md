Crash Dummy
===========

Sample Java app, intended to demonstrate different ways a dev can cause an app to fail.

This app is helpful for developers interested in learning more about the JVM failure modes, from thread death to
NullPointers to divide by zero.

- Allocate a window
- Allocate windows until crash
- Allocate session memory
- Bogus JDBC driver
- Generate dead-locked threads
- Execute bad SQL
- Fill the heap via local member
- Fill the heap via statics
- Open network connections until death
- Fill up PermGen (at least on older JDKs...)
- Execute native commands
- Stack overflow (not just a website!)
- System.exit()

... and many more!

Usage
=====

To build the app, just run the following command:

    mvn clean verify
    
To run the app, run the following command:

    mvn spring-boot:run
    
Use at your own risk!

This is a simple Spring Boot app, so you can check the Spring Boot docs and do things like convert this to a WAR
you can drop into your app server.

Good luck and happy crashing!

