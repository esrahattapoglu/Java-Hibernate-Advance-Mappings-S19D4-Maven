package com.workintech.s19d1.controller;

import com.workintech.s19d1.dto.ActorRequest;
import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;
import com.workintech.s19d1.service.ActorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/actor")
public class ActorController {

    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping
    public ResponseEntity<List<Actor>> findAll() {
        return ResponseEntity.ok(actorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Actor> findById(@PathVariable Long id) {
        return ResponseEntity.ok(actorService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Actor> save(@RequestBody ActorRequest actorRequest) {
        Actor actor = actorRequest.getActor();
        List<Movie> movies = actorRequest.getMovies();
        if (movies != null) {
            for (Movie movie : movies) {
                actor.addMovie(movie);
            }
        }
        return ResponseEntity.ok(actorService.save(actor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Actor> update(@PathVariable Long id, @RequestBody Actor updatedActor) {
        Actor existing = actorService.findById(id);
        existing.setFirstName(updatedActor.getFirstName());
        existing.setLastName(updatedActor.getLastName());
        existing.setGender(updatedActor.getGender());
        existing.setBirthDate(updatedActor.getBirthDate());
        return ResponseEntity.ok(actorService.save(existing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Actor> delete(@PathVariable Long id) {
        Actor actor = actorService.findById(id);
        actorService.delete(actor);
        return ResponseEntity.ok(actor);
    }
}
