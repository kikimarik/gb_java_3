import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class ArrayHelper {
    public static int[] getPartAfterLastFour(int[] numbers) {
        int lastFourIndex = List.of(IntStream.of(numbers).boxed().toArray()).lastIndexOf(4);
        if (lastFourIndex == -1) {
            throw new RuntimeException("Input array has not elements equals 4");
        }
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = lastFourIndex + 1; i < numbers.length; i++) {
            result.add(numbers[i]);
        }
        return result.stream().mapToInt(i->i).toArray();
    }

    public static boolean hasOneAndFour(int[] numbers) {
        boolean hasOne = false;
        boolean hasFour = false;
        for (int number : numbers) {
            if (number == 1) {
                hasOne = true;
            } else if (number == 4) {
                hasFour = true;
            }
        }
        return hasOne && hasFour;
    }
}
