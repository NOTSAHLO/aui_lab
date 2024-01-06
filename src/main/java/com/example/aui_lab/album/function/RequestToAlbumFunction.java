package com.example.aui_lab.album.function;

import com.example.aui_lab.album.Album;
import com.example.aui_lab.artist.Artist;
import com.example.aui_lab.album.dto.PutAlbumRequest;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;

@Component
public class RequestToAlbumFunction implements BiFunction<Artist, PutAlbumRequest, Album> {
    @Override
    public Album apply(Artist artist, PutAlbumRequest request){
        return Album.builder()
                .title(request.getTitle())
                .release_year(request.getRelease_year())
                .artist(artist)
                .build();
    }
}
