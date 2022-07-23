package platform;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class CodeController {
    //todo: implement get /api/code/N returns JSON with the N-th uploaded code snippet
    //todo: implement /code/N return HTML that contains N-th uploaded code snippet
    CodeSnippet code = new CodeSnippet();
    HashMap<Integer, CodeSnippet> snippetMap = new HashMap<>();

    @GetMapping("/code/new")
    public ResponseEntity<?> postCode() {
        return new ResponseEntity<>(code.getCreateHTML(), code.getHtmlHeaders(), HttpStatus.OK);
    }

    @GetMapping("/code/latest")
    public String getLatestSnippets(Model model) {
        model.addAttribute("snippets", getSnippetList());
        return "latest";
    }

    @GetMapping("/api/code/latest")
    public ResponseEntity<?> getAPICode() {
        return new ResponseEntity<>(getSnippetList(), code.getApiHeaders(), HttpStatus.OK);
    }

    @PostMapping("/api/code/new")
    public ResponseEntity<?> postAPICode(@RequestBody CodeSnippet newCode) {
        code.setCode(newCode.getCode());
        code.setDate();
        snippetMap.put(snippetMap.size() + 1, code);
        return new ResponseEntity<>(Map.of("id", snippetMap.size()), HttpStatus.OK);
    }

    public List<CodeSnippet> getSnippetList() {
        return snippetMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                .limit(10)
                .map(Map.Entry::getValue)
                .toList();
    }
}
