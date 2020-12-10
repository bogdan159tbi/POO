import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PizzaFactory {
    private static PizzaFactory instance = new PizzaFactory();
    /*
    constructor private pt ca class PizzaFactory sa nu poate fi instantiata
     */
    private PizzaFactory(){

    }
    public static PizzaFactory getInstance(){
        return instance;
    }
    public enum TipPizza {
        Diavola, Hawaii, Pepperoni
    }

    public Pizza getPizza(TipPizza pizzaType, int dimension, int price){
        switch(pizzaType){
            case Diavola:
                return new DiavolaPizza(dimension, price);
            case Hawaii:
                return new HawaiiPizza(dimension, price);
            case Pepperoni:
                return new PepperoniPizza(dimension, price);
        }

        throw new IllegalArgumentException("tipul de pizza" + pizzaType+ " " + "nu exista");

    }

}
