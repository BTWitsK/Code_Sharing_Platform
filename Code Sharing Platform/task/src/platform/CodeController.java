package platform;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
public class CodeController {
    CodeSnippet code = new CodeSnippet();

    @GetMapping("/code")
    public ResponseEntity<?> getCode() {
        return new ResponseEntity<>(code.getHtml(), code.getHtmlHeaders(), HttpStatus.OK);
    }

    @GetMapping("/api/code")
    public ResponseEntity<?> getAPICode() {
        return new ResponseEntity<>(Map.of("code", code.getCode()), code.getApiHeaders(), HttpStatus.OK);
    }
}
