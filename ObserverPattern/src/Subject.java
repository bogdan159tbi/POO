import java.util.ArrayList;
import java.util.List;

public class Subject {
    private List<Observer> observers = new ArrayList<>();
    private int state;

    public int getState(){
        return  state;
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObservers();
    }
    public void attach(Observer obsv){
        observers.add(obsv);
    }
    public void notifyAllObservers(){
        for(Observer obs : observers){
            obs.update();
        }
    }

    public static void main(String[] args) {
        Subject sub = new Subject();

        new HexObserver(sub);
        new BInaryObserver(sub);
        System.out.println("first state = 15");
        sub.setState(15);
        System.out.println();
        System.out.println("second state = 10");
        sub.setState(10);

    }

}
