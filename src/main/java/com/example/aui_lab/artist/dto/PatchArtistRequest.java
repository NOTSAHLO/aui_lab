package com.example.aui_lab.artist.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PatchArtistRequest {
    private String name;
    private Integer age;
}
