public abstract class SeqDecorator implements  Sequence{
    public SeqWord sword;
    public SeqDecorator(SeqWord sword) {
        this.sword = sword;
    }
    @Override
    public String parcurge() {
        return sword.parcurge();
    }
}
class SeqCharacter extends SeqDecorator{
    int index = -1;
    String word = super.parcurge();
    public SeqCharacter(SeqWord sword) {
        super(sword);
    }
    public String parcurge() {
        if(index == word.length())
        {
            index = -1;
            word = super.parcurge();
        }
        index++;
        return word.charAt(index)+"";
    }
}

class SeqUpper extends SeqDecorator{
    int index = -1;
    String word = super.parcurge();
    public SeqUpper(SeqWord sword) {
        super(sword);
    }
    public String parcurge() {
        if(index == word.length())
        {
            index = -1;
            word = super.parcurge();
        }
        index++;
        if(Character.isLowerCase(word.charAt(index))) {
            char s = Character.toUpperCase(word.charAt(index));
            StringBuilder word2 = new StringBuilder(word);
            word2.setCharAt(index,s);
            return word2.charAt(index)+"";
        }
        return word.charAt(index)+"";

    }

    @Override
    public String toString() {
        return "SeqUpper{" +
                "word='" + word + '\'' +
                '}';
    }
}
