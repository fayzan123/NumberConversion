# Linked Number System

This repository contains a Java implementation of a linked number system and associated classes for handling digit manipulation and exception handling. Key components include:

- **LinkedNumber.java**: Implements a linked structure to represent numbers using nodes.
- **Digit.java**: Handles individual digit operations within the linked number system.
- **LinkedNumberException.java**: Custom exception class for managing errors in the linked number system.
- **DLNode.java**: Defines a doubly linked node structure for use in the linked number operations.
- **TestLinkedNumber.java**: Unit tests to verify the correctness and performance of the linked number implementation.

## Features
1. Linked Number System: Create and manipulate numbers using a doubly linked node structure.
2. Custom Exceptions: Handle errors gracefully within the linked number system.
3. Unit Testing: Ensure reliability and accuracy through JUnit tests.

## How to Run
1. Clone this repository: `git clone <repository-url>`
2. Compile the Java files:
   ```
   javac LinkedNumber.java Digit.java LinkedNumberException.java DLNode.java
   ```
3. Run the unit tests:
   ```
   java org.junit.runner.JUnitCore TestLinkedNumber
   ```
