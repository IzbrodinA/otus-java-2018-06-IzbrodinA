package ru.otus.I041;

import ru.otus.I041.annotations.After;
import ru.otus.I041.annotations.Before;
import ru.otus.I041.annotations.Test;

public class AnnotationsTests {


    public AnnotationsTests() {
        System.out.println("Call of the constructor");
    }

    @Before
    public void before(){
        System.out.println("Before");
    }

    @Test
    public void testOne(){
        System.out.println("testOne");
    }

    @Test
    public void testRight(){
        System.out.println("testRight");
        Assert.assertEquals( "1", "1");

    }

    @Test
    public void testFailedOne(){
        System.out.println("testFailedOne");
        Assert.assertEquals("2", "1");
    }

    @Test
    public void testFailedTwo(){
        System.out.println("testFailedTwo");
        Assert.assertEquals("2", "1");
    }

    @After
    public void after(){
        System.out.println("After");
    }

}

