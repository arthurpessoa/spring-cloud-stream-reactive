package io.github.arthurpessoa.sink;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import reactor.core.publisher.Flux;

import java.io.Serializable;

@EnableBinding(Sink.class)
@SpringBootApplication
public class SinkApplication {
    private static Logger logger = LoggerFactory.getLogger(SinkApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SinkApplication.class, args);
    }


    @StreamListener(target = Sink.INPUT)
    public void loggerSink(Flux<Event> events) {
        events
                .map(Object::toString)
                .subscribe(event -> logger.info("Received: {}", event));
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
