package platform;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
//todo: updated getters to display date
//todo: implement post endpoints
//todo: api post endpoint accepts JSON object with "code" returns empty Json
//todo: get: /code/new returns html containing area to receive new code to display
public class CodeController {
    CodeSnippet code = new CodeSnippet();

    @GetMapping("/code")
    public ResponseEntity<?> getCode() {
        return new ResponseEntity<>(code.getHtml(), code.getHtmlHeaders(), HttpStatus.OK);
    }

    @GetMapping("/api/code")
    public ResponseEntity<?> getAPICode() {
        return new ResponseEntity<>(code, code.getApiHeaders(), HttpStatus.OK);
    }
}
