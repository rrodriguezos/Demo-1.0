package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.DemoRepository;
import domain.Demo;
import domain.Developer;

@Service
@Transactional
public class DemoService {

	// Managed repository -------------------
	@Autowired
	private DemoRepository demoRepository;

	// Supporting Services ------------------
	@Autowired
	private DeveloperService developerService;

	@Autowired
	private ResourceService resourceService;

	@Autowired
	private AdministratorService administratorService;

	// COnstructors -------------------------------------------------------
	public DemoService() {
		super();
	}

	// Simple CRUD methods--------------------------------------------------

	public Collection<Demo> findAll() {
		Collection<Demo> result;

		result = demoRepository.findAll();

		return result;
	}

	public Demo findOne(int demoId) {
		Demo result;

		result = demoRepository.findOne(demoId);

		return result;
	}

	// Other Methods--------------------
	private void checkPrincipal(Developer u) {
		Developer developer;

		developer = developerService.findByPrincipal();
		Assert.isTrue(developer != null);

		Assert.isTrue(developer.equals(u));
	}

	// Repository Methods--------------------

}
