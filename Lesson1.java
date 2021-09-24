import fruits.Apple;
import fruits.Box;
import fruits.Orange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lesson1 {
    public static void main(String[] args) {
        ArrayList<String> elements = new ArrayList<>();
        elements.add("str1");
        elements.add("str2");
        System.out.println(replaceTwoArrayElements(elements));

        Integer[] integers = new Integer[3];
        for (int i = 0; i < integers.length; i++) {
            integers[i] = i;
        }
        ArrayList<Integer> list = toArrayList(integers);
        System.out.println(list);

        Apple[] apples = {
                new Apple(),
                new Apple(),
        };
        Orange[] oranges = {
                new Orange(),
                new Orange(),
        };
        Apple[] apples2 = {
                new Apple(),
                new Apple(),
                new Apple(),
        };
        Orange[] oranges2 = {
                new Orange(),
                new Orange(),
        };
        Box<Apple> box1 = new Box<>(apples);
        Box<Orange> box2 = new Box<>(oranges);
        System.out.println(box1.compare(box2)); // false
        Box<Apple> box3 = new Box<>(apples2);
        Box<Orange> box4 = new Box<>(oranges2);
        System.out.println(box3.compare(box4)); // true
        System.out.println(box1.getWeight());
        System.out.println(box3.getWeight());
        box1.takeFrom(box3);
        System.out.println(box1.getWeight());
        System.out.println(box3.getWeight());
    }
    static <L extends List<E>, E> L replaceTwoArrayElements(L list) {
        E firstElement = list.get(0);
        E secondElement = list.get(1);
        list.set(0, secondElement);
        list.set(1, firstElement);
        return list;
    }

    static <E> ArrayList<E> toArrayList(E[] elements) {
        return new ArrayList<>(Arrays.asList(elements));
    }
}
