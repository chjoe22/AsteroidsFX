package dk.sdu.mmmi.cbse.scoringsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RequestMapping("/attributes")
@RestController
public class ScoringSystem {

    private long totalScore = 0L;

    public static void main(String[] args) {
        SpringApplication.run(ScoringSystem.class, args);
    }

    @GetMapping("/score")
    public Long getScore(){
        return totalScore;
    }
    @PutMapping("score/update/{score}")
    public Long updateScoring(@PathVariable(value = "score") Long score){
        totalScore += score;
        return totalScore;
    }
}
