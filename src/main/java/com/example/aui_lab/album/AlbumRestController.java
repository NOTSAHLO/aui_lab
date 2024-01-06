package com.example.aui_lab.album;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log
public class AlbumRestController{
    private final AlbumService service;

    @Autowired
    public AlbumRestController(AlbumService service){
        this.service = service;
    }

}
