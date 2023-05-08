package readability;

public record Text(String input) {
    public int charCount() {
        return input.replaceAll("\\s+", "").length();
    }

    public int wordCount() {
        return input.split(" ").length;
    }

    public int sentenceCount() {
        return input.split("[!?.]").length;
    }
}
