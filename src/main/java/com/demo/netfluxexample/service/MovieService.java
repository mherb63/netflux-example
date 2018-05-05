package com.demo.netfluxexample.service;

import com.demo.netfluxexample.domain.Movie;
import com.demo.netfluxexample.domain.MovieEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovieService {

    Flux<Movie> getAll();

    Mono<Movie> getById(String id);

    Flux<MovieEvent> events(String id);
}
