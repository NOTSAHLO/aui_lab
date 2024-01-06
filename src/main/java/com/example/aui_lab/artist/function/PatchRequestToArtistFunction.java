package com.example.aui_lab.artist.function;

import com.example.aui_lab.artist.Artist;
import com.example.aui_lab.artist.dto.PatchArtistRequest;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;

@Component
public class PatchRequestToArtistFunction implements BiFunction<Artist, PatchArtistRequest, Artist> {

    @Override
    public Artist apply(Artist entity, PatchArtistRequest request){
        return Artist.builder()
                .id(entity.getId())
                .name(request.getName())
                .age(request.getAge())
                .albums(entity.getAlbums())
                .build();
    }
}
