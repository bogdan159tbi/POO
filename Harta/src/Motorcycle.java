public class Motorcycle implements Vehicle  {
    static int gabarit = 1;
    static int cost = 2;
    String type = "moped";

    public int canPass(int maxGabarit){
        if(Motorcycle.gabarit  <= maxGabarit){
            return 1;
        }
        return 0;
    }

    public int cost(Node street){
        return this.cost * street.getCost();
    }
}
