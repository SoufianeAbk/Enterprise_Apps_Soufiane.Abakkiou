package be.ehb.enterpriseapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Een locatie waar evenementen kunnen plaatsvinden.
 * Een {@link Event} verwijst via zijn id naar een Location.
 */
@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naam;
    private String adres;
    private int capaciteit;

    protected Location() {
    }

    public Location(String naam, String adres, int capaciteit) {
        this.naam = naam;
        this.adres = adres;
        this.capaciteit = capaciteit;
    }

    public Long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public int getCapaciteit() {
        return capaciteit;
    }

    public void setCapaciteit(int capaciteit) {
        this.capaciteit = capaciteit;
    }
}
