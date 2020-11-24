import javax.lang.model.type.ArrayType;
import java.util.ArrayList;
public class Street {
    public String start, end;
    public int cost, maxGabarit;
    ArrayList<Restriction> restrictions;
    Street(){

    }

    public Street(String start, String end, int cost, int maxGabarit, ArrayList<Restriction> restriction) {
        this.start = start;
        this.end = end;
        this.cost = cost;
        this.maxGabarit = maxGabarit;
        this.restrictions = new ArrayList<Restriction>();
    }


    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    public void addRestriction(Restriction restriction) {
        this.restrictions.add(restriction);
    }

    public int getRestriction() {
        int restrCost = 0;
        for(Restriction r : this.restrictions)
            restrCost += r.getTime();
        return restrCost;
    }

    public int getMaxGabarit() {
        return maxGabarit;
    }

    public int getVehicleCost(Vehicle v){
        int cost = 0;
        if(v instanceof Car)
            cost = this.getCost() * Car.cost + this.getRestriction();
        else if(v instanceof Bike)
            cost = this.getCost() * Bike.cost + this.getRestriction();
        else if(v instanceof Truck)
            cost = this.getCost() * Truck.cost + this.getRestriction();
        else if(v instanceof  Motorcycle)
            cost = this.getCost() * Motorcycle.cost + this.getRestriction();

        return cost; // trebuie string la return
    }
    public static Street findStreet(ArrayList<Street> list,String start,String end){
        Street street = null;
        for(Street str: list){
            if(str.getStart().equals(start) && str.getEnd().equals(end))
                street = str;
        }
        return street;
    }

}
