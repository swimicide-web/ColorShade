// https://www.kaggle.com/datasets/avi1023/color-names?resource=download

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainColor {
    public static void main(String[] args) {
        ArrayList<ColorEntry> colorDataset = new ArrayList<>();
        colorDataset.add(new ColorEntry("Red", 255, 0, 0));
        colorDataset.add(new ColorEntry("Green", 0, 128, 0));
        colorDataset.add(new ColorEntry("Blue", 0, 0, 255));
        colorDataset.add(new ColorEntry("White", 255, 255, 255));
        colorDataset.add(new ColorEntry("Black", 0, 0, 0));
        colorDataset.add(new ColorEntry("Yellow", 255, 255, 0));
        colorDataset.add(new ColorEntry("Cyan", 0, 255, 255));
        colorDataset.add(new ColorEntry("Magenta", 255, 0, 255));
        colorDataset.add(new ColorEntry("Orange", 255, 165, 0));
        colorDataset.add(new ColorEntry("Purple", 128, 0, 128));
        colorDataset.add(new ColorEntry("Pink", 255, 192, 203));
        colorDataset.add(new ColorEntry("Brown", 165, 42, 42));
        colorDataset.add(new ColorEntry("Gray", 128, 128, 128));
        colorDataset.add(new ColorEntry("Lime", 0, 255, 0));
        colorDataset.add(new ColorEntry("Maroon", 128, 0, 0));
        colorDataset.add(new ColorEntry("Navy", 0, 0, 128));

        ColorClassifier classifier = new ColorClassifier(colorDataset, 3);
        System.out.println("init");

        ColorEntry unknownColor1 = new ColorEntry("?", 200, 10, 25);
        String predictedName1 = classifier.predict(unknownColor1);
        System.out.println(String.format("The color [%d, %d, %d] is predicted to be: %s",
                unknownColor1.r(), unknownColor1.g(), unknownColor1.b(), predictedName1));

        ColorEntry unknownColor2 = new ColorEntry("?", 135, 206, 235);
        String predictedName2 = classifier.predict(unknownColor2);

        ColorEntry unknownColor3 = new ColorEntry("?", 34, 139, 34);
        String predictedName3 = classifier.predict(unknownColor3);
        System.out.println(String.format("The color [%d, %d, %d] is predicted to be: %s",
                unknownColor3.r(), unknownColor3.g(), unknownColor3.b(), predictedName3));
    }
}
