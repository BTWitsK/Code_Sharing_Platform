package platform;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.http.HttpHeaders;

import javax.persistence.*;
import java.time.Duration;
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
    private LocalDateTime date = LocalDateTime.now();

    @Column(name = "VIEWS")
    private long views;

    @Column(name = "SECONDS")
    private long time;

    @JsonIgnore
    @Column(name = "RESTRICTED")
    boolean restricted = false;

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
                <input id="time_restriction" type="text"/>
                <input id="views_restriction" type="text"/>
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

    public void setRestricted(Boolean flag) {
        this.restricted = flag;
    }

    public boolean isRestricted() {
        return this.restricted;
    }

    public Long getTime() {
        return time - Duration.between(date.toLocalTime(), LocalDateTime.now().toLocalTime()).toSecondsPart();
    }

    public void updateViews() {
        views -= 1;
    }

}
