package string;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlParser {
    private static final String HTML_HREF_TAG_PATTERN = "\\s*(?i)href\\s*=\\s*(\"|')+([^\"'>\\s]*)";

    public List<String> parseUrls(String content) {
        List<String> links = new ArrayList<>();

        Pattern pattern = Pattern.compile(HTML_HREF_TAG_PATTERN, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(content);
        String url = null;
        while (matcher.find()) {
            url = matcher.group(2);
            if (url.length() == 0 || url.startsWith("#"))
                continue;
            links.add(url);
        }
        return links;
    }

    public static void main(String[] args) {
        String content = "<html>\n" +
                "  <body>\n" +
                "    <div>\n" +
                "      <a href=\"http://www.google.com\" class=\"text-lg\">Google</a>\n" +
                "      <a href='http://www.facebook.com' style=\"display:none\">Facebook</a>\n" +
                "    </div>\n" +
                "    <div>\n" +
                "      <a href=\"https://www.linkedin.com\">Linkedin</a>\n" +
                "      <a href = \"http://github.io\">LintCode</a>\n" +
                "    </div>\n" +
                "  </body>\n" +
                "</html>";

        UrlParser parser = new UrlParser();
        List<String> links = parser.parseUrls(content);
        for (String s : links) {
            System.err.println(s);
        }
    }
}
