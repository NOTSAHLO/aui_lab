package com.example.aui_lab.artist.function;



import com.example.aui_lab.artist.Artist;
import com.example.aui_lab.artist.dto.GetArtistResponse;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ArtistToResponseFunction implements Function<Artist, GetArtistResponse> {

    @Override
    public GetArtistResponse apply(Artist entity){
        return GetArtistResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .age(entity.getAge())
                .build();
    }
}
