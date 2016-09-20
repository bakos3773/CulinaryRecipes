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
//	Adnotacja @Cacheable szuka najpierw wpisu w pami�ci podr�cznej i pomija wywo-
//	�anie metody w przypadku jego odnalezienia. Je�li wpis nie zostanie znaleziony, metoda
//	jest wywo�ywana, a w pami�ci podr�cznej zapisywana jest warto�� zwr�cona przez
//	metod�. Adnotacj� @Cacheable mo�emy sprawi�, �e wiadomo�� zostanie zapisana w pami�ci podr�cznej,
//	a dzi�ki temu unikniemy zb�dnych zapyta� do bazy (ta sama metoda nie bedzie wywolywana ponownie)
	@CachePut("forum")
	public List<Forum> getAllSections();
	
	public List<Forum_Themes> setForumTopics(Forum_Themes forumThemes, int id);
	@CachePut("forumThemes")
//	 Adnotacja @CachePut z kolei nie sprawdza nigdy pasuj�cych warto�ci w pami�ci
//	 cache, zawsze wywo�uje metod�, a zwr�con� przez ni� warto�� zapisuje w pami�ci
//	 podr�cznej
	public List<Forum_Themes> getAllForum_Themes(int id);
	public Forum_Themes getForumThemeById(int id);

	
	public void setTopicAnswer(Forum_Messages forumMessages, int idTopics);
	@CachePut("forumMessages")
	public List<Forum_Messages> getAllAnswerOfTheme(int idSection, int idTopic);
}
