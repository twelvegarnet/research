package research.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import research.dto.Survey;
import research.dto.SurveyContent;
import research.service.ResearchService;

@Controller
@RequestMapping(value="/research")
public class ResearchController {
	private static final Logger logger = LoggerFactory.getLogger(ResearchController.class);
	
	@Autowired
	ResearchService researchService;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public void list(Model model) {
		List<Survey> rlist = researchService.getList();
		logger.info("얻어온 전체 설문조사 리스트 : {}", rlist);
		
		model.addAttribute("rlist", rlist);
	}
	
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public void createResearchForm() {
//		logger.info("/research/create [GET] 요청 완료");
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String createResearch(Survey survey, HttpServletRequest request) {
//		logger.info("/research/create [POST] 요청 완료");
		logger.info("얻어온 설문조사 정보 : {}", survey);
		
		//얻어온 데이터를 설문조사 테이블에 삽입
		researchService.write(survey, request);
		
		return "redirect:/research/list";
	}
	
	@RequestMapping(value="/view")
	public void ResearchView(Survey survey, Model model) {
//		logger.info("/research/view [GET] 요청 완료");
		logger.info("얻어온 survey data 확인 : {}", survey);
		
		// 받아온 설문조사 번호에 해당하는 설문조사 전체정보 얻어오기
		Survey s = researchService.getSurvey(survey);
//		logger.info("얻어온 전체 survey data 확인 : {}", s);
		
		// 받아온 설문조사 번호에 해당하는 설문조사 전체 문제정보 얻어오기
		List<SurveyContent> sc = researchService.getSurveyContent(survey);
//		logger.info("얻어온 전체 survey question 확인 : {}", sc);
		
		// 얻어온 전체정보 model값 지정
		model.addAttribute("survey", s);
		model.addAttribute("surveyContent", sc);
	}
	
	@RequestMapping(value="/submit", method=RequestMethod.POST)
	public String submit(HttpServletRequest request) {
		logger.info("/research/submit [POST] 요청 완료");
		
		// 총 문제수 얻어오기
//		int surCnt = Integer.parseInt(request.getParameter("surCnt"));

		// 모든 문제 확인
//		for(int i=1; i<=surCnt; i++) {
//			logger.info("선택 번호 : {}", request.getParameterValues("chooseNum"+i));
//			logger.info("선택 사유 : {}", request.getParameterValues("description"+i));
//		}
		
		// 얻어온 값 결과 테이블에 삽입
		researchService.insertResult(request);
		
		return "redirect:/research/list";
	}
	
	
	
}
