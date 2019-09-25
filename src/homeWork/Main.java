package homeWork;

import java.io.File;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        startProgram();
    }

    static void startProgram() {
        HomeWork homeWork = new HomeWork();
        File file = homeWork.enterFilePath();
        List<String> wordsList = homeWork.readFile(file);
        homeWork.printCountWords(wordsList);
        homeWork.printMostPopularWord();
    }
}
