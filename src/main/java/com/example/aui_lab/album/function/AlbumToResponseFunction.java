package com.example.aui_lab.album.function;

import com.example.aui_lab.album.Album;
import com.example.aui_lab.album.dto.GetAlbumResponse;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class AlbumToResponseFunction implements Function<Album, GetAlbumResponse> {

    @Override
    public GetAlbumResponse apply(Album entity){
        return GetAlbumResponse.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .release_year(entity.getRelease_year())
                .artist(GetAlbumResponse.Artist.builder()
                        .id(entity.getArtist().getId())
                        .name(entity.getArtist().getName())
                        .build())
                .build();
    }
}
