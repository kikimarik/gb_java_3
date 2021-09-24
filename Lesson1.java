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
