import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

/**
 * Dijkstra's Algorithm on an input consisting of a start, goal, valid & not-valid paths
 * to reach the goal. Edges all have a weight of 1.
 * Created by Corey Short on 10/10/2015.
 */
public class Solution {

    public static void main(String[] args) throws FileNotFoundException {
        /*
         * You will need to change the PATH of the input file to test
         * this class on your system
         */
        File file = null;
        /* Determine Operating System */
        final String OS = OsUtils.getOsName();
        if (OS.equals("Windows"))
            file = new File("C:\\Users\\Short\\Desktop\\repo\\Hacker-Rank\\GE\\src\\input.txt");
        if (OS.equals("Linux"))
            file = new File("/home/short/Desktop/repo/Company-Coding-Examples/GE/input.txt");

        assert file != null;

        final Scanner sc = new Scanner(file);
        final String line = sc.nextLine();
        final int N = Integer.parseInt(line);

        final Node[][] ar = new Node[N][N];
        boolean[][] visited = new boolean[N][N];

        //final List<Vertex> vertexList = new ArrayList<>(N*N);
        //final List<Edge> edgeList = new ArrayList<>();
        Node start = null;
        Node goal = null;
        //String prevID = "";
        /* Unvisited Vertex set */
        final Set<Node> unvisited = new HashSet<>();
        for (int row = 0; row < ar.length; row++) {
            for (int col = 0; col < ar[row].length; col++) {
                final String curID = sc.next();
                System.out.print(curID + " ");
                /* Start Vertex */
                if (curID.equals("S")) {
                    start = new Node(row, col);
                    start.setId(curID);
                    start.setDistance(0);
                    ar[row][col] = start;
                    //vertexList.add(new Vertex(curID));
                    //unvisited.add(start);
                }
                /* Goal Vertex */
                if (curID.equals("E")) {
                    goal = new Node(row, col);
                    goal.setId(curID);
                    ar[row][col] = goal;
                    //unvisited.add(goal);
                    //vertexList.add(new Vertex(curID));
                }
                /* A valid path */
                if (curID.equals("P")) {
                    ar[row][col] = new Node(row, col);
                    ar[row][col].setId(curID);
                    //unvisited.add(ar[row][col]);
                }
                /* A tree blocks our path. The Vertex is blocked */
                if (curID.equals("T")) {
                    ar[row][col] = new Node(row, col);
                    ar[row][col].setId(curID);
                    ar[row][col].setBlocked(true);
                }
                visited[row][col] = false;
                /*if (!prevID.equals(""))
                    edgeList.add(new Edge(vertexList.get(), vertexList.get(curID), 1));
                }*/
            }
            System.out.print("\n");
        }
        //final Graph dijkstra = new Graph(vertexList, edgeList);
        /*graph.execute(start);
        LinkedList path = graph.getPath(goal);

        assert (path != null && path.size() > 0);

        for (Vertex vertex : path) {
            System.out.print(vertex);
        }*/

        final int result = dijkstras(ar, start, goal, visited);
        System.out.printf("%d%n", result);
    }

    private static int dijkstras(Node[][] ar, Node start, Node goal, boolean[][] visited) {
        final String goalNode = goal.getId();
        int bfsDepth = 0;

        /* Parent node list keeps track of visited nodes */
        Node[][] parent = new Node[ar.length][ar.length];
        /* init parent */
        for (int row = 0; row < ar.length; row++) {
            for (int col = 0; col < ar[row].length; col++) {
                parent[row][col] = null;
            }
        }

        final int rows = ar.length;
        final int cols = ar.length;
        /* The shortest path of nodes. We return its size */
        final List<Node> shortestPath = new LinkedList<>();

        final Queue<Node> queue = new LinkedList<>();
        queue.add(start);

        while (queue.size() > 0) {
            /* Pop off node in queue. Check its location to see if curNode has reached the goal state */
            final Node curNode = queue.remove();
            final int curX = curNode.getX();
            final int curY = curNode.getY();
            System.out.println(curNode.getId() + " " + curX + " " + curY);
            /* We have visited this node so we should mark that we have been here */
            visited[curX][curY] = true;
            // Start will never be equal to the goal ?
            /* We have reached the goal node */
            if (curNode.getId().equals(goalNode)) {
                 return curNode.distance;


                /*final List<Node> curPath = new LinkedList<>();
                while (parent[curX][curY] != start) {
                    System.out.println("curNode " + curNode.getId() + "  parent " + parent[curX][curY].getId());
                    Node tmp = parent[curX][curY];
                    curPath.add(tmp);
                    //System.out.println(curPath.size());
                    //parent[curX][curY]
                }
                System.out.println("outside while");
                if (curPath.size() < Integer.MAX_VALUE) {
                    System.out.println("in if");
                    shortestPath.clear();
                    shortestPath.addAll(curPath);
                }*/
            }
            /* Check our neighbors, the array bounds, if our path is blocked, and if we have visited a node yet */
            /* If true then add the neighboring node to our queue */
            /* Save a reference to the node's parent */
            //if (curY+1 < cols && (!ar[curX][curY+1].isBlocked()) && !visited[curX][curY+1]) {
            /* Top neighbor */
            if (curY+1 < cols && (!ar[curX][curY+1].getId().equals("T")) && !visited[curX][curY+1]) {
                ar[curX][curY+1].distance += 1;
                queue.add(ar[curX][curY+1]);
                System.out.println(ar[curX][curY+1].distance);
                //queue.add(new Node(curX, curY+1));
                //parent[curX][curY+1] = curNode;
            }
            /* Bottom neighbor */
            if (curY-1 >= 0 && (!ar[curX][curY-1].getId().equals("T")) && !visited[curX][curY-1]) {
                ar[curX][curY-1].distance += 1;
                System.out.println(ar[curX][curY-1].distance);
                queue.add(ar[curX][curY-1]);
                //parent[curX][curY-1] = curNode;
            }
            /* Left neighbor */
            if (curX-1 >= 0 && (!ar[curX-1][curY].getId().equals("T")) && !visited[curX-1][curY]) {
                ar[curX][curX-1].distance += 1;
                queue.add(ar[curX-1][curY]);
                //parent[curX-1][curY] = curNode;
                System.out.println(ar[curX][curX-1].distance);
            }
            /* Right neighbor */
            if (curX+1 < rows &&  (!ar[curX+1][curY].getId().equals("T")) && !visited[curX+1][curY]) {
                ar[curX][curX+1].distance += 1;
                queue.add(ar[curX+1][curY]);
                //parent[curX+1][curY] = curNode;
                System.out.println(ar[curX][curX+1].distance);
            }
            //bfsDepth += 1;
        }
        return bfsDepth;
    }


        /*int[] dist = new int[ar.length*ar.length];

        int shortestPath = 0;
        Node[] neighbors = checkNeighbors(ar, curNode);
        /* Calculate distance to neighbors
        for (Node neighbor : neighbors) {
            int x2 = neighbor.getX();
            int y2 =neighbor.getY();
            if ()


            unvisited.remove(neighbor);
        }



        return shortestPath;
    }

    private static void comparePaths(Vertex start) {
        //for (Edge e : )
    }*/

    private static Node[] checkNeighbors(Node[][] ar, Node curNode) throws IndexOutOfBoundsException {
        /* Valid neighbors are North, East, South, or West of curVertex */
        final Node[] neighbors = new Node[4];
        int x = curNode.getX();
        int y = curNode.getY();
        neighbors[0] = ar[x-1][y];
        neighbors[1] = ar[x+1][y];
        neighbors[2] = ar[x][y-1];
        neighbors[3] = ar[x][y+1];
        return neighbors;
    }

    private static class Node {
        private String id;
        private int x;
        private int y;
        private int distance;
        private boolean blocked;
        private int weight;

        public Node(int xPos, int yPos) {
            x = xPos;
            y = yPos;
            distance = 0;
            blocked = false;
            weight = 1;
        }

        public int getX() { return x; }
        public int getY() { return y; }
        public String getId() { return id; }
        public int getWeight() { return weight; }
        public boolean isBlocked() { return blocked; }

        public void setDistance(int distance) { this.distance = distance; }
        public void setBlocked(boolean bool) { blocked = bool; }
        public void setId(String id) { this.id = id; }
    }

    private static class Graph {
        private final List<Vertex> vertexList;
        private final List<Edge> edgeList;

        public Graph(List<Vertex> vertexes, List<Edge> edges) {
            vertexList = vertexes;
            edgeList = edges;
        }

        public List<Vertex> getVertexList() { return vertexList; }
        public List<Edge> getEdgeList() { return edgeList; }
    }

    private static class Vertex {
        final private String id;
        private int index;

        public Vertex(String id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object obj) {
            if (this != obj || this.id == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Vertex v = (Vertex) obj;
            return this.id.equals(v.id);
        }

        public String getId() { return id; }
        public int getIndex() { return index; }

        public void setIndex(int index) { this.index = index; }
    }

    private static class Edge {
        private int weight ;
        private Vertex source;
        private Vertex target;

        public Edge(Vertex source, Vertex target, int weight) {
            this.source = source;
            this.target = target;
            /* In case we want to search graphs with weighted edges later */
            this.weight = weight;
        }

        public Vertex getDestination() { return target; }
        public Vertex getSource() { return source; }
        public int getWeight() { return weight; }

        @Override
        public String toString() {
            return source + " " + target;
        }
    }

    private static int findMinPath(char[][] ar) {
        /* right, left, down, up */
        int[] x = {0,0,1,-1};
        int[] y = {1,-1,0,0};
        LinkedList<Character> queue = new LinkedList<>();
        /* start */
        int n = ar.length;
        int m = ar[0].length;
        int[][] dist = new int[n][m];
        for (int[] a : dist) {
            /* init with -1 */
            Arrays.fill(a, -1);
        }
        dist[0][0] = 0;
        while (!queue.isEmpty()) {
            Character p = queue.removeFirst();
            for (int i=0; i < 4; i++) {
                /*int a = p.x + x[i];
                int b = p.y = y[i];
                if (a >= 0 && b >= && a < n && b < m && dist[a][b] == -1 && ar[a].charAt(b) != 'T') {
                    dist[a][b] = 1 + dist[p.x][p.y];
                    queue.add(new Character(a,b));
                }*/
            }

        }
        return 0;
    }

    /* Determine Operating System */
    public static final class OsUtils {
        private static String OS = null;

        public static String getOsName() {
            if (OS == null) {
                OS = System.getProperty("os.name");
            }
            return OS;
        }

        public static boolean isWindows() {
            return getOsName().startsWith("Windows");
        }

        public static boolean isUnix() {
            return getOsName().startsWith("Linux");
        }
    }
}
