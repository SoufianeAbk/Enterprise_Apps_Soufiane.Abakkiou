package be.ehb.enterpriseapp.web;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * Formulierobject voor het toevoegen van een nieuw evenement.
 * De locatie wordt gekozen via haar id (zie {@code locationId}).
 */
public class EventForm {

    @NotNull(message = "Kies een tijdstip.")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime tijdstip;

    @NotBlank(message = "Titel is verplicht.")
    private String titel;

    @NotBlank(message = "Omschrijving is verplicht.")
    private String omschrijving;

    @NotBlank(message = "Organisatie is verplicht.")
    private String organisatie;

    @NotBlank(message = "E-mailadres is verplicht.")
    @Email(message = "Geef een geldig e-mailadres in.")
    private String contactEmail;

    @NotNull(message = "Kies een locatie.")
    private Long locationId;

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

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }
}
