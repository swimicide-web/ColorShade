package dylan.colorshade.code;

import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class WebCrawler {

    public static void main(String[] args) {
        String initialUrl = "https://edition.cnn.com/";
        crawl(1, initialUrl, new ArrayList<String>());
    }

    private static void crawl(int level, String url, ArrayList<String> visited) {
        if (level <= 2 && !visited.contains(url)) {
            Document doc = request(url, visited);
            if (doc != null) {
                for (Element link : doc.select("a[href]")) {
                    String nextLink = link.absUrl("href");
                    if (!visited.contains(nextLink)) {
                        crawl(level + 1, nextLink, visited);
                    }
                }
            }
        }
    }

    private static Document request(String url, ArrayList<String> visited) {
        try {
            Connection con = Jsoup.connect(url);
            Document doc = con.get();
            if (con.response().statusCode() == 200) {
                System.out.println("Link: " + url);
                System.out.println(doc.title());
                visited.add(url);
                return doc;
            }
            return null;
        } catch (IOException e) {
            return null;
        }
    }
}