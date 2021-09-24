import java.util.ArrayList;
import java.util.List;

public class Lesson1 {
    public static void main(String[] args) {
        ArrayList<String> elements = new ArrayList<>();
        elements.add("str1");
        elements.add("str2");
        System.out.println(replaceTwoArrayElements(elements));
    }
    static <L extends List<E>, E> L replaceTwoArrayElements(L list) {
        E firstElement = list.get(0);
        E secondElement = list.get(1);
        list.set(0, secondElement);
        list.set(1, firstElement);
        return list;
    }
}
