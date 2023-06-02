package com.msedonald.result;

import com.msedonald.result.data.WinOrLose;
import com.msedonald.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Table(name = "results")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long score;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(STRING)
    private WinOrLose winOrLose;

    @Builder
    public Result(Long score, User user, WinOrLose winOrLose) {
        this.score = score;
        this.user = user;
        this.winOrLose = winOrLose;
    }
}
