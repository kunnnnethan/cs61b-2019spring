import es.datastructur.synthesizer.GuitarString;

public class GuitarHero {

    private GuitarString[] strings;

    public GuitarHero() {
        initialize();
    }

    public void initialize() {
        /** create 37 guitar strings, 440⋅2(i−24)/12 frequency for each note */
        strings = new GuitarString[37];
        for (int i = 0; i < strings.length; i += 1) {
            strings[i] = new GuitarString(440.0 * Math.pow(2, (i - 24) / 12.0));
        }
    }

    public static void main(String[] args) {
        GuitarHero play = new GuitarHero();
        GuitarString string = play.strings[0];
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                if (index >= 0) {
                    string = play.strings[index];
                    play.strings[index].pluck();
                }
            }

            double sample = string.sample(); // 不懂為什麼一定要放在if外面才會有聲音;
            StdAudio.play(sample);
            string.tic();
        }
    }
}
