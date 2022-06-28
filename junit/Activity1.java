package Activities;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Activity1 {
    //Test Fixtures
    static ArrayList<String> list;

    //Initialization of ArrayList
    @BeforeEach
    public void setUp(){
        list = new ArrayList<String>();
        list.add("zero"); // element @ index 0
        list.add("one"); // element @ index 1
    }

    //To Test Insert operation
    @Test
    public void insertTest(){
        // Assertion for array:list  size
        assertEquals(2,list.size(),"Wrong size");

        //Adding a new element to the array:list
        list.add(2, "two");

        //Assertion after adding new element
        assertEquals(3,list.size(),"Wrong size");

        // Assertions for individual elements
        assertEquals("zero",list.get(0),"Wrong element");
        assertEquals("one",list.get(1),"Wrong element");
        assertEquals("two",list.get(2),"Wrong element");

        // print list
        System.out.println("Inside InsertTest:");
        System.out.println(list);

    }

    // Test Method  to test  the replace operation

    @Test
    public void replaceTest(){
        // replace index 1 element with new element value
        list.set(1,"alpha");

        //Assertion for size of list
        assertEquals(2,list.size(),"Wrong size");

        // Assertion for individual elements of the array list

        assertEquals("zero", list.get(0), "Wrong element");
        assertEquals("alpha", list.get(1), "Wrong element");

        // print list
        System.out.println("Inside replaceTest:");
        System.out.println(list);
    }


}
