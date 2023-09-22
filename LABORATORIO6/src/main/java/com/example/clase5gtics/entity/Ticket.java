package com.example.clase5gtics.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.security.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "Ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="TicketID")
    private int TicketID;

    @ManyToOne
    @JoinColumn(name = "SiteID")
    private Site site;

    @ManyToOne
    @JoinColumn(name = "TechnicianID")
    private Technician technician;

    @Column(name="Status")
    private String Status;

    @Column(name="OpenedDate")
    private Timestamp OpenedDate;

    @Column(name="ClosedDate")
    private Timestamp ClosedDate;
}
