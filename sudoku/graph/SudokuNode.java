/**
 * Created by twasinudomro on 3/26/18.
 */
public class SudokuNode {

    private int rowIndex;
    private int columnIndex;

    public SudokuNode(int rowIndex, int columnIndex) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public int getBlockId() {
        int blockRowIndex = rowIndex / 3;
        int blockColumnIndex = columnIndex / 3;
        return blockRowIndex*3 + blockColumnIndex + 1;
    }

    public boolean connected(SudokuNode that) {
       if (this.equals(that)) {
           throw new IllegalArgumentException("These SudokuNodes are equal.");
       }
       return this.rowIndex == that.rowIndex || this.columnIndex == that.columnIndex || this.getBlockId() == that.getBlockId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SudokuNode that = (SudokuNode) o;

        if (rowIndex != that.rowIndex) return false;
        return columnIndex == that.columnIndex;
    }

    @Override
    public int hashCode() {
        int result = (int) rowIndex;
        result = 31 * result + (int) columnIndex;
        return result;
    }

    @Override
    public String toString() {
        return "SudokuNode{" +
                "rowIndex=" + rowIndex +
                ", columnIndex=" + columnIndex +
                ", blockId=" + getBlockId() +
                '}';
    }
}
