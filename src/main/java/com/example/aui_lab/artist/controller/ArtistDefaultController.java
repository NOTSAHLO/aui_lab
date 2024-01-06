package com.example.aui_lab.artist.controller;

import com.example.aui_lab.album.Album;
import com.example.aui_lab.album.AlbumService;
import com.example.aui_lab.album.dto.PutAlbumRequest;
import com.example.aui_lab.artist.Artist;
import com.example.aui_lab.artist.ArtistService;
import com.example.aui_lab.artist.dto.GetArtistResponse;
import com.example.aui_lab.artist.dto.GetArtistsResponse;
import com.example.aui_lab.artist.dto.PatchArtistRequest;
import com.example.aui_lab.artist.dto.PutArtistRequest;
import com.example.aui_lab.artist.function.ArtistToResponseFunction;
import com.example.aui_lab.artist.function.ArtistsToResponseFunction;
import com.example.aui_lab.artist.function.PatchRequestToArtistFunction;
import com.example.aui_lab.artist.function.RequestToArtistFunction;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@Log
public class ArtistDefaultController{
    private final ArtistService service;
    private final AlbumService albumService;
    private final ArtistToResponseFunction artistToResponse;
    private final ArtistsToResponseFunction artistsToResponse;
    private final RequestToArtistFunction requestToArtist;
    private final PatchRequestToArtistFunction patchRequestToArtist;

    @Autowired
    public ArtistDefaultController( ArtistService service, AlbumService albumService, ArtistToResponseFunction artistToResponse, ArtistsToResponseFunction artistsToResponse, RequestToArtistFunction requestToArtist, PatchRequestToArtistFunction patchRequestToArtist){
        this.service = service;
        this.albumService = albumService;
        this.artistToResponse = artistToResponse;
        this.artistsToResponse = artistsToResponse;
        this.requestToArtist = requestToArtist;
        this.patchRequestToArtist = patchRequestToArtist;
    }
    // create
    @PutMapping("/api/artists/new")
    @ResponseStatus(HttpStatus.CREATED)
    public void putArtist(@RequestBody PutArtistRequest request){
        service.create(requestToArtist.apply(request));
    }
    // update
    @PatchMapping("/api/artists/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void patchArtist(@PathVariable("id") UUID id, @RequestBody PatchArtistRequest request){
        Artist updated_artist = service.find(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        service.update(patchRequestToArtist.apply(updated_artist, request));
    }
    // delete
    @DeleteMapping("/api/artists/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArtist(@PathVariable("id") UUID id){
        // delete all artist albums
        List<Album> albumsToDelete = albumService.findAllByArtist(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        for (Album album : albumsToDelete){
            albumService.delete(album.getId());
        }
        service.find(id)
                .ifPresentOrElse(
                        profession -> service.delete(id),
                        () -> {
                            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                        }
                );
    }
    // find
    @GetMapping("/api/artists/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public GetArtistResponse getArtist(@PathVariable("id") UUID id){
        Artist artist = service.find(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND));
        return artistToResponse.apply(artist);
    }
    // find all
    @GetMapping("api/artists")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public GetArtistsResponse getArtists(){
        return artistsToResponse.apply(service.findAll());
    }

}

