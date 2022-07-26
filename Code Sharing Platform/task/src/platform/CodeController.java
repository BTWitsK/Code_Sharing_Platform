package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Controller
public class CodeController {
    CodeSnippet code = new CodeSnippet();
    @Autowired
    CodeService codeService;

    @GetMapping(value = "/code/{id}")
    public String getCodeById(Model model, @PathVariable String id) {
        Optional<CodeSnippet> code = codeService.getSnippetByID(id);

        if (code.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else if (code.get().isRestricted()) {
            boolean timeFails = "Time".equals(code.get().restriction) && code.get().getTime() < 1;
            boolean viewsFails = "Views".equals(code.get().restriction) && code.get().getViews() < 1;

            if (timeFails || viewsFails) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            } else {
                codeService.updateSnippet(code.get());
                model.addAttribute("snippet", codeService.getSnippetByID(id).get());
                return "snippet";
            }
        } else {
            model.addAttribute("snippet", codeService.getSnippetByID(id).get());
            return "snippet";
        }
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
    public ResponseEntity<?> getAPICodeById(@PathVariable String id) {
        Optional<CodeSnippet> code = codeService.getSnippetByID(id);

        if (code.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (code.get().isRestricted()) {
            boolean timeFails = code.get().restriction.contains("Time") && code.get().getTime() < 1;
            boolean viewsFails = code.get().restriction.contains("Views") && code.get().getViews() < 1;

            if (timeFails || viewsFails) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                codeService.updateSnippet(code.get());
                return new ResponseEntity<>(code.get(), HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(code.get(), HttpStatus.OK);
        }
    }

    @GetMapping("/api/code/latest")
    public ResponseEntity<?> getAPICode() {
        return new ResponseEntity<>(codeService.getSnippetList(), code.getApiHeaders(), HttpStatus.OK);
    }

    @PostMapping("/api/code/new")
    public ResponseEntity<?> postAPICode(@RequestBody CodeSnippet newCode) {
        newCode.setRestricted(newCode.getTime() > 0, newCode.getViews() > 0);
        if (newCode.getTime() < 0) {
            newCode.setTime(0);
        }

        if (newCode.getViews() < 0) {
            newCode.setViews(0);
        }
        codeService.addSnippet(newCode);

        return new ResponseEntity<>(Map.of("id", newCode.getId()), HttpStatus.OK);
    }
}
