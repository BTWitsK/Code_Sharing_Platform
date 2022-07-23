package platform;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
public class CodeController {
    CodeSnippet code = new CodeSnippet();

    @GetMapping("/code")
    public ResponseEntity<?> getCode() {
        return new ResponseEntity<>(code.getHtml(), code.getHtmlHeaders(), HttpStatus.OK);
    }

    @GetMapping("/code/new")
    public ResponseEntity<?> postCode() {
        return new ResponseEntity<>(code.getCreateHTML(), code.getHtmlHeaders(), HttpStatus.OK);
    }

    @GetMapping("/api/code")
    public ResponseEntity<?> getAPICode() {
        return new ResponseEntity<>(Map.of("code", code.getCode(), "date", code.getDate())
                , code.getApiHeaders(), HttpStatus.OK);
    }

    @PostMapping("/api/code/new")
    public ResponseEntity<?> postAPICode(@RequestBody CodeSnippet newCode) {
        code.setCode(newCode.getCode());
        code.setDate();
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }
}
