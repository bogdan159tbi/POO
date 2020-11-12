public class Truck implements Vehicle  {
    static int gabarit = 3;
    static int cost = 6;
    String type = "autoutilitar";

    public int canPass(int maxGabarit){
        if(Truck.gabarit  <= maxGabarit){
            return 1;
        }
        return 0;
    }
    public int cost(Node street){
        return cost * street.getCost();
    }
}
