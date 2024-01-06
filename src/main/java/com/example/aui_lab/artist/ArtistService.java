package com.example.aui_lab.artist;

import com.example.aui_lab.album.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ArtistService {
    private final ArtistRepository artistRepository;

    @Autowired
    public ArtistService(ArtistRepository artistRepository){
        this.artistRepository = artistRepository;
    }
    public void create(Artist artist) {artistRepository.save(artist);}
    public void update(Artist artist) {artistRepository.save(artist);}
    public void delete(UUID id) {
        artistRepository.findById(id).ifPresent(artistRepository::delete);
    }
    public Optional<Artist> find(UUID id){
        return artistRepository.findById(id);
    }
    public List<Artist> findAll() {
        return artistRepository.findAll();
    }
}
