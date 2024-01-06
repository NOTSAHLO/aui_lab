package com.example.aui_lab;

import com.example.aui_lab.album.Album;
import com.example.aui_lab.album.AlbumService;
import com.example.aui_lab.artist.Artist;
import com.example.aui_lab.artist.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

@Component
public class CommandLineRunnerApp implements CommandLineRunner{
    private final ArtistService artistService;
    private final AlbumService albumService;

    @Autowired
    public CommandLineRunnerApp(ArtistService artistService, AlbumService albumService) {
        this.artistService = artistService;
        this.albumService = albumService;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);

        boolean running = true;
        while (running) {
            System.out.println("Available commands:");
            System.out.println("1. Show all artists");
            System.out.println("2. Show all albums");
            System.out.println("3. Add new album");
            System.out.println("4. Delete album");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    showAllArtists();
                    waitForEnter(scanner);
                    break;
                case 2:
                    showAllAlbums();
                    waitForEnter(scanner);
                    break;
                case 3:
                    createAlbum(scanner);
                    waitForEnter(scanner);
                    break;
                case 4:
                    deleteAlbum(scanner);
                    waitForEnter(scanner);
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please select one of the provided options.");
            }
        }
        System.out.println("Quitting...");
        scanner.close();
    }
    public void waitForEnter(Scanner scanner){
        System.out.println("Press Enter to continue.");
        scanner.nextLine();
    }
    public void showAllArtists(){
        List <Artist> artists = artistService.findAll();
        for (Artist artist: artists){
            System.out.println(artist.toString());
        }
    }
    public void showAllAlbums(){
        List <Album> albums = albumService.findAll();
        for (Album album: albums){
            System.out.println(album.toString());
        }
    }
    public void deleteAlbum(Scanner scanner){
        showAllAlbums();
        System.out.println("Enter the title of the album you'd like to delete:");
        scanner.nextLine();
        String stringID = scanner.nextLine();
        UUID tmpID = UUID.fromString(stringID);
        albumService.delete(tmpID);
        System.out.println("Album deleted.");
    }
    public void createAlbum(Scanner scanner){
        showAllArtists();
        System.out.println("Enter album title: ");
        scanner.nextLine();
        String newTitle = scanner.nextLine();
        System.out.println("Enter release year: ");
        int newReleaseYear = scanner.nextInt();
        System.out.println("Enter the name of the artist:");
        scanner.nextLine();
        String stringID = scanner.nextLine();
        UUID tmpID = UUID.fromString(stringID);
        Artist creator = artistService.find(tmpID)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        albumService.create(new Album(newTitle, newReleaseYear, creator));
        System.out.println("New album added.");
    }
}
