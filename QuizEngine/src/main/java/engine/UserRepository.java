package engine;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends PagingAndSortingRepository<User, Integer> {

    //@Query("select child from Parent p inner join p.childs child where p = :parent")
    //public Page<Child> findBy(@Param("parent") Parent parent, Pageable pageable);

    //@Query("select completed from User p inner join p.completed completed where p = :user")
    //select completed from engine.User p inner join p.completed completed where p = :user order by p.User.CompletedQuiz.roles asc
    @Query("select completed from User p inner join p.completed completed where p = :user")
    public Page<CompletedQuiz> findBy(@Param("user") User user, Pageable pageable);
}