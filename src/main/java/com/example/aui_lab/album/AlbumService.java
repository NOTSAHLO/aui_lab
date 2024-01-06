package com.example.aui_lab.album;

import com.example.aui_lab.artist.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AlbumService {
    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;

    @Autowired
    public AlbumService(AlbumRepository albumRepository, ArtistRepository artistRepository){
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
    }
    public void create(Album album) {albumRepository.save(album);}
    public void update(Album album) {albumRepository.save(album);}
    public void delete(UUID id){
        albumRepository.findById(id).ifPresent(albumRepository::delete);
    }
    public Optional<Album> find(UUID id) {
        return albumRepository.findById(id);
    }
    public List<Album> findAll() {
        return albumRepository.findAll();
    }
    public Optional<List<Album>> findAllByArtist(UUID artistId){
        return artistRepository.findById(artistId)
                .map(albumRepository::findAllByArtist);
    }
}
