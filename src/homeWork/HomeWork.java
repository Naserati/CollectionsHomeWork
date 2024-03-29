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
    private Path path;

    File enterFilePath() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите путь до файла: ");
        try {
            while (true) {
                String filePath = reader.readLine();
                path = Paths.get(filePath);
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
        List<String> wordsList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                List<String> buffer = new ArrayList<>(Arrays.asList(line.split("[^A-zЁёА-я-]+[^A-zЁёА-я]?|[\\[\\]]+")));
                buffer.removeIf(String::isEmpty);
                wordsList.addAll(buffer);
            }
            wordsList.sort(Comparator.naturalOrder());
            System.out.println("Список всех встречаемых в файле слов в алфавитном порядке:\n" + wordsList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordsList;
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
                buffer = new ArrayList<>(Arrays.asList(line.split("[^A-zЁёА-я-]+[^A-zЁёА-я]?|[\\[\\]]+")));
                buffer.removeIf(String::isEmpty);
                wordsList.addAll(buffer);
            }
            wordsList.sort(Comparator.naturalOrder());
            System.out.println("Список всех встречаемых в файле слов в алфавитном порядке:\n" + wordsList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordsList;
    }

    /**
     * Вариант чтения файла №3
     * Для данного способа нужна JDK11
     * Чтение файла происходит сразу в String
     *
     * @param path - передаем в метод Path
     */
    List<String> readFileToString(Path path) {
        List<String> wordsList = null;
        try {
            wordsList = new ArrayList<>(Arrays.asList(Files.readString(path).split("[^A-zЁёА-я-]+[^A-zЁёА-я]?|[\\[\\]]+")))
                    .stream().filter(v -> !v.isEmpty()).sorted(Comparator.naturalOrder()).collect(Collectors.toList());
            System.out.println("Список всех встречаемых в файле слов в алфавитном порядке:\n" + wordsList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordsList;
    }

    void printCountWords(List<String> wordsList) {
        System.out.println("\nКоличество одинаковых слов в файле:");
        wordsList.forEach(s -> map.merge(s.toLowerCase(), 1, Integer::sum));
        map.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(x -> System.out.println(x.getKey() + " : " + x.getValue()));

        printMostPopularWord();
    }

    private void printMostPopularWord() {
        if (!map.isEmpty()) {
            int max = Collections.max(map.values());
            System.out.println("\nЧаще всего встречаются  следующие слова:");
            map.entrySet().stream().filter(v -> v.getValue() == max).forEach((k) -> System.out.println(k.getKey()));
            System.out.println("Количество повторений: " + max);
        }
    }

    Path getPath() {
        return path;
    }
}
