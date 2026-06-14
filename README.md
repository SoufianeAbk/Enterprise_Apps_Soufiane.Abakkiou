# Enterprise Apps — Samen Anderlecht

Een Spring Boot-webapplicatie voor **Samen Anderlecht vzw**, een buurtorganisatie in Anderlecht.
De app laat beheerders toe om buurtactiviteiten (evenementen) te beheren en stelt buurtbewoners in staat om via een contactformulier in contact te komen met de organisatie.

## Inhoud

- [Functionaliteiten]
- [Technologieën en libraries]
- [Projectstructuur](
- [Vereisten]
- [Installatie en opstarten]
- [Configuratie]
- [Pagina-overzicht]
- [Datamodel]
- [Gebruikte tutorials en documentatie]
- [AI-gesprekken]

## Functionaliteiten

- **Evenementenbeheer** — evenementen toevoegen, bekijken en verwijderen
- **Overzichtspagina** — toont de tien meest recente evenementen (titel + organisatie)
- **Detailpagina** — alle gegevens van een specifiek evenement, inclusief locatie
- **Contactformulier** — stuurt een e-mail naar de organisatie via SMTP
- **Formuliervalidatie** — server-side validatie met duidelijke foutmeldingen (o.a. controle op een geldig e-mailadres)
- **Voorbeelddata** — bij de eerste opstart worden automatisch 10 evenementen en 3 locaties aangemaakt

## Technologieën en libraries

| Laag | Technologie |
| --- | --- |
| Framework | Spring Boot 4.0.6 |
| Taal | Java 21 |
| Database | H2 (in-memory) |
| ORM | Spring Data JPA / Hibernate |
| Templates | Thymeleaf |
| Validatie | Jakarta Bean Validation |
| Mail | Spring Mail (Mailtrap voor ontwikkeling) |
| Styling | Tailwind CSS (via CDN) |
| Build | Maven (met `mvnw` wrapper) |

## Projectstructuur

```text
src/main/java/be/ehb/enterpriseapp/
├── Application.java              - Startpunt van de applicatie
├── config/
│   └── DataInitializer.java      - Vult de database bij eerste opstart
├── controller/
│   ├── AboutController.java      - /about
│   ├── ContactController.java    - /contact
│   └── EventController.java      - /, /events/{id}, /new
├── model/
│   ├── Event.java                - JPA-entiteit: evenement
│   └── Location.java             - JPA-entiteit: locatie
├── repository/
│   ├── EventRepository.java
│   └── LocationRepository.java
├── service/
│   └── MailService.java          - Verstuurt contactberichten via SMTP
└── web/
    ├── ContactForm.java          - Formulierobject contactpagina
    └── EventForm.java            - Formulierobject nieuw evenement

src/main/resources/
├── application.properties        - Configuratie (database, mail, Thymeleaf)
├── messages.properties           - Aangepaste validatiemeldingen
└── templates/
    ├── fragments/layout.html     - Gedeelde header, navigatie en footer
    ├── index.html                - Overzicht evenementen
    ├── details.html              - Detailpagina evenement
    ├── new.html                  - Formulier nieuw evenement
    ├── contact.html              - Contactpagina
    └── about.html                - Over de organisatie
```

## Vereisten

- **Java 21** (JDK)
- **Maven 3.9+** — of gebruik gewoon de meegeleverde `mvnw` / `mvnw.cmd` wrapper, dan moet je Maven niet apart installeren

## Installatie en opstarten

```bash
# 1. Repository klonen
git clone https://github.com/SoufianeAbk/Enterprise_Apps_Soufiane.Abakkiou
cd Enterprise_Apps_Soufiane.Abakkiou

# 2. Applicatie bouwen en starten (Linux/macOS)
./mvnw spring-boot:run
```

Op **Windows** gebruik je in plaats daarvan:

```bat
mvnw.cmd spring-boot:run
```

Daarna:

- De applicatie is bereikbaar op **http://localhost:8080**
- De H2-console staat op **http://localhost:8080/h2-console** (JDBC URL: `jdbc:h2:mem:ngodb`, gebruiker: `sa`, geen wachtwoord)

> Bij de eerste opstart vult `DataInitializer` de database met 3 locaties en 10 voorbeeldevenementen, zodat het overzicht meteen iets toont.

## Configuratie

De standaardconfiguratie in `application.properties` gebruikt een H2 in-memory database en **Mailtrap** als SMTP-server voor ontwikkeling, zodat e-mails fictief verstuurd worden zonder echte mailbox.

Stel de volgende omgevingsvariabelen in vóór het opstarten (of pas de standaardwaarden in `application.properties` aan):

| Variabele | Omschrijving | Standaard |
| --- | --- | --- |
| `MAILTRAP_HOST` | SMTP-host | `sandbox.smtp.mailtrap.io` |
| `MAILTRAP_PORT` | SMTP-poort | `2525` |
| `MAILTRAP_USER` | Mailtrap-gebruikersnaam | *(leeg)* |
| `MAILTRAP_PASS` | Mailtrap-wachtwoord | *(leeg)* |
| `CONTACT_TO` | Ontvanger van contactberichten | `info@samenanderlecht.be` |
| `CONTACT_FROM` | Afzender van contactberichten | `no-reply@samenanderlecht.be` |

**Voorbeeld (Linux/macOS):**

```bash
export MAILTRAP_USER=jouw_gebruikersnaam
export MAILTRAP_PASS=jouw_wachtwoord
./mvnw spring-boot:run
```

> De H2-database wordt bij elke herstart opnieuw aangemaakt (`spring.jpa.hibernate.ddl-auto=create-drop`). Alle data gaat dus verloren bij het afsluiten van de applicatie.

## Pagina-overzicht

| URL | Methode | Omschrijving |
| --- | --- | --- |
| `/` | GET | Overzicht van de 10 meest recente evenementen |
| `/events/{id}` | GET | Detailpagina van een evenement |
| `/new` | GET | Formulier om een evenement toe te voegen |
| `/new` | POST | Verwerkt het formulier en slaat het evenement op |
| `/events/{id}/delete` | POST | Verwijdert een evenement |
| `/contact` | GET | Contactformulier |
| `/contact` | POST | Verstuurt het contactbericht via e-mail |
| `/about` | GET | Informatiepagina over de organisatie |
| `/h2-console` | GET | H2-databaseconsole (enkel voor ontwikkeling) |

## Datamodel

```text
Location
├── id          Long (PK)
├── naam        String
├── adres       String
└── capaciteit  int

Event
├── id            Long (PK)
├── tijdstip      LocalDateTime
├── titel         String
├── omschrijving  String (max 2000 tekens)
├── organisatie   String
├── contactEmail  String
└── location      ManyToOne → Location
```

Een `Event` verwijst via een `location_id` naar een `Location`. Zo wordt de locatie als een apart object met naam, adres en capaciteit bijgehouden.

## Gebruikte tutorials en documentatie

- [Spring Boot Reference Documentation](https://docs.spring.io/spring-boot/index.html)
- [Spring Data JPA — Reference](https://docs.spring.io/spring-data/jpa/reference/index.html)
- [Thymeleaf — Documentation](https://www.thymeleaf.org/documentation.html)
- [Tailwind CSS — Documentation](https://tailwindcss.com/docs)
- [JetBrains IntelliJ IDEA — Maven support](https://www.jetbrains.com/help/idea/maven-support.html)
- [JetBrains IntelliJ IDEA — Tailwind CSS](https://www.jetbrains.com/help/idea/tailwind-css.html)

## AI-gesprekken
Tijdens de ontwikkeling werden onderstaande AI-gesprekken uitgevoerd:

- [Claude — gesprek](https://claude.ai/share/4759d2db-dcb7-499a-b550-3667fc9500d0)
- [ChatGPT — gesprek 1](https://chatgpt.com/c/6a296589-6ec4-83eb-8ef9-fba847a746ba)
- [ChatGPT — gesprek 2](https://chatgpt.com/c/6a2967b9-9b98-83eb-a2fe-66f1ed0d695d)


## Academisch project — Erasmushogeschool Brussel (EHB), opleiding Programmeren — vak Enterprise Applications.
