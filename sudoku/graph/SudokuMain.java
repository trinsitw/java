import java.util.ArrayList;
import java.util.List;

public class SudokuMain {

    public static void main(String[] args) {
        List<SudokuNode> nodes = new ArrayList<>();

        for (byte rowIndex = 0; rowIndex < 9; rowIndex++) {
            for (byte columnIndex = 0; columnIndex < 9; columnIndex++) {
                nodes.add(new SudokuNode(rowIndex, columnIndex));
            }
        }
        int edges = 0;
        for (int firstNodeIndex = 0; firstNodeIndex < 81; firstNodeIndex++) {
            for (int secondNodeIndex = firstNodeIndex+1; secondNodeIndex < 81; secondNodeIndex++) {
                SudokuNode firstNode = nodes.get(firstNodeIndex);
                SudokuNode secondNode = nodes.get(secondNodeIndex);
                if (firstNode.connected(secondNode)) {
                    edges++;
                    System.out.println(firstNode);
                    System.out.println(secondNode);
                    System.out.println("---------");
                }
            }
        }
        System.out.println(edges);
        System.out.println(new SudokuNode((byte)0,(byte)0).connected(new SudokuNode((byte)1,(byte)1)));
    }
}
