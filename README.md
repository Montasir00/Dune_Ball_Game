# 🏜️ Dune Ball Game

A physics-based 2D side-scrolling platform game developed in **Java** with **JavaFX**, created as an Object-Oriented Programming (OOP) project for the Bachelor's in Data Analysis program (Academic Year 2025/2026).

> **Student:** Fazlur Rahman  
> **Supervisor:** Prof. Salvatore Distefano

---

## 📖 Table of Contents

- [About the Game](#about-the-game)
- [Key Features](#key-features)
- [Technologies Used](#technologies-used)
- [System Architecture](#system-architecture)
- [OOP Concepts Implemented](#oop-concepts-implemented)
- [Installation & Requirements](#installation--requirements)
- [Game Controls](#game-controls)
- [Gameplay Instructions](#gameplay-instructions)
- [Project Structure](#project-structure)

---

## 🎮 About the Game

**Dune Ball Game** is a physics-based platform game where the player controls a ball rolling across a procedurally generated desert-like terrain. The project serves as a comprehensive demonstration of Object-Oriented Programming principles applied in a real-world, interactive context.

**Objective:** Roll, jump, and collect as many coins and power-ups as possible while avoiding obstacles and enemies.

---

## ✨ Key Features

- **Procedurally Generated Terrain** – Infinite terrain using mathematical sine functions for natural-looking hills
- **Physics-Based Movement** – Realistic gravity, momentum, and friction mechanics
- **Variable Jump Height** – Hold Spacebar longer for higher jumps
- **Dynamic Camera System** – Smooth camera that follows the player's position
- **Shield Power-Up** – Grants 5 seconds of invincibility with a cyan glow visual effect
- **Score Tracking** – Real-time score display (+10 per coin, +25 per power-up)
- **Multiple Game States** – Start screen, gameplay, pause, and game over
- **Polymorphic Entity Management** – Single heterogeneous `List<GameObject>` collection with dynamic method dispatch
- **Robust Exception Handling** – Try-catch blocks handle terrain access violations and game logic errors
- **Extensible Design** – New entity types can be added without modifying existing code (Open-Closed Principle)

---

## 🛠️ Technologies Used

| Technology | Purpose |
|---|---|
| **Java** (JDK 11+) | Core programming language |
| **JavaFX** | GUI framework for graphics rendering |
| **NetBeans / IntelliJ / Eclipse** | IDE |
| **Standard Java Compilation** | Build system |

---

## 🏗️ System Architecture

The system follows a layered architecture with the following core components:

| Class | Responsibility | Key Methods |
|---|---|---|
| `Main.java` | Application entry point | `start()`, `main()` |
| `GameController.java` | Input handling, rendering | `initKeyListeners()`, `startGameLoop()` |
| `Game.java` | Core game logic, collision detection | `update()`, `render()`, `endGame()` |
| `GameObject.java` | Abstract base class for all entities | `update()`, `render()` |
| `Ball.java` | Player character behavior | `jump()`, `moveLeft()`, `moveRight()` |
| `Enemy.java` | AI patrol behavior | `update()`, `collidesWith()` |
| `Coin.java` | Collectible behavior | `collect()`, `collidesWith()` |
| `Obstacle.java` | Static hazard | `collidesWith()` |
| `Terrain.java` | Procedural terrain generation | `getYAt()`, `extendIfNeeded()` |
| `GameException.java` | Custom exception handling | Constructor |

### Inheritance Hierarchy

```
GameObject (abstract)
├── Ball
├── Enemy
├── Coin
├── Obstacle
├── PowerUp
└── Terrain
```

---

## 🧩 OOP Concepts Implemented

This project demonstrates all core OOP principles:

| Concept | Implementation | Evidence |
|---|---|---|
| **Abstraction** | `GameObject` abstract class | Defines contract for all entities |
| **Encapsulation** | Private fields + controlled methods | `Ball`, `Enemy`, `Coin`, etc. |
| **Information Hiding** | 3-step process | `final` constants, `protected` setters |
| **Inheritance** | `GameObject` hierarchy | 6 entity types extend base class |
| **Polymorphism** | All 4 types implemented | Overloading, Coercion, Overriding, Generics |
| **Composition** | Dual collection architecture | `Game` contains and manages all entities |
| **Modularity** | 11 separate classes | Each with clear, single responsibility |
| **Reuse** | `GameObject` base class | Reused by all 6 entity subclasses |
| **Subtyping** | Multiple interface implementation | One object, multiple variable types |
| **Exception Handling** | Custom `GameException` + try-catch | Terrain → Game exception flow |
| **Extensibility** | `PowerUp` class addition | Demonstrates Open-Closed Principle |

### Polymorphism Types

| Type | Category | Example | Binding |
|---|---|---|---|
| **Overloading** | Ad-hoc | `update()` vs `update(Ball)` | Compile-time |
| **Coercion** | Ad-hoc | `int → double`, `long → double` | Compile-time |
| **Overriding** | Universal (Inclusion) | `Ball.update()`, `Enemy.update()` | Runtime |
| **Generics** | Universal (Parametric) | `List<Enemy>`, `List<GameObject>` | Compile-time |

---

## ⚙️ Installation & Requirements

### System Requirements

- **Java Version:** JDK 11 or higher
- **JavaFX:** Required (bundled or configured separately)
- **OS:** Windows, macOS, or Linux
- **RAM:** Minimum 512 MB
- **Display:** 800×600 minimum resolution

### How to Run

1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-username/dune-ball-game.git
   cd dune-ball-game
   ```

2. **Compile the project:**
   ```bash
   javac --module-path /path/to/javafx/lib --add-modules javafx.controls,javafx.fxml src/*.java
   ```

3. **Run the game:**
   ```bash
   java --module-path /path/to/javafx/lib --add-modules javafx.controls,javafx.fxml Main
   ```

> ℹ️ Replace `/path/to/javafx/lib` with your actual JavaFX SDK `lib` directory path.

---

## 🎮 Game Controls

| Key | Action |
|---|---|
| `ENTER` | Start game from title screen |
| `←` Left Arrow | Move ball left |
| `→` Right Arrow | Move ball right |
| `SPACEBAR` | Jump (hold longer for higher jump) |
| `P` | Pause / Unpause game |
| `R` | Restart game (on game over screen) |

---

## 📋 Gameplay Instructions

**Scoring:**
- 🪙 Collect coins → **+10 points** each
- 🛡️ Collect shield power-ups → **+25 points** + 5 seconds of invincibility

**Game Over Conditions:**
- 💥 Collide with a red obstacle block
- 👾 Collide with a purple patrolling enemy (while not invincible)

**Tips:**
- Hold `SPACEBAR` to jump higher over enemies and obstacles
- Collect shield power-ups before entering dangerous areas
- The terrain is infinite — keep moving right!

---

## 📁 Project Structure

```
dune-ball-game/
├── src/
│   ├── Main.java              # Application entry point
│   ├── GameController.java    # Input handling & rendering
│   ├── Game.java              # Core game logic
│   ├── GameObject.java        # Abstract base class
│   ├── Ball.java              # Player character
│   ├── Enemy.java             # AI patrol enemy
│   ├── Coin.java              # Collectible coin
│   ├── Obstacle.java          # Static hazard
│   ├── PowerUp.java           # Shield power-up
│   ├── Terrain.java           # Procedural terrain
│   └── GameException.java     # Custom exception
└── README.md
```

---

## 🙏 Acknowledgments

- **Prof. Salvatore Distefano** – For comprehensive guidance on OOP principles and course structure
- **Open-Source Community** – For game development insights and best practices
