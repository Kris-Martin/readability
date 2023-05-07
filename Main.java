package readability;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        String text = "";

        try (Scanner scanner = new Scanner(new File(args[0]))) {
            text = scanner.nextLine().trim();

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        int sentences = text.split("[!?.]").length;
        int words = text.split(" ").length;
        int characters = text.replaceAll("\\s+", "").length();

        double score = 4.71 * ((double) characters / words) + 0.5 * ((double) words / sentences) - 21.43;
        int rank = (int) Math.ceil(score);
        int lowerAge = rank + 4;
        int upperAge = lowerAge != 18 ? lowerAge + 1 : 24;
        String ageRange = "%s-%s".formatted(lowerAge, upperAge);

        String output =
        """
        The text is:
        %s
        
        Words: %d
        Sentences: %d
        Characters: %d
        The score is: %f
        This text should be understood by %s year-olds.
        """.formatted(text, words, sentences, characters, score, ageRange);

        System.out.println(output);
    }
}
