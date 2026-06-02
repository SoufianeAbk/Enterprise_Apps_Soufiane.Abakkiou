package be.ehb.enterpriseapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

/**
 * Een evenement van de NGO of een van haar partners.
 * De locatie wordt bijgehouden als verwijzing (location_id) naar een {@link Location}.
 */
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime tijdstip;

    private String titel;

    @Column(length = 2000)
    private String omschrijving;

    /** Organisatie: "Eigen beheer" of de naam van een partner. */
    private String organisatie;

    /** E-mailadres van de contactpersoon. */
    private String contactEmail;

    @ManyToOne(optional = false)
    @JoinColumn(name = "location_id")
    private Location location;

    protected Event() {
    }

    public Event(LocalDateTime tijdstip, String titel, String omschrijving,
                 String organisatie, String contactEmail, Location location) {
        this.tijdstip = tijdstip;
        this.titel = titel;
        this.omschrijving = omschrijving;
        this.organisatie = organisatie;
        this.contactEmail = contactEmail;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getTijdstip() {
        return tijdstip;
    }

    public void setTijdstip(LocalDateTime tijdstip) {
        this.tijdstip = tijdstip;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    public String getOrganisatie() {
        return organisatie;
    }

    public void setOrganisatie(String organisatie) {
        this.organisatie = organisatie;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
