/** This class outputs all palindromes in the words file in the current directory. */
public class PalindromeFinder {
    public static void main(String[] args) {
        int minLength = 4;

        /**
         * Find whether a word is palindrome or not
         */
        In in = new In("../library-sp19/data/words.txt");
        Palindrome palindrome = new Palindrome();
        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() >= minLength && palindrome.isPalindrome(word)) {
                System.out.println(word);
            }
        }
        System.out.println("Phase 1 done");

        /**
         * Find whether a word is a off-by-one palindrome
         */
        In in2 = new In("../library-sp19/data/words.txt");
        CharacterComparator offByOne = new OffByOne();
        while (!in2.isEmpty()) {
            String word = in2.readString();
            if (word.length() >= minLength && palindrome.isPalindrome(word, offByOne)) {
                System.out.println(word);
            }
        }
        System.out.println("Phase 2 done");

        /**
         * Just-for-fun: Try modifying PalindromeFinder.java so that it outputs a list of offByN palindromes for the N of your choosing.
         */
        In in3 = new In("../library-sp19/data/words.txt");
        while (!in3.isEmpty()) {
            String word = in3.readString();
            for (int i = 0; i <= 26; i += 1){
                if (word.length() >= minLength && palindrome.isPalindrome(word, new OffByN(i))) {
                    System.out.println("N :" + i);
                    System.out.println(word);
                }
            }
        }
        System.out.println("Phase 3 done");

        /**
         * What is the longest offByN palindrome for any N?
         */
        int lengths;
        String longest;
        for (int i = 0; i <= 30; i += 1){
            longest = null;
            lengths = 0;
            in = new In("../library-sp19/data/words.txt");
            while (!in.isEmpty()) {
                String word = in.readString();
                if (word.length() >= minLength && palindrome.isPalindrome(word, new OffByN(i))) {
                    if (word.length() - lengths > 0){
                        longest = word;
                        lengths = word.length();
                    }
                }
            }
            System.out.println("for N : " + i + " the longest word is : " + longest);
        }
        System.out.println("Phase 4 done");
    }
}