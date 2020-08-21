package engine;

import engine.compleatedQuiz.CompletedQuiz;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue
    public long id;

    @Column
    public String email;

    @Column
    public String password;
    @Column
    public boolean active = true;
    @Column
    public String roles = "ROLE_ADMIN";

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn()
    public List<CompletedQuiz> completed = new ArrayList<>();

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.active = true;
        this.roles = "ROLE_ADMIN";
    }

    public User() {
    }
}