package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Service class that uses repository to interact with database
 */
@Service
public class CodeService {
    private final CodeRepository codeRepository;

    @Autowired
    public CodeService(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    /**
     * Retrieves Code Snippet from database using string id
     * @param id string representation of unique id belonging to code Snippet
     * @return Optional containing CodeSnippet if found in database, empty Optional otherwise.
     */
    public Optional<CodeSnippet> getSnippetByID(String id) {
        return codeRepository.findById(id);
    }

    /**
     * Adds snippet to database after creating and setting a unique ID as well as the date it was created
     * @param snippet Code snippet to add to database
     */
    public void addSnippet(CodeSnippet snippet) {
        snippet.setId(String.valueOf(UUID.randomUUID()));
        snippet.setDate();
        codeRepository.save(snippet);
    }

    /**
     * Updates the views on the Code Snippet and updates it in the database
     * @param snippet snippet to be updated in database
     */
    public void updateSnippet(CodeSnippet snippet) {
        snippet.updateViews();
        codeRepository.save(snippet);
    }

    /**
     * looks in database for all snippets then filters by snippets that aren't restricted and sorts them
     * @return list of 10 latest snippets
     */
    public List<CodeSnippet> getSnippetList() {
        List<CodeSnippet> codeList = new ArrayList<>();
        codeRepository.findAll().forEach(codeList::add);

        return codeList.stream()
                .filter(code -> !code.isRestricted())
                .sorted(Comparator.comparing(CodeSnippet::getDate).reversed())
                .limit(10)
                .toList();
    }
}
