public abstract class Pizza {
    int dimensiune, pret;

    public Pizza(int dimensiune, int pret) {
        this.dimensiune = dimensiune;
        this.pret = pret;
    }

    public  abstract String toString();
}
class DiavolaPizza extends Pizza{
    public DiavolaPizza(int dimensiune, int pret){
        super(dimensiune,pret);
    }
    @Override
    public String toString() {
        return "DiavolaPizza" + " "+
                 dimensiune + " "+
                 pret;
    }
}

class PepperoniPizza extends Pizza{
    public PepperoniPizza(int dimensiune, int pret) {
        super(dimensiune, pret);
    }

    @Override
    public String toString() {
        return "PepperoniPizzași" + " "+
                dimensiune + " "+
                pret;
    }
}

class HawaiiPizza extends Pizza{
    public HawaiiPizza(int dimensiune, int pret) {
        super(dimensiune, pret);
    }
    @Override
    public String toString() {
        return "HawaiiPizza" + " "+
                dimensiune + " "+
                pret;
    }
}