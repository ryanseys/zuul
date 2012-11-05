zuul (Github : http://github.com/ryanseys/zuul)
====

Authors: Ryan Seys, Vinayak Bansal, Jarred Linthorne-Shaw

Milestone 2
===========

## Deliverables

* README.md (this file)

### Zuul package

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

### View package

* IView.java
* TextView.java
* TwoDView.java

### Test package

* CommandTest.java
* DirectionTest.java
* HumanoidTest.java
* ItemTest.java
* MonsterTest.java
* PlayerHistoryTest.java
* PlayerTest.java
* RoomTest.java

### Image files

* apple.png
* bread.gif
* flamethrower.jpg
* hatchet.png
* key.png
* map.jpg
* orange.png
* pear.png
* rooms_eastroom.png
* rooms_noMap.png
* rooms_northroom1.png
* rooms_northroom2.png
* rooms_northwestroom.png
* rooms_southroom.png
* rooms_startroom.png
* rooms_westroom.png
* sword.png

### Sequence Diagrams

* drop.png
* eat.png
* fight.png
* Garbage.png
* Go.png
* pickup.png
* help.png
* quit.png
* UndoRedo.png

### Class Diagrams and Sequence Diagrams

* SecondMilestoneUML.png
* TestUML.png
* hint.png
* objective.png
* reset.png
* quit.png
* pickup.png
* Inspect.png
* Go.png
* fight.png
* eat.png
* drop.png
* UndoRedo.png

## Authors and Their Roles

**Ryan Seys:**
  Responsible for Command.java, CommandWords.java, Direction.java, and Room.java classes, README.md

**Vinayak Bansal:**
  Responsible for Game.java, PlayerHistory.java and View.java classes, Sequence Diagrams

**Jarred Linthorne-Shaw:**
  Responsble for Humanoid.java, Item.java, Monster.java and Player.java classes, UML Diagram

**All:**
  Responsible for reporting issues/bugs to Github (https://github.com/ryanseys/zuul/issues)
  Responsible for the GUI building and testing
  Responsible for writing tests for their respective class files

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

## Changes made for Milestone 2

* Added GUI interface
  * Buttons for directions
  * Inventory list
  * Map functionality shows current status
  * Added images of the items you have
  * Added images for the map functionality
  * Console panel in GUI for showing status of health of player/monsters
* Inspect functionality on all items
  * New GUI displays the information about the items
* Hint functionality provides hint depending on the state of the game
* Added lots of unit tests for the classes
* Splitted the project into multiple packages
* Added reset functionality
