package be.ehb.enterpriseapp.controller;

import be.ehb.enterpriseapp.model.Event;
import be.ehb.enterpriseapp.model.Location;
import be.ehb.enterpriseapp.repository.EventRepository;
import be.ehb.enterpriseapp.repository.LocationRepository;
import be.ehb.enterpriseapp.web.EventForm;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class EventController {

    private final EventRepository events;
    private final LocationRepository locations;

    public EventController(EventRepository events, LocationRepository locations) {
        this.events = events;
        this.locations = locations;
    }

    /** Index: overzicht van de tien laatste evenementen. */
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("events", events.findTop10ByOrderByTijdstipDesc());
        return "index";
    }

    /** Details: alle gegevens van een specifiek evenement. */
    @GetMapping("/events/{id}")
    public String details(@PathVariable Long id, Model model) {
        Event event = events.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evenement niet gevonden"));
        model.addAttribute("event", event);
        return "details";
    }

    /** New: toont het formulier om een nieuw evenement toe te voegen. */
    @GetMapping("/new")
    public String newForm(Model model) {
        if (!model.containsAttribute("eventForm")) {
            model.addAttribute("eventForm", new EventForm());
        }
        model.addAttribute("locations", locations.findAll());
        return "new";
    }

    /**
     * Verwerkt het formulier. Bij geldige gegevens wordt opgeslagen en
     * teruggekeerd naar de index; anders wordt het formulier opnieuw getoond.
     */
    @PostMapping("/new")
    public String create(@Valid @ModelAttribute("eventForm") EventForm form,
                         BindingResult result, Model model) {
        Location location = null;
        if (form.getLocationId() != null) {
            location = locations.findById(form.getLocationId()).orElse(null);
            if (location == null) {
                result.rejectValue("locationId", "invalid", "Kies een geldige locatie.");
            }
        }
        if (result.hasErrors()) {
            model.addAttribute("locations", locations.findAll());
            return "new";
        }
        events.save(new Event(form.getTijdstip(), form.getTitel(), form.getOmschrijving(),
                form.getOrganisatie(), form.getContactEmail(), location));
        return "redirect:/";
    }
}
