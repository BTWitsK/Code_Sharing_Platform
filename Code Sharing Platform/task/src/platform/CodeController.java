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
    //todo: implement get endpoints */code/latest to retrieve latest code snipped
    //todo: update endpoint /api/code/new to return json with single field id starting from 1
    //todo: /api/code/latest returns json array with 10 most recently uploaded snippets sorted from newest to oldest
    //todo: /code/latest returns html containing 10 most recently uploaded snippets with title Latest
    CodeSnippet code = new CodeSnippet();
    HashMap<Integer, CodeSnippet> snippetMap = new HashMap<>();

    @GetMapping("/code/new")
    public ResponseEntity<?> postCode() {
        return new ResponseEntity<>(code.getCreateHTML(), code.getHtmlHeaders(), HttpStatus.OK);
    }

    @GetMapping("/code/latest")
    public String getLatestSnippets(Model model) {
        List<CodeSnippet> snippetList = snippetMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                .limit(10)
                .map(Map.Entry::getValue)
                .toList();

        model.addAttribute("snippets", snippetList);
        return "latest";
    }

    @GetMapping("/api/code/latest")
    public ResponseEntity<?> getAPICode() {
        return new ResponseEntity<>(snippetMap.get(snippetMap.size()), code.getApiHeaders(), HttpStatus.OK);
    }

    @PostMapping("/api/code/new")
    public ResponseEntity<?> postAPICode(@RequestBody CodeSnippet newCode) {
        code.setCode(newCode.getCode());
        code.setDate();
        snippetMap.put(snippetMap.size() + 1, code);
        return new ResponseEntity<>(Map.of("id", snippetMap.size()), HttpStatus.OK);
    }
}
