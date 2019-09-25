package homeWork;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

class HomeWork {
    private Map<String, Integer> map = new TreeMap<>();

    File enterFilePath() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите путь до файла: ");
        try {
            while (true) {
                String filePath = reader.readLine();
                Path path = Paths.get(filePath);
                File file = new File(path.toUri());
                if (!file.isFile()) System.out.println("Введен не правильный путь. Попробуйте снова:");
                else return file;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Вариант чтения файла №1
     * Стандартный вариант
     *
     * @param file - передаём в метод File
     */
    List<String> readFile(File file) {
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(file))) {
            List<String> wordsList = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                List<String> buffer = new ArrayList<>(Arrays.asList(line.split("[^A-zЁёА-я]+")));
                buffer.removeIf(String::isEmpty);
                wordsList.addAll(buffer);
            }
            wordsList.sort(Comparator.naturalOrder());
            System.out.println("Список всех встречаемых в файле слов в алфавитном порядке:\n" + wordsList);
            return wordsList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Вариант чтения файла №2
     * Чтение файла происходит сразу в List
     *
     * @param path - передаем в метод Path
     */
    List<String> readFileToList(Path path) {
        List<String> buffer, wordsList = new ArrayList<>();
        try {
            List<String> lines = Files.lines(path).map(String::toLowerCase).collect(Collectors.toList());
            for (String line : lines) {
                buffer = new ArrayList<>(Arrays.asList(line.split("[^A-zЁёА-я]+")));
                buffer.removeIf(String::isEmpty);
                wordsList.addAll(buffer);
            }
            wordsList.sort(Comparator.naturalOrder());
            System.out.println("Список всех встречаемых в файле слов в алфавитном порядке:\n" + wordsList);
            return wordsList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Вариант чтения файла №3
     * Для данного способа нужна JDK11
     * Чтение файла происходит сразу в String
     *
     * @param path - передаем в метод Path
     */
    List<String> readFileToString(Path path) {
        try {
            List<String> wordsList = new ArrayList<>(
                    Arrays.asList(Files.readString(Paths.get("test.txt")).split("[^A-zЁёА-я]+")));
            wordsList.removeIf(String::isEmpty);
            wordsList.sort(Comparator.naturalOrder());
            System.out.println("Список всех встречаемых в файле слов в алфавитном порядке:\n" + wordsList);
            return wordsList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    void printCountWords(List<String> wordsList) {
        System.out.println("\nКоличество одинаковых слов в файле:");
        wordsList.forEach(s -> map.merge(s.toLowerCase(), 1, Integer::sum));
        map.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(x -> System.out.println(x.getKey() + " : " + x.getValue()));

        printMostPopularWord();
    }

    void printMostPopularWord() {
        if (!map.isEmpty()) {
            int max = Collections.max(map.values());
            System.out.println("\nЧаще всего встречаются  следующие слова:");
            map.forEach((String k, Integer v) -> {
                if (v == max) System.out.println(k);
            });
            System.out.println("Количество повторений: " + max);
        }
    }
}
