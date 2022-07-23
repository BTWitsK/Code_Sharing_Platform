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

    private String code = """
            public static void main(String[] args) {
                System.out.println("Hello");
            }
            """;

    LocalDateTime date;

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

    @JsonIgnore
    String createHTML = """
            <html>
            <head>
                <title>Create</title>
            </head>
            <body>
                <form>
                    <textarea id="code_snippet">
                    Post code to upload here
                    </textarea>
                    <br>
                        <button id="send_snippet"
                        type="submit"
                        onclick="send()">
                            Submit
                        </button>
                    <script>
                    function send() {
                        let object = {
                            "code": document.getElementById("code_snippet").value
                        };
                        
                        let json = JSON.stringify(object);
                        
                        let xhr = new XMLHttpRequest();
                        xhr.open("POST", '/api/code/new', false)
                        xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
                        xhr.send(json);
                        
                        if (xhr.status == 200) {
                          alert("Success!");
                        }
                    }
                    </script>
                </form>
            </body>
            </html>
            """;

    public CodeSnippet() {}

    public CodeSnippet(String code) {
        this.code = code;
    }

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

    public String getCreateHTML() {return createHTML;}

    public void setCode(String code) {this.code = code;}

    public String getCode() {
        return code;
    }
}
