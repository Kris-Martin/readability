package readability;


public class Text {
    String input;
    String[] words;
    private final int sentenceCount;
    private final int charCount;
    private final int totalSyllables;
    private final int wordCount;

    public Text(String input) {
        this.input = input;
        words = setWords();
        sentenceCount = setSentenceCount();
        charCount = setCharCount();
        totalSyllables = setTotalSyllables();
        wordCount = setWordCount();
    }

    public static boolean isVowel(char c) {
        final String vowels = "aeiouy";
        c = Character.toLowerCase(c);
        return vowels.contains(String.valueOf(c));
    }

    public static int syllablesPerWord(String word) {
        char prev = ' ';
        char curr;
        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            curr = word.charAt(i);
            if (isVowel(curr) && !isVowel(prev)) {
                count++;
            }
            if (curr == 'e' && i == word.length() - 1) {
                count--;
            }
            prev = curr;
        }
        return count > 0 ? count : 1;
    }

    private int setCharCount() {
        return input.replaceAll("\\s+", "").length();
    }

    private int setTotalSyllables() {
        int count = 0;
        for (String word : this.getWords()) {
            count += syllablesPerWord(word);
        }
        return count;
    }

    private String[] setWords() {
        return input.replaceAll("[!?.]", "").split(" ");
    }

    private int setWordCount() {
        return this.getWords().length;
    }

    public String[] getWords() {
        return words;
    }

    private int setSentenceCount() {
        return input.split("[!?.]").length;
    }

    public String getInput() {
        return input;
    }

    public int getSentenceCount() {
        return sentenceCount;
    }

    public int getCharCount() {
        return charCount;
    }

    public int getTotalSyllables() {
        return totalSyllables;
    }

    public int getWordCount() {
        return wordCount;
    }
}
