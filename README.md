This README provides an overview and explanation of the Java programs included in the project. Each program demonstrates specific concepts and solutions to various real-world problems in Java programming.

1. Multi-Class Program (Constructor Chaining)
File: Q1A.java
Description:
Demonstrates the implementation of constructor chaining across three classes in a hierarchical inheritance structure. It also includes:

Parameterized constructors.
Copy constructors for deep copying.
Usage of access modifiers for encapsulation.
A synchronized method to display all variables from the hierarchy.
Private constructor in one class to prevent direct object creation.
Key Concepts:

Constructor chaining with super.
Encapsulation using private, protected, and public access modifiers.
Synchronization for thread-safe operations.
2. Static and Final Nested Classes
File: Q1B.java
Description:
Illustrates the use of static and final nested classes in Java. The program includes:

A static nested class with a static block, instance block, and constructor.
A final nested class with a final method and variable to demonstrate immutability.
Usage of this and super keywords.
Thread-safe static methods with synchronized blocks.
Key Concepts:

Nested classes (static and final).
Immutability using final.
Synchronized blocks for thread safety.
3. Library Management System
File: Q2A.java
Description:
Simulates a library management system with an abstract base class LibraryItem and concrete subclasses (Book and DVD). It supports:

Polymorphism for dynamic method invocation.
Searching library items by title or ID using method overloading.
Custom exception handling for unavailable items.
Finally block to log borrow/return operations.
Key Concepts:

Abstract classes and method overriding.
Polymorphism and method overloading.
Custom exceptions and exception handling.
Collections for managing library items.
4. File Compression and Decompression
File: Q2B.java
Description:
Provides a solution for compressing and decompressing files using Run-Length Encoding (RLE). The program includes:

File I/O using BufferedReader and BufferedWriter.
Exception handling for missing files or invalid compression formats.
A custom exception for invalid data during decompression.
Logging to track file size before and after compression.
Key Concepts:

Run-Length Encoding for compression.
File I/O and exception handling.
Polymorphism with method overloading for text and binary compression.
5. Deadlock Simulation and Detection
File: Q3A.java
Description:
Simulates a deadlock scenario involving two threads accessing shared resources in opposite orders. Uses ThreadMXBean to detect deadlocks dynamically and terminates the program gracefully upon detection.

Key Concepts:

Synchronized blocks for resource locking.
Deadlock simulation.
Deadlock detection with ThreadMXBean.
6. Handling ConcurrentModificationException
File: Q3B.java
Description:
Demonstrates the ConcurrentModificationException using a standard ArrayList and resolves it using CopyOnWriteArrayList or explicit synchronization.

Key Concepts:

Multi-threading and shared resources.
ConcurrentModificationException and its resolution.
Thread-safe collections (CopyOnWriteArrayList).
