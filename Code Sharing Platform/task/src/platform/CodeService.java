package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CodeService {
    private final CodeRepository codeRepository;

    @Autowired
    public CodeService(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    public Optional<CodeSnippet> getSnippetByID(long id) {
        return codeRepository.findById(id);
    }

    public void addSnippet(CodeSnippet snippet) {
        snippet.setDate();
        codeRepository.save(snippet);
    }
}
