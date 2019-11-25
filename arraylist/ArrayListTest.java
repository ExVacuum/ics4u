package arraylist;

import java.util.ArrayList;

public class ArrayListTest {

    ArrayList<String> inventory = new ArrayList<String>();
    String currentRoom = "";

    public static void main(String[] args) {
        new ArrayListTest();
    }

    ArrayListTest(){

        //Assume that the player has been playing for a while and has the following things in his/her inventory
        inventory.add("food");
        inventory.add("water");
        inventory.add("knife");
        inventory.add("newspaper");

        //set current room
        currentRoom = "beach";

        useItem("knife");

        printInventory();

    }

    /* Write the useItem() method so that it accomplishes the following:
     *
     * 1. the item must be in the inventory, or else print out "you don't have a _______" (itemname)
     * 2. if itemname == "knife", then
     * 2.a. if you are in the "barn":
     * 2.a.i  print out "You escape from the barn"
     * 2.a.ii unfortunately, you have to leave the knife behind. Remove the knife from the inventory.
     * 2.b. if you are in the "forest"
     * 2.b.i  if you have the knife but NOT the axe, then add "sticks" to your inventory.
     * 2.b.ii  if you have the axe as well, then print "you should probably use the axe instead"
     * 2.c if you are in any other room, print "you can't use that here"
     */
    void useItem(String itemName) {
        if(inventory.contains(itemName.toLowerCase())){
            if(itemName.equalsIgnoreCase("knife")){

                //Barn
                if(currentRoom.equals("barn")){
                    System.out.println("You escape form the barn. Unfortunately, you have to leave the knife behind.");
                    inventory.remove("knife");
                    return;
                }

                //Forest
                if(currentRoom.equals("forest")){

                    //No Axe
                    if(!inventory.contains("axe")){
                        System.out.println("You get some sticks.");
                        inventory.add("sticks");
                        return;
                    }

                    //Has Axe
                    System.out.println("You should probably use the axe instead.");
                    return;
                }

                System.out.println("You can't use that here.");
                return;
            }
        }
        System.out.printf("You don't have a %s.\n", itemName);
    }

    /* Write this method */
    void printInventory() {
        System.out.println("Inventory:\n __________ ");
        for(String itemName : inventory){
            System.out.printf("|%-10s|\n", itemName);
        }
        System.out.println("|__________|");
    }
}
