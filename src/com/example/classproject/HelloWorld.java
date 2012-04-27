package com.example.classproject;

import java.io.*;

public class HelloWorld {

    static String _tale;

    public static void main(String[] args) throws IOException {
        runProgram(args[0], System.in);
    }

    public static void runProgram(String filename, InputStream userInputStream) throws IOException {
        Program program = new Program("story");

        _tale = program.readFile(filename);
        printText(_tale, userInputStream);

        System.out.print("Please type your selection: ");
        String userInput = readInput(userInputStream);
        while(!validateInput(userInput))
        {
            userInput = promptForInput(userInputStream);
        }

        _tale = program.readFile(userInput + ".txt");
        printText(_tale, userInputStream);
    }

    public static String promptForInput(InputStream userInputStream)   throws IOException
    {
        System.out.println("That doesn't look right. Please try again.");
        return readInput(userInputStream);

    }

    public static void printText(String text, InputStream userInputStream) throws IOException {
        String[] lines = text.split("\n");
        int i = 0;
        for (String line : lines) {
            if ((i) % 10 == 0 && i > 0) {
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
}
