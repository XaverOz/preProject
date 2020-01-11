package app.model;

import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class Timer {

    private Long nanoTime = System.nanoTime();

    public Long getTime() {
        return nanoTime;
    }
}
