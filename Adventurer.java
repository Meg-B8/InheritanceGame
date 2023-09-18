
/**
 * Write a description of class Adventurer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Adventurer extends Character
{
    //since every adventurer has a backpack: 
    Backpack backpack = new Backpack();
    int money;
    public Adventurer(String name, int health, int defense, int attack, int money){
        //make the person - health stat, defense stat, attack stat
        super(name, health, defense, attack);
        this.money = money;
    }
    //getting variables
    public Backpack getBackpack(){
        return backpack;
    }
    public int getMoney(){
        return money;
    }
    public void addMoney(int amt){
        money += amt; 
    }
    //changes the variables (I forgot the vocab word)
    public void consume(Consumable item){
        if(item instanceof Food){
            health += ((Food)item).getHealth();
            System.out.println("You consumed " + item.getName() + 
            ", it rose your health by " + ((Food)item).getHealth());
            if (item instanceof QuestionableStew){
                if (health == 0){
                    System.out.println("You ate too much questionable Stew! You died!");
                    System.exit(0);
                }
            }
        } else if (item instanceof DefBoost){
            defense += ((DefBoost)item).getDefense();
            System.out.println("You consumed " + item.getName() + 
            ", it rose your defense by " + ((DefBoost)item).getDefense());
        } else if (item instanceof AttackBoost){
            attack += ((AttackBoost)item).getAttack();
            System.out.println("You consumed " + item.getName() + 
            ", it rose your attack by " + ((AttackBoost)item).getAttack());
        } else if (item instanceof Mana){
            if (handSlot instanceof Magic){
                ((Magic)handSlot).addMana(((Mana)item).getManaPotion());
                System.out.println("You consumed " + item.getName() + 
            ", it rose your weapon mana by " + ((Mana)item).getManaPotion());
            } else {
                System.out.println("Please equip a magic item before using!");
            }
        } else if (item instanceof Amo){
            if (handSlot instanceof Gun){
                ((Gun)handSlot).addAmo(((Amo)item).getAmoPack());
                System.out.println("You consumed " + item.getName() + 
            ", it rose your weapon's amo by " + ((Amo)item).getAmoPack());
            } else {
                System.out.println("Please equip a gun before using!");
            }
        }
    }

    public void wear(Defense item){
        if (item instanceof Armor){
            defense +=  ((Armor)item).getDefense();
            backpack.useItem(item);
        } else if (item instanceof Shield){
            if(armSlot == null){
                defense += ((Shield)item).getDefense();
                backpack.useItem(item);
                armSlot = item;
            } else {
                unequip((Armor)item);
                defense += ((Shield)item).getDefense();
                backpack.useItem(item);
                armSlot = item;
            }
        }
    }

    public void equip(Weapon item){
        if (handSlot == null){
            attack += ((Weapon)item).getDamage();
            backpack.useItem(item);
            handSlot = item;
        } else {
            unequip((Weapon)handSlot);
            attack += ((Weapon)item).getDamage();
            backpack.useItem(item);
            handSlot = item;
        }
    }

    public void unequip(Item item){
        if (item instanceof Weapon){
            attack -= ((Weapon)item).getDamage();
            handSlot = null;
        } else if (item instanceof Defense){
            defense -= ((Defense)item).getDefense();
            armSlot = null;
        }
        backpack.storeItem(item);
    }
}
