package readability;


import java.util.ArrayList;
import java.util.List;

public class Text {
    private final int charCount;
    private final int wordCount;
    private final int sentenceCount;
    private final int totalSyllables;
    private final long polySyllables;
    private final String input;
    private final String[] words;

    public Text(String input) {
        this.input = input;
        words = setWords();
        sentenceCount = setSentenceCount();
        charCount = setCharCount();
        wordCount = setWordCount();
        List<Integer> syllableCountsArray = setSyllableCountArray();
        totalSyllables = syllableCountsArray.stream().reduce(0, Integer::sum);
        polySyllables = syllableCountsArray.stream().filter(c -> c > 2).count();
    }

    public static boolean isVowel(char c) {
        final String vowels = "aeiouy";
        c = Character.toLowerCase(c);
        return vowels.contains(String.valueOf(c));
    }

    public static int getSyllablesPerWord(String word) {
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

    private List<Integer> setSyllableCountArray() {
        List<Integer> syllableCounts = new ArrayList<>();
        for (String word : this.getWords()) {
            syllableCounts.add(getSyllablesPerWord(word));
        }
        return syllableCounts;
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

    public int getSentenceCount() {
        return sentenceCount;
    }

    public int getCharCount() {
        return charCount;
    }

    public int getTotalSyllables() {
        return totalSyllables;
    }

    public long getPolySyllables() {
        return polySyllables;
    }

    public int getWordCount() {
        return wordCount;
    }

    public String getStats() {
        return """
                The text is:
                %s
                
                Words: %d
                Sentences: %d
                Characters: %d
                Syllable count: %d
                Polysyllables: %d
                """.formatted(input, wordCount, sentenceCount, charCount, totalSyllables, polySyllables);
    }
}
