package com.msedonald.user;

import com.msedonald.result.Result;
import com.msedonald.user.data.UserSave;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @OneToMany(mappedBy = "user", cascade = ALL)
    private List<Result> results = new ArrayList<>();

    @Builder
    protected User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static User createUser(UserSave userSave) {
        return User.builder()
                .username(userSave.username())
                .password(userSave.password())
                .build();
    }
}
