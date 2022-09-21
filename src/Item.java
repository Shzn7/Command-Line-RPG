public abstract class Item {
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
        damage = 20;
        usage = 2;
        isWeapon = true;
    }
}

class Pistol extends Item {
    public Pistol() {
        damage = 25;
        usage = 1;
        isWeapon = true;
    }
}

class Wand extends Item {
    public Wand() {
        damage = 25;
        usage = 1;
        isWeapon = true;
    }
}

class FireBall extends Item {
    public FireBall() {
        damage = 25;
        usage = 1;
        isWeapon = true;
    }
}

class Poison extends Item {
    public Poison() {
        damage = 25;
        usage = 1;
        isWeapon = true;
    }
}

class HealthPotion extends Item {
    public HealthPotion() {
        isWeapon = false;
        HPBoost = 25;
    }
}

class  SmallRock extends Item {
    public SmallRock() {
        damage = 10;
        usage = 5;
        isWeapon = true;
    }
}

class  Nunchucks extends Item {
    public Nunchucks() {
        damage = 15;
        usage = 2;
        isWeapon = true;
    }
}

class Punch extends Item {
    public Punch() {
        damage = 10;
        usage = 5;
        isWeapon = true;
    }
}

class  Kick extends Item {
    public Kick() {
        damage = 10;
        usage = 5;
        isWeapon = true;
    }
}

class Apple extends Item {

}
