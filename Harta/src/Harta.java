import java.util.*;
import java.io.*;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Harta {
    private static BufferedWriter writer ;
    private int[] distances;
    private Set<Integer> visited;
    private PriorityQueue<Node> qDistances;
    private final int nodes, edges;
    List<List<Node> > graph;
    private int[] parents;

    public Harta(){
        nodes = 0;
        edges = 0;
    }
    public void out(String name){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(name, true));
            Harta.writer = writer;
            //myWriter.write
            //map.showGraph();
            //writer.close();
        }catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public Harta(int nodes, int edges){
        this.nodes = nodes;
        this.edges = edges;
        distances = new int[nodes];
        visited = new HashSet<Integer>();
        qDistances = new PriorityQueue<Node>(nodes, new Node());
        this.parents = new int[nodes];
    }

    public void dijkstra(List<List<Node> > graph, int src,Vehicle auto){
        this.graph = graph;
        this.parents = new int[nodes];
        /*
        le am initializat din nou pentru a reface metoda atunci cand
        se da comanda drive din nou
         */
        this.visited = new HashSet<Integer>();
        this.qDistances = new PriorityQueue<Node>(nodes, new Node());
        for (int i = 0; i < nodes; i++)
            distances[i] = Integer.MAX_VALUE;

        // Add source node to the priority queue
        Node node = new Node(src, 0,0);
        qDistances.add(node);
        // Distance to the source is 0
        distances[src] = 0;
        int[] parents = new int[this.nodes];

        while (visited.size() != nodes && !qDistances.isEmpty()) {

            // remove the minimum distance node
            // from the priority queue
            Node first =  qDistances.remove();
            int u= first.end;
            // adding the node whose distance is
            // finalized
            visited.add(u);

            e_Neighbours(u,auto);
        }

    }
    public int  getStreetCost(int start, int end){
        for(List<Node> edges : graph) {
            for (Node node : edges) {
                if (node.start == start && node.end == end)
                    return node.getCost();
            }
        }
        return Integer.MAX_VALUE;
    }
    private void e_Neighbours(int u,Vehicle auto)
    {
        int edgeDistance = -1;
        int newDistance = -1;

        int costAuto = 0, gabAuto = 200;//200 pt ca e mai mare decat toate gab si nu se incurca cu niciun tip
        if(auto instanceof  Bike) {
            costAuto = Bike.cost;
            gabAuto = Bike.gabarit;
        }
        else if(auto instanceof  Truck) {
            costAuto = Truck.cost;
            gabAuto = Truck.gabarit;

        }
        else if(auto instanceof  Motorcycle) {
            costAuto = Motorcycle.cost;
            gabAuto = Motorcycle.gabarit;

        }
        else if(auto instanceof Car) {
            costAuto = Car.cost;
            gabAuto = Car.gabarit;

        }

        // All the neighbors of v
        for (int i = 0; i < graph.get(u).size(); i++) {
            Node v = graph.get(u).get(i);

            // If current node hasn't already been processed
            if (!visited.contains(v.end) &&  gabAuto <= v.gabarit) {
                edgeDistance = v.getCost() * costAuto + v.getCostRestrictions() ;
                newDistance = distances[u] + edgeDistance;
                //System.out.println("nodul" +" " +u + "catre" +" "+ v.end+ " "+ edgeDistance);
                parents[v.end] = u;
                // If new distance is cheaper in cost
                if (newDistance < distances[v.end])
                    distances[v.end] = newDistance;

                // Add the current node to the queue
                qDistances.add(new Node(v.end, distances[v.end],v.gabarit));
            }
        }
    }

    public void addStreet(int start, int end, int cost,int gabarit){
        Node n = new Node(end, cost,gabarit);
        this.graph.get(start).add(n);
    }
    public void addRestriction(String type, int start, int end, int cost){
        //1. gaseste muchia/strada unde trebuia adaugata resticita
        //2. adauga restrictia
        for(List<Node> edges : graph) {
            for (Node node : edges) {
                if (node.start == start && node.end == end)
                    node.costRestrictions += cost;
            }
        }
    }
    public void showGraph(){
        int src = 0;
        for(List<Node> edges : graph) {
            for (Node node : edges) {
                System.out.print(src +"->" + " "+ node.end + " " +node.getCost() + " ");
            }
            src += 1;
            System.out.println();
        }

    }

    public void getPath(int end, BufferedWriter myWriter ){
        /*
        cazul cand distanta e null si tre afisat doar start si end
         */
        if(this.distances[end] == Integer.MAX_VALUE){
            int src = -1;
            for(int i = 0 ;i < this.nodes; i++){
                if(parents[i] == -1)
                    src = i;
            }
            if(src > -1) {
                try {
                    myWriter.write("P" + src + " ");
                    return;
                }catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
                System.out.print("P" + src + " ");
            }
            return;
        }
        if(parents[end] == -1){
            try {
                myWriter.write("P" + end + " ");
                return;
            }catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

        }
        getPath(parents[end], myWriter);
        /*
        for(int i = 0 ; i < this.nodes; i++)
            System.out.print(i  + "=" +" " +parents[i]+" ");
        System.out.println();

         */
        try {
            myWriter.write("P"+end +" " );
        }catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        //System.out.print("P"+end +" " );
    }
    public void getLength(int end, BufferedWriter myWriter ){
        if(this.distances[end] == Integer.MAX_VALUE){
            try {
                myWriter.write("null");
            }catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

        }
        else {
            try {
                int value = this.distances[end];
                myWriter.write(String.valueOf(value));
            }catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }

        //afisarea tuturor distantelor de la sursa la restul nodurilor
        /*
        for(int i = 0 ; i < this.distances.length; i++){
            if(this.distances[i] < Integer.MAX_VALUE)
                System.out.println("dist de la sursa " + " " +i +" " + "distanta" + " "+ this.distances[i]);
        }
          */
    }
    /*
     punctele prin care trece drumul si costul total al drumului.
     trebuie sa adaug la drive costul * cost vehicul + restrictii
     cred ca fac drive in loc de dijkstra si adaug parametru Vehicle
     ca sa calculez dist ca fiind Node.getCost() * Vehicle(care o fi).cost + node.getCostRestrictions()
     */
    public void drive(Vehicle v, int start, int end){


    }
    public int nodeIndex(String word){
        int index = -1;
        if(word.length() == 2)
            index = Character.getNumericValue(word.charAt(1));
        else if(word.length() >= 3) {
            String cpy = word.substring(1);
            index = Integer.parseInt(cpy);
        }
        return index;
    }
    public static void main(String arg[]){
        int nodes ,edges ;
        Harta map = new Harta();
        List<List<Node> > graph = new ArrayList<List<Node> >();
        // Initialize list for every node
        try{
            File input = new File("/home/oh/IdeaProjects/Harta/src/map2.in");
            Scanner sc = new Scanner(input);
            map.out("/home/oh/IdeaProjects/Harta/src/map2.out");

            while (sc.hasNextLine()){
                String line = sc.nextLine();
                String words[] = line.split(" ");
                /*
                in loc sa folosesc hash map,pt fiecare nod iau doar numarul si denumirea o afisez la sfarsit
                daca primul carac de pe linie e P
                    interpetez ca fiind o muchie
                altfel
                    interpretez ca fiind accident/trafic/blocaj
                    citesc dupa nodurile unde tre sa adaug costul suplimentar
                 */
                char firstLetter = words[0].charAt(0);
                if (words[0].charAt(0) == 'P'){
                    int start = map.nodeIndex(words[0]);
                    int end = map.nodeIndex(words[1]);
                    int cost = Integer.parseInt(words[2]);
                    int gabarit = Integer.parseInt(words[3]);
                    map.addStreet(start,end,cost,gabarit);
                    //graph.get(start).add(new Node(end, cost, gabarit)); nu stiu sigur daca asta e echiv cu linia de mai sus
                }
                else if(words[0].charAt(0) > '0' && words[0].charAt(0) < '9' ) {
                    //cand citim prima linie pt noduri si muchii
                    nodes = Integer.parseInt(words[0]);
                    edges = Integer.parseInt(words[1]);
                    for (int i = 0; i < nodes; i++) {
                        List<Node> item = new ArrayList<Node>();
                        graph.add(item);
                    }
                    map = new Harta(nodes,edges);
                    map.graph = graph;
                }
                else if(firstLetter == 'a' || firstLetter == 'b' || firstLetter == 't') { // abordez cazurile pt ambuteiaj
                    int start = map.nodeIndex(words[1]);
                    int end = map.nodeIndex(words[2]);
                    int cost = Integer.parseInt(words[3]);
                    map.addRestriction("accident",start,end,cost);
                }
                else if(firstLetter == 'd'){
                    char vehicul = words[1].charAt(0);
                    int start = map.nodeIndex(words[2]);
                    int end = map.nodeIndex(words[3]);
                    if(vehicul == 'c')
                        map.dijkstra(graph,start,new Truck());
                    else if(vehicul == 'b')
                        map.dijkstra(graph,start,new Bike());
                    else if(vehicul == 'a')
                        map.dijkstra(graph,start,new Car());
                    else if(vehicul == 'm')
                        map.dijkstra(graph,start,new Motorcycle());
                    //doar am gasit distanta finala dar nu am retinut si path
                    map.parents[start] = -1;
                    map.getPath(end, map.writer);
                    map.getLength(end, map.writer);
                    try {
                        map.writer.write('\n');
                    }catch (IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }
                }
                //else
                //System.out.println("nimic");
            }
            sc.close();
            try {
                map.writer.close();
            }catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

        } catch (FileNotFoundException e){
            System.out.println("an error occured,file not found");
            e.printStackTrace();
        }

    }


}