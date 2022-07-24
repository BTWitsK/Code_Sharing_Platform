package platform;

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

    @GetMapping("/code/{id}")
    public String getCodeById(Model model, @PathVariable int id) {
        model.addAttribute("snippet", snippetMap.get(id));
        return "snippet";
    }

    @GetMapping("/code/latest")
    public String getLatestSnippets(Model model) {
        model.addAttribute("snippets", getSnippetList());
        return "latest";
    }

    @GetMapping("/code/new")
    public ResponseEntity<?> postCode() {
        return new ResponseEntity<>(code.getCreateHTML(), code.getHtmlHeaders(), HttpStatus.OK);
    }

    @GetMapping("/api/code/{id}")
    public ResponseEntity<?> getAPICodeById(@PathVariable int id) {
        return new ResponseEntity<>(snippetMap.get(id), HttpStatus.OK);
    }

    @GetMapping("/api/code/latest")
    public ResponseEntity<?> getAPICode() {
        return new ResponseEntity<>(getSnippetList(), code.getApiHeaders(), HttpStatus.OK);
    }

    @PostMapping("/api/code/new")
    public ResponseEntity<?> postAPICode(@RequestBody CodeSnippet newCode) {
        snippetMap.put(snippetMap.size() + 1, newCode.setDate());
        return new ResponseEntity<>(Map.of("id", String.valueOf(snippetMap.size())), HttpStatus.OK);
    }

    public List<CodeSnippet> getSnippetList() {
        return snippetMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                .limit(10)
                .map(Map.Entry::getValue)
                .toList();
    }
}
