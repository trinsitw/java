import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by twasinudomro on 3/26/18.
 */
public class SudokuFrame extends JFrame {


    public static final int WIDTH = 2400;
    public static final int HEIGHT = 2400;
    public static final int BORDER = 60;

    private SudokuPanel panel;

    public SudokuFrame() {
        setSize(WIDTH + BORDER *2, HEIGHT + BORDER *2);
        panel = new SudokuPanel(WIDTH, HEIGHT, BORDER);
        panel.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));

        // Set the Drawing JPanel as the JFrame's content-pane
        Container cp = getContentPane();
        cp.add(panel);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setTitle(this.getClass().getName());
        setVisible(true);

        BufferedImage bi = new BufferedImage(WIDTH + BORDER*2,HEIGHT + BORDER*2, BufferedImage.TYPE_INT_ARGB);
        Graphics g2 = bi.createGraphics();
        panel.paint(g2);  //this == JComponent
        g2.dispose();
        try{
            ImageIO.write(bi,"gif",new File("sudoku.gif"));}catch (Exception e) {}
    }

    public static void main(String[] args) {
        // Run the GUI codes on the Event-Dispatching thread for thread safety
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SudokuFrame(); // Let the constructor do the job
            }
        });
    }
}
