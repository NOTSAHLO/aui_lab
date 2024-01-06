package com.example.aui_lab.artist;

import com.example.aui_lab.album.Album;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;


@Data
@Entity
@Table(name = "artists")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Artist implements Comparable<Artist>, Serializable {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "artist")
    private List<Album> albums;

    Artist(String  name, Integer age, List<Album> albums){
        this.name = name;
        this.age = age;
        this.albums = albums;
    }

    @Override
    public int compareTo(Artist other) {
        return this.name.compareTo(other.name);
    }
}