package fruits;

import java.util.ArrayList;
import java.util.Iterator;

public class Box <F extends ArrayList<Fruit>> {
    private static final Float DEFAULT_WEIGHT = 0.5f; // Вес пустой коробки :D
    private F fruits = (F) new ArrayList<Fruit>();
    private String type;

    public Box(F fruits) {
        this.type = "EMPTY";
        this.setFruits(fruits);
    }

    public F getFruits()
    {
        return this.fruits;
    }

    public void addFruit(Fruit fruit) {
        switch (this.type) {
            case "EMPTY":
                if (fruit instanceof Apple) {
                    this.type = "APPLE";
                } else if (fruit instanceof Orange) {
                    this.type = "ORANGE";
                }
                break;
            case "ORANGE":
                if (fruit instanceof Apple) {
                    throw new RuntimeException("Trying to add incompatible fruit");
                }
                break;
            case "APPLE":
                if (fruit instanceof Orange) {
                    throw new RuntimeException("Trying to add incompatible fruit");
                }
                break;
        }
        this.fruits.add(fruit);
    }

    public Float getWeight() {
        Iterator<Fruit> it = fruits.iterator();
        Float weight = Box.DEFAULT_WEIGHT;
        while (it.hasNext()) {
            weight += it.next().getWeight();
        }
        return weight;
    }

    public Boolean compare(Box<F> box) {
        return this.getWeight().equals(box.getWeight());
    }

    public void takeFrom(Box<F> box) {
        this.setFruits(box.getFruits());
        box.removeFruits();
    }

    private void removeFruits() {
        this.fruits = (F) new ArrayList<Fruit>();
    }

    private void setFruits(F fruits) {
        for (Fruit fruit : fruits) {
            this.addFruit(fruit);
        }
    }
}
