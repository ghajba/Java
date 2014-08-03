package biz.hahamo.dev.prettierprint;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeVisitor;

/**
 * Created by GHajba on 2014.08.03..
 */
public class FormattingVisitor implements NodeVisitor {
    private StringBuilder accumulatedText = new StringBuilder();

    @Override
    public void head(Node node, int depth) {
        String name = node.nodeName();
        if (node instanceof TextNode) {
            append(((TextNode) node).text(), depth);
        }
        else if (!name.equals("body")) {
            appendNode(node, depth);
        }
    }

    @Override
    public void tail(Node node, int depth) {
        String name = node.nodeName();
        if (!(node instanceof TextNode) && !"body".equals(name) && !"br".equals(name)) {
            append("</" + name + ">", depth);
        }
    }

    private void appendNode(Node node, int depth) {
        accumulatedText.append(StringUtils.repeat(" ", (depth - 1) * 4));
        accumulatedText.append("<" + node.nodeName());
        for (Attribute attr : node.attributes()) {
            accumulatedText.append(" " + attr.html());
        }
        if ("br".equals(node.nodeName())) {
            accumulatedText.append("/");
        }
        accumulatedText.append(">\n");
    }

    private void append(String text, int depth) {
        text = StringUtils.normalizeSpace(text);
        if (StringUtils.isNotEmpty(text)) {
            accumulatedText.append(StringUtils.repeat(" ", (depth - 1) * 4));
            accumulatedText.append(text);
            accumulatedText.append("\n");
        }
    }

    public String toString() {
        return accumulatedText.toString();
    }
}

