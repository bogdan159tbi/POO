public abstract class Observer {
    protected Subject subject;// protected => same package and subclasses
    public abstract  void update();

}
