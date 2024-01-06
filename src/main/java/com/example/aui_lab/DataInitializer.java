package com.example.aui_lab;

import com.example.aui_lab.album.Album;
import com.example.aui_lab.album.AlbumService;
import com.example.aui_lab.artist.Artist;
import com.example.aui_lab.artist.ArtistService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DataInitializer implements InitializingBean {
    private final AlbumService albumService;
    private final ArtistService artistService;

    @Autowired
    public DataInitializer(AlbumService albumService, ArtistService artistService) {
        this.albumService = albumService;
        this.artistService = artistService;
    }

    @Override
    public void afterPropertiesSet() throws Exception{
        System.out.println("Initializing data...");
        Artist ari = Artist.builder()
                .name("Ariana Grande")
                .age(30)
                .albums(new ArrayList<>())
                .build();
        Artist tay = Artist.builder()
                .name("Taylor Swift")
                .age(34)
                .albums(new ArrayList<Album>())
                .build();
        Artist ye = Artist.builder()
                .name("Ye")
                .age(46)
                .albums(new ArrayList<Album>())
                .build();
        artistService.create(ari);
        artistService.create(tay);
        artistService.create(ye);
        Album pos = Album.builder()
                .title("Positions")
                .release_year(2020)
                .artist(ari)
                .build();
        Album tun = Album.builder()
                .title("thank u, next")
                .release_year(2019)
                .artist(ari)
                .build();
        Album swe = Album.builder()
                .title("Sweetener")
                .release_year(2018)
                .artist(ari)
                .build();
        Album dan = Album.builder()
                .title("Dangerous Woman")
                .release_year(2016)
                .artist(ari)
                .build();
        Album mid = Album.builder()
                .title("Midnights")
                .release_year(2022)
                .artist(tay)
                .build();
        Album lov = Album.builder()
                .title("Lover")
                .release_year(2019)
                .artist(tay)
                .build();
        Album rep = Album.builder()
                .title("Reputation")
                .release_year(2017)
                .artist(tay)
                .build();
        Album nin = Album.builder()
                .title("1989")
                .release_year(2014)
                .artist(tay)
                .build();
        Album don = Album.builder()
                .title("Donda")
                .release_year(2021)
                .artist(ye)
                .build();
        Album jes = Album.builder()
                .title("JESUS IS KING")
                .release_year(2019)
                .artist(ye)
                .build();
        Album yea = Album.builder()
                .title("ye")
                .release_year(2018)
                .artist(ye)
                .build();
        Album tlo = Album.builder()
                .title("The Life Of Pablo")
                .release_year(2016)
                .artist(ye)
                .build();

        albumService.create(pos);
        albumService.create(tun);
        albumService.create(swe);
        albumService.create(dan);
        albumService.create(mid);
        albumService.create(lov);
        albumService.create(rep);
        albumService.create(nin);
        albumService.create(don);
        albumService.create(jes);
        albumService.create(yea);
        albumService.create(tlo);
    }
}
