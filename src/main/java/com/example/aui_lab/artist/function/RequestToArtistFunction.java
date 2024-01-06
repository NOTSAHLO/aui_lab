package com.example.aui_lab.artist.function;

import com.example.aui_lab.artist.Artist;
import com.example.aui_lab.artist.dto.PutArtistRequest;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class RequestToArtistFunction implements Function<PutArtistRequest, Artist> {

    @Override
    public Artist apply(PutArtistRequest request){
        return Artist.builder()
                .name(request.getName())
                .age(request.getAge())
                .build();
    }
}
