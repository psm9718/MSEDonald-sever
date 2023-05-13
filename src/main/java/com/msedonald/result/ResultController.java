package com.msedonald.result;

import com.msedonald.result.data.ResultResponse;
import com.msedonald.result.data.ResultSave;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ResultController {

    private final ResultService resultService;

    @PostMapping("/api/scores")
    public void saveScore(@RequestBody ResultSave resultSave) {
        resultService.save(resultSave);
    }

    @GetMapping("/api/scores")
    public List<ResultResponse> getResults() {
        return resultService.getAllResults();
    }
}
