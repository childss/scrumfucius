package com.example.classproject;

import java.io.*;
import java.util.Random;
import org.apache.commons.lang.WordUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HelloWorld {

    static String _tale;

    public static void main(String[] args) throws IOException {
        runProgram(args[0], "story", System.in);
    }

    public static void runProgram(String filename, String storyRoot, InputStream userInputStream) throws IOException {
        Program program = new Program(storyRoot);

        _tale = program.readFile(filename);
        printText(_tale, userInputStream);

        System.out.print("Please type your selection: ");
        String userInput = readInput(userInputStream);
        while(!validateInput(userInput))
        {
            userInput = promptForInput(userInputStream);
        }

        _tale = program.readFile(getPseudoRandomFilename(userInput));
        printText(_tale, userInputStream);
    }

    public static String buildWrappedText(String string) {
        return WordUtils.wrap(string, 60);
    }

    public static String promptForInput(InputStream userInputStream)   throws IOException
    {
        System.out.println("That doesn't look right. Please try again.");
        return readInput(userInputStream);

    }

    public static void printText(String text, InputStream userInputStream) throws IOException {
        text = buildWrappedText(text);
        String[] lines = text.split("\n");
        int i = 0;
        for (String line : lines) {
            if ((i) % 15 == 0 && i > 0) {
                System.out.println();
                System.out.println("(Please press enter to view more.)");
                readInput(userInputStream);
            }
            System.out.println(line);
            i++;
        }
        System.out.println();
    }

    public static String readInput(InputStream input) throws IOException {
        return new BufferedReader(new InputStreamReader(input)).readLine();
    }

    public static boolean validateInput(String userInput) {
        try {
            int storyNumber = Integer.parseInt(userInput);
            return (storyNumber >= 1 && storyNumber <= 4);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String getPseudoRandomFilename(String goodInput) {
        Random random = new Random();
        return random.nextInt() % 2 == 0 ?  goodInput+".2.txt" : goodInput+".1.txt";

    }
}
