package engine.repos;

import engine.compleatedQuiz.CompletedQuiz;
import engine.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
    @Query("select completed from User p inner join p.completed completed where p = :user")
    public Page<CompletedQuiz> findBy(@Param("user") User user, Pageable pageable);
}
