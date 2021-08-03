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
	public void ResearchView() {
		logger.info("/research/view [GET] 요청 완료");
	}
	
	
	
}
