package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.Developer;

public interface DeveloperRepository extends JpaRepository<Developer, Integer> {

	@Query("select d from Developer d where d.userAccount.id=?1")
	Developer findByDeveloperAccountId(int userAccountId);

	@Query("select a from Administrator a where a.userAccount.id=?1")
	Developer findByUserAccountId(int userAccountId);

}
