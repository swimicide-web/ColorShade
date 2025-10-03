import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class SliderModule extends JComponent implements ChangeListener, ActionListener {
//    instance variables to use in every method, not limited on single method
//    more variable = more heap usage
    private JSlider verticalJSlider;
    private ColorPanel colorPanel;
    private int colorValue = 0;

//    getting instance variable and invoking gui
    public static void main(String[] args) {
        SliderModule sliderModule = new SliderModule();
        SwingUtilities.invokeLater(sliderModule::init);
    }

    public void init() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        colorPanel = new ColorPanel();
        setVerticalJSlider();
        frame.add(verticalJSlider, BorderLayout.WEST);
        frame.add(colorPanel);
        frame.setVisible(true);
    }

    public void setVerticalJSlider() {
        verticalJSlider = new JSlider(JSlider.VERTICAL, 0, 360, 0);
        verticalJSlider.setBorder(BorderFactory.createTitledBorder("Color"));
//        horizontalJSlider configurations
        verticalJSlider.addChangeListener(this);
        verticalJSlider.setMajorTickSpacing(30);
        verticalJSlider.setMinorTickSpacing(5);
        verticalJSlider.setPaintTicks(true);
        verticalJSlider.setPaintLabels(true); // paints font such as number, letter etc
    }

    /**
     * Invoked when the target of the listener has changed its state.
     * eventually needs to divide with color and contrast
     * @param e a ChangeEvent object
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider) e.getSource();
        colorValue = source.getValue();
        System.out.println("colorValue: " + colorValue);

        Color newColor = Color.getHSBColor(colorValue / 360f, 1.0f, 1.0f);
        if (colorPanel != null) {
            colorPanel.setPrimaryColor(newColor);
        }
//        1. MAKE SLIDER CHANGE COLOR.
//        2. GET CURRENT COLOR AFTER ONCE GET THE COLOR SUCCESSFULLY IN GUI
//        3. RUN ALGORITHM
//        * can change demonstration of color later. focus on above things first.
//        colorPanel.addPoint(contrastValue, colorValue);
//                check if slider is currently being adjusted
        if (!source.getValueIsAdjusting()) {
            System.out.println("slider adjustment finished");
        }
    }

//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//    }

    /**
     * Invoked when an action occurs.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

