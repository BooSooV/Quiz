package engine.repos;


import engine.quiz.Quiz;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for User
 */
@Repository
public interface QuizRepository extends PagingAndSortingRepository<Quiz, Integer> {
}


//public interface QuizRepository extends CrudRepository<QuizForStore, Integer> {
//}

