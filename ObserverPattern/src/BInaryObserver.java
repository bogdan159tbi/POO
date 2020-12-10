public class BInaryObserver extends Observer {
    public BInaryObserver(Subject sub) {
        this.subject = sub;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("binarry string" + " "+Integer.toBinaryString(subject.getState()));
    }
}
