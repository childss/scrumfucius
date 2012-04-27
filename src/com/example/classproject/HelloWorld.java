package com.example.classproject;

import java.io.*;

public class HelloWorld {

    static String _tale;

    public static void main(String[] args) throws IOException {
        runProgram(args[0], System.in);
    }

    public static void runProgram(String filename, InputStream userInputStream) throws IOException {
        File storyFile = new File(filename);
        readFile(storyFile);
        printText(_tale);

        System.out.print("Please type your selection: ");
        String userInput = readInput(userInputStream);
        if (!validateInput(userInput)) {
            System.out.println("Invalid selection: '" + userInput + "'");
            return;
        }
        File file = new File("story/" + userInput + ".txt");
        readFile(file);
        printText(_tale);
    }

    private static void printText(String text) throws IOException {

        System.out.print(text);
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
