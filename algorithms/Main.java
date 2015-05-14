import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
class Vertex implements Comparable<Vertex>
{
    public final String name;
    public List<Edge> adjacencies;
    public int minDistance = Integer.MAX_VALUE;
    public Vertex previous;
    public Vertex(int argName) { name = String.valueOf(argName); }
    public String toString() { return name; }
    public int compareTo(Vertex other)
    {
        return Double.compare(minDistance, other.minDistance);
    }

}


class Edge
{
    public final Vertex target;
    public final int weight;
    public Edge(Vertex argTarget, int argWeight)
    { target = argTarget; weight = argWeight; }
}

public class Main
{
	public static Vertex[] cities;
	public static List<Vertex> stores;
	public static List<Vertex> withoutStores;
        public static void computePaths(Vertex source){
			source.minDistance = 0;
			PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
			vertexQueue.add(source);

			while (!vertexQueue.isEmpty()) {
				Vertex u = vertexQueue.poll();
				// Visit each edge exiting u
				for (Edge e : u.adjacencies){
					Vertex v = e.target;
					int weight = e.weight;
					int distanceThroughU = u.minDistance + weight;
					if (distanceThroughU < v.minDistance) {
						vertexQueue.remove(v);
						v.minDistance = distanceThroughU ;
						v.previous = u;
						vertexQueue.add(v);
					}
				}
			}	
		}
    public static void main(String[] args)
    {	
		File file = new File("7.txt");
		Scanner sc = new Scanner(System.in);;
		try{
			sc = new Scanner(file);
		}
		catch (FileNotFoundException e){ 
		}
		int c = sc.nextInt();
		int r = sc.nextInt();
		int w = sc.nextInt();
		cities = new Vertex[c];
		stores = new ArrayList<Vertex>();
		withoutStores = new ArrayList<Vertex>();
		for (int x=0; x<c; x++){
			cities[x] = new Vertex(x);
			cities[x].adjacencies = new ArrayList<Edge>();
			withoutStores.add(cities[x]);
		}
		for (int x=0; x<r; x++){
			int s = sc.nextInt() -1;
			int t = sc.nextInt() -1;
			int d = sc.nextInt();
			cities[s].adjacencies.add(new Edge(cities[t], d));
			cities[t].adjacencies.add(new Edge(cities[s], d));
		}
		
		
		for (int x=0; x<w; x++){
			int y = sc.nextInt() -1;
			stores.add(cities[y]);
			withoutStores.remove(cities[y]);
		}
		int totalMin = Integer.MAX_VALUE;
		int newCity = -1;
		int min = 0;
		int tmp = Integer.MAX_VALUE;
		
        //computePaths(cities[0], stores[0]);
        for (Vertex v : cities)
		{
			//if not(v in stores)
			if (!(stores.contains(v))){
				//add to stores
				stores.add(v);
				//for (vertex v : cities)
				for (Vertex v2 : cities){
					//if not (v in stores)
					if (!(stores.contains(v2))){
						computePaths(v2);
						//sum min distance for every path that goes to a store
						for (Vertex v3 : cities){	
							if (stores.contains(v3)){
								if (v3.minDistance < tmp){
									tmp = v3.minDistance;
								}								
							}
							v3.minDistance = Integer.MAX_VALUE;	
						}			
						min += tmp;	
						tmp = Integer.MAX_VALUE;
					}
				}				
				//if totalMin < min
				if (min < totalMin){
					//totalMin = min, int new store = v.name
					totalMin = min;
					newCity = Integer.parseInt(v.name) + 1;
				}
				else if (min == totalMin){
					if (Integer.parseInt(v.name) < (newCity-1)){
						newCity = Integer.parseInt(v.name) + 1;
					}
				}
				min = 0;
				//remove from stores
				stores.remove(v);				
			}			
		}
		System.out.println(newCity);	
    }
}
