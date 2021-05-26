import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class SubstringOccurrences {

    public static void main(String[] args) {
        printCheckGetSubstringsCount();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите имя файла с текстом:");
        String inputFilePath = scanner.nextLine();

        if (!isFileCorrect(inputFilePath)) {
            System.out.println("Файла нет или он пустой.");
            return;
        }

        System.out.println("Введите подстроку для поиска:");
        String examinedSubstring = scanner.nextLine();

        int substringsCount = getInFileSubstringsCount(inputFilePath, examinedSubstring);
        System.out.println("Количество подстрок в файле без учета регистра равно " + substringsCount);
    }

    private static boolean isFileCorrect(String inputFilePath) {
        File file = new File(inputFilePath);

        if (!file.exists() || file.isDirectory()) {
            return false;
        }

        return file.length() != 0;
    }

    private static int getInFileSubstringsCount(String inputFilePath, String substring) {
        String line;

        String lowerCaseSubstring = substring.toLowerCase();

        int substringsCount = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFilePath))) {
            while ((line = bufferedReader.readLine()) != null) {
                line = line.toLowerCase();
                substringsCount += getSubstringsCount(line, lowerCaseSubstring);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return substringsCount;
    }

    private static int getSubstringsCount(String line, String substring) {
        int substringsCount = 0;
        int targetIndex = 0;
        int i = 0;

        while (targetIndex != -1) {
            targetIndex = line.indexOf(substring, i);

            if (targetIndex != -1) {
                substringsCount++;
                i = targetIndex + substring.length();
            }
        }

        return substringsCount;
    }

    private static String[] getDemoStrings() {
        return new String[]{
                "Я мечтою ловил уходящие тени",
                "Уходящие тени погасавшего дня",
                "Я на башню всходил, и дрожали ступени",
                "И меня дрожали ступени под ногой у меня",
                "мен мен                     ",
                "менмен:",
                "менменмен"
        };
    }

    private static void printCheckGetSubstringsCount() {
        String substring = "ен";
        int countSubstring = 0;

        for (String s : getDemoStrings()) {
            countSubstring += getSubstringsCount(s, substring);
        }

        System.out.println("Количество подстрок в демо массиве равно " + countSubstring);
    }
}