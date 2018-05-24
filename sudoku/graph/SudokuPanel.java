import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Created by twasinudomro on 3/26/18.
 */
public class SudokuPanel extends JPanel {

    private int width;
    private int height;
    private int border;

    private static final int NODE_WIDTH = 18;

    public SudokuPanel(int width, int height, int border) {
        this.width = width;
        this.height = height;
        this.border = border;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);     // paint parent's background
        setBackground(Color.WHITE);  // set background color for this JPanel
        g.setColor(Color.BLACK);

        g.drawRect(border, border, width, height);

        SudokuGraph sudokuGraph = new SudokuGraph();
        List<SudokuNode> nodes = sudokuGraph.getNodes();
        for (SudokuNode node : nodes) {
            Point nodePoint = toPoint(node);
            drawNode(g, nodePoint.x, nodePoint.y);
        }

        for (int firstNodeIndex = 0; firstNodeIndex < 81; firstNodeIndex++) {
            for (int secondNodeIndex = firstNodeIndex + 1; secondNodeIndex < 81; secondNodeIndex++) {
                SudokuNode firstNode = nodes.get(firstNodeIndex);
                SudokuNode secondNode = nodes.get(secondNodeIndex);
                if (firstNode.connected(secondNode)) {
                    Point firstNodePoint = toPoint(firstNode);
                    Point secondNodePoint = toPoint(secondNode);
                    g.drawLine(firstNodePoint.x, firstNodePoint.y, secondNodePoint.x, secondNodePoint.y);
                }
            }

        }

    }

    private Point toPoint(SudokuNode node) {

        byte orbitIndex; // 0-4 from innermost to outermost
        byte orbitPoints;
        int radius;
        double angle = 0;
        Point center = new Point(border + width / 2, border + height / 2);
        if (node.getRowIndex() == 4 && node.getColumnIndex() == 4) {
            orbitIndex = 0; // center node
            orbitPoints = 1;
            radius = 0;
            return center;
        } else if (node.getBlockId() == 5) {
            orbitIndex = 1;
            orbitPoints = 8;
            radius = orbitIndex*(width/(2*4));
            List<SudokuNode> orderedNodes = Arrays.asList(
                    new SudokuNode(4, 5),
                    new SudokuNode(3, 5),
                    new SudokuNode(3, 4),
                    new SudokuNode(3, 3),
                    new SudokuNode(4, 3),
                    new SudokuNode(5, 3),
                    new SudokuNode(5, 4),
                    new SudokuNode(5, 5));
            angle = orderedNodes.indexOf(node)*(2*Math.PI/orbitPoints);
        } else if (
                (node.getRowIndex() == 2 && (2 <= node.getColumnIndex() && node.getColumnIndex() <= 6))
                        || (node.getRowIndex() == 6 && (2 <= node.getColumnIndex() && node.getColumnIndex() <= 6))
                        || (node.getColumnIndex() == 2 && (2 <= node.getRowIndex() && node.getRowIndex() <= 6))
                        || (node.getColumnIndex() == 6 && (2 <= node.getRowIndex() && node.getRowIndex() <= 6))
                )

        {
            orbitIndex = 2;
            orbitPoints = 16;
            radius = orbitIndex*(width/(2*4));
            List<SudokuNode> orderedNodes = Arrays.asList(new SudokuNode(4, 6),
                    new SudokuNode(3, 6),
                    new SudokuNode(2, 6),
                    new SudokuNode(2, 5),
                    new SudokuNode(2, 4),
                    new SudokuNode(2, 3),
                    new SudokuNode(2, 2),
                    new SudokuNode(3, 2),
                    new SudokuNode(4, 2),
                    new SudokuNode(5, 2),
                    new SudokuNode(6, 2),
                    new SudokuNode(6, 3),
                    new SudokuNode(6, 4),
                    new SudokuNode(6, 5),
                    new SudokuNode(6, 6),
                    new SudokuNode(5, 6));
            angle = orderedNodes.indexOf(node)*(2*Math.PI/orbitPoints);
        } else if (
                (node.getRowIndex() == 1 && (1 <= node.getColumnIndex() && node.getColumnIndex() <= 7))
                        || (node.getRowIndex() == 7 && (1 <= node.getColumnIndex() && node.getColumnIndex() <= 7))
                        || (node.getColumnIndex() == 1 && (1 <= node.getRowIndex() && node.getRowIndex() <= 7))
                        || (node.getColumnIndex() == 7 && (1 <= node.getRowIndex() && node.getRowIndex() <= 7))
                ) {
            orbitIndex = 3;
            orbitPoints = 24;
            radius = orbitIndex*(width/(2*4));
            List<SudokuNode> orderedNodes = Arrays.asList(
                    new SudokuNode(4, 7),
                    new SudokuNode(3, 7),
                    new SudokuNode(2, 7),
                    new SudokuNode(1, 7),
                    new SudokuNode(1, 6),
                    new SudokuNode(1, 5),
                    new SudokuNode(1, 4),
                    new SudokuNode(1, 3),
                    new SudokuNode(1, 2),
                    new SudokuNode(1, 1),
                    new SudokuNode(2, 1),
                    new SudokuNode(3, 1),
                    new SudokuNode(4, 1),
                    new SudokuNode(5, 1),
                    new SudokuNode(6, 1),
                    new SudokuNode(7, 1),
                    new SudokuNode(7, 2),
                    new SudokuNode(7, 3),
                    new SudokuNode(7, 4),
                    new SudokuNode(7, 5),
                    new SudokuNode(7, 6),
                    new SudokuNode(7, 7),
                    new SudokuNode(6, 7),
                    new SudokuNode(5, 7));
            angle = orderedNodes.indexOf(node)*(2*Math.PI/orbitPoints);
        } else {
            orbitIndex = 4;
            orbitPoints = 32;
            radius = orbitIndex*(width/(2*4));
            List<SudokuNode> orderedNodes = Arrays.asList(
                    new SudokuNode( 4, 8),
                    new SudokuNode( 3, 8),
                    new SudokuNode( 2, 8),
                    new SudokuNode( 1, 8),
                    new SudokuNode( 0, 8),
                    new SudokuNode( 0, 7),
                    new SudokuNode( 0, 6),
                    new SudokuNode( 0, 5),
                    new SudokuNode( 0, 4),
                    new SudokuNode( 0, 3),
                    new SudokuNode( 0, 2),
                    new SudokuNode( 0, 1),
                    new SudokuNode( 0, 0),
                    new SudokuNode( 1, 0),
                    new SudokuNode( 2, 0),
                    new SudokuNode( 3, 0),
                    new SudokuNode( 4, 0),
                    new SudokuNode( 5, 0),
                    new SudokuNode( 6, 0),
                    new SudokuNode( 7, 0),
                    new SudokuNode( 8, 0),
                    new SudokuNode( 8, 1),
                    new SudokuNode( 8, 2),
                    new SudokuNode( 8, 3),
                    new SudokuNode( 8, 4),
                    new SudokuNode( 8, 5),
                    new SudokuNode( 8, 6),
                    new SudokuNode( 8, 7),
                    new SudokuNode( 8, 8),
                    new SudokuNode( 7, 8),
                    new SudokuNode( 6, 8),
                    new SudokuNode( 5, 8));
            angle = orderedNodes.indexOf(node)*(2*Math.PI/orbitPoints);
        }
        return new Point(center.x + (int) Math.round(radius*Math.cos(angle)), center.y - (int) Math.round(radius*Math.sin(angle)));
    }

    private void drawNode(Graphics g, int x, int y) {
        g.fillRect(x - NODE_WIDTH / 2, y - NODE_WIDTH / 2, NODE_WIDTH, NODE_WIDTH);
    }
}

