package zadanie28.zadanie28.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import zadanie28.zadanie28.model.Post;


@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
}
