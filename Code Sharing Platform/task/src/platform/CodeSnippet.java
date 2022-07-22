package platform;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpHeaders;

public class CodeSnippet {
    @JsonIgnore
    private final HttpHeaders htmlHeaders = new HttpHeaders();
    @JsonIgnore
    private final HttpHeaders apiHeaders = new HttpHeaders();

    private final String code = """
            public static void main(String[] args) {
                System.out.println("Hello");
            }
            """;

    @JsonIgnore
    String html = """
            <html>
            <head>
                <title>Code</title>
            </head>
            <body>
                <pre>
            %s
            </pre>
            </body>
            </html>
            """.formatted(code);

    public HttpHeaders getHtmlHeaders() {
        htmlHeaders.add("Content-Type", "text/html" );
        return htmlHeaders;
    }

    public HttpHeaders getApiHeaders() {
        apiHeaders.add("Content-Type", "application/json");
        return apiHeaders;
    }

    public String getHtml() {
        return html;
    }

    public String getCode() {
        return code;
    }
}
