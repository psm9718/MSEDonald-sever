package com.msedonald.result;

import com.msedonald.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Long> {

    List<Result> findTop5ByUserOrderByScoreDesc(User user);
}
