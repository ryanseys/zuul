zuul (Github : http://github.com/ryanseys/zuul)
====

Authors: Ryan Seys, Vinayak Bansal, Jarred Linthorne-Shaw

Milestone 4
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

### Builders package

* AbstractBuilder.java
* Builder.java
* ButtonBuilder.java
* ItemBuilder.java
* MapBuilder.java
* MonsterBuilder.java
* RoomBuilder.java

### View package

* TextView.java
* ThreeDView.java
* TwoDView.java
* View.java

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
* background_plain.png
* boss1_in_room.png
* boss2_in_room.png
* bread.gif
* chest_in_room_filled.png
* chest_in_room.png
* claws.png
* east_door_locked.png
* east_door.png
* fix.png
* flamethrower.jpg
* full_map.png
* key.png
* map.jpg
* monster_in_room.png
* north_door_locked.png
* north_door.png
* orange.png
* pear.png
* room_empty.png
* room_player.png
* rooms_eastroom.png
* rooms_noMap.png
* rooms_northroom1.png
* rooms_northroom2.png
* rooms_northwestroom.png
* rooms_southroom.png
* rooms_startroom.png
* rooms_westroom.png
* south_door_locked.png
* south_door.png
* sword.png
* treasure_in_room.png
* west_door_locked.png
* west_door.png

### Sequence Diagrams

* drop.png
* eat.png
* fight.png
* Go.png
* help.png
* hint.png
* Inspect.png
* objective.png
* pickup.png
* quit.png
* reset.png
* UndoRedo.png

### Class Diagrams

* ThirdMilestoneUML.png

## Authors and Their Roles

**Ryan Seys:**
  Responsible for Command.java, CommandWords.java, Direction.java, and Room.java classes, README.md

**Vinayak Bansal:**
  Responsible for Game.java, PlayerHistory.java and View.java classes, Sequence Diagrams

**Jarred Linthorne-Shaw:**
  Responsble for Humanoid.java, Item.java, Monster.java and Player.java classes, UML Diagram

**All:**

  * Responsible for reporting issues/bugs to Github (https://github.com/ryanseys/zuul/issues)
  * Responsible for the GUI building and testing
  * Responsible for writing tests for their respective class files
  * Responsbile for writing builder classes and hooking them up to the views and testing of this feature

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

## Changes made for Milestone 3

* Added 3D GUI Interface
  * Clickable doors (to travel through)
  * Clickable item chests (to pickup)
  * Clickable monsters (to fight)
  * Drop/Eat/Inspect buttons for item actions
* Very large refactoring of codebase.
  * Moved all compared Strings into constants
  * Extracted many similar methods from the different views into a superclass

## Changes made for Milestone 4

* Added builders to allow you to create your own levels
  * Level builder featuring a 4x4 grid of possible rooms
  * Many different items can be placed in any of the rooms
  * Two different monsters (Alien & Boss) can be placed in any two rooms
  * Treasure can be placed in any one room
    * Get the treasure to win the game
* Can save and load games from a file
  * Games automatically will save when you begin playing to ensure you may reset the game at any point
* Refactored more code

# User manual

The goal of the game is to find the long lost treasure of zuul. Once you find the treasure the game will quit. The only other time the game will quit is if you die, or click File ---> Quit.

If you click a door (or the downward arrow in the interface) you will go through it if the door is unlocked. If the door is not unlocked, you must find and pickup the key associated with that door to unlock it.

To fight a monster, you can click it. If you are holding a weapon you will do damage to the monster, if you aren't holding a weapon then you will do no damage. When you attack a monster, it will immediately attack back, doing some damage to you. This will depleat your health by a certain amount. If your health depleats to zero (0) then you will lose the game and it will quit automatically. If you defeat a monster, it will disappear from the room and it may drop some new items into the room you are in. In this case, you can now pick them up to use them.

To recover some health, eat food items. If you select an item you have in your inventory, and the "Eat" button is highlighted, it means that you can eat it. If the button is not highlighted, it means that you cannot eat that item.

To reset the game, you can select File-->Reset. This will reset everything in the same way that quitting and restarting the game would do.

To undo an action, you can click the undo button in the File menu. This will only undo actions that could seamingly be undone in real life. As such, eating a food item is NOT undoable, neither is fighting a monster, or dying. You also cannot undo a reset. Travelling between rooms is undoable, as is picking up and dropping an item. You can also undo your undos by clicking the "Redo" button from the File menu.

**NOTE:**
Clicking on the objects sometimes isn't detected for some reason we cannot explain. We detect using the Polygon library whether the point we click is within the box we have generated around the object, and yet sometimes clicks that are clearly within the space defined are not detected. For this we recommend moving your mouse a little bit and trying to click the object again.
