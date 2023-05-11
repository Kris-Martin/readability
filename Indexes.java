package readability;

import java.util.List;

public enum Indexes {
    ARI("ARI","Automated Readability Index"),
    FK("FK","Flesch–Kincaid readability tests"),
    SMOG("SMOG", "Simple Measure of Gobbledygook"),
    CL("CL", "Coleman–Liau index"),
    ALL("all", "all");

    private final String abbrev;
    private final String name;

    Indexes(String abbrev, String name) {
        this.abbrev = abbrev;
        this.name = name;
    }

    public String getAbbrev() {
        return abbrev;
    }

    public String getName() {
        return name;
    }

    public static double getARI(Text text) {
        return 4.71 * ((double) text.getCharCount() / text.getWordCount()) +
                0.5 * ((double) text.getWordCount() / text.getSentenceCount()) - 21.43;
    }

    public static double getFleschKincaid(Text text) {
        return 0.39 * ((double) text.getWordCount() / text.getSentenceCount()) +
                11.8 * ((double) text.getTotalSyllables() / text.getWordCount()) - 15.59;
    }

    public static double getSMOG(Text text) {
        return 1.043 * Math.sqrt(text.getPolySyllables() * (30.0 / text.getSentenceCount())) + 3.1291;
    }

    public static double getColemanLiau(Text text) {
        double avgChars100Words = (double) text.getCharCount() / text.getWordCount() * 100;
        double avgSentences100Words = (double) text.getSentenceCount() / text.getWordCount() * 100;

        return 0.0588 * avgChars100Words - 0.296 * avgSentences100Words - 15.8;
    }

    public static int getUpperAgeLevel(double score) {
        int rank = (int) Math.ceil(score);
        int lowerAge = rank + 4;
        return lowerAge != 18 ? lowerAge + 1 : 24;
    }

    public static double getAverageAgeLevel(List<Integer> ages) {
        return ages.stream().mapToInt(Integer::intValue).average().orElse(0);
    }
}
