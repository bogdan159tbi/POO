import java.io.*;
import java.util.*;

public class GoogleMaps {
    public int noStreets;
    public int noNodes;
    ArrayList<Street> streets;
    //Map<Node, Integer>distances;

    public GoogleMaps() {

    }
    public GoogleMaps(int noNodes, int noStreets) {
        this.noNodes = noNodes;
        this.noStreets = noStreets;
        this.streets = new ArrayList<>();
        //this.links = new ArrayList<Node>();
        //this.distances = new HashMap<>();
    }

    public int getNoStreets() {
        return noStreets;
    }

    public int getNoNodes() {
        return noNodes;
    }

    public ArrayList<Street> getStreets() {
        return streets;
    }

    public void addStreet(String start, String end, int cost, int maxGabarit){
        Street s = new Street(start, end, cost, maxGabarit, null );
        streets.add(s);

    }
    public void addRestriction(String type,String start,String end, int time){
        Restriction r = new Restriction(start, end, time);
        Street str = Street.findStreet(this.streets, start, end);
        str.addRestriction(r);
    }

    public static void main(String[] args) throws IOException {

        try{
        String fileName = "/home/oh/IdeaProjects/Waze/src/map6.in";
        String outName = "map.out";
        int noStreets = 0, noNodes = 0, streetCost = 0, streetGab = 0, streetRestriciton = 0;
        String start = null, end = null, vehicle = null;
        Scanner sc = new Scanner(new File(fileName));
        noStreets = sc.nextInt();
        noNodes = sc.nextInt();
        GoogleMaps maps = new GoogleMaps(noNodes, noStreets);

        for (int i = 0; i < noStreets; i++) {
            start = sc.next();
            end = sc.next();
            streetCost = sc.nextInt();
            streetGab = sc.nextInt();
            maps.addStreet(start, end, streetCost, streetGab);
        }

        while (sc.hasNext()) {
            String event = sc.next();
            if (event.equals("drive")) {
                String veh = sc.next();
                start = sc.next();
                end = sc.next();
                if (veh.equals("a")) {
                    Car c = new Car();
                    Dijkstra path = new Dijkstra(maps);
                    path.createGraph(c);
                    path.drive(start,end,c);
                    path.parents(start,end);
                } else if (veh.equals("c")) {
                    Truck t = new Truck();
                    Dijkstra path = new Dijkstra(maps);
                    path.createGraph(t);
                    path.drive(start,end,t);
                    path.parents(start, end);
                } else if (veh.equals("b")) {
                    Bike b = new Bike();
                    Dijkstra path = new Dijkstra(maps);
                    path.createGraph(b);
                    path.drive(start,end, b);
                    path.parents(start, end);
                } else if (veh.equals("m")) {
                    Motorcycle m = new Motorcycle();
                    Dijkstra path = new Dijkstra(maps);
                    path.createGraph(m);
                    path.drive(start,end, m);
                    path.parents(start, end);
                }

            } else if (event.equals("blocaj") || event.equals("accident") || event.equals("trafic")) {
                start = sc.next();
                end = sc.next();
                streetRestriciton = sc.nextInt();
                Street str = Street.findStreet(maps.streets, start, end);
                Restriction r = new Restriction(start, end, streetRestriciton);
                str.addRestriction(r);
            }
            //maps.printMap();
        }
        sc.close();
    } catch (FileNotFoundException e){
        System.out.println("an error occured,file not found");
        e.printStackTrace();
        }

    }


}
