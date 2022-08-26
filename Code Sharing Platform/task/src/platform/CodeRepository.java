package platform;

import org.springframework.data.repository.CrudRepository;

/**
 * Implements CRUD repository used to interact with database
 */
public interface CodeRepository extends CrudRepository<CodeSnippet, String> {
}
