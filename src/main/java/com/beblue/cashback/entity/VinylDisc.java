package com.beblue.cashback.entity;

import com.beblue.cashback.entity.enums.Genre;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="vinyl_discs")
public class VinylDisc {

    @Id
    @SequenceGenerator(name="vinyl_discs_id_seq",
            sequenceName="vinyl_discs_id_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="vinyl_discs_id_seq")
    private Long id;

    @Column
    private String name;

    @Column
    private String artist;

    @Enumerated(EnumType.STRING)
    @Column
    private Genre genre;

    @Column
    private BigDecimal value;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name= name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
