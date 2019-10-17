import java.util.ArrayList;

public class Document {
    private String text;
    private String[] words;
    private ArrayList<String> sentences;

    public Document (String text) {
        this.text = text;
         words = text.split(" ");
         sentences = TextLib.splitIntoSentences(text);
    }

    public double getWordCount(){
        return words.length;
    }

    public double getSentenceCount(){
        return sentences.size();
    }

    public double getAverageWordsPerSentence(){
        return words.length/sentences.size();
    }

    public double getAverageCharPerWord(){
        int numOfChar = 0;
        for (int i = 0; i < words.length; i++) {
            numOfChar += words[i].length();
        }
        return numOfChar/words.length;
    }

    public int getVocabSize(){
        ArrayList<String> vocab = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            for (int j = 0; j < vocab.size(); j++) {
                if(!vocab.contains(word)){
                    vocab.add(word);
                }
            }
        }
        return vocab.size();
    }

    public double getReadabilityScore(){
        double wordCount = 0;
        double syllableCount = 0;

        for(String sentence: sentences){
            String newSentence = stripPunctuation(sentence);
            String[] words = breakIntoWords(newSentence);
            wordCount += words.length;

            for (String word: words){
                syllableCount += syllablesFor(word);
            }
        }
        return 206.835 - 1.01*(wordCount/sentences.size()) - 84.6*(syllableCount/wordCount);

    }

    private static double syllablesFor(String testWord) {
        boolean inVowelChain = false;
        int boundaries = 0;

        for (int i = 0; i < testWord.length(); i++) {
            String letter = testWord.substring(i, i+1);
            if (isVowel(letter)) {
                if (!inVowelChain) {
                    inVowelChain = true;
                    boundaries++;
                }
            } else {
                inVowelChain = false;
            }
        }

        return boundaries;
    } //for getReadabilityScore

    private static boolean isVowel(String letter) {
        return "aeiouy".contains(letter);
    } //for getReadabilityScore

    private static String[] breakIntoWords(String sentence) {
        String[] words = sentence.split(" ");
        return words;
    } //for getReadabilityScore

    private static String stripPunctuation(String sentence) {
        StringBuilder newSentence = new StringBuilder();
        String letters = "abcdefghijklmnopqrstuvwxyz1234567890 ";
        String lowerCaseSentence = sentence.toLowerCase();
        for (int i = 0; i < lowerCaseSentence.length(); i++) {
            if(letters.contains(sentence.substring(i, i+1))){
                newSentence.append(sentence.substring(i, i+1));
            }
        }
        return newSentence.toString();
    } //for getReadabilityScore

    public int countOcurrences(String target){
        int occurences = 0;
        for (int i = 0; i < text.length(); i++) {
            if(text.substring(i, i + target.length()).equals(target)){
                occurences++;
            }
        }
        return occurences;
    }

    public boolean stringCoocurrence(String target1, String target2){
        for (int i = 0; i < sentences.size(); i++) {
            if(sentences.get(i).contains(target1) && sentences.get(i).contains(target2)){
                return true;
            }
        }
        return false;
    }
}
