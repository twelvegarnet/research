package research.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import research.dto.Survey;
import research.dto.SurveyContent;

@Repository
public interface ResearchDao {

	/**
	 * 전체 설문조사 리스트를 얻어온다
	 * 
	 * @return 작성된 전체 설문조사 리스트
	 */
	List<Survey> selectAll();

	/**
	 * 작성된 설문조사 정보를 DB에 삽입한다
	 * 
	 * @param survey - 설문조사 정보가 들어있는 객체
	 */
	void insertSurvey(Survey survey);

	/**
	 * 작성된 모든 설문조사 문제를 DB에 삽입한다
	 * 
	 * @param surveyC - 하나의 설문조사 안의 n번째 설문조사 문제 정보
	 */
	void insertSurveyContent(SurveyContent surveyC);


}
