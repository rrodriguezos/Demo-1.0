package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Demo;

@Repository
public interface DemoRepository extends JpaRepository<Demo, Integer> {

}
