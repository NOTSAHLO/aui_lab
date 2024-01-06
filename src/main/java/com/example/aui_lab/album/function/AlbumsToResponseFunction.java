package com.example.aui_lab.album.function;

import com.example.aui_lab.album.Album;
import com.example.aui_lab.album.dto.GetAlbumsResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public class AlbumsToResponseFunction implements Function<List<Album>, GetAlbumsResponse> {

    @Override
    public GetAlbumsResponse apply(List<Album> entities){
        return GetAlbumsResponse.builder()
                .albums(entities.stream()
                    .map(album -> GetAlbumsResponse.Album.builder()
                            .id(album.getId())
                            .title(album.getTitle())
                            .build())
                        .toList())
                .build();
    }
}
