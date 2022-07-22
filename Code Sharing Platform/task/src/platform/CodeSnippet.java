package platform;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpHeaders;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CodeSnippet {
    @JsonIgnore
    private final HttpHeaders htmlHeaders = new HttpHeaders();
    @JsonIgnore
    private final HttpHeaders apiHeaders = new HttpHeaders();

    LocalDateTime date;

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
                <pre id = "code_snippet">
            %s
            </pre>
                <span id = "load_date">
            %s
            </span>
            </body>
            </html>
            """.formatted(code, getDate());

    public HttpHeaders getHtmlHeaders() {
        htmlHeaders.add("Content-Type", "text/html" );
        return htmlHeaders;
    }

    public HttpHeaders getApiHeaders() {
        apiHeaders.add("Content-Type", "application/json");
        return apiHeaders;
    }

    public void setDate() {
        this.date = LocalDateTime.now();
    }

    public String getDate() {return date.format(DateTimeFormatter.BASIC_ISO_DATE);}

    public String getHtml() {
        return html;
    }

    public String getCode() {
        return code;
    }
}
