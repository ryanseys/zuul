zuul
====

Authors: Ryan Seys, Vinayak Bansal, Jarred Linthorne-Shaw

Milestone 1
===========

## Deliverables

* README.md (this file)
* Command.java
* CommandWords.java
* Direction.java
* Game.java
* Humanoid.java
* Item.java
* Monster.java
* Player.java
* PlayerHistory.java
* Room.java
* View.java

## Authors and Their Roles

*Ryan Seys*
  Responsible for Command.java, CommandWords.java, Direction.java, and Room.java classes
*Vinayak Bansal*
  Responsible for Game.java, PlayerHistory.java and View.java classes
*Jarred Linthorne-Shaw*
  Responsble for Humanoid.java, Item.java, Monster.java and Player.java classes

## Changes made for Milestone 1

* Added undo/redo support for a player's actions (except fight, eat, help, and quit)
* Added a health meter to the player, which can be maintained by eating food items (e.g. bread).
  * Health meter starts at 100 and when reduced to 0, the player is dead and the game is over (you lost).
* Added Monsters which can be fought with the "fight" command
  * Monsters can kill you by reducing your health to 0
  * Monsters can drop items if you defeat them, these items can be used by you.
* Added items which can be picked up, eaten (food items only) or used as a weapon to fight (weapon items only)
* Added a player inventory (support for the player to hold a number of items)
* Added documentation which conforms to JavaDoc specifications
