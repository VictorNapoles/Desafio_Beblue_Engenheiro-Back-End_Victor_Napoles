package com.beblue.cashback.entity;

import com.beblue.cashback.entity.enums.Genre;

import javax.persistence.*;

@Entity
@Table(name="cashbacks", uniqueConstraints={@UniqueConstraint(columnNames = {"day" , "genre"}, name = "cashbacks_uq_day_genre")})
public class Cashback {

    @Id
    @SequenceGenerator(name="cashbacks_id_seq",
                sequenceName="cashbacks_id_seq",
                allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="cashbacks_id_seq")
    private Long id;

    @Column
    private Integer percentage;

    @Column
    private Integer day;

    @Enumerated(EnumType.STRING)
    @Column
    private Genre genre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
