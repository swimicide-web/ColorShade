import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ColorPanel extends JComponent implements MouseListener {
    private ArrayList<Point> points = new ArrayList<>();
    private Color primaryColor = Color.ORANGE;
    private BufferedImage canvasImage;

    public void setPrimaryColor(Color color) {
        this.primaryColor = color;
        repaint();
    }

    public ColorPanel() {
        this.setSize(500, 500);
        this.setVisible(true);
        repaint();
        this.addMouseListener(this);
    }

    /**
     * 1. Check if the canvasImage exists or if the panel has been resized. If so, create a new image.
     * 2. Get a Graphics2D object from the image(canvasImage) and perform all your drawing operations on it.
     * 3. Draw the finished canvasImage onto the actual panel.
     * standard technique called "off-screen rendering" or "double buffering"
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println("w: " + getWidth() + ", h: " + getHeight());
        canvasImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
//        1. Create or update the off-screen image if necessary.
//        if (canvasImage == null ||
//                canvasImage.getWidth() != getWidth() ||
//                canvasImage.getHeight() != getHeight()) {
//            canvasImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
//        }

        System.out.println("canvasImage: " + canvasImage);

//        2. Get the graphics context from the image and draw the following paints based on it.
//        Graphics2D g2 = canvasImage.createGraphics(); // ? not sure what canvasImage is, but makes error.
        Graphics2D g2 = (Graphics2D) g;

        GradientPaint primary = new GradientPaint(
                0f, 0f, Color.WHITE, getWidth(), 0f, Color.RED); // later, change this value. red to anything else.
        GradientPaint shade = new GradientPaint(
                0f, 0f, new Color(0, 0, 0, 0),
                0f, getHeight(), new Color(0, 0, 0, 255));

        g2.setPaint(primary);
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setPaint(shade);
        g2.fillRect(0, 0, getWidth(), getHeight());

        g2.dispose();

        g2.drawImage(canvasImage, 0, 0, null);
    }

    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) { // Make sure the image has been created and the click is within bounds
        if (canvasImage != null && e.getX() < canvasImage.getWidth() && e.getY() < canvasImage.getHeight()) {
            // Get the RGB integer value of the pixel at the clicked coordinates
            int rgb = canvasImage.getRGB(e.getX(), e.getY());
            System.out.println(rgb);

            Color clickedColor = new Color(rgb, true); // 'true' to include alpha/transparency


            System.out.println("Clicked at (" + e.getX() + ", " + e.getY() + ")");
            System.out.println("Picked Color: R=" + clickedColor.getRed() +
                    ", G=" + clickedColor.getGreen() +
                    ", B=" + clickedColor.getBlue());
        }
    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {}

    /**
     * Invoked when a mouse button has been released on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {}

    /**
     * Invoked when the mouse enters a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {}

    /**
     * Invoked when the mouse exits a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {}
}

