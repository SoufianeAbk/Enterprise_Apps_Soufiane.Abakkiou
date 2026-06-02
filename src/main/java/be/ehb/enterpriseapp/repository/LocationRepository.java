package be.ehb.enterpriseapp.repository;

import be.ehb.enterpriseapp.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
