package fileReader;

import java.io.File;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        startProgram();
    }

    static void startProgram() {
        FileReader fileReader = new FileReader();
        File file = fileReader.enterFilePath();
        List<String> wordsList = fileReader.readFile(file);
        fileReader.printCountWords(wordsList);
        fileReader.printMostPopularWord();
    }
}
