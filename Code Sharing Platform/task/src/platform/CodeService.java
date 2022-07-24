package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public List<CodeSnippet> getSnippetList() {
        List<CodeSnippet> codeList = new ArrayList<>();
        codeRepository.findAll().forEach(codeList::add);

        return codeList.stream()
                .sorted(Comparator.comparing(CodeSnippet::getDate).reversed())
                .limit(10)
                .toList();
    }
}
