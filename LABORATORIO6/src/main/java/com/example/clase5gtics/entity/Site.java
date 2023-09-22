package com.example.clase5gtics.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "Site")
public class Site {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="SiteID")
    private int SiteID;

    @Column(name="SiteName")
    private String SiteName;

    @ManyToOne
    @JoinColumn(name = "LocationID")
    private Location location;

    @Column(name="InstallationDate")
    private Date InstallationDate;

    @Column(name="Latitude")
    private String Latitude;

    @Column(name="Longitude")
    private String Longitude;

}
