import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ColorClassifier {
    private final List<ColorEntry> dataset;
    private final int k;

    /**
     * A constructor for the K-Nearest Neighbor Classifier
     * @param dataset: The list of known colors.
     * @param k: The number of neighbors to select actual closest color
     */
    public ColorClassifier(List<ColorEntry> dataset, int k) {
        this.dataset = dataset;
        this.k = k;
    }

    /**
     * Calculates the squared Euclidean distance between two colors.
     * Use squared distance to avoid square root.
     * relative order of distance remains same.
     * @param ce1: ColorEntry 1
     * @param ce2: ColorEntry 2
     * @return Distance between two color, using squared Euclidean distance formular
     */
    private double getDistance(ColorEntry ce1, ColorEntry ce2) {
        double dR = ce1.r() - ce2.r(); // distance Red
        double dG = ce1.b() - ce2.b(); // Green
        double dB = ce1.b() - ce2.r(); // Blue

        return dR * dR + dG * dG + dB * dB;
    }

    /**
     * Predicts the name of a new color.
     * @param unknown The unknown color to classify. Receives value(instance) from MainColor class.
     * @return The predicted color name as a String.
     */
//    ColorEntry unknownColor1 = new ColorEntry("?", 200, 10, 25);
    public String predict(ColorEntry unknown) {
//        1. Calculate the distance to every color in the dataset.
        List<Map.Entry<Double, String>> distances = new ArrayList<>();
        for (ColorEntry entry : dataset) {
            double d = getDistance(entry, unknown); // two point of two colors | entry and unknown color
            /*
            * [
                  Map.entry(112061.0, "Red"),
                  Map.entry(69981.0, "Green"),
                  Map.entry(61061.0, "Blue"),
                  Map.entry(20090.0, "White"),
                  Map.entry(79501.0, "Black"),
                  Map.entry(35426.0, "Yellow"),
                  Map.entry(21026.0, "Cyan"),
                  // ... and so on for all other colors
                ]
            * */
            distances.add(Map.entry(d, entry.name()));

        }
        System.out.println("distance: " + distances);

//        2. Sort the distances in ascending order
        distances.sort(Map.Entry.comparingByKey());

//        3. List should be left with only 3 closest color
        List<String> neighbors = distances.stream()
                .limit(k)
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());

        System.out.println(neighbors);

//        4. Perform a majority vote to find the most common name
//        actually pretty pointless at all
        Map<String, Long> voteCounts = neighbors.stream()
                .collect(Collectors.groupingBy(Function.identity(),
                        Collectors.counting()));

        System.out.println("voteCounts " + voteCounts);

//        5. Find the entry with the highest count
        Optional<Map.Entry<String, Long>> result = voteCounts.entrySet().stream()
                .max(Map.Entry.comparingByValue());

        System.out.println("result " + result);

//        Return the name of color
//          or "Unknown" if no neighbors found
        return result.map(Map.Entry::getKey).orElse("Unknown");
    }
}
