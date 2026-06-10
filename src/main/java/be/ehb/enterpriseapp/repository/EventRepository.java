package be.ehb.enterpriseapp.repository;

import be.ehb.enterpriseapp.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    /** De tien meest recent toegevoegde evenementen. */
    List<Event> findTop10ByOrderByIdDesc();
}