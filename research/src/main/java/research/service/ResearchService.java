package research.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import research.dto.Survey;

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


}
