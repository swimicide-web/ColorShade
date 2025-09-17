package dylan.colorshade.code;//import org.w3c.dom.Document;
import javax.swing.text.Document;
import java.awt.Color;
import java.net.URL;


public interface Colors {
    default void monochromes() {
//        1. Monochrome decimal. Add 0 ~ 255 as RGB value.
//        2. Search the color in web. Provide the name of color if it exists.
        System.out.println("-----Color interface - Monochrome");
        Color black = new Color(0, 0, 0);
        Color gray = new Color(128, 128, 128);
        Color white = new Color(255, 255, 255);
        System.out.println("black: " + black);
        System.out.println("gray: " + gray);
        System.out.println("white: " + white);
        byte b = 101 & 100;
        System.out.println(b);

        for (int i = 0; i < 256; i++) {
            Color color = new Color(i, i, i);
            System.out.println(i + "th color: " + color);
        }
    }

    static void m() {
        System.out.println("Interface of colors");
    }

    static void main(String[] args) {
        System.out.println("--");
        Colors colors = new Colors() {};
        colors.monochromes();
    }
}
