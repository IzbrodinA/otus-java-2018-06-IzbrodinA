package ru.otus.I041;

import ru.otus.I041.error.AssertionError;

public class Assert {

    protected Assert(){}

    public static void assertEquals(Object expexted, Object actual){
        assertEquals(null,expexted, actual);
    }

    private static void assertEquals(String message, Object expected, Object actual) {
        if (equalsRegardingNull(expected, actual)) {
            System.out.println("The matching is right");//check
            return;
        } else {
            message= message == null ? "" : message;
            throw new AssertionError(message);
        }
    }

    private static boolean equalsRegardingNull(Object expected, Object actual) {
        if (expected == null) {
            return actual == null;
        }

        return isEquals(expected, actual);
    }

    private static boolean isEquals(Object expected, Object actual) {
        return expected.equals(actual);
    }
}
