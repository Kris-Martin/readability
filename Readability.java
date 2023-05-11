package readability;

public record Readability(Text text) {
    public double getARI() {
        return 4.71 * ((double) text.getCharCount() / text.getWordCount()) +
               0.5 * ((double) text.getWordCount() / text.getSentenceCount()) - 21.43;
    }

    public double getFleschKincaid() {
        return 0.39 * ((double) text.getWordCount() / text.getSentenceCount()) +
               11.8 * ((double) text.getTotalSyllables() / text.getWordCount()) - 15.59;
    }

    public String getAgeRange(double score) {
        int rank = (int) Math.ceil(score);
        int lowerAge = rank + 4;
        int upperAge = lowerAge != 18 ? lowerAge + 1 : 24;
        return "%s-%s".formatted(lowerAge, upperAge);
    }
}
