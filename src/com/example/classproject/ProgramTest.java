package com.example.classproject;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ProgramTest {

    @Test
    public void testThrowsFileNotFound() {
        try {
            Program program = new Program("story");

            program.readFile("a file that does not exist");

            fail("It didn't throw!");
        } catch (FileNotFoundException e) {

        } catch (Exception e) {
            fail("Unexpected: " + e.getMessage());
        }
    }

    @Test
    public void testReadsATextFile() {
        try {
            Program program = new Program("story");
            String output = program.readFile("dummy.txt");
            assertEquals("this is some example text"+System.getProperty("line.separator")+"line 2"+System.getProperty("line.separator"), output);
        } catch (Exception e) {
            fail("exception!: " + e.getMessage());
        }

    }
}
