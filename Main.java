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
                    Polysyllables: %d
                    """.formatted(
                            Arrays.toString(text.getWords()),
                            text.getWordCount(),
                            text.getSentenceCount(),
                            text.getTotalSyllables(),
                            text.getPolySyllables());
            System.out.println(textStats);

            double ari = readability.getARI();
            int ariAgeLevel = Readability.getUpperAgeLevel(ari);
            double fleshKincaid = readability.getFleschKincaid();
            int fleshKincaidAgeLevel = Readability.getUpperAgeLevel(fleshKincaid);
            double smog = readability.getSMOG();
            int smogAgeLevel = Readability.getUpperAgeLevel(smog);
            double colemanLiau = readability.getColemanLiau();
            int colemanLiauAgeLevel = Readability.getUpperAgeLevel(colemanLiau);

            String readabilityScores =
                    """
                    Automated Readability Index: %.2f (about %d-year-olds).
                    Flesch–Kincaid readability tests: %.2f (about %d-year-olds).
                    Simple Measure of Gobbledygook: %.2f (about %d-year-olds).
                    Coleman–Liau index: %.2f (about %d-years-old).
                    
                    This text should be understood in average by %.2f-year-olds.
                    """.formatted(
                            ari,
                            ariAgeLevel,
                            fleshKincaid,
                            fleshKincaidAgeLevel,
                            smog,
                            smogAgeLevel,
                            colemanLiau,
                            colemanLiauAgeLevel,
                            Readability.getAverageAgeLevel(ariAgeLevel, fleshKincaidAgeLevel, smogAgeLevel, colemanLiauAgeLevel));
            System.out.println(readabilityScores);

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
