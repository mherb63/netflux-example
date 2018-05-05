package com.demo.netfluxexample.controller;

import com.demo.netfluxexample.domain.Movie;
import com.demo.netfluxexample.domain.MovieEvent;
import com.demo.netfluxexample.service.MovieService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping(value = "/movies/{id}/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<MovieEvent> streamMovieEvents(@PathVariable String id) {
        return movieService.events(id);
    }

    @GetMapping("movies/{id}")
    Mono<Movie> getById(@PathVariable String id) {
      return movieService.getById(id);
    }

    @GetMapping("/movies")
    Flux<Movie> getAll() {
        return movieService.getAll();
    }
}
