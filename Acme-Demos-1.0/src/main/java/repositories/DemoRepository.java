package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Demo;

@Repository
public interface DemoRepository extends JpaRepository<Demo, Integer> {

	@Query("select d from Demo d where d.developer.id = ?1")
	Collection<Demo> findDemosByDeveloper(int developerId);

	
	@Query("select avg(d.comments.size) from Demo d")
	Double averageNumberOfCommentsByDemo();

//	@Query("")
//	Double averageNumberOfStarsByDemo();

}
