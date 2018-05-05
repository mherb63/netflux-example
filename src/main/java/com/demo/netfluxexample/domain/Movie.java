package com.demo.netfluxexample.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@RequiredArgsConstructor
public class Movie {

    private String id;

    @NonNull
    private String title;
}
