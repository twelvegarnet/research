package research.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import research.dao.ResearchDao;
import research.dto.Survey;
import research.dto.SurveyContent;
import research.dto.SurveyResult;

@Service
public class ResearchServiceImpl implements ResearchService{
	private static final Logger logger = LoggerFactory.getLogger(ResearchServiceImpl.class);
	
	@Autowired
	ResearchDao researchDao;

	@Override
	public List<Survey> getList() {
		return researchDao.selectAll();
	}

	@Override
	@Transactional
	public void write(Survey survey, HttpServletRequest request) {
		// 설문조사 정보 삽입
		researchDao.insertSurvey(survey);
		
		// 반복해서 삽입해야하는 List의 길이(문제 수) 얻어오기
		int cnt = survey.getSurCnt();
		
		// 직전에 삽입한 설문번호 가져오기
		int surSeq = survey.getSurSeq();
		
		// 설문조사 문제 삽입
		for(int i=0; i<cnt; i++) {
			SurveyContent surveyC = new SurveyContent();
			
			// n+1번째 설문조사 문제 삽입하기
			surveyC.setSurSeq(surSeq);
			surveyC.setSurqTitle(request.getParameterValues("surqTitle")[i]);
			surveyC.setSurqTitle1(request.getParameterValues("surqTitle1")[i]);
			surveyC.setSurqTitle2(request.getParameterValues("surqTitle2")[i]);
			if(!request.getParameterValues("surqTitle3")[i].equals("")){
				surveyC.setSurqTitle3(request.getParameterValues("surqTitle3")[i]);
			}
			if(!request.getParameterValues("surqTitle4")[i].equals("")){
				surveyC.setSurqTitle4(request.getParameterValues("surqTitle4")[i]);
			}
			if(!request.getParameterValues("surqTitle5")[i].equals("")){
				surveyC.setSurqTitle5(request.getParameterValues("surqTitle5")[i]);
			}
			
//			logger.info((i+1)+"번째 문제 : {}", surveyC);
			
			researchDao.insertSurveyContent(surveyC);
		}
		
	}

	@Override
	public Survey getSurvey(Survey survey) {
		return researchDao.selectSurvey(survey);
	}

	@Override
	public List<SurveyContent> getSurveyContent(Survey survey) {
		return researchDao.selectSurveyContent(survey);
	}

	@Override
	@Transactional
	public void insertResult(HttpServletRequest request) {
		
		// 회원이 참여한 설문조사 결과를 DB에 삽입한다
		int surCnt = Integer.parseInt(request.getParameter("surCnt"));
		for(int i=1; i<=surCnt; i++) {
			SurveyResult sr = new SurveyResult();
			
			sr.setSurSeq(Integer.parseInt(request.getParameter("surSeq")));
			sr.setSurqSeq(Integer.parseInt(request.getParameter("surqSeq"+i)));
			sr.setChooseNum(Integer.parseInt(request.getParameter("chooseNum"+i)));
			if(!"".equals(request.getParameter("descriptipn"+i)) || request.getParameter("descriptipn"+i) != null) {
				sr.setDescription(request.getParameter("description"+i));
			}
			
//			logger.info("얻어온 각 결과값 확인 : {}", sr);
			
			//결과값 DB 삽입
			researchDao.insertSurveyResult(sr);
			
		}
		
		// 삽입했다면 조회수를 1 올린다
		
	}

	
	
	
	
}
