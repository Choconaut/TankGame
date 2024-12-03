## Overview
A 2D tank game developed in Java using JavaFX to demonstrate OOP design principles. Players control a tank to battle against AI-controlled tanks. The game features smooth movement, collision detection, AI opponents, power-ups, and various obstacles.

## Table of Contents
- [Features](#features)
- [Gameplay](#gameplay)
- [Requirements](#requirements)
- [Setup Instructions](#requirements)
- [Project Structure](#project-structure)

## Features
- Player-Controlled Tank: Navigate through the battlefield and engage enemy tanks.
- AI-Tanks: AI opponents that track and attack the player and allied tanks.
- Obstacle Interaction: Walls and obstacles that tanks and missiles cannot pass through. 
- Power-Ups: Collectible items like medpacks to restore currentHealth.
- Collision Detection: Collision handling between tanks, obstacles, missiles, and power ups.

## Gameplay
Up: Up Arrow Key<br>
Down: Down Arrow Key<br>
Left: Left Arrow Key<br>
Right: Right Arrow Key<br>
Space: Fire Missile (has a cooldown)<br>

## Requirements
To run this project, you need: 
- **Java Runtime Environment (JRE) or Java Development Kit(JDK) 21 or higher**

## Setup Instructions
1. Download the JAR file.
   Get the latest version of `TankGame.jar` from the [Releases page](https://github.com/Choconaut/TankGame/releases/tag/v1.0).
3. Navigate to the directory where TankGame.jar is located.
4. If using terminal/command prompt, execute the following:
```
java -jar TankGame.jar
```

**Notes:**
- Ensure that ```java``` is accessible in your system's PATH if you're using the terminal/command prompt.

## Project Structure
**UML Diagram**
![Tank Game-2024-11-28-013912](https://github.com/user-attachments/assets/9a53a2cd-a80c-487c-b3bc-a93d666f994f)

**Key Components**
- *GameObject:* Ensures consistent behavior across all game entities, such as tanks, walls, and powerups.
- *GameObjectManager*: Oversees the game logic, manages the state of game objects, and handles overall game flow.
- *GameObjectFactory*: Responsible for the creation of all gameObjects.
- *TankController:* Allows user input to interact with playerTank movement.



