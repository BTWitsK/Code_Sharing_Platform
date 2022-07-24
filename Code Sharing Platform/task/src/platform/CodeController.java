package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class CodeController {

    CodeSnippet code = new CodeSnippet();
    HashMap<Integer, CodeSnippet> snippetMap = new HashMap<>();
    @Autowired
    CodeService codeService;

    @GetMapping("/code/{id}")
    public String getCodeById(Model model, @PathVariable int id) {
        model.addAttribute("snippet", codeService.getSnippetByID(id).get());
        return "snippet";
    }

    @GetMapping("/code/latest")
    public String getLatestSnippets(Model model) {
        model.addAttribute("snippets", codeService.getSnippetList());
        return "latest";
    }

    @GetMapping("/code/new")
    public ResponseEntity<?> postCode() {
        return new ResponseEntity<>(code.getCreateHTML(), code.getHtmlHeaders(), HttpStatus.OK);
    }

    @GetMapping("/api/code/{id}")
    public ResponseEntity<?> getAPICodeById(@PathVariable int id) {
        return new ResponseEntity<>(codeService.getSnippetByID(id).get(), HttpStatus.OK);
    }

    @GetMapping("/api/code/latest")
    public ResponseEntity<?> getAPICode() {
        return new ResponseEntity<>(codeService.getSnippetList(), code.getApiHeaders(), HttpStatus.OK);
    }

    @PostMapping("/api/code/new")
    public ResponseEntity<?> postAPICode(@RequestBody CodeSnippet newCode) {
        codeService.addSnippet(newCode);
        return new ResponseEntity<>(Map.of("id", newCode.getId()), HttpStatus.OK);
    }
}
