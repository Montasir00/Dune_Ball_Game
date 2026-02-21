# Dune Ball Game

Dune Ball Game is an endless-runner style 2D game built with Java and JavaFX. The game demonstrates core Object-Oriented Programming (OOP) concepts like abstraction, inheritance, encapsulation, and polymorphism in a simple, interactive game environment.

## Features

- **Endless Gameplay**: The terrain generates continuously as you progress further.
- **Dynamic Entities**: Traverse a world populated with obstacles, enemies, coins, and power-ups.
- **Physics & Movement**: Smooth ball movement with jumping, moving left and right, and collision detection.
- **Score System**: Collect coins and power-ups to increase your score while surviving for as long as possible.

## Controls

- **A / Left Arrow**: Move Left
- **D / Right Arrow**: Move Right
- **Space**: Jump (Hold for a higher jump)

## Requirements

- **Java JDK** (tested with JDK 23)
- **JavaFX SDK** (tested with JavaFX 25.0.1)

## How to Run (Windows)

A PowerShell script `run.ps1` is provided to compile and run the game easily. Ensure that the Java JDK bin path in the script matches your local installation path.

To run the game, open PowerShell in the project directory and execute:

```powershell
.\run.ps1
```

*(Note: Ensure the `javafx-sdk-25.0.1` folder is present in the root directory, or update the `run.ps1` script to point to your JavaFX library folder.)*

## Screenshots

![Gameplay](image/Screenshot%202026-02-21%20144130.png)
![Gameplay](image/Screenshot%202026-02-21%20144156.png)
![Gameplay](image/Screenshot%202026-02-21%20144216.png)
![Gameplay](image/Screenshot%202026-02-21%20144308.png)

## Architecture & OOP Concepts

This project serves as a showcase of Object-Oriented Programming (OOP) principles:
- **Inheritance & Abstraction**: Game entities extend base classes (like `GameObject`) or implement interfaces (`Collidable`, `Collectible`).
- **Encapsulation**: Game state, scores, and entity properties are securely managed within their respective classes.
- **Polymorphism**: The central game loop updates and renders a generic list of `GameObject` entities (`Ball`, `Enemy`, `Coin`, `Obstacle`) seamlessly.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

