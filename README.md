# Multithreaded Chat Application (Java)

A console-based chat application built with Java Sockets and Threads. A central server accepts multiple clients at the same time, and any message sent by one client is broadcast to all the other connected clients.

## Features
- Server built with `ServerSocket`, listening on port **54321**
- One thread per client (`ClientHandler extends Thread`), so many users can chat at once
- Messages from one client are broadcast to all other connected clients
- Each user enters a name, and messages appear as `Name: message`
- Clients can leave by typing `exit`
- Disconnected clients are removed from the server's list automatically

## Project structure
```
CodvedaChatApp/
└── src/
    ├── Server.java   # Server class and ClientHandler thread class
    └── Client.java   # Client program
```

## How it works
1. `Server` opens a `ServerSocket` and waits for connections with `accept()`.
2. Each new connection gets its own `ClientHandler` thread and is added to a shared, synchronized list of clients.
3. When a `ClientHandler` reads a message, it calls `Server.broadcast()`, which sends the message to every client except the sender.
4. `Client` uses two threads: one reads what the user types and sends it, the other prints messages coming from the server.

## Requirements
- Java Development Kit (JDK) 8 or newer
- NetBeans IDE (or any Java IDE / command line)

## How to run

### In NetBeans
1. Open the project in NetBeans and click **Clean and Build**.
2. Right-click `Server.java` → **Run File**. The output shows `Server has started...`.
3. When `Server.java` is running, Right-click `Client.java` → **Run File**. repeat this step to add more chats/clients.
4. *Alternative for step 3;*
   Open two or more Command Prompt / Terminal windows and run:
   ```
   cd path\to\CodvedaChatApp
   java -cp build\classes Client
   ```
   (On Mac/Linux use `/` instead of `\`.)
5. Enter a different name in each window and start chatting.

### From the command line
```
cd CodvedaChatApp/src
javac Server.java Client.java
java Server
```
Then, in other windows:
```
java Client
```

## Example
```
Alice's window:   Hello everyone
Bob's window:     Alice: Hello everyone
Carol's window:   Alice: Hello everyone
```

## Concepts demonstrated
- Client-server networking with `Socket` and `ServerSocket`
- Multithreading with `Thread`
- Thread-safe shared data with `Collections.synchronizedList`
- Broadcasting messages to multiple clients

## Author
Khutso Elias Langa
