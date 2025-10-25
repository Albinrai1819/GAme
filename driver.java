package main;

import utils.inventory;

import java.util.ArrayList;
import java.util.Scanner;

public class driver {

    private static final Scanner my_Input = new Scanner(System.in);

    //personal backpack
    private static final ArrayList<inventory> bag = new ArrayList<>();

    //creating the shop inventory
    private static ArrayList<inventory> shop = populateShopInventory();


    private static double coins = 120.00;

    public static void main(String[] args) {
        viewShopItems();
        System.out.println("Do you like to view your Inventory(y/n)");
        String viewingInventory = my_Input.nextLine().toLowerCase();
        switch (viewingInventory) {
            case "y":
                System.out.println("\nViewing Inventory\n");
                viewBag();
                break;
            case "n":
                System.out.println("Your Game continue...");
                //combact class continuessss..
        }
    }

    public static ArrayList<inventory> populateShopInventory() {
        shop = new ArrayList<inventory>();
        shop.add(new inventory("Long Sword", 8, 55.00, "Damage: 50, Speed: Medium, Critical: 10%, Durability: 120, Element: None"));
        shop.add(new inventory("Crossbow", 6, 45.00, "Damage: 40, Range: Long, Accuracy: 90%, Reload Speed: Slow"));
        shop.add(new inventory("Enchanted Book", 1, 75.00, "Damage: 20 (Magic), Mana Regen: +5/sec, Special: Random spell cast"));
        shop.add(new inventory("Spear", 4, 50.00, "Damage: 45, Range: Medium-Long, Piercing Power: +15%"));
        shop.add(new inventory("Kunai", 7, 60.00, "Damage: 30, Speed: Very Fast, Critical: 20%, Throw Distance: Short"));
        shop.add(new inventory("Bomb", 5, 5.00, "Damage: 70 (AoE), Radius: 3m, Element: Fire, Knockback: Strong"));
        return shop;
    }

    public static void viewShopItems() {
        System.out.println("===================================================================================================================");
        System.out.println("                                                   SHOP MENU 🗡️ ");
        System.out.println("===================================================================================================================");
        System.out.printf("| %-15s | %-3s | %-7s | %-120s | \n", "Item", "Qty", "Price", "Attribute");
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
        System.out.println("Here are the shop items: ");
        for (int i = 0; i < shop.size(); i++) {
            inventory item = shop.get(i);
            System.out.printf("| %-3d | %-15s | %-3d | %-7.2f | %-120s\n", (i + 1), item.getItemName(), item.getQuantity(), item.getPrice(), item.getAttribute().replace("\n", ""));
        }
        while (true) { //looping inside while loop unitll the switch conditons are truee
            System.out.print("Do you like to purchase weapons(y/n):  ");
            String choice = my_Input.nextLine().toLowerCase();

            switch (choice) {
                case "y":
                    purchaseitems();
                    return; //exit method after purchasing
                case "n":
                    System.out.println("You did not purchase anything.. But it's recommended to purchase your weapons.. Good luck!!");
                    return;
                default:
                    System.out.println("I am not sure what you are saying");
            }
        }
    }

    public static void purchaseitems() {
        while (true) {
            System.out.println("You have " + coins + " coins💰");
            System.out.print("What would you like to purchase(1-6) and 0 to exit: ");
            String selectedIteminput = my_Input.nextLine();
            int itemselect;
            try {
                itemselect = Integer.parseInt(selectedIteminput); // try to convert to int
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please read the instruction...");
                continue; // asks againnn
            }
            if (itemselect == 0) {
                System.out.println("Thank you for shopping");
                break;
            }
            //check valid section
            boolean found = false;
            double cost = 0.0;
            int index = 0;
            int itemQty = 0;

            for (int i = 0; i < shop.size(); i++) {
                if (itemselect == (i + 1)) {
                    found = true;
                    cost = shop.get(i).getPrice();
                    itemQty = shop.get(i).getQuantity();
                    index = i;
                    break;
                }
            }
            if (found && coins >= cost && itemQty > 0) {
                System.out.printf("Are you sure you want to purchase %s for %.2f coins? (y/n): ",
                        shop.get(index).getItemName(), cost);
                String confirm = my_Input.nextLine().toLowerCase();

                if (confirm.equals("y")) {
                    coins = coins - cost;
                    //Add to bag or increase quantity of if already there

                    boolean foundInBag = false;
                    for (inventory item : bag) {
                        if (item.getItemName().equals(shop.get(index).getItemName())) {
                            item.setQuantity(item.getQuantity() + 1);
                            foundInBag = true;
                            break;
                        }
                    }
                    if (!foundInBag) {
                        bag.add(new inventory(
                                shop.get(index).getItemName(),
                                1,
                                shop.get(index).getPrice(),
                                shop.get(index).getAttribute()
                        ));
                    }
                    // Reduce shop stock
                    shop.get(index).setQuantity(shop.get(index).getQuantity() - 1);
                    System.out.println("Purchase successful! Remaining coins: " + coins);
                } else {
                    System.out.println("Purchase cancelled");
                }
            } else if (!found) {
                System.out.println("Invalid selection. Please re-enter correctly");
            } else if (coins < cost) {
                System.out.println("Not enough coins!💰💰");
            } else if (itemQty <= 0) { //when items in shop is out of stock
                System.out.println("Item out of stock!");
            }

        }
    }

    public static void viewBag() {
        System.out.println("Your Inventory: ");
        System.out.printf("| %-15s | %-3s | %-7s | %-8s |\n", "Item", "Qty", "Total", "Attribute");
        System.out.println("-------------------------------------------------");
        for (int i = 0; i < bag.size(); i++) {
            inventory item = bag.get(i);
            double totalPrice = item.getQuantity() * item.getPrice();
            System.out.printf("| %-15s | %-3d | %-7.2f | %-8s  |\n", item.getItemName(), item.getQuantity(), totalPrice, item.getAttribute());
        }
    }
}
