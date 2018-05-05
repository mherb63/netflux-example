package com.demo.netfluxexample.service;

import com.demo.netfluxexample.domain.Movie;
import com.demo.netfluxexample.domain.MovieEvent;
import com.demo.netfluxexample.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Date;

@Service
@Slf4j
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Flux<Movie> getAll() {
        return movieRepository.findAll();
    }

    @Override
    public Mono<Movie> getById(String id) {
        return movieRepository.findById(id);
    }

    @Override
    public Flux<MovieEvent> events(String movieId) {
        return Flux.<MovieEvent>generate(movieEventSynchronousSink -> {
            movieEventSynchronousSink.next(new MovieEvent(movieId, new Date()));
        }).delayElements(Duration.ofSeconds(1));
    }
}
