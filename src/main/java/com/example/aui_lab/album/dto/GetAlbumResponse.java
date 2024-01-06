package com.example.aui_lab.album.dto;

import lombok.*;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetAlbumResponse {
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

    private UUID id;
    private String title;
    private Integer release_year;
    private Artist artist;
}
