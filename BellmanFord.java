import java.util.Scanner;

public class BellmanFord {
    private int D[];
    private int numVertices;
    public static final int MAX_VALUE = 999;

    public BellmanFord(int numVertices) {
        this.numVertices = numVertices;
        D = new int[numVertices + 1];
    }

    public void bellmanFordEvaluation(int source, int[][] adjacencyMatrix) {
        // Initialize distances from source to all vertices as maximum
        for (int vertex = 1; vertex <= numVertices; vertex++) {
            D[vertex] = MAX_VALUE;
        }
        D[source] = 0; // Distance from source to itself is 0

        // Relax all edges |V| - 1 times
        for (int node = 1; node <= numVertices - 1; node++) {
            for (int sourceNode = 1; sourceNode <= numVertices; sourceNode++) {
                for (int destinationNode = 1; destinationNode <= numVertices; destinationNode++) {
                    // Update the distance if a shorter path exists through node 'sourceNode'
                    if (adjacencyMatrix[sourceNode][destinationNode] != MAX_VALUE) {
                        if (D[destinationNode] > D[sourceNode] + adjacencyMatrix[sourceNode][destinationNode]) {
                            D[destinationNode] = D[sourceNode] + adjacencyMatrix[sourceNode][destinationNode];
                        }
                    }
                }
            }
        }

        // Check for negative-weight cycles
        for (int sourceNode = 1; sourceNode <= numVertices; sourceNode++) {
            for (int destinationNode = 1; destinationNode <= numVertices; destinationNode++) {
                if (adjacencyMatrix[sourceNode][destinationNode] != MAX_VALUE) {
                    if (D[destinationNode] > D[sourceNode] + adjacencyMatrix[sourceNode][destinationNode]) {
                        System.out.println("Graph contains negative edge cycle");
                        return;
                    }
                }
            }
        }

        // Print distances from source to each vertex
        for (int vertex = 1; vertex <= numVertices; vertex++) {
            System.out.println("Distance of source " + source + " to vertex " + vertex + " is " + D[vertex]);
        }
    }

    public static void main(String[] args) {
        int numVertices = 0;
        int source;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of vertices");
        numVertices = scanner.nextInt();

        int[][] adjacencyMatrix = new int[numVertices + 1][numVertices + 1];

        System.out.println("Enter the adjacency matrix");
        for (int sourceNode = 1; sourceNode <= numVertices; sourceNode++) {
            for (int destinationNode = 1; destinationNode <= numVertices; destinationNode++) {
                adjacencyMatrix[sourceNode][destinationNode] = scanner.nextInt();

                if (sourceNode == destinationNode) {
                    adjacencyMatrix[sourceNode][destinationNode] = 0;
                }

                if (adjacencyMatrix[sourceNode][destinationNode] == 0) {
                    adjacencyMatrix[sourceNode][destinationNode] = MAX_VALUE;
                }
            }
        }

        System.out.println("Enter the source vertex");
        source = scanner.nextInt();

        BellmanFord bellmanFord = new BellmanFord(numVertices);
        bellmanFord.bellmanFordEvaluation(source, adjacencyMatrix);

        scanner.close();
    }
}
