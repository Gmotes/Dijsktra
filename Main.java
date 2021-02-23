import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Main {

    static Graph graph = new Graph();
    static HashMap<String,Node> nodeHashMap = new HashMap<>();
    static  int[][] pyramide = new int[][]{
        {215,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {193,124,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {117,237,442,0,0,0,0,0,0,0,0,0,0,0,0},
        {218,935,347,235,0,0,0,0,0,0,0,0,0,0,0},
        {320,804,522,417,345,0,0,0,0,0,0,0,0,0,0},
        {229,601,723,835,133,124,0,0,0,0,0,0,0,0,0},
        {248,202,277,433,207,263,257,0,0,0,0,0,0,0,0},
        {359,464,504,528,516,716,871,182,0,0,0,0,0,0,0},
        {461,441,426,656,863,560,380,171,923,0,0,0,0,0,0},
        {381,348,573,533,447,632,387,176,975,449,0,0,0,0,0},
        {223,711,445,645,245,543,931,532,937,541,444,0,0,0,0},
        {330,131,333,928,377,733,017,778,839,168,197,197,0,0,0},
        {131,171,522,137,217,224,291,413,528,520,227,229,928,0,0},
        {223,626,034,683,839,053,627,310,713,999,629,817,410,121,0},
        {924,622,911,233,325,139,721,218,253,223,107,233,230,124,233},

    };

    static int numberOfRowAndColumn = 15;

    public static boolean checkIfPrime(int num){
        boolean flag = false;
        for (int i = 2; i <= num / 2; ++i) {
            // condition for nonprime number
            if (num % i == 0) {
                flag = true;
                break;
            }
        }

        return !flag;
    }

    public static void main(String[] args) {

        //
        for (int i=0;i<=numberOfRowAndColumn-1;i++){

            for (int j=0;j<=numberOfRowAndColumn-1;j++) {
                fillNodes(i,j);
            }
        }

        for (int i=0;i<=numberOfRowAndColumn-1;i++){

            for (int j=0;j<=numberOfRowAndColumn-1;j++) {

                Node node =  (Node)nodeHashMap.get(i+"-"+j);
                if (node != null)
                fillDestinations(i,j,node);

            }
        }

        Node node =  (Node)nodeHashMap.get(0+"-"+0);
        graph = Dijkstra.calculateShortestPathFromSource(graph, node);

        Iterator<Node> iterator= graph.getNodes().iterator();
        while(iterator.hasNext()) {
             node = iterator.next();
             if (node.getName().startsWith("14")) {
                 System.out.println("Node: " + node.getName() + " - " + node.getDistance());
                 List<Node> nodes = node.getShortestPath();
                 for (Node node1:nodes) {
                     System.out.println("Node path: " + node1.getName());
                 }
             }
        }

    }

    public static void fillNodes(int row,int column){

        if (pyramide[row][column] != 0 && !checkIfPrime(pyramide[row][column])) {

            Node node = new Node(row+"-"+column);

            nodeHashMap.put(row+"-"+column,node);
            graph.addNode(node);
        }
    }

    public static void fillDestinations(int row,int column,Node node) {

        if (row+1 < numberOfRowAndColumn+1) {
            //System.out.println("Node: "+node.getName());
            //left downward
            if (column-1>=0) {
                Node leftNode = (Node)nodeHashMap.get((row+1)+"-"+(column-1));
                if (leftNode!=null) {
              //      System.out.println("Left Node: " + leftNode.getName());
                    node.addDestination(leftNode, pyramide[row + 1][column - 1]);
                }
            }

            // down
            Node downNode = (Node)nodeHashMap.get((row+1)+"-"+(column));
            if (downNode!=null) {
              //  System.out.println("Down Node: " + downNode.getName());
                node.addDestination(downNode, pyramide[row + 1][column]);
            }


            //right downward
            if (column+1<numberOfRowAndColumn) {
                Node rightNode = (Node)nodeHashMap.get((row+1)+"-"+(column+1));
                if (rightNode!= null) {
                //    System.out.println("Right Node: " + rightNode.getName());
                    node.addDestination(rightNode, pyramide[row + 1][column + 1]);
                }
            }

        }
        else {

            return; //lastNode
        }

    }


}
