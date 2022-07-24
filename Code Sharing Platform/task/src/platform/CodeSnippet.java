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

    private String code = "public static void main(String[] args) {System.out.println(\"Hello\");}";

    private LocalDateTime date = LocalDateTime.now();

    @JsonIgnore
    String createHTML = """
            <html lang="en">
            <head>
                <title>Create</title>
            </head>
            <body>
                <form>
                    <textarea id="code_snippet">
                    Post code to upload here
                    </textarea>
                    <br>
                        <button id="send_snippet" type="submit" onclick="send()">
                            Submit
                        </button>
                    <script>
                    function send() {
                        let object = {
                            "code": document.getElementById("code_snippet").value
                        };
                       \s
                        let json = JSON.stringify(object);
                        let xhr = new XMLHttpRequest();
                        xhr.open("POST", '/api/code/new', false)
                        xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
                        xhr.send(json);
                       \s
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

    public HttpHeaders getHtmlHeaders() {
        htmlHeaders.add("Content-Type", "text/html" );
        return htmlHeaders;
    }

    public HttpHeaders getApiHeaders() {
        apiHeaders.add("Content-Type", "application/json");
        return apiHeaders;
    }

    public CodeSnippet setDate() {
        this.date = LocalDateTime.now();
        return this;
    }

    public String getDate() {return date.format(DateTimeFormatter.BASIC_ISO_DATE);}

    public String getCreateHTML() {return createHTML;}

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return """
                    "code": "%s",
                    "date": "%s"
                """.formatted(getCode(), getDate());
    }
}
