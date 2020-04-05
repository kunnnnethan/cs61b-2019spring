public class Palindrome {

    /**
     * Return a Deque where the characters appear in the same
     * order as in the String
     */
    public Deque<Character> wordToDeque(String word){
        Deque<Character> deque = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i += 1){
            deque.addLast(word.charAt(i));
        }
        return deque;
    }

    /**
     * To determine whether a word is palindrome or not
     */
    public boolean isPalindrome(String word){
        for (int i = 0; i < word.length(); i += 1){
            if (!Character.isLetter(word.charAt(i))){
                return false;
            }
        }
        Deque<Character> deque = wordToDeque(word);
        return Helper(deque, word.length()/2);
    }

    private boolean Helper(Deque<Character> d, int i){
        if (d.size() == 0 || d.size() == 1){
            return true;
        }
        if (d.removeFirst().equals(d.removeLast())){
            return Helper(d, i -1 );
        }else{
            return false;
        }
    }

    /**
     * TO determine whether a word is a off-by-one palindrome
     */
    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> deque = wordToDeque(word);
        return HelperOverload(deque, word.length()/2, cc);
    }

    private boolean HelperOverload(Deque<Character> d, int i, CharacterComparator cc){
        if (d.size() == 0 || d.size() == 1){
            return true;
        }
        if (cc.equalChars(d.removeFirst(), d.removeLast())){
            return HelperOverload(d, i -1, cc);
        }else{
            return false;
        }
    }
}
