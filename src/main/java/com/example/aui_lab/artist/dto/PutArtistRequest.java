package com.example.aui_lab.artist.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PutArtistRequest {
    private String name;
    private Integer age;
}
