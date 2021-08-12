package research.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.nurigo.java_sdk.exceptions.CoolsmsException;
import research.dao.ResearchDao;
import research.dto.Survey;
import research.dto.SurveyContent;
import research.dto.SurveyResult;
import research.dto.Users;
import research.util.Paging;

@Service
public class ResearchServiceImpl implements ResearchService{
	private static final Logger logger = LoggerFactory.getLogger(ResearchServiceImpl.class);
	
	@Autowired
	ResearchDao researchDao;
	
	@Override
	public Paging getPaging(Paging p) {
		
		int curPage = p.getCurPage();
		int totalCount = researchDao.selectCntAll(p);
		
		Paging paging = new Paging(totalCount, curPage);
		
		return paging;
	}



	@Override
	public List<Survey> getList(Paging paging) {
		return researchDao.selectAll(paging);
	}

	@Override
	@Transactional
	public void write(Survey survey, HttpServletRequest request) {
		// 설문조사 정보 삽입
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("userId");
		String userName = (String)session.getAttribute("userName");
		survey.setRegName(userId);
		survey.setWriter(userName);
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

	@Override
	public void updateSurveyResult(HttpServletRequest request) {
		
		// 회원이 수정한 설문조사 결과를 DB에 반영한다
		int surCnt = Integer.parseInt(request.getParameter("surCnt"));
		int surSeq = Integer.parseInt(request.getParameter("surSeq"));
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
			
			logger.info("얻어온 각 결과값 확인 : {}", sr);
			
			//수정값 DB 반영
			researchDao.updateSurveyResult(sr);
			
		}
				
	}

	@Override
	@Transactional
	public void deleteSurveyResult(SurveyResult surveyResult) {
		
		// 해당 설문조사 결과 삭제
		researchDao.deleteSurveyResult(surveyResult);
		
		// 참여수 1 감소
		researchDao.decreaseHit(surveyResult);
	}



	@Override
	public int userChk(SurveyResult sr) {
		return researchDao.userChk(sr);
	}



	@Override
	@Transactional
	public void sendMail(Survey survey) {
		
//		logger.info("얻어온 설문조사 번호 조회 : {}", survey);
		
		final String bodyEncoding = "UTF-8"; //콘텐츠 인코딩
		String subject = "참여하신 설문조사가 종료되었습니다.";
		String fromEmail = "ehdcks112@gmail.com";
		String fromUsername = "설문조사 관리자";
		
		// 해당 설문조사의 모든 정보를 얻어온다 (제목용)
		Survey s = researchDao.selectSurvey(survey);
		
		// 해당 설문조사의 결과 정보를 얻어온다
		List<HashMap<String, Object>> sc = researchDao.selectTotalResult(s);
		
		// 해당 설문조사 번호에 응답한 회원들의 이메일 리스트를 얻어온다
		List<Users> sr = researchDao.getUsersData(s);
		
		// 메일을 보내기 위한 계정 정보
		final String username = "ehdcks112@gmail.com";         
		final String password = "genirccufyshmclh";
		String surTitle = (String) s.getSurTitle();
		
		
		StringBuffer sb = new StringBuffer();
		sb.append("<h3>알려드립니다</h3>\n");
		sb.append("<h4>참여하신 설문조사가 아래와 같이 종료되었습니다.</h4>");
		sb.append("<h4>설문조사 제목 : "+surTitle+"</h4>\r\n");
		sb.append("<div>&nbsp;</div>\r\n");
		
		for(HashMap<String, Object> h:sc) {
			
			String surqTitle = (String) h.get("SURQ_TITLE");
			String surqTitle1 = (String) h.get("SURQ_TITLE1");
			String surqTitle2 = (String) h.get("SURQ_TITLE2");
			String surqTitle3 = (String) h.get("SURQ_TITLE3");
			String surqTitle4 = (String) h.get("SURQ_TITLE4");
			String surqTitle5 = (String) h.get("SURQ_TITLE5");
			
			String totalS = String.valueOf(h.get("TOTALCNT"));
			int total = Integer.parseInt(totalS);
			String num1S = String.valueOf(h.get("NUM1"));
			int num1 = Integer.parseInt(num1S);
			String num2S = String.valueOf(h.get("NUM2"));
			int num2 = Integer.parseInt(num2S);
			String num3S = String.valueOf(h.get("NUM3"));
			int num3 = Integer.parseInt(num3S);
			String num4S = String.valueOf(h.get("NUM4"));
			int num4 = Integer.parseInt(num4S);
			String num5S = String.valueOf(h.get("NUM5"));
			int num5 = Integer.parseInt(num5S);
			
			sb.append("	 	<div style=\"width : 1000px\">문제.&nbsp;"+surqTitle+"</div>\r\n"
					+ "	 		\r\n");
			sb.append("	 		<div style=\"width : 500px\">\r\n"
					+ "		 		<div style=\"width: 500px; overflow: hidden; text-overflow: ellipsis; white-space: pre;\">①&nbsp;"+surqTitle1+"</div>\r\n");
					if(num1 != 0) {
						sb.append("			 		<div style=\"width: "+(( (num1+0.00)/(total+0.00) )*100)+"%; background-color: red; line-height: 15px;\">&nbsp;</div>"
								+ "			 		"+(( (num1+0.00)/(total+0.00) )*100)+"%\r\n");
						
					} else {
						sb.append("			 		<div style=\"width: 5%; background-color: red; line-height: 15px;\">&nbsp;</div>\r\n"
								+ "			 		0%\r\n");
						
					}
			sb.append("		 	</div>\r\n");
				
			sb.append("	 		<div style=\"width : 500px\">\r\n"
					+ "		 		<div style=\"width: 500px; overflow: hidden; text-overflow: ellipsis; white-space: pre;\">②&nbsp;"+surqTitle2+"</div>\r\n");
					if(num2 != 0) {
						sb.append("			 		<div style=\"width: "+(( (num2+0.00)/(total+0.00) )*100)+"%; background-color: blue; line-height: 15px;\">&nbsp;</div>"
								+ "			 		"+(( (num2+0.00)/(total+0.00) )*100)+"%\r\n");
						
					} else {
						sb.append("			 		<div style=\"width: 5%; background-color: blue; line-height: 15px;\">&nbsp;</div>\r\n"
								+ "			 		0%\r\n");
						
					}
			sb.append("		 	</div>\r\n");

					if(surqTitle3 != null) {
						
						sb.append("	 		<div style=\"width : 500px\">\r\n"
								+ "		 		<div style=\"width: 500px; overflow: hidden; text-overflow: ellipsis; white-space: pre;\">③&nbsp;"+surqTitle3+"</div>\r\n");
								if(num3 != 0) {
									sb.append("			 		<div style=\"width: "+(( (num3+0.00)/(total+0.00) )*100)+"%; background-color: orange; line-height: 15px;\">&nbsp;</div>"
											+ "			 		"+(( (num3+0.00)/(total+0.00) )*100)+"%\r\n");
									
								} else {
									sb.append("			 		<div style=\"width: 5%; background-color: orange; line-height: 15px;\">&nbsp;</div>\r\n"
											+ "			 		0%\r\n");
									
								}
						sb.append("		 	</div>\r\n");
						
					}
					
					if(surqTitle4 != null) {
						
						sb.append("	 		<div style=\"width : 500px\">\r\n"
								+ "		 		<div style=\"width: 500px; overflow: hidden; text-overflow: ellipsis; white-space: pre;\">④&nbsp;"+surqTitle4+"</div>\r\n");
						if(num4 != 0) {
							sb.append("			 		<div style=\"width: "+(( (num4+0.00)/(total+0.00) )*100)+"%; background-color: purple; line-height: 15px;\">&nbsp;</div>"
									+ "			 		"+(( (num4+0.00)/(total+0.00) )*100)+"%\r\n");
							
						} else {
							sb.append("			 		<div style=\"width: 5%; background-color: purple; line-height: 15px;\">&nbsp;</div>\r\n"
									+ "			 		0%\r\n");
							
						}
						sb.append("		 	</div>\r\n");
						
					}
					
					if(surqTitle5 != null) {
						
						sb.append("	 		<div style=\"width : 500px\">\r\n"
								+ "		 		<div style=\"width: 500px; overflow: hidden; text-overflow: ellipsis; white-space: pre;\">⑤&nbsp;"+surqTitle5+"</div>\r\n");
						if(num5 != 0) {
							sb.append("			 		<div style=\"width: "+(( (num5+0.00)/(total+0.00) )*100)+"%; background-color: gray; line-height: 15px;\">&nbsp;</div>"
									+ "			 		"+(( (num5+0.00)/(total+0.00) )*100)+"%\r\n");
							
						} else {
							sb.append("			 		<div style=\"width: 5%; background-color: gray; line-height: 15px;\">&nbsp;</div>\r\n"
									+ "			 		0%\r\n");
							
						}
						sb.append("		 	</div>\r\n");
						
					}
					
			sb.append("		</div>\r\n"
					+ "		<hr><br>\r\n");
		}
		
		// 메일에 출력할 텍스트
		String html = sb.toString();
		
		// 메일 옵션 설정
		Properties props = new Properties();    
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.auth", "true");
		
		props.put("mail.smtp.quitwait", "false");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		
		
		for(Users u:sr) {
			
			String toEmail = u.getEmail(); // 콤마(,)로 여러개 나열
			
			try {
				// 메일 서버  인증 계정 설정
				Authenticator auth = new Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				};
				
				// 메일 세션 생성
				Session session = Session.getInstance(props, auth);
				
				// 메일 송/수신 옵션 설정
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(fromEmail, fromUsername));
				message.setRecipients(RecipientType.TO, InternetAddress.parse(toEmail, false));
				message.setSubject(subject);
				message.setSentDate(new Date());
				
				// 메일 콘텐츠 설정
				Multipart mParts = new MimeMultipart();
				MimeBodyPart mTextPart = new MimeBodyPart();
//				MimeBodyPart mFilePart = null;
				
				// 메일 콘텐츠 - 내용
				mTextPart.setText(html, bodyEncoding, "html");
				mParts.addBodyPart(mTextPart);
				
				// 메일 콘텐츠 설정
				message.setContent(mParts);
				
				// MIME 타입 설정
				MailcapCommandMap MailcapCmdMap = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
				MailcapCmdMap.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
				MailcapCmdMap.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
				MailcapCmdMap.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
				MailcapCmdMap.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
				MailcapCmdMap.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
				CommandMap.setDefaultCommandMap(MailcapCmdMap);
				
				// 메일 발송
				Transport.send( message );
				
			} catch ( Exception e ) {
				e.printStackTrace();
			}
			
		}
		
	}

	
	@Override
	public List<Users> getUsersData(Survey survey) {
		return researchDao.getUsersData(survey);
	}


	
	
	
	
}
