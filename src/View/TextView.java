package View;

import zuul.CommandWords;
import zuul.Item;
import zuul.Monster;
import zuul.Player;

/**
 * This class is intended to display the current state of the model in the
 * console
 * 
 * @author Vinayak Bansal
 * @version 2012.10.22
 */

public class TextView {
  private Player player;

  public TextView(Player p) {
    player = p;
  }

  public void update() {
    System.out.println(player + " has a health of " + player.getHealth());
    System.out.println(player.getInventoryString());
    System.out.println(player.getCurrentRoom().getDescription());
    System.out.print("> ");
  }

  public void displayHelp() {
    System.out
        .println("You are lost. You are alone. You wander around in a cave.\n");
    System.out.println("Your command words are:");
    for (CommandWords commandWord : CommandWords.values())
      System.out.print(commandWord + " ");
    System.out.println("\n");
  }

  public void monsterMissing() {
    System.out.println("Nothing to Fight!");
  }

  public void garbageCommand() {
    System.out.println("Type better! Try again!");
  }

  public void invalidRoom() {
    System.out.println("Do you really want to walk into a wall?! Try again!");
  }

  public void gameDone() {
    System.out.println("Sorry you lost. The game is over");
  }

  public void monsterDead(Monster m) {
    System.out.println("The following monster has died: \n" + m);
  }

  public void eatingWeapon(Item i) {
    System.out.println("You cannot eat a weapon named " + i.getName());
  }

  public void noItem(Item i) {
    System.out.println("You do not have an item named " + i.getName()
        + ". Therefore you cannot eat it.");
  }

  public void itemInvalid(Item i) {
    System.out.println("The item " + i.getName() + " does not exist!");
  }

  public void itemError(Item i) {
    System.out.println("You don't have the item " + i.getName()
        + " in your inventory");
  }

  public void inCompleteCommand() {
    System.out
        .println("You typed in a correct command, but one that expects a second piece of info. Please try again");
  }

  public void undoRedoUnavailable(CommandWords commandWord) {
    System.out.println("There is nothing to " + commandWord + " right now.");
  }

  public void quit() {
    System.out.println("You quit the game.");
  }
}
