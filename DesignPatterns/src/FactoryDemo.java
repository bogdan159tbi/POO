import java.util.ArrayList;
import java.util.List;

public class FactoryDemo {

    public static void main(String[] args){
        PizzaFactory pizzaFactory = PizzaFactory.getInstance();
        Pizza diavola = pizzaFactory.getPizza(PizzaFactory.TipPizza.Diavola,3, 30);
        Pizza pep = pizzaFactory.getPizza(PizzaFactory.TipPizza.Pepperoni,2, 20);
        Pizza hawaii = pizzaFactory.getPizza(PizzaFactory.TipPizza.Hawaii,1, 10);
        List<Pizza> frigider = new ArrayList<>();
        frigider.add(diavola);
        frigider.add(pep);
        frigider.add(hawaii);
        for(int i = 0 ;i < frigider.size(); i++){
            System.out.println(frigider.get(i));
        }
    }
}
