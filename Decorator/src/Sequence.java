import java.util.Arrays;

public interface Sequence {
    public String parcurge();
}
class SeqWord implements  Sequence{
    String[] words;
    int index = -1;

    public SeqWord(String text) {
        words = text.split("[\\s]+");
    }

    @Override
    public String parcurge() {
        index++;
        return words[index];
    }
    @Override
    public String toString() {
        return "SeqWord{" +
                "words=" + Arrays.toString(words) +
                '}';
    }
}
