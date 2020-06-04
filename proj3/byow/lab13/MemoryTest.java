package byow.lab13;

import org.junit.Test;

public class MemoryTest {

    @Test
    public void testDrawFrame() {
        MemoryGame m = new MemoryGame(40, 40, 123);
        m.drawFrame("Hello");
    }

    @Test
    public void testFlaseSequence() {
        MemoryGame m = new MemoryGame(40, 40, 123);
        m.flashSequence("Hello");
    }

    @Test
    public void testSolicitNCharsInput() {
        MemoryGame m = new MemoryGame(40, 40, 123);
        m.solicitNCharsInput(5);
    }

    @Test
    public void testGenerateRansomString() {
        MemoryGame m = new MemoryGame(40, 40, 123);
        System.out.println(m.generateRandomString(5));
    }
}
