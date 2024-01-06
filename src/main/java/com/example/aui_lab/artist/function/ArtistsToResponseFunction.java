package com.example.aui_lab.artist.function;

import com.example.aui_lab.artist.Artist;
import com.example.aui_lab.artist.dto.GetArtistsResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public class ArtistsToResponseFunction implements Function<List<Artist>, GetArtistsResponse> {
    @Override
    public GetArtistsResponse apply(List<Artist> entities){
        return GetArtistsResponse.builder()
                .artists(entities.stream()
                        .map(artist -> GetArtistsResponse.Artist.builder()
                                .id(artist.getId())
                                .name(artist.getName())
                                .build())
                        .toList())
                .build();
    }
}
