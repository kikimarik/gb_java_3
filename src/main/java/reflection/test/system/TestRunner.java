package reflection.test.system;

import reflection.test.annotation.AfterSuite;
import reflection.test.annotation.BeforeSuite;
import reflection.test.annotation.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.PriorityQueue;

public class TestRunner {
    public static void start(Class<?> c) throws InstantiationException,
                                                IllegalAccessException,
                                                NoSuchMethodException,
                                                InvocationTargetException {
        Method[] methods = c.getMethods();
        PriorityQueue<Method> queue = new PriorityQueue<>(methods.length, (o1, o2) -> {
            if (!(o1.isAnnotationPresent(Test.class) && o2.isAnnotationPresent(Test.class))) {
                throw new Error("Methods in queue must be declared with " + Test.class);
            }
            if (o1.getAnnotation(Test.class).priority() > 10 || o2.getAnnotation(Test.class).priority() < 1 ||
                    o2.getAnnotation(Test.class).priority() > 10 || o1.getAnnotation(Test.class).priority() < 1) {
                throw new Error("Priority of " + Test.class + " must be in range 1..10.");
            }
            int w1;
            int w2;
            if (o1.isAnnotationPresent(BeforeSuite.class)) {
                w1 = 0;
            } else if (o1.isAnnotationPresent(AfterSuite.class)) {
                w1 = 11;
            } else {
                w1 = o1.getAnnotation(Test.class).priority();
            }
            if (o2.isAnnotationPresent(BeforeSuite.class)) {
                w2 = 0;
            } else if (o2.isAnnotationPresent(AfterSuite.class)) {
                w2 = 11;
            } else {
                w2 = o2.getAnnotation(Test.class).priority();
            }
            if ((w1 == 11 && w2 == 11) || (w1 == 0 && w2 == 0)) {
                throw new RuntimeException(
                        BeforeSuite.class + " or " + AfterSuite.class + " allowed only one time."
                );
            }
            return w1 - w2;
        });
        for (Method method : methods) {
            if (!method.isAnnotationPresent(Test.class)) {
                continue;
            }
            queue.add(method);
        }
        Object test = c.getConstructor().newInstance();

        while (!queue.isEmpty()) {
            Method method = queue.remove();
            System.out.print("RUN " + method.getName() + ": ");
            method.invoke(test);
        }
    }

    public static void start(String classname) throws InvocationTargetException,
            InstantiationException,
            IllegalAccessException,
            NoSuchMethodException,
            ClassNotFoundException {
        start(Class.forName(classname));
    }
}
