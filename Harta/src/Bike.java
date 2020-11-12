public class Bike implements Vehicle {
    static int gabarit = 1;
    static int cost = 1;
    static String type = "moped";

    public Bike(){

    }
    @Override
    public int canPass(int maxGabarit){
        if(Bike.gabarit  <= maxGabarit){
            return 1;
        }
        return 0;
    }
    public int cost(Node street){
        return cost * street.getCost();
    }

}
