package platform;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;

@Data
@NoArgsConstructor
public class CodeSnippet {
    private HttpHeaders htmlHeaders = new HttpHeaders();
    private HttpHeaders apiHeaders = new HttpHeaders();

    public HttpHeaders getHtmlHeaders() {
        htmlHeaders.add("Content-Type", "text/html" );
        return htmlHeaders;
    }

    public HttpHeaders getApiHeaders() {
        apiHeaders.add("Content-Type", "application/json");
        return apiHeaders;
    }

    private String code = """
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

}
