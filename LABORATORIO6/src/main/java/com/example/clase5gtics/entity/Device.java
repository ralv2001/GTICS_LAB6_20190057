package com.example.clase5gtics.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "Device")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="DeviceID")
    private int DeviceID;

    @Column(name="DeviceName")
    private String DeviceName;

    @Column(name="DeviceType")
    private String DeviceType;

    @Column(name="Model")
    private String Model;

    @Column(name="SerialNumber")
    private String SerialNumber;

    @ManyToOne
    @JoinColumn(name = "SiteID")
    private Site site;


}
