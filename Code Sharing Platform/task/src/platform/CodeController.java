package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

/**
 * Controller class used to handle requests made to server end points
 * @author Kae Mattis
 */
@Controller
public class CodeController {
    CodeSnippet code = new CodeSnippet();
    @Autowired
    CodeService codeService;

    /**
     * Retrieves code snippet by ID and displays it to browser if found
     * @param model object used to interact with thymeleaf template
     * @param id string representation of id used to search for code snippet in database
     * @return returns string name of thymeleaf template used to display information on browser
     */
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

    /**
     * Retrieves the latest snippets from database, populates thymeleaf template to display in browser
     * @param model object used to interact with thymeleaf template
     * @return string name of thymeleaf template for displaying on browser
     */
    @GetMapping("/code/latest")
    public String getLatestSnippets(Model model) {
        model.addAttribute("snippets", codeService.getSnippetList());
        return "latest";
    }

    /**
     * Displays information on browser used by user to add new code snippet to database
     * every code class object has a default html code to display browser information as well as
     * JS code used to send information to server
     * @return a response entity code OK as well as the correct website display
     */
    @GetMapping("/code/new")
    public ResponseEntity<?> postCode() {
        return new ResponseEntity<>(code.getCreateHTML(), code.getHtmlHeaders(), HttpStatus.OK);
    }

    /**
     * This is the api equivalent @getCodeById except instead of interacting with the server with the browser
     * and internal api sends and retrieves information
     * @param id string representation of the snippet id used to look for the code snippet in the database
     * @return a response entity as well as JSON object if code snippet is found, not found status otherwise
     */
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

    /**
     * API equivalent of @getLatestSnippets used to look for latest code snippets in database
     * @return a JSON array containing the latest snippets added to database
     */
    @GetMapping("/api/code/latest")
    public ResponseEntity<?> getAPICode() {
        return new ResponseEntity<>(codeService.getSnippetList(), code.getApiHeaders(), HttpStatus.OK);
    }

    /**
     * The API equivalent of @postCode used to post a new code snippet to database using API
     * @param newCode JSON object representing the code snippet to add to database
     * @return the id of the code snippet as well as OK http status
     */
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
