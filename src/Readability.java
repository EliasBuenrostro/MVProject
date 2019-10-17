import java.util.ArrayList;

public class Readability {
    public static void main(String[] args) {
        //testSyllableMethod();
        ArrayList<DocumentInfo> info = TextLib.readDocInfo("data/Texts/allfeatures-ose-final.csv");
        double totalError = 0;

        for (int i = 0; i < info.size(); i++) {
            DocumentInfo doc = info.get(i);
            String filename = doc.getName();

            String text = TextLib.readFileAsString("data/Texts/AllTexts/" + filename);
            ArrayList<String> sentences = TextLib.splitIntoSentences(text);
            if (!text.equals(null)){
                double prediction = FKReadability(sentences);
                double error = (prediction - doc.getFlesch());
                totalError += Math.abs(error);
            }
        }
        System.out.println("Total error is " + totalError);
        System.out.println("Size is " + info.size());
        System.out.println("Average error is " + totalError/info.size());


        //String test = TextLib.readFileAsString("data/Texts/AllTexts/Amazon-adv.txt");
        //System.out.println(test);

       // ArrayList<String> sentences = TextLib.splitIntoSentences(test);

       //double readability = FKReadability(sentences);
        //System.out.println(readability);

        // TODO:  Break each sentence into words.
        // TODO:  Force to lower-case and strip out all puctuation for doing syllable counts.
    }

    private static double FKReadability(ArrayList<String> sentences){
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
    }

    private static String[] breakIntoWords(String sentence) {
        String[] words = sentence.split(" ");
        return words;
    }


    private static void testSyllableMethod() {
        ArrayList<Word> words = TextLib.readSyllablesFile("data/syllables.txt");

        double right = 0;
        for (Word w : words) {
            String word = w.getWord();
            int prediction = syllablesFor(word);

            if (prediction == w.getSyllables()) right++;
        }

        System.out.println("You got " + (right/words.size()) + " right");
    }

    private static int syllablesFor(String testWord) {
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
    }

    private static boolean isVowel(String letter) {
        return "aeiouy".contains(letter);
    }
}
