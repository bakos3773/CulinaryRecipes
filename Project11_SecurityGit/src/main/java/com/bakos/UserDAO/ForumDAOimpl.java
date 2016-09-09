package com.bakos.UserDAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bakos.UserDTO.Forum;
import com.bakos.UserDTO.Forum_Messages;
import com.bakos.UserDTO.Forum_Themes;

@Repository
@Transactional
public class ForumDAOimpl implements ForumDAO{

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	UserDAO userDAO;
	
	List<Forum_Themes> listForumThemes = new ArrayList<Forum_Themes>();
	
	@Override
	public List<Forum> setSections(Forum forum) {
		
		Date data = new Date();
		
		Forum forumDB = new Forum();
		forumDB.setSections(forum.getSections());
		forumDB.setLastPost(data+"");
		
		manager.persist(forumDB);
		
		List<Forum> listForum = new ArrayList<Forum>();
		
		return listForum;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Forum> getAllSections() {
		
		Query query = manager.createQuery("Select x FROM Forum x");
		List<Forum> allSections = query.getResultList();
		
			return allSections;
	}

	@Override
	public List<Forum_Themes> setForumTopics(Forum_Themes forumThemes, int id) {
		
		Query query = manager.createQuery("Select x FROM Forum x WHERE x.id= :id");
		Forum forum = (Forum) query.setParameter("id", id).getSingleResult();
		int counterTopic = forum.getHowManyThemes();
		counterTopic++;
		forum.setHowManyThemes(counterTopic);
				
		Date data = new Date();		
		Forum_Themes forumTopic = new Forum_Themes();
		forumTopic.setThemes(forumThemes.getThemes());
		forumTopic.setLastPost(data+"");
		forumTopic.setAuthor(userDAO.findUserByUsername().getLogin());
		forumTopic.setForum(forum);
		

		manager.merge(forum);
		manager.persist(forumTopic);
	
		
		return null;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Forum_Themes> getAllForum_Themes(int id) {

		Query query = manager.createQuery("SELECT x FROM Forum y INNER JOIN y.forumThemes x WHERE y.id= :id");	
		query.setParameter("id", id);
		List<Forum_Themes> allThemes = query.getResultList();
		
		return allThemes;
	}

	@Override
	public Forum_Themes getForumThemeById(int id) {
		
		Query query = manager.createQuery("SELECT x FROM Forum_Themes x WHERE x.id= :id");
		query.setParameter("id", id);
		Forum_Themes forum_Theme = (Forum_Themes) query.getSingleResult();
		return forum_Theme;
	}

	@Override
	public void setTopicAnswer(Forum_Messages forumMessages, int idTopics) {
		
		Forum_Messages messages = new Forum_Messages();
		messages.setAuthor(userDAO.findUserByUsername().getLogin());
		messages.setDate(new Date()+"");
		messages.setMessage(forumMessages.getMessage());
		
		Query query = manager.createQuery("SELECT x FROM Forum_Themes x WHERE id= :id");
		query.setParameter("id", idTopics);
		Forum_Themes forumThemes = (Forum_Themes)query.getSingleResult();
		int incrementNumerAnswers = forumThemes.getNumberOfAnswers(); incrementNumerAnswers++;
		forumThemes.setNumberOfAnswers(incrementNumerAnswers);
		messages.setForumTopics(forumThemes);
		
		Forum getForum = forumThemes.getForum();
		int counterTopic = getForum.getHowManyPosts();
		counterTopic++;
		getForum.setHowManyPosts(counterTopic);
		
		manager.merge(getForum);		
		manager.persist(messages);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Forum_Messages> getAllAnswerOfTheme(int idSection, int idTopic) {
		
		Query query = manager.createQuery("SELECT y FROM Forum_Themes x INNER JOIN x.forumMessages y WHERE x.id= :id");
		query.setParameter("id", idTopic);
		List<Forum_Messages> allThemes = query.getResultList();
		
		return allThemes;
	}



}