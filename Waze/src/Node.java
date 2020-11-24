import java.lang.reflect.Array;
import java.util.*;

public class Node implements  Comparator<Node> {
    String name;
    int distanceFromSource;
    public Node parent;
    public boolean visited = false;
    Map<Node, Integer> destinations = new HashMap<>(); //Node si dist src la Node
    public Node(){

    }
    public Node(String name){
        this.name = name;
    }
    public void setDistance(int distanceFromSource){
        this.distanceFromSource = distanceFromSource;
    }
    public int getDistance(){
        return this.distanceFromSource;
    }

    public void setParent(Node parent){
        this.parent = parent;
    }
    public Node getParent(){
        return this.parent;
    }

    public String getName(){
        return name;
    }
    public void setVisited(boolean visited){
        this.visited = visited;
    }

    public void setDestinations(Map<Node, Integer> destinations){
        this.destinations = destinations;
    }
    public Map<Node, Integer> getDestinations(){
        return this.destinations;
    }

    public void addDestination(Node destination, int distanceFromSource){
        this.destinations.put(destination, distanceFromSource);
    }
    /*
    pentru copiere nod  sa implementam metoda cloneable
     */

    public Node findClosest(ArrayList<Node> places){
        int minDist = Integer.MAX_VALUE;
        Node closestPoint = places.get(0);
        for(Node n : places){
            int dist = n.getDistance();
            if(dist < minDist){
                minDist = dist;
                closestPoint = n;
            }
        }
        return closestPoint;
    }
    public static Node findStart(ArrayList<Node> list,String name){
        for(Node n : list){
            if(n.getName().equals(name))
                return n;
        }
        return null;
    }
    public static Node findDest(ArrayList<Node> list,String name){
        for(Node n:list){
            for (Map.Entry<Node, Integer> edge : n.getDestinations().entrySet()){
                Node end = edge.getKey();
                if(end.getName().equals((name)))
                    return end;
            }
        }

        return null;
    }

    @Override
    public int compare(Node o1, Node o2) {
        if(o1.getDistance() < o2.getDistance())
            return -1;
        if(o1.getDistance() > o2.getDistance())
            return 1;
        return 0;
    }
}
