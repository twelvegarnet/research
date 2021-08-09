package research.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import research.dao.ResearchDao;
import research.dto.Survey;
import research.dto.SurveyContent;
import research.dto.SurveyResult;
import research.dto.Users;

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
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("userId");
		survey.setRegName(userId);
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
			surveyC.setRegName(userId);
			surveyC.setSurqTitle(request.getParameterValues("surqTitle")[i]);
			surveyC.setSurqTitle1(request.getParameterValues("surqTitle1")[i]);
			surveyC.setSurqTitle2(request.getParameterValues("surqTitle2")[i]);
			if(!"".equals(request.getParameterValues("surqTitle3")[i])){
				surveyC.setSurqTitle3(request.getParameterValues("surqTitle3")[i]);
			} else {
				surveyC.setSurqTitle3("");
			}
			if(!"".equals(request.getParameterValues("surqTitle4")[i])){
				surveyC.setSurqTitle4(request.getParameterValues("surqTitle4")[i]);
			} else {
				surveyC.setSurqTitle4("");
			}
			if(!"".equals(request.getParameterValues("surqTitle5")[i])){
				surveyC.setSurqTitle5(request.getParameterValues("surqTitle5")[i]);
			} else {
				surveyC.setSurqTitle5("");
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
		int surSeq = Integer.parseInt(request.getParameter("surSeq"));
		int surCnt = Integer.parseInt(request.getParameter("surCnt"));
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		for(int i=1; i<=surCnt; i++) {
			SurveyResult sr = new SurveyResult();
			
			sr.setSurSeq(surSeq);
			sr.setSurqSeq(Integer.parseInt(request.getParameter("surqSeq"+i)));
			sr.setChooseNum(Integer.parseInt(request.getParameter("chooseNum"+i)));
			if(!"".equals(request.getParameter("descriptipn"+i)) || request.getParameter("descriptipn"+i) != null) {
				sr.setDescription(request.getParameter("description"+i));
			}
			sr.setWriter(userId);
			
//			logger.info("얻어온 각 결과값 확인 : {}", sr);
			
			//결과값 DB 삽입
			researchDao.insertSurveyResult(sr);
			
		}
		
		// 삽입했다면 참여수를 1 올린다
		researchDao.updateHit(surSeq);
		
	}

	@Override
	public Users adminLogin(Users users) {
		return researchDao.selectAdminLogin(users);
	}

	@Override
	public Users userLogin(Users users) {
		return researchDao.selectUsersLogin(users);
	}

	@Override
	@Transactional
	public void updateSurveyContent(Survey survey, HttpServletRequest request) {

		int surCnt = survey.getSurCnt();
		int surSeq = survey.getSurSeq();
		String userName = (String)request.getAttribute("userName");
		survey.setUdtName(userName);
		
		logger.info("담겨있는 Survey 객체 데이터 : {}", survey);
		
		researchDao.updateSurvey(survey);
		
		//해당 설문조사의 모든 문제를 삭제한다
		researchDao.deleteSurveyContent(survey);
		
		for(int i=0; i<surCnt; i++) {
			SurveyContent surveyC = new SurveyContent();
			
			// n+1번째 설문조사 문제 삽입하기
			surveyC.setSurSeq(surSeq);
			surveyC.setSurqTitle(request.getParameterValues("surqTitle")[i]);
			surveyC.setSurqTitle1(request.getParameterValues("surqTitle1")[i]);
			surveyC.setSurqTitle2(request.getParameterValues("surqTitle2")[i]);
			if(!"".equals(request.getParameterValues("surqTitle3")[i])){
				surveyC.setSurqTitle3(request.getParameterValues("surqTitle3")[i]);
			} else {
				surveyC.setSurqTitle3("");
			}
			if(!"".equals(request.getParameterValues("surqTitle4")[i])){
				surveyC.setSurqTitle4(request.getParameterValues("surqTitle4")[i]);
			} else {
				surveyC.setSurqTitle4("");
			}
			if(!"".equals(request.getParameterValues("surqTitle5")[i])){
				surveyC.setSurqTitle5(request.getParameterValues("surqTitle5")[i]);
			} else {
				surveyC.setSurqTitle5("");
			}
			
//			logger.info((i+1)+"번째 문제 : {}", surveyC);
			
			researchDao.insertSurveyContent(surveyC);
		}
	}

	@Override
	public void deleteSurvey(Survey survey) {
		researchDao.deleteSurvey(survey);
	}

	@Override
	public List<SurveyResult> getSurveyResult(Survey survey) {
		return researchDao.selectSurveyResult(survey);
	}

	@Override
	public List<SurveyContent> getAllSurveyContents(Survey survey) {
		return researchDao.selectSurveyContent(survey);
	}

	@Override
	public List<SurveyResult> getAllSurveyResult(Survey survey) {
		return researchDao.selectSurveyResult(survey);
	}

	@Override
	public List<HashMap<String, Object>> getResult(Survey survey) {
		return researchDao.selectTotalResult(survey);
	}

	@Override
	public List<SurveyResult> getDescriptions(SurveyResult sr) {
		return researchDao.selectSurveyResultDescription(sr);
	}

	@Override
	public List<HashMap<String, Object>> getMyResult(Survey survey) {
		return researchDao.selectMyResult(survey);
	}

	
	
	
	
}
