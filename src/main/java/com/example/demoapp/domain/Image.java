package com.example.demoapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    @Id
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String link;

    @OneToOne
    private Size size;
}
