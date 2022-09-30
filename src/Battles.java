import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Battles.java class is used to house the classes for the possible interactions a player could have on their journey along
 * the game string. This includes the fight sequence or battles, and the NPC encounters.
 *
 * @author Alex Basserabie
 */

public class Battles {
    /**
     * Variables to store the global variables required for the battle class, including the flags for if the user/enemy has died, the user
     * and enemy and the default enemy list.
     */

    static List<Enemies> defaultEnemies = new ArrayList<>();
    static User user;
    static Enemies enemy;
    static Boolean playerDead = false;
    static Boolean enemyDead = false;
    static GamemodesEnum gamemode;

    //Constructor used for testing.
    public Battles(User user) {
        this.user = user;
        initialise();
    }

    /***
     * This method is called within the game class and allows the user to enter the 'battle' sequence. In this sequence, the user
     * and enemy battle to the death, utilising additional classes to facilitate the fight logic.
     * @author Alex Basserabie
     * @param theUser, given by game.
     */
    public static void battle(User theUser){
        //Calls the initialise function to set the default enemies and player/enemy dead states.
        user = theUser;
        initialise();

        System.out.println("You have come across an " + enemy.getCharacterName() + " who has " + enemy.getHP() + " HP. Prepare to battle!");

        //Loops until one character dies.
        while (!playerDead && !enemyDead) {

            System.out.println(Game.DEFAULT_LINE_BREAK);
            playerVsEnemy();
            if (enemyDead) {break;}

            //Adds some suspense to the game by creating a pause between the user and enemy's turn.
            try {
                TimeUnit.MILLISECONDS.sleep(2500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Game.DEFAULT_LINE_BREAK);
            enemyVsPlayer();
            if (playerDead) {break;}
        }
    }

    /***
     * Initialises the global variables and sets the default enemies
     * @author Alex Basserabie
     */
    public static void initialise(){
        playerDead = false;
        enemyDead = false;
        defaultEnemies = new ArrayList<>();
        defaultEnemies.add( new Enemies("Demon Monster", 50, new ArrayList<>(Arrays.asList(new EvilThoughts(), new FireBall(), new Punch()))));
        defaultEnemies.add(new Enemies("Angry Zombie", 60, new ArrayList<>(Arrays.asList(new Moan(), new Headbutt(), new ZombieBite()))));
        defaultEnemies.add(new Enemies("Elon Musk", 30, new ArrayList<>(Arrays.asList(new Lawsuit(), new TwitterAttack(), new SelfDrivingCar()))));

        //Retrieves a random enemy from the default list.
        Random rand = new Random();
        int index = rand.nextInt(defaultEnemies.size()) ;
        enemy = defaultEnemies.get(index);
        gamemode = user.getGamemode();
    }


    /***
     * The method for gameplay when it's the player's turn. This includes calling the scanner class to get inputs from
     * the user, and printing the relevant statements during gameplay.
     * @author Alex Basserabie
     */
    public static void playerVsEnemy(){

        List<Item> inventory = user.getInventory();

        //Instructions for checking if the player's inventory doesn't contain any weapons. Includes a good joke.
        if (inventory.size() == 0) {
            System.out.println("Gulp, you stand in-front of " + enemy.getCharacterName() + " with no remaining weapons.");
            System.out.println("Your last resort is to pray to the developer gods for help.");
            System.out.println("Enter your prayers: ");

            Scanner read = new Scanner(System.in);
            String input = read.nextLine();

            if (!Objects.equals(input, "")) {
                user.inventory.add(new DeveloperDonation());
            } else {
                playerDead = true;
                return;
            }
        }

        //Shows the player the attacks available to them.
        System.out.println("Your attacks are: ");
        for(int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getUsage() > 0) {
                Item current = inventory.get(i);
                if (current.isWeapon) {
                    System.out.println(i + ". " + current + " with damage " + current.getDamage() +
                                    (gamemode == GamemodesEnum.SURVIVAL ?  " and " + current.usage + " uses remaining." : ".")
                           );
                } else {
                    System.out.println(i + ". " + current + " with HP boost " + current.getHPBoost() + ". ");
                }
            }
        }
        System.out.println("Which attack would you like to choose? ");

        Scanner read = new Scanner(System.in);
        String input = read.nextLine();
        if (!parseInput(input)) return;

        Item chosen = inventory.get(Integer.parseInt(input));
        System.out.println(Game.DEFAULT_LINE_BREAK);

        //There are two different dynamics, depending on whether the user chose to use a weapon or healing item.
        if (chosen.isWeapon) {
            System.out.println("You hit "+ enemy.getCharacterName() + " for " + chosen.damage + " damage!");
            enemy.HP -= chosen.damage;
            if (gamemode == GamemodesEnum.SURVIVAL) { user.decrementInv(chosen);}

            if (enemy.HP <= 0) {
                System.out.println("You killed " + enemy.getCharacterName() + "! Well done!");
                enemyDead = true;
                return;
            }

            System.out.println("They now have " + enemy.getHP() + " HP.");
        } else {
            System.out.println("You used "+ chosen + " for " + chosen.getHPBoost() + " HP!");
            user.HP += chosen.getHPBoost();
            if (gamemode == GamemodesEnum.SURVIVAL) { user.decrementInv(chosen);}
            System.out.println("You now have " + user.getHP() + " HP.");
        }

    }

    /***
     * The method for determining if a number input is valid
     * @author Alex Basserabie
     */
    public static boolean parseInput(String input){
        List<Item> inventory = user.getInventory();
        //Determines if the input is just numerical
        for (int i = 0; i < input.length(); i++){
            if (!Character.isDigit(input.charAt(i))) {
                System.out.println("You fumbled your attack by inputting an invalid selection and have therefore given up your turn!");
                System.out.println("Try harder next time.");
                return false;
            }
        }

        //Determines if the input falls within the accepted range
        if (Integer.parseInt(input) < 0 || Integer.parseInt(input) > (inventory.size()-1)) {
            System.out.println("You fumbled your attack by inputting an invalid number and have therefore given up your turn!");
            System.out.println("Try harder next time.");
            return false;
        }

        return true;
    }

    /***
     * The method for gameplay when it's the enemy's turn. This uses a random enemy attack to try and hurt the player,
     * and includes the death message for the player if the enemy wins.
     * @author Alex Basserabie
     */
    public static void enemyVsPlayer(){
        List<Item> enInventory = enemy.getInventory();
        Item item;
        //Gets a random item from the enemy's inventory.
        do {
            Random rand = new Random();
            item = enInventory.get(rand.nextInt(enInventory.size()));
        } while (item.getUsage() <= 0);

        //Attacks the player with said item.
        System.out.println(enemy.getCharacterName() + " used the " +item + " for " + item.getDamage() + " HP.");
        enemy.decrementInv(item);

        user.HP -= item.getDamage();
        System.out.println("You now have " + user.getHP() + " HP.");

        if (user.getHP() <= 0) {
            playerDead = true;
            System.out.println("Oh no! " +enemy.getCharacterName() + " killed you!");
        }

    }


}
