package io.github.arthurpessoa.source;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.cloud.stream.reactive.StreamEmitter;
import reactor.core.publisher.Flux;

import java.io.Serializable;

import static java.time.Duration.ofMillis;

@SpringBootApplication
@EnableBinding(Source.class)
public class SourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SourceApplication.class, args);
    }

    @StreamEmitter
    @Output(Source.OUTPUT)
    public Flux<Event> emit() {
        return Flux
                .interval(ofMillis(1000))
                .map(Event::new);
    }

    public static class Event implements Serializable {

        private Long id;

        public Event() {
        }

        public Event(Long id) {
            this.id = id;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "Event{" +
                    "id=" + id +
                    '}';
        }
    }

}
