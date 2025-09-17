package dylan.colorshade.code;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SimpleWebScraper {
    public static void main(String[] args) {
        try {
            // Connect to the website and get the HTML document
            Document doc = Jsoup.connect("https://www.geeksforgeeks.org/").get();

            // Select all links (<a> elements with an href attribute)
            Elements links = doc.select("a[href]");
            System.out.println("Links:");
            for (Element link : links) {
                System.out.println(link.attr("href"));
            }

            System.out.println("\n-----\n");

            // Select all images (<img> elements with a src attribute)
            Elements images = doc.select("img[src]");
            System.out.println("Images:");
            for (Element image : images) {
                System.out.println(image.attr("src"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}