
import java.io.*;
import java.util.*;

public class Dijkstra {
    private Map<String,Integer> distances;
    private Set<Node> visited;
    private PriorityQueue<Node> pq;
    private ArrayList<Street> streets;
    private int noNodes, len;
    private ArrayList<String> passed;
    ArrayList<Node> list, nodes;
    public Dijkstra(GoogleMaps maps){
        this.distances = new HashMap<>();
        visited = new HashSet<Node>();
        pq = new PriorityQueue<Node>(maps.noNodes, new Node());
        this.list = new ArrayList<>();
        this.nodes = new ArrayList<>();
        this.noNodes = maps.noNodes;
        this.streets = maps.streets;
        this.passed = new ArrayList<>();
        len = -1;
    }
    public void createGraph(Vehicle vehicle){
        int gabarit = -1;
        if(vehicle instanceof Bike)
            gabarit = Bike.gabarit;
        else if(vehicle instanceof Motorcycle)
            gabarit = Motorcycle.gabarit;
        else if(vehicle instanceof Car)
            gabarit = Car.gabarit;
        else if(vehicle instanceof Truck)
            gabarit = Truck.gabarit;

        for(int i = 0 ;i < noNodes; i++)
            nodes.add(new Node("P" +i));
        for(Node startNode : nodes) {
            for (Street street : streets)
                if (startNode.getName().equals(street.getStart())) {
                    for (Node endNode : nodes) {
                        if (endNode.getName().equals(street.getEnd()) && gabarit <= street.getMaxGabarit())
                            startNode.addDestination(endNode, street.cost);
                    }
                }
        }
    }
    public void drive(String st,String end,Vehicle vehicle){
        Node root = null;
        for(int i = 0; i < noNodes; i++){
            String name = "P" + i;
            distances.put(name,Integer.MAX_VALUE);
        }
        for(Node findRoot: nodes)
            if(findRoot.getName().equals(st)) {
                root = findRoot;
                distances.put(st,0);
                root.setDistance(0);
            }
        pq.add(root);
        while(!pq.isEmpty()){
            Node neighbour = pq.remove();
            if(!neighbour.visited) {
                visitNeighbours(neighbour, vehicle);
                neighbour.setVisited(true);
                visited.add(neighbour);
            }

        }

    }
    public void visitNeighbours(Node root, Vehicle vehicle){
        int newDist = -1, edgeDist = -1,rootDist = -1;
        rootDist = distances.get(root.getName());
        for (Map.Entry<Node, Integer> edge : root.getDestinations().entrySet()){
            Node neighbour = edge.getKey();
            if(!visited.contains(neighbour)){
                neighbour.setParent(root);
                int distNeigh = distances.get(neighbour.getName());
                Street str = Street.findStreet(this.streets ,root.getName(), neighbour.getName());
                edgeDist = str.getVehicleCost(vehicle);
                newDist =  rootDist + edgeDist;
                if(newDist < distNeigh) {
                    neighbour.setDistance(newDist);
                    distances.put(neighbour.getName(), newDist);
                }
                pq.add(neighbour);
            }
        }
    }
    public void parents(String start,String end) {
        if(distances.get(end) == Integer.MAX_VALUE){
            passed.add(start);
            passed.add(end);
            passed.add("null");
            System.out.println(start + " "+end+ " "+"null");
        }
        else{
        if(len < 0) {
            len = distances.get(end);
            passed.add(Integer.toString(len));
        }
        if(end.equals(start)) {
            passed.add(start);
            for(int i = passed.size() - 1; i >= 0 ; i--)
                System.out.printf(passed.get(i) + " ");
            System.out.println();
            return;
        }
        for(Node findRoot: nodes)
            if(findRoot.getName().equals(end)) {
                passed.add(end);
                parents(start,findRoot.getParent().getName());
            }


        }
    }


}
