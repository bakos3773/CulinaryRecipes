package com.bakos.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bakos.UserDAO.ForumDAO;
import com.bakos.UserDTO.Forum;
import com.bakos.UserDTO.Forum_Messages;
import com.bakos.UserDTO.Forum_Themes;

@Service
public class ForumServiceImpl implements ForumService{
	
	@Autowired
	ForumDAO forumDAO;

	@Override
	public List<Forum> setSections(Forum forum) {
		
		return forumDAO.setSections(forum);
	}

	@Override
	public List<Forum> getAllSections(){
		
		return forumDAO.getAllSections();
	}

	@Override
	public void setForumTopics(Forum_Themes forumThemes, int id) {
		
		forumDAO.setForumTopics(forumThemes, id);
	}

	@Override
	public List<Forum_Themes> getAllForum_Themes(int id) {
		
		return forumDAO.getAllForum_Themes(id);
	}

	@Override
	public Forum_Themes getForumThemeById(int id) {
		
		return forumDAO.getForumThemeById(id);
	}

	@Override
	public void setTopicAnswer(Forum_Messages forumMessages, int idTopics) {
		
		forumDAO.setTopicAnswer(forumMessages, idTopics);
	}

	@Override
	public List<Forum_Messages> getAllAnswerOfTheme(int idSection, int idTopic) {
		
		return forumDAO.getAllAnswerOfTheme(idSection, idTopic);
	}



}
