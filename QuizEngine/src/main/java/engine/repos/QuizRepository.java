package engine.repos;


import engine.quiz.QuizForStore;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository interface for User
 */
public interface QuizRepository extends PagingAndSortingRepository<QuizForStore, Integer> {
}


//public interface QuizRepository extends CrudRepository<QuizForStore, Integer> {
//}

