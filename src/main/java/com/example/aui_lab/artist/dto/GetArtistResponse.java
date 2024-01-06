package com.example.aui_lab.artist.dto;

import lombok.*;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetArtistResponse {
    private UUID id;
    private String name;
    private Integer age;
}
