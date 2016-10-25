package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Description;

@Repository
public interface DescriptionRepository extends JpaRepository<Description, Integer> {

}
