package com.whalespottingjava.models.database;

import java.sql.Date;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;

@Entity
@Table(name = "whale_sighting")
public class Sighting {
    private static final String ID_SEQUENCE = "whale_sighting_id_sequence";

    @Id
    @SequenceGenerator(name = ID_SEQUENCE, sequenceName = ID_SEQUENCE, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ID_SEQUENCE)
    @Hidden
    private Long id;

    @Column(name = "member_id", nullable = false)
    private int memberId;
    @Column(name = "date")
    private Date date;
    @Column(name = "latitude")
    private double latitude;
    @Column(name = "longitude")
    private double longitude;
    @Column(name = "approved")
    @Hidden
    private Boolean approved;

    protected Sighting() {}
    
    public Sighting(int memberId, Date date, double latitude, double longitude) {
        this.memberId = memberId;
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
        this.approved = false;
    }


    public Long getId() {
        return id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getMemberId() {
        return this.memberId;
    }
}
