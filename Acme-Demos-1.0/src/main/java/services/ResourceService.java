package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ResourceRepository;
import domain.Developer;
import domain.Resource;

@Service
@Transactional
public class ResourceService {

	// Managed repository -------------------
	@Autowired
	private ResourceRepository resourceRepository;

	// Supporting Services ------------------
	@Autowired
	private DeveloperService developerService;

	// COnstructors -------------------------
	public ResourceService() {
		super();
	}

	// Simple CRUD methods--------------------

	public Collection<Resource> findAll() {
		Collection<Resource> result;

		result = resourceRepository.findAll();

		return result;
	}

	public Resource findOne(int dailyPlanId) {
		Resource result;

		result = resourceRepository.findOne(dailyPlanId);

		return result;
	}

	// Other Methods--------------------

	private void checkPrincipal(Developer u) {
		Developer developer;

		developer = developerService.findByPrincipal();
		Assert.isTrue(developer != null);

		Assert.isTrue(developer.equals(u));
	}

}
