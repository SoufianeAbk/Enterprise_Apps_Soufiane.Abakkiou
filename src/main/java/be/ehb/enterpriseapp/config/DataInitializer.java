package be.ehb.enterpriseapp.config;

import be.ehb.enterpriseapp.model.Event;
import be.ehb.enterpriseapp.model.Location;
import be.ehb.enterpriseapp.repository.EventRepository;
import be.ehb.enterpriseapp.repository.LocationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Vult de in-memory database met voorbeelddata zodat het prototype
 * meteen iets toont. Wordt enkel uitgevoerd als de database leeg is.
 */
@Component
public class DataInitializer implements CommandLineRunner {

    private final LocationRepository locations;
    private final EventRepository events;

    public DataInitializer(LocationRepository locations, EventRepository events) {
        this.locations = locations;
        this.events = events;
    }

    @Override
    public void run(String... args) {
        if (locations.count() > 0) {
            return;
        }

        Location buurthuis = locations.save(new Location(
                "Buurthuis De Verbinding", "Nijverheidskaai 170, 1070 Anderlecht", 120));
        Location park = locations.save(new Location(
                "Astridpark Paviljoen", "Astridpark 1, 1070 Anderlecht", 60));
        Location sporthal = locations.save(new Location(
                "Sporthal Scheut", "Ninoofsesteenweg 999, 1070 Anderlecht", 250));

        LocalDateTime base = LocalDateTime.now().withMinute(0).withSecond(0).withNano(0);

        events.save(new Event(base.plusDays(2).withHour(18),
                "Gemeenschappelijk buurtdiner",
                "Een warme maaltijd voor en door de buurt. Iedereen is welkom, een bijdrage is vrijblijvend.",
                "Eigen beheer", "diner@samenanderlecht.be", buurthuis));

        events.save(new Event(base.plusDays(5).withHour(14),
                "Huiswerkbegeleiding voor kinderen",
                "Vrijwilligers helpen kinderen uit de buurt met hun schoolwerk.",
                "Eigen beheer", "onderwijs@samenanderlecht.be", buurthuis));

        events.save(new Event(base.plusDays(7).withHour(10),
                "Kledinginzameling",
                "Inzameling van warme winterkleding voor wie het nodig heeft.",
                "Rode Kruis Anderlecht", "info@rodekruis-anderlecht.be", sporthal));

        events.save(new Event(base.plusDays(9).withHour(19),
                "Taaltafel Nederlands",
                "Oefen je Nederlands in een ontspannen sfeer met anderstalige buurtbewoners.",
                "Eigen beheer", "taal@samenanderlecht.be", buurthuis));

        events.save(new Event(base.plusDays(12).withHour(13),
                "Buurtmoestuin: samen planten",
                "We leggen samen de moestuin aan in het Astridpark. Groene vingers niet vereist!",
                "Natuurpunt Brussel", "tuin@natuurpunt-brussel.be", park));

        events.save(new Event(base.plusDays(14).withHour(20),
                "Filmavond onder de sterren",
                "Een gratis openluchtfilm voor jong en oud in het paviljoen.",
                "Eigen beheer", "cultuur@samenanderlecht.be", park));

        events.save(new Event(base.plusDays(16).withHour(9),
                "Sportdag voor jongeren",
                "Een dag vol sport en spel voor jongeren tussen 12 en 18 jaar.",
                "Sporting Anderlecht Jeugd", "jeugd@sporting-anderlecht.be", sporthal));

        events.save(new Event(base.plusDays(19).withHour(15),
                "Repair Café",
                "Breng je kapotte spullen mee en herstel ze samen met onze vrijwilligers.",
                "Eigen beheer", "repair@samenanderlecht.be", buurthuis));

        events.save(new Event(base.plusDays(21).withHour(11),
                "Gezondheidsworkshop",
                "Een infosessie over gezonde voeding en beweging, met gratis proevertjes.",
                "Wijkgezondheidscentrum Kaai", "gezond@wgc-kaai.be", buurthuis));

        events.save(new Event(base.plusDays(24).withHour(18),
                "Solidariteitsconcert",
                "Lokale artiesten spelen ten voordele van het buurtfonds.",
                "Eigen beheer", "concert@samenanderlecht.be", sporthal));

        events.save(new Event(base.plusDays(27).withHour(14),
                "Workshop CV en sollicitatie",
                "Hulp bij het opstellen van je cv en het voorbereiden van sollicitatiegesprekken.",
                "VDAB Brussel", "begeleiding@vdab-brussel.be", buurthuis));

        events.save(new Event(base.plusDays(30).withHour(10),
                "Buurtontbijt",
                "Start de dag samen met een gezellig ontbijt voor de hele buurt.",
                "Eigen beheer", "ontbijt@samenanderlecht.be", park));
    }
}
