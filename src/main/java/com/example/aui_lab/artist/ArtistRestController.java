package com.example.aui_lab.artist;

import com.example.aui_lab.album.AlbumService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log
public class ArtistRestController {
    private final ArtistService service;

    @Autowired
    public ArtistRestController(ArtistService service){
        this.service = service;
    }
}
