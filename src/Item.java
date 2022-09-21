public abstract class Item {
    @Override
    public String toString() {
        return getClass().getName();
    }
}

class Sword extends Item {

}

class HealthPotion extends Item {

}

class  SmallRock extends Item {

}

class Apple extends Item {

}