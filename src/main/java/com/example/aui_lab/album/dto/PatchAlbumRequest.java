package com.example.aui_lab.album.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PatchAlbumRequest {
    private String title;
    private Integer release_year;
}
