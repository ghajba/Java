package biz.hahamo.dev.prettierprint;

import org.jsoup.Jsoup;
import org.jsoup.select.NodeTraversor;
import org.jsoup.nodes.Element;

/**
 * Created by GHajba on 2014.08.03..
 */
public class Main {
    public static void main(String... args) {
        System.out.println(Jsoup.parseBodyFragment("<div><span>test text</span></div>").html());
        System.out.println(myFormattedHTML(Jsoup.parseBodyFragment("<div><p>test text</p></div>").body()));
    }

    public static String myFormattedHTML(Element element) {
        FormattingVisitor visitor = new FormattingVisitor();
        NodeTraversor traversor = new NodeTraversor(visitor);
        traversor.traverse(element);
        return visitor.toString();
    }
}
