package com.bakos.UserDAO;

import java.util.List;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.bakos.UserDTO.Forum;
import com.bakos.UserDTO.Forum_Messages;
import com.bakos.UserDTO.Forum_Themes;

public interface ForumDAO {

	public List<Forum> setSections(Forum forum);
	//@Cacheable("forum")//lepiej uzywac do wczytywania wartosci stalych ktore sa czesto przegladane na stronie
//	Adnotacja @Cacheable szuka najpierw wpisu w pamiêci podrêcznej i pomija wywo-
//	³anie metody w przypadku jego odnalezienia. Jeœli wpis nie zostanie znaleziony, metoda
//	jest wywo³ywana, a w pamiêci podrêcznej zapisywana jest wartoœæ zwrócona przez
//	metodê. Adnotacj¹ @Cacheable mo¿emy sprawiæ, ¿e wiadomoœæ zostanie zapisana w pamiêci podrêcznej,
//	a dziêki temu unikniemy zbêdnych zapytañ do bazy (ta sama metoda nie bedzie wywolywana ponownie)
	@CachePut("forum")
	public List<Forum> getAllSections();
	
	public List<Forum_Themes> setForumTopics(Forum_Themes forumThemes, int id);
	@CachePut("forumThemes")
//	 Adnotacja @CachePut z kolei nie sprawdza nigdy pasuj¹cych wartoœci w pamiêci
//	 cache, zawsze wywo³uje metodê, a zwrócon¹ przez ni¹ wartoœæ zapisuje w pamiêci
//	 podrêcznej
	public List<Forum_Themes> getAllForum_Themes(int id);
	public Forum_Themes getForumThemeById(int id);

	
	public void setTopicAnswer(Forum_Messages forumMessages, int idTopics);
	@CachePut("forumMessages")
	public List<Forum_Messages> getAllAnswerOfTheme(int idSection, int idTopic);
}
