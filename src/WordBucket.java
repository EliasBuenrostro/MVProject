import java.util.ArrayList;

public class WordBucket {
    private ArrayList<String> bucket = new ArrayList<>();
    public WordBucket(){

    }

    public void add(String item){
        bucket.add(item);
    }

    public void add(String item, long count){
        for (int i = 0; i < count; i++) {
            bucket.add(item);
        }
    }

    public int getCountOf(String word){
        int count = 0;
        for (int i = 0; i < bucket.size(); i++) {
            if(bucket.get(i).equals(word)){
                count++;
            }
        }
        return count;
    }

    public int getSize(){
        return bucket.size();
    }

    public int getNumUnique(){
        ArrayList<String> uniques = new ArrayList<>();
        for (int i = 0; i < bucket.size(); i++) {
            if(!uniques.contains(bucket.get(i))){
                uniques.add(bucket.get(i));
            }
        }
        return uniques.size();
    }

    public String getMostFreq(){
        String mostFreq = "";
        int highCount = 0;
        for (int i = 0; i < bucket.size(); i++) {
            int currentCount = 0;
            for (int j = i; j < bucket.size(); j++) {
                currentCount++;
            }
            if (currentCount > highCount){
                highCount = currentCount;
                mostFreq = bucket.get(i);
            }
        }
        return mostFreq;
    }

    public ArrayList<String> getTopMostFreq(int n){
        return null;
    }

}
