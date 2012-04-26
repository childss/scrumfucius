package com.example.classproject;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class HelloWorldTest {

    @Test
    public void testThrowsFileNotFound() {
        try {
            File textFile = new File("a file that does not exist");

            HelloWorld.readFile(textFile);

            fail("It didn't throw!");
        } catch (FileNotFoundException e) {

        } catch (Exception e) {
            fail("Unexpected: " + e.getMessage());
        }
    }

    @Test
    public void testReadsATextFile() {
        File textFile = new File("dummy.txt");
        try {
            String output = HelloWorld.readFile(textFile);
            assertEquals("this is some example text", output);
        } catch (Exception e) {
            fail("exception!: " + e.getMessage());
        }

    }

    @Test
    public void testMainPrintsOutFile() {
        String[] args = new String[1];
        args[0] = "dummy.txt";
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        String inputData = "something";
        ByteArrayInputStream input = new ByteArrayInputStream(inputData.getBytes());

        try {
            HelloWorld.runProgram(args[0], input);
            assertEquals("this is some example text\n", output.toString());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void canReadInputFromStream() {
        String inputData = "something";
        ByteArrayInputStream input = new ByteArrayInputStream(inputData.getBytes());

        try {
            String result = HelloWorld.readInput(input);
            assertEquals("something", result);
        } catch (Exception e) {
            fail("exception" + e.getMessage());
        }

    }
}
