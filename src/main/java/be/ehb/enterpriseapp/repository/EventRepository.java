package be.ehb.enterpriseapp.repository;

import be.ehb.enterpriseapp.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    /** De tien meest recente evenementen, gesorteerd op tijdstip (nieuwste eerst). */
    List<Event> findTop10ByOrderByTijdstipDesc();
}
