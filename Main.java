package readability;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        // args[0] for command line, "~/test1.txt" for test
        try (Scanner scanner = new Scanner(new File("/Users/kristymartin/test1.txt"))) {
            String input = scanner.nextLine().trim();
            Text text = new Text(input);
            Readability readability = new Readability(text);

            String textStats =
                    """
                    Words: %s
                    Word count: %d
                    Sentences: %d
                    Syllable count: %d
                    Polysyllables: //TODO
                    """.formatted(
                            Arrays.toString(text.getWords()),
                            text.getWordCount(),
                            text.getSentenceCount(),
                            text.getTotalSyllables());
            System.out.println(textStats);

            String readabilityScores =
                    """
                    Automated Readability Index: %.2f
                    Flesch–Kincaid readability tests: %.2f
                    Simple Measure of Gobbledygook:
                    Coleman–Liau index:
                    """.formatted(readability.getARI(), readability.getFleschKincaid());
            System.out.println(readabilityScores);

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
