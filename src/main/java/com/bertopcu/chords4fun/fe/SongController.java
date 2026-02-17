package com.bertopcu.chords4fun.fe;

import com.bertopcu.chords4fun.model.Song;
import com.bertopcu.chords4fun.svc.SongService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1/song")
@RequiredArgsConstructor
public class SongController {
    @Autowired
    SongService songService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Song> save(final @RequestBody Song song) {
        return ResponseEntity.ok(songService.saveSong(song));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Song> updateSong(
            @PathVariable String id,
            @RequestBody Song updatedSong) {

        Song song = songService.updateSong(id, updatedSong);
        return ResponseEntity.ok(song);
    }

    public record SongGetRequest(
            @NotBlank(message = "Song name should not be empty!")
            String songName) {}

    @GetMapping
    public ResponseEntity<List<Song>> getSongByName(final @RequestBody SongGetRequest request) {
        return ResponseEntity.ok(songService.getSongsByName(request.songName));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Song> getSongById(@PathVariable String id) {
        return ResponseEntity.ok(songService.getSongById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSong(@PathVariable String id) {
        songService.deleteSongById(id);
        return ResponseEntity.noContent().build();
    }


}
