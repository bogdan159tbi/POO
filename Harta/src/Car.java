public class Car  implements Vehicle {
    static int gabarit = 2;
    static int cost = 4;
    String type = "autovehicul";

    public int canPass(int maxGabarit){
        if(Car.gabarit  <= maxGabarit){
            return 1;
        }
        return 0;
    }

    public int cost(Node street){
        return cost * street.getCost();
    }
}
