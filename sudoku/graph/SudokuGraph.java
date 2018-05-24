import java.util.ArrayList;
import java.util.List;

/**
 * Created by twasinudomro on 3/26/18.
 */
public class SudokuGraph {
    private List<SudokuNode> nodes;

    public SudokuGraph() {
        nodes = new ArrayList<>();
        for (int rowIndex = 0; rowIndex < 9; rowIndex++) {
            for (int columnIndex = 0; columnIndex < 9; columnIndex++) {
                nodes.add(new SudokuNode(rowIndex, columnIndex));
            }
        }
    }

    public List<SudokuNode> getNodes() {
        return nodes;
    }
}
