package research.service;

import java.util.List;

import research.dto.Survey;

public interface ResearchService {

	/**
	 * 전체 설문조사 리스트를 얻어온다
	 * 
	 * @return 작성된 전체 설문조사 리스트
	 */
	List<Survey> getList();


}
