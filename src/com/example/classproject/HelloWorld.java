package com.example.classproject;

import sun.net.idn.StringPrep;

import java.io.*;

public class HelloWorld {

    static String _tale;

    public static void main(String[] args) throws IOException {
        runProgram(args[0], System.in);
    }

    public static void runProgram(String filename, InputStream userInputStream) throws IOException {
        File storyFile = new File(filename);
        readFile(storyFile);
        printText(_tale, userInputStream);

        System.out.print("Please type your selection: ");
        String userInput = readInput(userInputStream);
        if (!validateInput(userInput)) {
            System.out.println("Invalid selection: '" + userInput + "'");
            return;
        }
        File file = new File("story/" + userInput + ".txt");
        readFile(file);
        printText(_tale, userInputStream);
    }

    public static void printText(String text, InputStream userInputStream) throws IOException {
        String[] lines = text.split("\n");
        int i = 0;
        for (String line : lines) {
            if ((i) % 10 == 0 && i > 0) {
                System.out.println("Please press enter to view more.");
                readInput(userInputStream);
            }
            System.out.println(line);

            i++;
        }
        System.out.println();
    }

    public static String readFile(File inputFile) throws IOException {
        if (!inputFile.exists()) {
            throw new FileNotFoundException();
        }

        FileReader fileReader = new FileReader(inputFile);
        BufferedReader reader = new BufferedReader(fileReader);

        StringBuffer contents = new StringBuffer();
        String line = null;
        while (( line = reader.readLine()) != null){
            contents.append(line);
            contents.append(System.getProperty("line.separator"));
        }
        _tale = contents.toString();
         return _tale;

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
