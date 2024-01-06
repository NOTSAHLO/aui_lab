package com.example.aui_lab.album.controller;

import com.example.aui_lab.album.Album;
import com.example.aui_lab.album.AlbumService;
import com.example.aui_lab.album.dto.GetAlbumResponse;
import com.example.aui_lab.album.dto.GetAlbumsResponse;
import com.example.aui_lab.album.dto.PatchAlbumRequest;
import com.example.aui_lab.album.dto.PutAlbumRequest;
import com.example.aui_lab.album.function.AlbumToResponseFunction;
import com.example.aui_lab.album.function.AlbumsToResponseFunction;
import com.example.aui_lab.album.function.PatchRequestToAlbumFunction;
import com.example.aui_lab.album.function.RequestToAlbumFunction;
import com.example.aui_lab.artist.Artist;
import com.example.aui_lab.artist.ArtistService;
import com.example.aui_lab.artist.dto.GetArtistResponse;
import com.example.aui_lab.artist.dto.PatchArtistRequest;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@Log
public class AlbumDefaultController{
    private final AlbumService service;
    private final ArtistService artistService;
    private final AlbumToResponseFunction albumToResponse;
    private final AlbumsToResponseFunction albumsToResponse;
    private final RequestToAlbumFunction requestToAlbum;

    private final PatchRequestToAlbumFunction patchRequestToAlbum;

    @Autowired
    public AlbumDefaultController(AlbumService service, ArtistService artistService, AlbumToResponseFunction albumToResponse, AlbumsToResponseFunction albumsToResponse, RequestToAlbumFunction requestToAlbum, PatchRequestToAlbumFunction patchRequestToALbum){
        this.service = service;
        this.artistService = artistService;
        this.albumToResponse = albumToResponse;
        this.albumsToResponse = albumsToResponse;
        this.requestToAlbum = requestToAlbum;
        this.patchRequestToAlbum = patchRequestToALbum;
    }
    // create
    @PutMapping("/api/albums/{artist_id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void putAlbum(@PathVariable("artist_id") UUID artist_id, @RequestBody PutAlbumRequest request){
        Artist artist = artistService.find(artist_id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        service.create(requestToAlbum.apply(artist, request));
    }
    // update
    @PatchMapping("/api/albums/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void patchAlbum(@PathVariable("id") UUID id, @RequestBody PatchAlbumRequest request){
        Album updated_album = service.find(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        service.update(patchRequestToAlbum.apply(updated_album, request));
    }
    // delete
    @DeleteMapping("/api/albums/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAlbum(@PathVariable("id") UUID id){
        service.find(id)
                .ifPresentOrElse(
                        profession -> service.delete(id),
                        () -> {
                            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                        }
                );
    }
    // find
    @GetMapping("/api/albums/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public GetAlbumResponse getAlbum(@PathVariable("id") UUID id){
        Album album = service.find(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return albumToResponse.apply(album);
    }
    // find all
    @GetMapping("/api/albums")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public GetAlbumsResponse getAlbums(){
        return albumsToResponse.apply(service.findAll());
    }
    // find all by artist
    @GetMapping("/api/artists/{artist_id}/albums")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public GetAlbumsResponse getAlbumsByArtist(@PathVariable("artist_id") UUID artist_id){
        List <Album> artistAlbums = service.findAllByArtist(artist_id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return albumsToResponse.apply(artistAlbums);
    }
}

