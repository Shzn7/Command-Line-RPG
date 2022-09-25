import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * For future use in creating encounter sequences.
 */
public class Interactions {
    static List<Enemies> defaultEnemies = new ArrayList<>();
    static User user;
    static Enemies enemy;
    static Boolean playerDead = false;
    static Boolean enemyDead = false;

    public static void battle(User theUser){
        initialise();
        Random rand = new Random();
        int index = rand.nextInt(defaultEnemies.size()) ;
        enemy = defaultEnemies.get(index);
        user = theUser;

        System.out.println("You have come across an " + enemy.getCharacterName() + " who has " + enemy.getHP() + " HP. Prepare to battle!");

        while (!playerDead && !enemyDead) {

            System.out.println("----------------------------------------");
            playerVsEnemy();
            if (enemyDead) {break;}

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("----------------------------------------");
            enemyVsPlayer();
            if (playerDead) {break;}
        }



    }

    public static void playerVsEnemy(){

        List<Item> inventory = user.getInventory();

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

        System.out.println("Your attacks are: ");
        for(int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getUsage() > 0) {
                Item current = inventory.get(i);
                if (current.isWeapon) {
                    System.out.println(i + ". " + current + " with damage " + current.getDamage() + " and " + current.usage + " uses remaining.");
                } else {
                    System.out.println(i + ". " + current + " with HP boost " + current.getHPBoost() + ". ");
                }
            }
        }
        System.out.println("Which attack would you like to choose? ");

        Scanner read = new Scanner(System.in);
        String input = read.nextLine();

        for (int i = 0; i < input.length(); i++){
            if (!Character.isDigit(input.charAt(i))) {
                System.out.println("You fumbled your attack by inputting an invalid selection and have therefore given up your turn!");
                System.out.println("Try harder next time.");
                return;
            }
        }

        if (Integer.parseInt(input) < 0 || Integer.parseInt(input) > (inventory.size()-1)) {
            System.out.println("You fumbled your attack by inputting an invalid number and have therefore given up your turn!");
            System.out.println("Try harder next time.");
            return;
        }



        Item chosen = inventory.get(Integer.parseInt(input));
        System.out.println("----------------------------------------");

        if (chosen.isWeapon) {
            System.out.println("You hit "+ enemy.getCharacterName() + " for " + chosen.damage + " damage!");
            enemy.HP -= chosen.damage;
            user.decrementInv(chosen);

            if (enemy.HP <= 0) {
                System.out.println("You killed " + enemy.getCharacterName() + "! Well done!");
                enemyDead = true;
                return;
            }

            System.out.println("They now have " + enemy.getHP() + " HP.");
        } else {
            System.out.println("You used "+ chosen + " for " + chosen.getHPBoost() + " HP!");
            user.HP += chosen.getHPBoost();
            user.decrementInv(chosen);
            System.out.println("You now have " + user.getHP() + " HP.");
        }

    }

    public static void enemyVsPlayer(){
        List<Item> enInventory = enemy.getInventory();
        Item item;
        do {
            Random rand = new Random();
            item = enInventory.get(rand.nextInt(enInventory.size()));
        } while (item.getUsage() <= 0);

        System.out.println(enemy.getCharacterName() + " used the " +item + " for " + item.getDamage() + " HP.");
        enemy.decrementInv(item);

        user.HP -= item.getDamage();
        System.out.println("You now have " + user.getHP() + " HP.");

        if (user.getHP() <= 0) {
            playerDead = true;
            System.out.println("Oh no! " +enemy.getCharacterName() + " killed you!");
        }

    }

    public static void initialise(){

        playerDead = false;
        enemyDead = false;
        defaultEnemies = new ArrayList<>();
        defaultEnemies.add( new Enemies("Demon Monster", 50, new ArrayList<>(Arrays.asList(new EvilThoughts(), new Fire(), new Punch()))));
        defaultEnemies.add(new Enemies("Angry Zombie", 60, new ArrayList<>(Arrays.asList(new Moan(), new Headbutt(), new ZombieBite()))));
        defaultEnemies.add(new Enemies("Elon Musk", 30, new ArrayList<>(Arrays.asList(new Lawsuit(), new TwitterAttack(), new SelfDrivingCar()))));
    }

    public static void talkWithNPC(){
        System.out.println("under construction!");
    }

}
