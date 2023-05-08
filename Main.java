package readability;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        // args[0] for command line, "~/test1.txt" for test
        try (Scanner scanner = new Scanner(new File(args[0]))) {
            String input = scanner.nextLine().trim();
            Text text = new Text(input);
            Readability readability = new Readability(text);
            double ariScore = readability.getARI();

            String output =
                    """
                    The text is:
                    %s
                                        
                    Words: %d
                    Sentences: %d
                    Characters: %d
                    The score is: %f
                    This text should be understood by %s year-olds.
                    """.formatted(
                            text.input(),
                            text.wordCount(),
                            text.sentenceCount(),
                            text.charCount(),
                            ariScore,
                            readability.getAgeRange(ariScore));

            System.out.println(output);

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
