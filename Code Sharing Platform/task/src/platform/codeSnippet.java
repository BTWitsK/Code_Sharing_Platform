package platform;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class codeSnippet {
    String code = """
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
