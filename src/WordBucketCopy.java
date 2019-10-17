import java.util.ArrayList;

public class WordBucketCopy {
    private ArrayList<WordReps> bucket = new ArrayList<>();

    public WordBucketCopy() {

    }

    public void add(String item) {
        WordReps word = new WordReps(item, 1);
        bucket.add(word);
    }

    public void add(String item, long count) {
        WordReps word = new WordReps(item, count);
        bucket.add(word);
    }

    public long getCountOf(String word) {
        for (int i = 0; i < bucket.size(); i++) {
            if(bucket.get(i).equals(word)){
                return bucket.get(i).getReps();
            }
        }
        return 0;
    }

    public int getSize() {
        return bucket.size();
    }

    public int getNumUnique() {
        ArrayList<String> uniques = new ArrayList<>();
        for (int i = 0; i < bucket.size(); i++) {
            if (!uniques.contains(bucket.get(i))) {
                uniques.add(bucket.add(i));
            }
        }
        return uniques.size();
    }

    public String getMostFreq() {
        String mostFreq = "";
        int highCount = 0;
        for (int i = 0; i < bucket.size(); i++) {
            int currentCount = 0;
            for (int j = i; j < bucket.size(); j++) {
                currentCount++;
            }
            if (currentCount > highCount) {
                highCount = currentCount;
                mostFreq = bucket.get(i);
            }
        }
        return mostFreq;
    }

    public ArrayList<String> getTopMostFreq(int n) {
        return null;
    }

}

