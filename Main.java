package readability;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String input = new Scanner(System.in).nextLine().trim();
        List<String> sentences = List.of(input.split("[.?!]"));
        int averageWords = sentences.stream()
                                    .map(sentence -> sentence.split(" ").length)
                                    .reduce(0, Integer::sum) / sentences.size();

        System.out.print(averageWords > 10 ? "HARD" : "EASY");
    }
}
