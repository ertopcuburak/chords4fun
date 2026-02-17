package com.bertopcu.chords4fun.svc;

import com.bertopcu.chords4fun.model.Song;
import com.bertopcu.chords4fun.repo.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SongService {

    @Autowired
    SongRepository songRepository;

    public Song saveSong(Song song) {
        return songRepository.save(song);
    }

    public Song updateSong(String id, Song updatedSong) {

        Song existingSong = songRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Song not found with id: "+id));

        existingSong.setName(updatedSong.getName());
        existingSong.setArtist(updatedSong.getArtist());
        existingSong.setAlbumName(updatedSong.getAlbumName());
        existingSong.setAuthor(updatedSong.getAuthor());
        existingSong.setComposer(updatedSong.getComposer());
        existingSong.setLyricsWithChords(updatedSong.getLyricsWithChords());
        existingSong.setPureLyrics(updatedSong.getPureLyrics());
        existingSong.setUpdatedAt(updatedSong.getUpdatedAt());
        existingSong.setUserMail(updatedSong.getUserMail());

        return songRepository.save(existingSong);
    }

    public Song getSongById(String id) {

        return songRepository.findById(id).orElseThrow( () -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Song not found with id: "+id));
    }

    public List<Song> getSongsByName(String songName) {
        return songRepository.findByName(songName);
    }

    public List<Song> getSongsByArtist(String artist) {
        return songRepository.findSongByArtist(artist);
    }

    public void deleteSongById(String id) {

        Song song = songRepository.findById(id)
                .orElseThrow( () -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Song not found with id: "+id));

        songRepository.delete(song);
    }
}
