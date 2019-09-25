package homeWork;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

class FileReader {
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

    void printCountWords(List<String> wordsList) {
        System.out.println("\nКоличество одинаковых слов в файле:");
        wordsList.forEach(s -> map.merge(s.toLowerCase(), 1, Integer::sum));
        map.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(x -> System.out.println(x.getKey() + " : " + x.getValue()));
    }

    void printMostPopularWord() {
        if (!map.isEmpty()) {
            int max = Collections.max(map.values());
            System.out.println("\nЧаще всего встречались следующие слова:");
            map.forEach((String k, Integer v) -> {
                if (v == max) System.out.println(k);
            });
            System.out.println("Количество повторений: " + max);
        }
    }
}
