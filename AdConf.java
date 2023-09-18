import java.util.Scanner;
/**
 * Write a description of class AdConf here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class AdConf
{
    private boolean configuring = true; 
    private Adventurer play = null;
    private Backpack pack = null;
    private String name = "user";
    Scanner scanner = new Scanner(System.in).useDelimiter("~");
    public AdConf(Adventurer player){
        play = player;
        name = player.getName();
        pack = player.getBackpack();
        
    }

    public Adventurer Configuring(){
        if (configuring == true){
            System.out.println("What would you like to do?");
            System.out.println("1. Look at stats \n2. Look at backpack \n3. Stop");
            String looking = scanner.nextLine();
            if (looking.toLowerCase().equals("look at stats") || 
            looking.toLowerCase().equals("stats") || 
            looking.toLowerCase().equals("1")){
                stats();
            } else if (looking.toLowerCase().equals("look at backpack") || 
            looking.toLowerCase().equals("backpack") || 
            looking.toLowerCase().equals("2")){
                pack();
            } else if (looking.toLowerCase().equals("stop") || looking.toLowerCase().equals("3")){
                configuring = false;
                return play;
            }
            
        }
        return play;
    }

    public void stats(){
        System.out.print("Name: " + name);
        System.out.println("  Coins: " + play.getMoney());
        System.out.print("Health: " + play.getHealth());
        System.out.print("  Defense: " + play.getDefense());
        System.out.println("  Attack: " + play.getAttack());
        System.out.println("Equiped: " + play.getHand() + " in hands");
        System.out.println("Equiped: " + play.getArm() + " on their arm");
        System.out.println("Equiped: " + play.getChest() + " on their chest");
        System.out.println("Equiped: " + play.getLeg() + " on their leg area");
        System.out.println("-------------------------");
        continueConf();
    }

    public Adventurer continueConf(){
        System.out.println("Would you like to continue configuring?");
        String cont = scanner.nextLine();
        if (cont.toLowerCase().equals("yes") || cont.toLowerCase().equals("y")){
            return Configuring();
        } else if (cont.toLowerCase().equals("no") || cont.toLowerCase().equals("n")){
            configuring = false;
            System.out.println("Alright! Stopping configurations...");
        }
        return play;
    }

    public Adventurer pack(){
        if (configuring == true){
            System.out.println("Here are the items in your backpack:");
            for (Item i : pack.getInventory()){
                System.out.print(" " + i.getName() + ",");
            }
            System.out.println("What would you like to use?");
            String obj = scanner.nextLine();
            for (Item i : pack.getInventory()){
                if (obj.toLowerCase().equals(i.getName())){
                    if (i instanceof Consumable){
                        play.consume((Consumable)i);
                    } else if (i instanceof Weapon){
                        play.equip((Weapon)i);
                    } else if (i instanceof Defense){
                        play.wear((Defense)i);
                    }
                }
            }
            continueConf();
        }
        return play;
    }
    
}
