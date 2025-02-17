import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlAnalyzer {
    private static class HtmlNode {
        private final String content;
        private final int depth;

        public HtmlNode(String content, int depth) {
            this.content = content;
            this.depth = depth;
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Please provide a URL as argument");
            return;
        }

        try {
            String htmlContent = fetchUrl(args[0]);
            String result = analyzeHtml(htmlContent);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("URL connection error");
        }
    }

    private static String fetchUrl(String urlString) throws Exception {
        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();
        conn.setRequestProperty("Accept-Charset", "UTF-8"); // Garante a codificação correta

        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) { // Usa UTF-8 corretamente
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line.trim()).append("\n");
            }
        }

        return content.toString();
    }

    private static String analyzeHtml(String html) {
        if (html == null || html.trim().isEmpty()) {
            return "";
        }

        Pattern tagPattern = Pattern.compile("</?([a-zA-Z0-9]+)[^>]*>");
        Stack<String> tagStack = new Stack<>();
        List<HtmlNode> textNodes = new ArrayList<>();

        int maxDepth = 0;
        Matcher matcher = tagPattern.matcher(html);
        int lastEnd = 0;

        while (matcher.find()) {
            String beforeText = html.substring(lastEnd, matcher.start()).trim();
            if (!beforeText.isEmpty() && !tagStack.isEmpty()) {
                int depth = tagStack.size();
                textNodes.add(new HtmlNode(beforeText, depth));
                maxDepth = Math.max(maxDepth, depth);
            }

            String tag = matcher.group(1);
            boolean isClosingTag = matcher.group().startsWith("</");

            if (isClosingTag) {
                if (tagStack.isEmpty() || !tagStack.peek().equals(tag)) {
                    return "malformed HTML";
                }
                tagStack.pop();
            } else {
                tagStack.push(tag);
            }

            lastEnd = matcher.end();
        }

        if (!tagStack.isEmpty()) {
            return "malformed HTML";
        }

        // Corrigido: Armazena o valor antes de usar a lambda
        final int finalMaxDepth = maxDepth;

        return textNodes.stream()
                .filter(node -> node.depth == finalMaxDepth)
                .map(node -> node.content)
                .findFirst()
                .orElse("");
    }
}
