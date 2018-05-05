package com.demo.netfluxexample.bootstrap;

import com.demo.netfluxexample.domain.Movie;
import com.demo.netfluxexample.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Component
@Slf4j
public class Bootstrap implements CommandLineRunner {

    private final MovieRepository movieRepository;

    public Bootstrap(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        movieRepository.deleteAll().thenMany(
                Flux.just("Silence of the Lambs", "AEon Flux", "Enter the Mongo<Void>", "The Fluxination", "Back to the Future", "Meet the Fluxses", "Lord of the Fluxes")
                        .map(title -> new Movie(title, UUID.randomUUID().toString()))
                        .flatMap(movieRepository::save))
                        .subscribe(null, null, () -> {
                            movieRepository.findAll().subscribe(System.out::println);
                        });

    }
}
