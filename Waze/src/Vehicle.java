public abstract class Vehicle {
    abstract int getCost();
    abstract int getGabarit();
}

class Bike extends Vehicle{
    static int gabarit = 1;
    static int cost = 1;
    static String type = "moped";

    public int getCost(){
        return Bike.cost;
    }
    public int getGabarit(){
        return Bike.gabarit;
    }

}
class Car extends Vehicle{
    static int gabarit = 2;
    static int cost = 4;
    static String type = "car";

    public int getCost(){
        return Car.cost;
    }
    public int getGabarit(){
        return Car.gabarit;
    }
}
class Motorcycle extends Vehicle{

    static int gabarit = 1;
    static int cost = 2;
    static String type = "moped";
    public int getCost(){
        return Motorcycle.cost;
    }
    public int getGabarit(){
        return Motorcycle.gabarit;
    }
}
class Truck extends Vehicle{
    static int gabarit = 3;
    static int cost = 6;
    static String type = "autoutilitar";

    public int getCost(){
        return Truck.cost;
    }
    public int getGabarit(){
        return Truck.gabarit;
    }
}
