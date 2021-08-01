package research.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import research.dto.Survey;

@Repository
public interface ResearchDao {

	/**
	 * 전체 설문조사 리스트를 얻어온다
	 * 
	 * @return 작성된 전체 설문조사 리스트
	 */
	List<Survey> selectAll();


}
