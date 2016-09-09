package com.bakos.UserDAO;

import java.util.List;

import com.bakos.UserDTO.Forum;
import com.bakos.UserDTO.Forum_Messages;
import com.bakos.UserDTO.Forum_Themes;

public interface ForumDAO {

	public List<Forum> setSections(Forum forum);
	public List<Forum> getAllSections();
	
	public List<Forum_Themes> setForumTopics(Forum_Themes forumThemes, int id);
	public List<Forum_Themes> getAllForum_Themes(int id);
	public Forum_Themes getForumThemeById(int id);

	
	public void setTopicAnswer(Forum_Messages forumMessages, int idTopics);
	public List<Forum_Messages> getAllAnswerOfTheme(int idSection, int idTopic);
}
