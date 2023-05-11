package readability;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        // args[0] for command line, "~/test1.txt" for test
        try (Scanner scanner = new Scanner(new File("/Users/kristymartin/test1.txt"))) {
            String input = scanner.nextLine().trim();
            Text text = new Text(input);
            System.out.println(text.getStats());

            String validSelections = "ARI, FK, SMOG, CL, ALL";
            String selection = "none";
            Scanner userInput = new Scanner(System.in);

            while (!validSelections.contains(selection.toUpperCase())) {
                System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
                selection = userInput.nextLine().trim().toUpperCase();
                System.out.println();
                getSelection(selection, text);
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void getSelection(String selection, Text text) {
        switch (selection) {
            case "ARI" -> {
                Indexes.ARI.printOutput(text);
            }
            case "FK" -> {
                Indexes.FK.printOutput(text);
            }
            case "SMOG" -> {
                Indexes.SMOG.printOutput(text);
            }
            case "CL" -> {
                Indexes.CL.printOutput(text);
            }
            case "ALL" -> {
                List<Integer> ageLevels = new ArrayList<>();
                for (Indexes index : Indexes.values()) {
                    ageLevels.add(index.getUpperAgeLevel(text));
                    index.printOutput(text);
                }

                System.out.printf("%nThis text should be understood in average by %.2f-year-olds.%n",
                        Indexes.getAverageAgeLevel(ageLevels));
            }
            default -> System.out.println("Error! Invalid selection. Please try again.");
        }
    }
}
