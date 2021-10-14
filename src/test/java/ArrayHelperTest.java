import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ArrayHelperTest {
    private static Stream<Arguments> getPartAfterLastFourPositiveDataProvider() {
        List<Arguments> out = new ArrayList<>(List.of(
                Arguments.arguments(new int[]{5, 6, 7}, new int[]{1, 2, 3, 4, 5, 6, 7}),
                Arguments.arguments(new int[]{5, 7}, new int[]{4, 2, 3, 4, 4, 5, 7}),
                Arguments.arguments(new int[]{}, new int[]{4, 2, 3, 4, 5, 4})
        ));
        return out.stream();
    }

    private static Stream<Arguments> getPartAfterLastFourNegativeDataProvider() {
        List<Arguments> out = new ArrayList<>(List.of(
                Arguments.arguments(RuntimeException.class, new int[]{1, 2, 3}),
                Arguments.arguments(RuntimeException.class, new int[]{2}),
                Arguments.arguments(RuntimeException.class, new int[]{5, 9, 88})
        ));
        return out.stream();
    }

    private static Stream<Arguments> hasOneAndFourDataProvider() {
        List<Arguments> out = new ArrayList<>(List.of(
                Arguments.arguments(true, new int[]{1, 2, 3, 4}), // 1 and 4
                Arguments.arguments(false, new int[]{1, 2, 3}), // only 1
                Arguments.arguments(false, new int[]{2, 3, 4}), // only 4
                Arguments.arguments(false, new int[]{2, 3}) // other
        ));
        return out.stream();
    }

    @ParameterizedTest
    @MethodSource("getPartAfterLastFourPositiveDataProvider")
    public void getPartAfterLastFourPositive(int[] expected, int[] input) {
        Assertions.assertArrayEquals(expected, ArrayHelper.getPartAfterLastFour(input));
    }

    @ParameterizedTest
    @MethodSource("getPartAfterLastFourNegativeDataProvider")
    public void getPartAfterLastFourNegative(Class<Throwable> expected, int[] input) {
        Assertions.assertThrows(expected, () -> ArrayHelper.getPartAfterLastFour(input));
    }

    @ParameterizedTest
    @MethodSource("hasOneAndFourDataProvider")
    public void hasOneAndFour(boolean expected, int[] input) {
        Assertions.assertEquals(expected, ArrayHelper.hasOneAndFour(input));
    }
}
