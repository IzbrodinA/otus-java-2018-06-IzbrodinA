package ru.otus.I041;

import ru.otus.I041.annotations.After;
import ru.otus.I041.annotations.Before;
import ru.otus.I041.annotations.Test;

import java.lang.reflect.Method;

class MyCoreFramework {


    void run(Class<?> annotationsTests) {
        for (Method aMethodsTest : annotationsTests.getDeclaredMethods()) {
            if (aMethodsTest.isAnnotationPresent(Test.class)) {
                test(annotationsTests, aMethodsTest);
            }
        }
    }

    private void test(Class<?> annotationsTests, Method m) {
        Object testClass = ReflectionHelper.instantiate(annotationsTests);
        if (testClass == null) {
            System.out.println("Can't constructor class");
        } else {
            Method[] methodsBefore = ReflectionHelper.getMethods(testClass, Before.class);
            Method[] methodsAfter = ReflectionHelper.getMethods(testClass, After.class);

            ReflectionHelper.callMethods(testClass, methodsBefore != null ? methodsBefore : new Method[0]);
            ReflectionHelper.callMethod(testClass, m.getName(), (Object[]) null);
            ReflectionHelper.callMethods(testClass, methodsAfter != null ? methodsAfter : new Method[0]);

        }

    }

}




