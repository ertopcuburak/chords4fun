package com.bertopcu.chords4fun.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("song")
@CompoundIndex(name = "artist_created_idx", def = "{'artist': 1, 'createdAt': -1}")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Song {
    @Id
    private String id;
    @Indexed
    private String name;
    private String artist;
    private String albumName;
    private String author;
    private String composer;
    private String lyricsWithChords;
    private String pureLyrics;
    private Long createdAt;
    private String userMail;
    private Long updatedAt;
}
