package engine.repos;


import engine.quiz.Quiz;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository interface for User
 */
public interface QuizRepository extends PagingAndSortingRepository<Quiz, Integer> {
}


//public interface QuizRepository extends CrudRepository<QuizForStore, Integer> {
//}

