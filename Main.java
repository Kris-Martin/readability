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
                double score = Indexes.getARI(text);
                int age = Indexes.getUpperAgeLevel(score);
                printOutput(Indexes.ARI.name(), score, age);
            }
            case "FK" -> {
                double score = Indexes.getFleschKincaid(text);
                int age = Indexes.getUpperAgeLevel(score);
                printOutput("Flesch–Kincaid readability tests", score, age);
            }
            case "SMOG" -> {
                double score = Indexes.getSMOG(text);
                int age = Indexes.getUpperAgeLevel(score);
               printOutput("Simple Measure of Gobbledygook", score, age);
            }
            case "CL" -> {
                double score = Indexes.getColemanLiau(text);
                int age = Indexes.getUpperAgeLevel(score);
                printOutput("Coleman–Liau index", score, age);
            }
            case "ALL" -> {
                Map<String, Double> indexes = Map.of(
                        Indexes.ARI.getName(), Indexes.getARI(text),
                        Indexes.FK.getName(), Indexes.getFleschKincaid(text),
                        Indexes.SMOG.getName(), Indexes.getSMOG(text),
                        Indexes.CL.getName(), Indexes.getColemanLiau(text));

                List<Integer> ageLevels = new ArrayList<>();
                indexes.forEach((name, score) -> {
                    int age = Indexes.getUpperAgeLevel(score);
                    ageLevels.add(age);
                    printOutput(name, score, age);
                });

                System.out.printf("%nThis text should be understood in average by %.2f-year-olds.%n",
                        Indexes.getAverageAgeLevel(ageLevels));
            }
            default -> System.out.println("Error! Invalid selection. Please try again.");
        }
    }

    public static void printOutput(String name, double score, int age) {
        System.out.printf("%s: %.2f (about %d-year-olds).%n",  name, score, age);
    }
}
