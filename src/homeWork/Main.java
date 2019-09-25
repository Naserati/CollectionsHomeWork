package homeWork;

import java.io.File;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        startProgramV1();
    }

    private static void startProgramV1() {
        System.out.println("Вариант чтения из файла №1");
        HomeWork homeWork = new HomeWork();
        File file = homeWork.enterFilePath();
        List<String> wordsList = homeWork.readFile(file);
        homeWork.printCountWords(wordsList);
    }

    private static void startProgramV2(){
        System.out.println("Вариант чтения из файла №2");
        HomeWork homeWork = new HomeWork();
        homeWork.enterFilePath();
        List<String> wordsList = homeWork.readFileToList(homeWork.getPath());
        homeWork.printCountWords(wordsList);
    }

    private static void startProgramV3(){
        System.out.println("Вариант чтения из файла №3");
        HomeWork homeWork = new HomeWork();
        homeWork.enterFilePath();
        List<String> wordsList = homeWork.readFileToString(homeWork.getPath());
        homeWork.printCountWords(wordsList);
    }
}
