package research.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.nurigo.java_sdk.exceptions.CoolsmsException;
import research.dto.Survey;
import research.dto.SurveyContent;
import research.dto.SurveyResult;
import research.dto.Users;
import research.service.ResearchService;
import research.util.Coolsms;
import research.util.Paging;

@Controller
@RequestMapping(value="/research")
public class ResearchController {
	private static final Logger logger = LoggerFactory.getLogger(ResearchController.class);
	
	@Autowired
	ResearchService researchService;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@RequestMapping(value="/login")
	public void main() {
		logger.info("/research/login [GET] 요청 완료");
	}
	
	
	@RequestMapping(value="/adminLogin")
	public String adminLogin(Users users, HttpSession session) {
		logger.info("/research/adminLogin 요청 완료");
		
		users = researchService.adminLogin(users);
		if(users!=null) {
			session.setAttribute("userNo", users.getUserNo());
			session.setAttribute("userId", users.getUserId());
			session.setAttribute("userName", users.getUserName());
			session.setAttribute("userGrade", users.getUserGrade());
		}
		
		return "redirect:/research/list";
	}

	@RequestMapping(value="/userLogin")
	public String userLogin(Users users, HttpSession session) {
		logger.info("/research/userLogin 요청 완료");
		
		users = researchService.userLogin(users);
		if(users!=null) {
			session.setAttribute("userNo", users.getUserNo());
			session.setAttribute("userId", users.getUserId());
			session.setAttribute("userName", users.getUserName());
			session.setAttribute("userGrade", users.getUserGrade());
		}
		
		return "redirect:/research/list";
	}
	
	@RequestMapping(value="/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/research/login";
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public void list(
			@RequestParam(defaultValue="0") int curPage
			, @RequestParam(defaultValue="") String category
			, @RequestParam(defaultValue="") String search
			, HttpServletRequest request
			,Model model) {
		
		HttpSession session = request.getSession();
		String writer = (String)session.getAttribute("userId");
		
		Paging p = new Paging();
		p.setCurPage(curPage);
		p.setCategory(category);
		p.setSearch(search);
		
		Paging paging = researchService.getPaging(p);
		paging.setCategory(category);
		paging.setSearch(search);
		paging.setWriter(writer);

		model.addAttribute("paging", paging);
		
		List<Survey> slist = researchService.getList(paging);
		logger.info("얻어온 전체 설문조사 리스트 : {}", slist);
		
		model.addAttribute("slist", slist);
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
	
	@RequestMapping(value="/updateSurveyContent")
	public void updateSurveyContentForm(Survey survey, Model model) {
		Survey s = researchService.getSurvey(survey);
		
		List<SurveyContent> sc = researchService.getSurveyContent(survey);
		
		model.addAttribute("survey", s);
		model.addAttribute("surveyContent", sc);
		
	}
	
	@RequestMapping(value="/updateSurveyContent", method=RequestMethod.POST)
	public String updateSurvey(Survey survey, HttpServletRequest request) {
		
		researchService.updateSurveyContent(survey, request);
		
		
		return "redirect:/research/view?surSeq="+survey.getSurSeq();
	}
	
	@RequestMapping(value="/deleteSurvey")
	public String deleteSurvey(Survey survey) {
		
		researchService.deleteSurvey(survey);
		
		return "redirect:/research/list";
	}
	
	@RequestMapping(value="/view")
	public void viewSurvey(Survey survey, SurveyResult surveyResult, HttpServletRequest request, Model model) {
		Survey s = researchService.getSurvey(survey);
		
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		survey.setRegName(userId);
		List<HashMap<String, Object>> surveyContent = researchService.getMyResult(survey);
		
		model.addAttribute("survey", s);
		model.addAttribute("surveyContent", surveyContent);
	}
	
	@RequestMapping(value="/doSurvey")
	public void doSurvey(Survey survey, Model model) {
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
	
	@RequestMapping(value="/viewResult")
	public void viewResult(Survey survey, Model model) {
//		logger.info("viewResult 요청 완료");
		
		Survey s = researchService.getSurvey(survey);
//		List<SurveyContent> surveyContent = researchService.getAllSurveyContents(survey);
//		List<SurveyResult> surveyResult = researchService.getAllSurveyResult(survey);
		List<HashMap<String, Object>> surveyContent = researchService.getResult(survey);
		
//		for( HashMap<String, Object> i : surveyContent) {
//			logger.info("얻어온 각각의 데이터 확인 : {}", i);
//		}
		
		model.addAttribute("survey", s);
		model.addAttribute("surveyContent", surveyContent);
//		model.addAttribute("surveyResult", surveyResult);
	}
	
	@RequestMapping(value="/viewDescription")
	public void viewDescription(SurveyResult sr, Model model) {
//		logger.info("얻어온 sr 데이터 확인 : {}", sr);
		
		List<SurveyResult> srList = researchService.getDescriptions(sr);
		
//		for(SurveyResult s : srList) {
//			logger.info("얻어온 SurveyResult 데이터 확인 : {}", s);
//		}
		
		model.addAttribute("srList", srList);
	}
	
	@RequestMapping(value="/updateSurveyResult", method=RequestMethod.GET)
	public void updateSurveyResultForm(Survey survey, HttpServletRequest request, Model model) {
//		logger.info("얻어온 survey 데이터 확인 : {}", survey);
		Survey s = researchService.getSurvey(survey);
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		survey.setRegName(userId);
		List<HashMap<String, Object>> surveyContent = researchService.getMyResult(survey);
		
		model.addAttribute("survey", s);
		model.addAttribute("surveyContent", surveyContent);
	}
	
	@RequestMapping(value="/updateSurveyResult", method=RequestMethod.POST)
	public String updateSurveyResult(HttpServletRequest request) {
		
		researchService.updateSurveyResult(request);
		
		int surSeq = Integer.parseInt(request.getParameter("surSeq"));
		
		return "redirect:/research/view?surSeq="+surSeq;
	}
	
	@RequestMapping(value="/deleteSurveyResult")
	public String deleteSurveyResult(SurveyResult surveyResult, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		surveyResult.setWriter(userId);
//		logger.info("얻어온 SurveyResult의 값 : {}", surveyResult);
//		logger.info("접속되어있는 아이디 : {}", userId);
		
		// 해당 회원의 설문조사 결과 삭제
		researchService.deleteSurveyResult(surveyResult);
		
		// 설문조사 리스트로 이동
		return "redirect:/research/list";
	}
	
	@RequestMapping(value="/sendMail", method=RequestMethod.POST)
	public String mailSending(Survey survey) {
		
//		String setfrom = "ehdcks112@gmail.com";
//		String tomail = "ehdcks112@gmail.com"; // 받는 사람 이메일
//		String title = "테스트용 메일 제목"; // 제목
//		String content = "테스트용 메일 내용입니다."; // 내용
//
//		try {
//			MimeMessage message = mailSender.createMimeMessage();
//			MimeMessageHelper messageHelper = new MimeMessageHelper(message,
//					true, "UTF-8");
//			
//			messageHelper.setFrom(setfrom); // 보내는사람 생략하면 정상작동을 안함
//			messageHelper.setTo(tomail); // 받는사람 이메일
//			messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
//			messageHelper.setText(content); // 메일 내용
//
//			mailSender.send(message);
//		} catch (Exception e) {
//			System.out.println(e);
//		}
		
		researchService.sendMail(survey);

		return "redirect:/research/list";
	}
	
	
	@RequestMapping(value="/sendSMS", method=RequestMethod.POST)
	public String sendSMS(Survey survey, HttpServletRequest request) throws CoolsmsException {
		// 해당 설문조사를 참여한 모든 회원들에게 설문종료를 문자로 알린다
		Survey s = researchService.getSurvey(survey);
		List<Users> users = researchService.getUsersData(survey);

	    String api_key = ""; //위에서 받은 api key를 추가
        String api_secret = "";  //위에서 받은 api secret를 추가

        Coolsms coolsms = new Coolsms(api_key, api_secret);
        
        for(Users u:users) {
        	
        	HashMap<String, String> params = new HashMap<String, String>();
        	
        	params.put("to", u.getPhoneNo());
        	params.put("from", "01063615524");
        	params.put("type", "SMS");
        	params.put("text", "설문조사가 종료되었습니다.\r\n홈페이지에서 확인해주세요.\r\n\r\n제목 : "+s.getSurTitle());
        	params.put("app_version", "test app 1.2");
        	
        	JSONObject obj = (JSONObject) coolsms.send(params);
        	System.out.println(obj.toString());
        	
        }


        return "redirect:/research/list"; //문자 메시지 발송 성공했을때 number페이지로 이동함
	}
	
	@Scheduled(cron="0 0 14 * * *")
	public void TestScheduler() {
		
		logger.info("제대로 작동하나요?");
		
	}
	
	
	
}
