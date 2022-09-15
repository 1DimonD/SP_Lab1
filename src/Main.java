import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Math.max;

public class Main {
    static final String consonants = "bcdfghjklmnpqrstvwxzбвгджзклмнпрстфхцчьшщ";
    static int MaxConsonantConsistentLength(String word) {
        word = word.toLowerCase();
        int len = 0, maxLen = 0;
        for(int i = 0; i < word.length(); i++) {
            if( consonants.contains(word.substring(i, i+1)) ) {
                len++;
            } else {
                maxLen = max(maxLen, len);
                len = 0;
            }
        }
        return maxLen;
    }

    public static void main(String[] args) throws IOException {

        Scanner in = new Scanner(new FileReader("resources/test.txt"));
        String word;
        int consonantLength;

        int maxLength = 0;
        ArrayList<String> words = new ArrayList<>();

        while(in.hasNext()) {
            word = in.next();
            word = word.toLowerCase().replaceAll("[^a-zа-яіїє]", "");
            consonantLength = MaxConsonantConsistentLength(word);

            if(consonantLength > maxLength) {
                maxLength = Math.min(consonantLength, 30);
                words.clear();
                words.add(word);
            }

            if(consonantLength == maxLength && !words.contains(word)) {
                words.add(word);
            }
        }
        in.close();

        System.out.println("Max consistent consonants length was " + maxLength + " letters.");
        System.out.print("List of words: ");
        for(int i = 0; i < words.size(); i++) {
            System.out.print(words.get(i));
            if (i != words.size()-1) {
                System.out.print(", ");
            }
            if(i != 0 && i % 4 == 0) {
                System.out.println();
            }
        }
    }
}