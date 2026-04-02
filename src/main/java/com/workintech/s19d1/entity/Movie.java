package com.workintech.s19d1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String directorName;
    private int rating;
    private LocalDate releaseDate;

    @JsonIgnore
    @ManyToMany(mappedBy = "movies",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Actor> actors = new ArrayList<>();

    public void addActor(Actor actor) {
        if (actors == null) actors = new ArrayList<>();
        actors.add(actor);
    }
}