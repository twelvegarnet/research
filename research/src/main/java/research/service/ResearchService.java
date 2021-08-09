package research.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import research.dto.Survey;
import research.dto.SurveyContent;
import research.dto.SurveyResult;
import research.dto.Users;

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

	/**
	 * 회원이 실행한 설문조사 결과를 DB에 삽입한다
	 * 
	 * @param request - 회원의 설문조사 결과를 담고있는 객체
	 */
	void insertResult(HttpServletRequest request);

	Users adminLogin(Users users);

	Users userLogin(Users users);

	/**
	 * 수정된 설문조사 목록을 DB에 update한다
	 * 
	 * @param survey - 수정된 설문조사 번호
	 * @param request - 수정된 설문조사 문제가 담겨있는 객체
	 */
	void updateSurveyContent(Survey survey, HttpServletRequest request);

	void deleteSurvey(Survey survey);

	List<SurveyResult> getSurveyResult(Survey survey);

	List<SurveyContent> getAllSurveyContents(Survey survey);

	List<SurveyResult> getAllSurveyResult(Survey survey);

	/**
	 * 결과창을 보여주기 위해 각 문제별 문항 선택수도 얻어온다
	 * 
	 * @param survey - 설문조사 번호를 담고있는 객체
	 * @return 설문조사 문제 데이터와 선택수를 담은 HashMap
	 */
	List<HashMap<String, Object>> getResult(Survey survey);

	/**
	 * 선택이유 상세보기 시 보여줄 데이터를 얻어온다
	 * 
	 * @param sr - 설문조사 번호, 설문조사 문제번호, 선택번호가 담겨있는 객체
	 * @return - 보내는 값과 모두 일치하는 데이터들
	 */
	List<SurveyResult> getDescriptions(SurveyResult sr);

	/**
	 * 설문조사 제목 클릭시 내가 수행한 설문조사 내용을 가져온다
	 * 
	 * @param survey - 설문조사 번호가 담겨있는 객체
	 * @return - 내가 수행한 설문조사 정보
	 */
	List<HashMap<String, Object>> getMyResult(Survey survey);


}
