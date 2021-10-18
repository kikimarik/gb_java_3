import reflection.test.annotation.AfterSuite;
import reflection.test.annotation.BeforeSuite;
import reflection.test.annotation.Test;
import reflection.test.system.TestRunner;

import java.lang.reflect.InvocationTargetException;

public class SimpleTest {
    public static void main(String[] args) throws InvocationTargetException,
                                                    InstantiationException,
                                                    IllegalAccessException,
                                                    NoSuchMethodException {
        TestRunner.start(SimpleTest.class);
    }

    @Test(priority = 4)
    public void sumTest() {
        int expected = 4;
        int result = 2 + 2;
        System.out.println(result == expected);
    }

    @Test(priority = 2)
    @AfterSuite
    public void sumFailTest() {
        int expected = 4;
        int result = 2 + 1;
        System.out.println(result == expected);
    }

    @Test(priority = 9)
    @BeforeSuite
    public void concatTest() {
        String expected = "Hello, world!";
        String result = "Hello" + ", " + "world!";
        System.out.println(result.equals(expected));
    }

    @Test(priority = 1)
    public void concatFailTest() {
        String expected = "Hello, world!";
        String result = "Hello" + ", " + "friend!";
        System.out.println(result.equals(expected));
    }
}
