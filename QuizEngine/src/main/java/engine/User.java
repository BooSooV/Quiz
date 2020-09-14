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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public List<CompletedQuiz> getCompleted() {
        return completed;
    }

    public void setCompleted(List<CompletedQuiz> completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                ", roles='" + roles + '\'' +
                ", completed=" + completed +
                '}';
    }
}