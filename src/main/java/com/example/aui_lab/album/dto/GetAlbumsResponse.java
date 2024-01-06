package com.example.aui_lab.album.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetAlbumsResponse {
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Album{
        private UUID id;
        private String title;
    }

    @Singular
    private List<Album> albums;
}
