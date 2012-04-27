package com.example.classproject;

import java.io.*;

public class Program {
    private final String storyFolder;

    public Program(String storyFolder) {
        this.storyFolder = storyFolder;
    }

    public String readFile(String filename) throws IOException {
        File inputFile = new File(storyFolder, filename);
        if (!inputFile.exists()) {
            throw new FileNotFoundException();
        }

        FileReader fileReader = new FileReader(inputFile);
        BufferedReader reader = new BufferedReader(fileReader);

        StringBuffer contents = new StringBuffer();
        String line = null;
        while ((line = reader.readLine()) != null) {
            contents.append(line);
            contents.append(System.getProperty("line.separator"));
        }

        return contents.toString();
    }
}
