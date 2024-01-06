package com.example.aui_lab.artist.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetArtistsResponse {
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Artist{
        private UUID id;
        private String name;
    }

    @Singular
    private List<Artist> artists;
}
