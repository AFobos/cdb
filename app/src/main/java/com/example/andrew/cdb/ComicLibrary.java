package com.example.andrew.cdb;

/**
 * Created by Andrew on 10.05.2017.
 */

public class ComicLibrary {
    private long id;
    private String image;
    private String name;
    private String number;
    private String writer;
    private String artist;
    private String publisher;

    ComicLibrary(long id, String image, String name, String number, String writer, String artist, String publisher){
        this.id = id;
        this.image = image;
        this.name = name;
        this.number = number;
        this.writer = writer;
        this.artist = artist;
        this.publisher = publisher;
    }

    public long getId() {
        return id;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image){
        this.image = image;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber(){
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getWriter(){
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getArtist(){
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getPublisher(){
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

}
