package platform;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.http.HttpHeaders;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SNIPPETS")
public class CodeSnippet {
    @Id
    @JsonIgnore
    @Column(name = "ID")
    private String id;

    @Column(name = "CODE")
    private String code = "";

    @Column(name = "DATE")
    private LocalDateTime date;

    @Column(name = "VIEWS")
    private long views;

    @Column(name = "SECONDS")
    private long seconds;

    @JsonIgnore
    @Transient
    private final HttpHeaders htmlHeaders = new HttpHeaders();

    @JsonIgnore
    @Transient
    private final HttpHeaders apiHeaders = new HttpHeaders();

    @JsonIgnore
    @Transient
    private final String createHTML = """
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

    public LocalDateTime getDate() {return date;}

    public String getCreateHTML() {return createHTML;}

    public String getCode() {
        return code;
    }

}
