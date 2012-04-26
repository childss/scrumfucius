package com.example.classproject;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
    public void testCanReadInputFromStream() {
        String inputData = "something";
        ByteArrayInputStream input = new ByteArrayInputStream(inputData.getBytes());

        try {
            String result = HelloWorld.readInput(input);
            assertEquals("something", result);
        } catch (Exception e) {
            fail("exception" + e.getMessage());
        }

    }

    @Test
    public void testPrintsCorrectStory() {
        String inputData = "1";
        ByteArrayInputStream input = new ByteArrayInputStream(inputData.getBytes());
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        try {
            HelloWorld.runProgram("story.txt", input);
            assertTrue(output.toString().endsWith("story 1\n"));
        } catch (IOException e) {
            fail("exception: " + e.getMessage());
        }
    }
}
