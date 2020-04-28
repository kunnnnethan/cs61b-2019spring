public class OffByN implements CharacterComparator{
    public int difference;

    public OffByN(int N){
        difference = N;
    }

    @Override
    public boolean equalChars(char x, char y){
        if (!Character.isLetter(x) || !Character.isLetter(y)){
            return false;
        }
        return Math.abs(x - y) == difference;
    }
}
