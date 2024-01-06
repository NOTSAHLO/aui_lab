package com.example.aui_lab.album.function;

import com.example.aui_lab.album.Album;
import com.example.aui_lab.album.dto.PatchAlbumRequest;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;

@Component
public class PatchRequestToAlbumFunction implements BiFunction<Album, PatchAlbumRequest, Album> {

    @Override
    public Album apply(Album album, PatchAlbumRequest request){
        return Album.builder()
                .id(album.getId())
                .title(request.getTitle())
                .release_year(request.getRelease_year())
                .artist(album.getArtist())
                .build();
    }
}
