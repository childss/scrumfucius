package com.example.classproject;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class HelloWorldTest {

    @Test
    public void testCanReadInputFromStream() {
        ByteArrayInputStream input = getInputStream("something");

        try {
            String result = HelloWorld.readInput(input);
            assertEquals("something", result);
        } catch (Exception e) {
            fail("exception" + e.getMessage());
        }
    }

    @Test
    public void testPrintsCorrectStory() {
        ByteArrayInputStream input = getInputStream("1");
        String text = null;
        try {
            text = readFile("test-stories/1.1.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        try {
            HelloWorld.runProgram("dummy.txt", "test-stories", input);
            String outputString = output.toString();
            boolean contains = outputString.contains(text);
            assertTrue(contains);
        } catch (IOException e) {
//            fail("exception: " + e.getMessage());
            e.printStackTrace();
            fail("exception");
        }
    }

    private String readFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
        String line;
        StringBuilder builder = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            builder.append(line + "\n");
        }

        return builder.toString();
    }
    @Test
    public void testPseudoRandomSelectionOfFiles() {
        String goodInput = "1";
        boolean evenOption = false;
        boolean oddOption = false;
        for(int i=1; i<51; i++)
        {
            String filename = HelloWorld.getPseudoRandomFilename(goodInput);
            if (filename.equals("1.1.txt")  )
            {
                oddOption = true;
            }
            if (filename.equals("1.2.txt")  )
            {
                evenOption = true;
            }

        }
        assertTrue(oddOption && evenOption);

    }

    @Test
    public void testValidateReturnsTrueIfOk() {
        String goodInput = "1";
        assertTrue(HelloWorld.validateInput(goodInput));
    }

    @Test
    public void testValidateDoesNotThrowForNonNumeric() {
        String badInput = "hello";
        assertFalse(HelloWorld.validateInput(badInput));
    }

    @Test
    public void testValidateReturnsFalseIfOutOfStoryRange() {
        String badInput = "5";
        assertFalse(HelloWorld.validateInput(badInput));
    }

    @Test
    public void runProgramPrintsAnErrorIfUserInputDoesNotValidate() {
        ByteArrayInputStream input = getInputStream("b");
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        try {
            HelloWorld.promptForInput(input);
            assertTrue(output.toString().contains("That doesn't look right. Please try again."));
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testShouldPaginateText() {
        ByteArrayInputStream input = getInputStream("\n");
        String firstChunk = "1\n2\n3\n4\n5\n6\n7\n8\n9\n10\n";
        String prompt = "Please press enter to view more.";
        String secondChunk = "11\n12\n13\n14\n\n";
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        try {
            HelloWorld.printText(firstChunk + secondChunk, input);
        } catch (IOException e) {
            fail(e.getMessage());
        }


        assertTrue(output.toString().startsWith(firstChunk));
        assertTrue(output.toString().contains(prompt));
        assertTrue(output.toString().endsWith(secondChunk));
    }

    @Test
    public void testShouldWrapLongLines() {
        String aLongLine = "This is a really long line of text that should be wrapped so that it doesn't wrap in a really dumb way.";
        String result = HelloWorld.buildWrappedText(aLongLine);

        String line1 = "This is a really long line of text that should be wrapped so";
        String line2 = "that it doesn't wrap in a really dumb way.";

        String[] lines = result.split("\n");
        assertEquals(lines[0], line1);
        assertEquals(lines[1], line2);
    }

    @Test
    public void testPrintTextShouldWrapWords() {
        ByteArrayInputStream input = getInputStream("\n");
        String aLongLine = "This is a really long line of text that should be wrapped so that it doesn't wrap in a really dumb way.";
        String line1 = "This is a really long line of text that should be wrapped so";
        String line2 = "that it doesn't wrap in a really dumb way.";


        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        try {
            HelloWorld.printText(aLongLine, input);
        } catch (Exception e) {
            e.printStackTrace();
            fail("exception");

        }

        String outputText = output.toString();
        String expectedText = line1 + System.getProperty("line.separator") + line2;
        assertTrue(outputText.contains(expectedText));
    }

    private ByteArrayInputStream getInputStream(String inputStream) {
        return new ByteArrayInputStream(inputStream.getBytes());
    }
}
