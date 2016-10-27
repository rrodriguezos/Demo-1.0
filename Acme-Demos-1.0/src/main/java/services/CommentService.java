package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CommentRepository;
import security.Authority;
import domain.Actor;
import domain.Comment;
import domain.Resource;

@Transactional
@Service
public class CommentService {

	// Constructor
	// ---------------------------------------------------------------
	public CommentService() {
		super();
	}

	// Managed
	// Repository-----------------------------------------------------------
	@Autowired
	private CommentRepository commentRepository;

	// Supported
	// Services------------------------------------------------------------

	@Autowired
	private ActorService actorService;

	// CRUD methods-------------------------------------------------------------
	public Collection<Comment> findAll() {
		Collection<Comment> result;

		result = commentRepository.findAll();

		return result;
	}

	public Comment findOne(int commentId) {
		Assert.isTrue(commentId != 0);

		Comment result;

		result = commentRepository.findOne(commentId);
		Assert.notNull(result);

		return result;
	}

	// Other Business Methods
	// ------------------------------------------------------

	private void checkPrincipalAdministrator() {
		Actor actor;
		Authority authority;

		actor = actorService.findByPrincipal();
		Assert.isTrue(actor != null);

		authority = new Authority();
		authority.setAuthority("ADMINISTRATOR");

		Assert.isTrue(actor.getUserAccount().getAuthorities()
				.contains(authority));
	}
	
	
	public Collection<Comment> findCommentsByDemoId(int demoId){
		Collection<Comment> result;
		
		result = commentRepository.findCommentsByDemoId(demoId);
		return result;
	}
}
