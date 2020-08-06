package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public long SaveUser(User user) {
        User userFeedback;
        userFeedback = userRepository.save(user);
        return userFeedback.id;
    }
    public List<User> GetAllUsers() {
        List<User> users = new ArrayList<User>();
        userRepository.findAll().forEach(user -> users.add(user));
        return  users;
    }

    public Page<CompletedQuiz> getUsersSolvedQuizzesPaging(String UsersEmail, Integer pageNo, Integer pageSize, String sortBy)
    {
        UserService userService = new UserService();
        User user = userService.getUserByEmail(userRepository, UsersEmail);
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC, sortBy));
        //Page<User> pagedResult = userRepository.findAll(paging);
        Page<CompletedQuiz> findBy = userRepository.findBy(user, paging);


        return findBy;
        /*
        UserService userService = new UserService();
        User user = userService.getUserByEmail(UsersEmail);

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy)).;
        PageRequest.of()
        user.completed.
        Page<QuizForStore> pagedResult = userRepository.fin
                findAll(paging);
        System.out.println(pagedResult);
        */

    }

    public User getUserByEmail(String email) {
        List<User> users = new ArrayList<User>();
        System.out.println("point 10");
        System.out.println(userRepository);
        userRepository.findAll().forEach(user -> users.add(user));
        System.out.println("point 11");
        if (users.size() > 0){
            for (User userFromBase : users) {
                if (userFromBase.email.equals(email)) {
                    return userFromBase;
                }
            }
        }
        return new User("null", "null");
    }

    public User getUserByEmail(UserRepository userRepository, String email) {
        List<User> users = new ArrayList<User>();
        System.out.println("point 10");
        System.out.println(userRepository);
        userRepository.findAll().forEach(user -> users.add(user));
        System.out.println("point 11");
        if (users.size() > 0){
            for (User userFromBase : users) {
                if (userFromBase.email.equals(email)) {
                    return userFromBase;
                }
            }
        }
        return new User("null", "null");
    }
}

