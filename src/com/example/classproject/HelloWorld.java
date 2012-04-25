package com.example.classproject;

import java.io.*;

public class HelloWorld {

    static String _tale;

	public static void main(String[] args) throws IOException {
        String filename = args[0];
        File storyFile = new File(filename);

        readFile(storyFile);
		System.out.print(_tale);
        System.out.println();
	}

    public static String readFile(File inputFile) throws FileNotFoundException, IOException {
        if (!inputFile.exists()) { throw new FileNotFoundException(); }

        FileReader fileReader = new FileReader(inputFile);
        BufferedReader reader = new BufferedReader(fileReader);

        _tale = reader.readLine();
        return _tale;
    }

}
