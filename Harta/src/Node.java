import java.util.*;
public class Node implements Comparator<Node>{
    int start, end, cost, gabarit;
    int costRestrictions;//suma restrictiilor fie ca e acc sau blocaj sau trafic
    String name;

    public Node(){
        start = 0;
        end = 0;
    }
    public Node(int end,int cost, int gabarit){
        this.end = end;
        this.cost = cost;
        this.gabarit = gabarit;
        this.costRestrictions = 0;
    }

    public void setGabarit(int gabarit){
        this.gabarit = gabarit;
    }
    public int getGabarit(){
        return this.gabarit;
    }

    public void setCost(int cost){
        this.cost = cost ;
    }
    public int getCost(){
        return this.cost;
    }

    public void addRestriction(int restiction){
        this.costRestrictions += restiction;
    }
    public int getCostRestrictions(){
        return this.costRestrictions;
    }
    @Override
    public int compare(Node n1, Node n2){
        if(n1.getCost() > n2.getCost())
            return 1;
        else if(n1.getCost() < n2.getCost())
            return -1;
        return 0;
    }
}
