
package twocitiesshortestroute;
import java.util.*;

class TwoCitiesShortestRoute {
    static class Edge {
        int destination;
        int weight;

        public Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    static class Graph {
        private Map<Integer, List<Edge>> adjList;

        public Graph() {
            adjList = new HashMap<>();
        }

        public void addEdge(int source, int destination, int weight) {
            adjList.putIfAbsent(source, new ArrayList<>());
            adjList.get(source).add(new Edge(destination, weight));
        }

        public int[] dijkstra(int start, int numCities) {
            int[] distances = new int[numCities];
            Arrays.fill(distances, Integer.MAX_VALUE);
            distances[start] = 0;

            PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(node -> distances[node]));
            pq.add(start);

            while (!pq.isEmpty()) {
                int currentNode = pq.poll();

                if (adjList.containsKey(currentNode)) {
                    for (Edge edge : adjList.get(currentNode)) {
                        int neighbor = edge.destination;
                        int weight = edge.weight;

                        if (distances[currentNode] + weight < distances[neighbor]) {
                            distances[neighbor] = distances[currentNode] + weight;
                            pq.add(neighbor);
                        }
                    }
                }
            }
            return distances;
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph();        String[] cities = {"Davao City", "Digos City"};


        graph.addEdge(0, 1, 4); 

        int startCity = 0;
        int numCities = cities.length;
        int[] distances = graph.dijkstra(startCity, numCities);

        System.out.println("Shortest distances from " + cities[startCity] + ":");
        for (int i = 0; i < numCities; i++) {
            if (distances[i] == Integer.MAX_VALUE) {
                System.out.println(cities[i] + " is unreachable.");
            } else {
                System.out.println(cities[i] + ": " + distances[i]);
            }
        }
    }
}
