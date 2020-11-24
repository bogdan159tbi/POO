public class Restriction {
    String start, end;
    int time;
    Restriction(){

    }
    Restriction(String start, String end, int time){
        this.start = start;
        this.end = end;
        this.time = time;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public int getTime() {
        return time;
    }

}
