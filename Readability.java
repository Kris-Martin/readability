package readability;

public record Readability(Text text) {
    public double getARI() {
        return 4.71 * ((double) text.charCount() / text().wordCount()) +
               0.5 * ((double) text.wordCount() / text.sentenceCount()) - 21.43;
    }

    public String getAgeRange(double score) {
        int rank = (int) Math.ceil(score);
        int lowerAge = rank + 4;
        int upperAge = lowerAge != 18 ? lowerAge + 1 : 24;
        return "%s-%s".formatted(lowerAge, upperAge);
    }
}
