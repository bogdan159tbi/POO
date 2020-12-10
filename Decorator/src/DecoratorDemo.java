public class DecoratorDemo {
    public static void main(String[] args){
        Sequence sw = new SeqWord("ana are mere");
        Sequence swordUppered = new SeqUpper(new SeqWord("litere mici"));

        System.out.println("cuvant nemodificat de decorator");
        System.out.println(sw.parcurge());
        System.out.println("cuvant to upper" + " "+swordUppered);
        //System.out.println(swordUppered);
    }
}
