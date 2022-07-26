package platform;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.http.HttpHeaders;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    @JsonFormat(pattern = "yyyy-MM-dd")
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
    @Column(name = "RESTRICTION")
    String restriction = "";

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

    @JsonIgnore
    public String getFormattedDate() {
        return date.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getCreateHTML() {return createHTML;}

    public String getCode() {
        return code;
    }

    public void setRestricted(Boolean time, Boolean views) {
        if (time && views) {
            this.restricted = true;
            this.restriction = "TimeAndViews";
        } else if (time || views) {
            this.restricted = true;
            this.restriction = time ? "Time" : "Views";
        }

    }

    public boolean isRestricted() {
        return this.restricted;
    }

    public Long getTime() {
        long seconds =  time - Duration.between(date.toLocalTime(), LocalDateTime.now().toLocalTime()).toSecondsPart();

        return seconds < 0? 0 : seconds;
    }

    public void updateViews() {
        if (views == 0) {
            return;
        }
        views -= 1;
    }
}
