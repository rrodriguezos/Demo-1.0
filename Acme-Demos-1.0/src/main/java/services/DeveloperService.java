package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.DeveloperRepository;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.Developer;

@Service
@Transactional
public class DeveloperService {

	// Managed repository -------------------------

	@Autowired
	private DeveloperRepository developerRepository;

	// Supporting Services -------------------------

	@Autowired
	private UserAccountService userAccountService;

	@Autowired
	private AdministratorService administratorService;

	// Constructors -------------------------------
	public DeveloperService() {
		super();
	}

	// Simple CRUD methods -----------------------------

	public Collection<Developer> findAll() {
		Collection<Developer> result;

		result = developerRepository.findAll();

		return result;
	}

	public Developer findOne(int userId) {
		Developer result;

		result = developerRepository.findOne(userId);

		return result;
	}

	// other methods
	// ----------------------------------------------------------------------

	public Developer findByPrincipal() {
		UserAccount userAccount;
		Developer result;
		int id;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		id = userAccount.getId();
		result = developerRepository.findByDeveloperAccountId(id);
		Assert.notNull(result);

		return result;

	}

	public Actor findByUserAccount(UserAccount userAccount) {
		Assert.notNull(userAccount);
		Developer result;
		result = developerRepository.findByUserAccountId(userAccount.getId());
		return result;
	}

}
