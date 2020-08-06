package engine;


import org.apache.catalina.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository interface for User
 */
public interface QuizRepository extends PagingAndSortingRepository<QuizForStore, Integer> {
}


//public interface QuizRepository extends CrudRepository<QuizForStore, Integer> {
//}

