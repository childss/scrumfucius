package com.example.classproject;

import java.io.*;

public class HelloWorld {

    static String _tale;

	public static void main(String[] args) throws IOException {
        runProgram(args[0], System.in);
	}

    public static void runProgram(String filename, InputStream userInputStream) throws IOException {
        File storyFile = new File(filename);
        readAndPrintFile(storyFile);

        System.out.print("Please type your selection: ");
        String userInput = readInput(userInputStream);
        File file = new File(userInput + ".txt");
        readAndPrintFile(file);
    }

    private static void readAndPrintFile(File file) throws IOException {
        readFile(file);
        System.out.print(_tale);
        System.out.println();
    }

    public static String readFile(File inputFile) throws IOException {
        if (!inputFile.exists()) { throw new FileNotFoundException(); }

        FileReader fileReader = new FileReader(inputFile);
        BufferedReader reader = new BufferedReader(fileReader);

        _tale = reader.readLine();
        return _tale;
    }

    public static String readInput(InputStream input) throws IOException {
        return new BufferedReader(new InputStreamReader(input)).readLine();
    }
}
