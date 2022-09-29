import java.util.ArrayList;
import java.util.Objects;

public abstract class Item {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return damage == item.damage && HPBoost == item.HPBoost && usage == item.usage && isWeapon == item.isWeapon && Objects.equals(name, item.name);
    }

    @Override
    public String toString() {
        return name;
    }
    public String name;
    public int damage;
    public int HPBoost;
    public int usage;
    public boolean isWeapon;

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHPBoost() {
        return HPBoost;
    }

    public void setHPBoost(int HPBoost) {
        this.HPBoost = HPBoost;
    }

    public int getUsage() {
        return usage;
    }

    public void setUsage(int usage) {
        this.usage = usage;
    }

    public boolean isWeapon() {
        return isWeapon;
    }

    public void setWeapon(boolean weapon) {
        isWeapon = weapon;
    }
}

class Sword extends Item {
    public Sword() {
        name = "Sword";
        damage = 20;
        usage = 2;
        isWeapon = true;
    }
}

class Pistol extends Item {
    public Pistol() {
        name = "Pistol";
        damage = 25;
        usage = 1;
        isWeapon = true;
    }
}

class Wand extends Item {
    public Wand() {
        name = "Wand";
        damage = 10;
        usage = 5;
        isWeapon = true;
    }
}

class FireBall extends Item {
    public FireBall() {
        name = "Fire Ball";
        damage = 25;
        usage = 1;
        isWeapon = true;
    }
}

class Poison extends Item {
    public Poison() {
        name = "Poison";
        damage = 25;
        usage = 1;
        isWeapon = true;
    }
}

class HealthPotion extends Item {
    public HealthPotion() {
        name = "Health Potion";
        isWeapon = false;
        usage = 1;
        HPBoost = 25;
    }
}

class  SmallRock extends Item {
    public SmallRock() {
        name = "Small Rock";
        damage = 10;
        usage = 5;
        isWeapon = true;
    }
}

class  Nunchucks extends Item {
    public Nunchucks() {
        name = "Nunchucks";
        damage = 15;
        usage = 2;
        isWeapon = true;
    }
}

class Punch extends Item {
    public Punch() {
        name = "Punch";
        damage = 10;
        usage = 8;
        isWeapon = true;
    }
}

class  Kick extends Item {
    public Kick() {
        name = "Kick";
        damage = 10;
        usage = 8;
        isWeapon = true;
    }
}

class  StockMarket extends Item {
    public StockMarket() {
        name = "Stock Market";
        damage = 20;
        usage = 3;
        isWeapon = true;
    }
}

class Apple extends Item {
    public Apple() {
        name = "Apple";
        usage = 1;
        isWeapon = false;
        HPBoost = 15;
    }
}


class EvilThoughts extends Item {
    public EvilThoughts() {
        name = "Bad Thoughts";
        damage = 15;
        usage = 2;
        isWeapon = true;
    }
}


class SelfDrivingCar extends Item {
    public SelfDrivingCar() {
        name = "Self-driving Car Army Attack";
        damage = 30;
        usage = 1;
        isWeapon = true;
    }
}

class TwitterAttack extends Item {
    public TwitterAttack() {
        name = "Twitter Attack";
        damage = 10;
        usage = 8;
        isWeapon = true;
    }
}

class Lawsuit extends Item {
    public Lawsuit() {
        name = "Lawsuit";
        damage = 15;
        usage = 3;
        isWeapon = true;
    }
}

class ZombieBite extends Item {
    public ZombieBite() {
        name = "Bite";
        damage = 15;
        usage = 3;
        isWeapon = true;
    }
}

class Moan extends Item {
    public Moan() {
        name = "Moan";
        damage = 10;
        usage = 8;
        isWeapon = true;
    }
}

class Headbutt extends Item {
    public Headbutt() {
        name = "Headbutt";
        damage = 20;
        usage = 2;
        isWeapon = true;
    }
}

class DeveloperDonation extends Item {
    public DeveloperDonation() {
        name = "Developer-Donated Smack";
        damage = 5;
        usage = 8;
        isWeapon = true;
    }
}