package research.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import research.dto.Survey;
import research.dto.SurveyContent;
import research.dto.SurveyResult;
import research.dto.Users;
import research.util.Paging;

@Repository
public interface ResearchDao {

	/**
	 * 전체 설문조사 리스트를 얻어온다
	 * 
	 * @return 작성된 전체 설문조사 리스트
	 */
	List<Survey> selectAll(Paging paging);

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

	/**
	 * 설문조사 조회에서 보여줄 설문조사 정보를 얻어온다
	 * 
	 * @param survey - 설문조사 번호가 담겨있는 객체
	 * @return 설문조사 번호에 해당하는 정보
	 */
	Survey selectSurvey(Survey survey);

	/**
	 * 설문조사 조회에서 보여줄 설문조사 문제 정보를 얻어온다
	 * 
	 * @param survey - 설문조사 번호가 담겨있는 객체
	 * @return 설문조사 번호에 해당하는 모든 문제
	 */
	List<SurveyContent> selectSurveyContent(Survey survey);

	/**
	 * 회원이 실행한 설문조사 결과를 DB에 삽입한다
	 * 
	 * @param request - 회원의 설문조사 결과를 담고있는 객체
	 */
	void insertSurveyResult(SurveyResult sr);

	Users selectAdminLogin(Users users);

	Users selectUsersLogin(Users users);

	/**
	 * 회원이 설문조사를 참여하여 완료했을 경우, 참여수를 1 증가시킨다
	 * 
	 * @param surSeq - 참여수를 증가시길 설문조사 번호
	 */
	void updateHit(int surSeq);

	/**
	 * 설문조사 수정시 총 문제수와 수정일, 수정자를 변경한다
	 * 
	 * @param survey - 변경된 총 문제수와 수정자를 담고있는 객체
	 */
	void updateSurvey(Survey survey);

	/**
	 * 설문조사 수정시 기존의 문제를 모두 삭제한다
	 * 
	 * @param survey - 설문조사 번호를 담고있는 객체
	 */
	void deleteSurveyContent(Survey survey);

	void deleteSurvey(Survey survey);

	List<SurveyResult> selectSurveyResult(Survey survey);

	/**
	 * 결과창을 보여주기 위해 각 문제별 문항 선택수도 얻어온다
	 * 
	 * @param survey - 설문조사 번호를 담고있는 객체
	 * @return 설문조사 문제 데이터와 선택수를 담은 HashMap
	 */
	List<HashMap<String, Object>> selectTotalResult(Survey survey);

	/**
	 * 선택이유 상세보기 시 보여줄 데이터를 얻어온다
	 * 
	 * @param sr - 설문조사 번호, 설문조사 문제번호, 선택번호가 담겨있는 객체
	 * @return - 보내는 값과 모두 일치하는 데이터들
	 */
	List<SurveyResult> selectSurveyResultDescription(SurveyResult sr);

	/**
	 * 설문조사 제목 클릭시 내가 수행한 설문조사 내용을 가져온다
	 * 
	 * @param survey - 설문조사 번호가 담겨있는 객체
	 * @return - 내가 수행한 설문조사 정보
	 */
	List<HashMap<String, Object>> selectMyResult(Survey survey);

	/**
	 * 회원이 수정한 설문조사 결과를 기존 DB에 덮어씌운다
	 * 
	 * @param request - 수정된 설문조사 결과를 가지고있는 객체
	 */
	void updateSurveyResult(SurveyResult sr);

	/**
	 * 접속한 회원이 작성한 설문조사 결과를 삭제한다
	 * 
	 * @param surveyResult - 설문조사 번호와 아이디가 담겨있는 객체
	 */
	void deleteSurveyResult(SurveyResult surveyResult);

	/**
	 * 설문조사 결과 삭제시 참여수를 1 감소시킨다
	 * 
	 * @param surveyResult - 설문조사 번호가 담겨있는 객체
	 */
	void decreaseHit(SurveyResult surveyResult);

	/**
	 * 검색된 전체 설문조사 리스트 조회
	 * 
	 * @param p - 페이징을 적용할 기준값을 가진 객체
	 * @return 기준값에 해당하는 설문조사 리스트
	 */
	int selectCntAll(Paging p);

	int userChk(SurveyResult sr);

	/**
	 * 종료된 설문조사 상사보기에서 메일 보내기 클릭 시 
	 * 해당 설문조사에 참여한 모든 회원들의 이메일 정보를 불러온다
	 * 
	 * @param s - 설문조사 번호를 담고있는 객체
	 * @return - 해당 설문조사를 수행한 모든 결과 데이터
	 */
	List<Users> getUsersData(Survey s);


}
