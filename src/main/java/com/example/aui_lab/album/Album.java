package com.example.aui_lab.album;

import com.example.aui_lab.artist.Artist;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "albums")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Album implements Comparable<Album>, Serializable {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "release_year")
    private Integer release_year;

    @ManyToOne
    @JoinColumn(name = "artist_name")
    private Artist artist;

    public Album(String title, Integer release_year, Artist artist){
        this.title = title;
        this.release_year = release_year;
        this.artist = artist;
    }

    @Override
    public int compareTo(Album other) {
        return this.title.compareTo(other.title);
    }

}