package research.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import research.dto.Survey;
import research.dto.SurveyContent;

public interface ResearchService {

	/**
	 * 전체 설문조사 리스트를 얻어온다
	 * 
	 * @return 작성된 전체 설문조사 리스트
	 */
	List<Survey> getList();

	/**
	 * 설문조사 작성 폼에서 작성된 내용을 DB에 삽입한다
	 * @param survey 
	 * 
	 * @param request - 설문조사 정보를 갖고있는 객체
	 */
	void write(Survey survey, HttpServletRequest request);

	/**
	 * 설문조사 조회에서 보여줄 설문조사 정보를 얻어온다
	 * 
	 * @param survey - 설문조사 번호가 담겨있는 객체
	 * @return 설문조사 번호에 해당하는 정보
	 */
	Survey getSurvey(Survey survey);

	/**
	 * 설문조사 조회에서 보여줄 설문조사 문제 정보를 얻어온다
	 * 
	 * @param survey - 설문조사 번호가 담겨있는 객체
	 * @return 설문조사 번호에 해당하는 모든 문제
	 */
	List<SurveyContent> getSurveyContent(Survey survey);


}
