package engine.service;

import engine.User;
import engine.compleatedQuiz.CompletedQuiz;
import engine.repos.UserRepository;
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
        Page<CompletedQuiz> findBy = userRepository.findBy(user, paging);
        return findBy;
    }

    public User getUserByEmail(String email) {
        List<User> users = new ArrayList<User>();
        userRepository.findAll().forEach(user -> users.add(user));
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
        userRepository.findAll().forEach(user -> users.add(user));
        if (users.size() > 0){
            for (User userFromBase : users) {
                if (userFromBase.email.equals(email)) {
                    return userFromBase;
                }
            }
        }
        return new User("null", "null");
    }

    public boolean addCompleteQuiz(Integer idQuiz, String userEmail) {

        User user = this.getUserByEmail(userEmail);
        user.completed.add(new CompletedQuiz(idQuiz));
        this.SaveUser(user);
        System.out.println(user.id);
        return true;
    }
}

