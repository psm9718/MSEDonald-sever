package com.msedonald.result;

import com.msedonald.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Long> {

    //Top 5 score results for requested user
    List<Result> findTop5ByUserOrderByScoreDesc(User user);

    //Current 5 results for requested user
    List<Result> findTop5ByUserOrderByCreatedDateDesc(User user);
}
