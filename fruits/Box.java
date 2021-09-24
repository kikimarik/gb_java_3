package fruits;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Box <F extends Fruit> {
    private static final Float DEFAULT_WEIGHT = 0.5f; // Вес пустой коробки :D
    private ArrayList<F> fruits;

    public Box(F[] fruits) {
        this.fruits = new ArrayList<>();
        this.setFruits(new ArrayList<>(List.of(fruits)));
    }

    public ArrayList<F> getFruits()
    {
        return this.fruits;
    }

    public Float getWeight() {
        Iterator<F> it = fruits.iterator();
        Float weight = Box.DEFAULT_WEIGHT;
        while (it.hasNext()) {
            weight += it.next().getWeight();
        }
        return weight;
    }

    public Boolean compare(Box<?> box) {
        return this.getWeight().equals(box.getWeight());
    }

    public void takeFrom(Box<F> box) {
        this.setFruits(box.getFruits());
        box.removeFruits();
    }

    private void removeFruits() {
        this.fruits = new ArrayList<>();
    }

    private void setFruits(ArrayList<F> fruits) {
        this.fruits.addAll(fruits);
    }
}
