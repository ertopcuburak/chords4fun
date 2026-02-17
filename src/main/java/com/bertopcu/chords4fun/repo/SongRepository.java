package com.bertopcu.chords4fun.repo;

import com.bertopcu.chords4fun.model.Song;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SongRepository extends MongoRepository<Song, String> {

    // Custom query method (Spring generates the implementation automatically)
    List<Song> findByName(String name);

    // Complex query using @Query annotation
    @Query("{ 'artist' : ?0 }")
    List<Song> findSongByArtist(String artist);
}
