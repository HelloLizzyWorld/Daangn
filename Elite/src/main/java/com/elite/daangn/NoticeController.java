package com.elite.daangn;

import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.elite.service.MemberService;
import com.elite.service.NoticeService;
import com.elite.service.PageService;
import com.elite.vo.DaangnAnswerVO;
import com.elite.vo.DaangnNoticeVO;
import com.elite.vo.SessionVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
public class NoticeController {
	
	@Resource(name="noticeService")
	private NoticeService noticeService;
	@Resource(name="memberService")
	private MemberService memberService;
	@Resource(name="pageService")
	private PageService pageService;

	@RequestMapping(value="/notice.carrot", method=RequestMethod.GET)
	public ModelAndView notice(String rpage){
		ModelAndView mv = new ModelAndView();
		String countName = "notice";
		
		ArrayList<DaangnNoticeVO> list = (ArrayList<DaangnNoticeVO>)pageService.getPageList(rpage, "notice").get("list");
		
		mv.addObject("list", list);
		mv.addObject("dbCount",pageService.getDbCount());
		mv.addObject("pageSize",pageService.getPageSize());
		mv.addObject("rpage",pageService.getReqPage());
		mv.setViewName("/notice/notice");
		
		return mv;
	}
	
	@RequestMapping(value="/notice_content.carrot", method=RequestMethod.GET)
	public ModelAndView notice_content(String nid, String rno, String rpage){
		ModelAndView mv = new ModelAndView();
		ArrayList<DaangnNoticeVO>list =(ArrayList<DaangnNoticeVO>)noticeService.noticeTotalListPre(nid);
		mv.addObject("vo", noticeService.getContent(nid));
		mv.addObject("list",list);
		mv.addObject("nid", nid);
		mv.addObject("rno", rno);
		mv.addObject("rpage", rpage);
		
		mv.setViewName("/notice/notice_content");
		return mv;
	}
	
	/*@RequestMapping(value="/notice_question.carrot", method=RequestMethod.GET)
	public ModelAndView notice_question(HttpSession session,String result){
		ModelAndView mv = new ModelAndView();
		SessionVO svo = (SessionVO)session.getAttribute("svo");
		
		mv.addObject("list", noticeService.getQuestList(svo.getId()));
		mv.addObject("id", svo.getId());
		mv.addObject("name", svo.getName());
		mv.addObject("result", result);
		mv.setViewName("/notice/notice_question");
		return mv;
	}*/
	
	
	@RequestMapping(value="/notice_check_answer.carrot", method=RequestMethod.GET, produces = "application/text; charset=utf8")
	@ResponseBody
	public String notice_check_answer(String aid){
		
		Gson gson = new GsonBuilder().create();
		String json = "";
		DaangnAnswerVO vo = noticeService.getCheckAnswer(aid);
		json = gson.toJson(vo);
		/*System.out.println(json.toString());*/
		
		return json;
	}
	
	@RequestMapping(value="/notice_question_part1.carrot", method=RequestMethod.GET)
	public String notice_question_part1(){
		return "/notice/notice_question_part1";
	}
	
	@RequestMapping(value="/notice_question_part2.carrot", method=RequestMethod.GET)
	public ModelAndView notice_question_part2(HttpSession session){
		ModelAndView mv = new ModelAndView();
		SessionVO svo = (SessionVO)session.getAttribute("svo");
		
		mv.addObject("list", noticeService.getQuestList(svo.getId()));
		mv.addObject("id", svo.getId());
		mv.addObject("name", svo.getName());
		mv.setViewName("/notice/notice_question_part2");
		return mv;
	}
	
	@RequestMapping(value="/notice_question_part2_proc.carrot", method=RequestMethod.POST)
	public String notice_question_part2_proc(DaangnAnswerVO vo, HttpServletRequest request) throws Exception{
		String page="";
		boolean result = false;
		
		result = noticeService.getQuestion(vo, request);
		
		if(result){
			page = "redirect:/notice_question_part2.carrot";
		}

		return page;
	}
	
	@RequestMapping(value="/notice_question_part3.carrot", method=RequestMethod.GET)
	public ModelAndView notice_question_part3(HttpSession session, String rpage){
		ModelAndView mv = new ModelAndView();
		SessionVO svo = (SessionVO)session.getAttribute("svo");
		
		ArrayList<DaangnAnswerVO> list = (ArrayList<DaangnAnswerVO>)pageService.getPageList(rpage, "answer", svo.getId()).get("list");
		
		mv.addObject("list", list);
		mv.addObject("dbCount",pageService.getDbCount());
		mv.addObject("pageSize",pageService.getPageSize());
		mv.addObject("rpage",pageService.getReqPage());
		mv.setViewName("/notice/notice_question_part3");
		return mv;
	}
	
	@RequestMapping(value="/notice_search.carrot")
	public ModelAndView notice_search(String rpage, String keyword){
		ModelAndView mv = new ModelAndView();
		
		int dbCount = pageService.getSearchDbCount(keyword);
		
		if(dbCount == 0){
				mv.setViewName("redirect:/notice.carrot");
		}else{
		
		ArrayList<DaangnNoticeVO> list =new ArrayList<DaangnNoticeVO>();
		list =(ArrayList<DaangnNoticeVO>)pageService.getSearchList(rpage, keyword).get("list");
			
		mv.addObject("list",list);
		mv.addObject("dbCount",pageService.getSearchDbCount(keyword));
		mv.addObject("pageSize",pageService.getPageSize());
		mv.addObject("rpage",pageService.getReqPage());
		mv.setViewName("/notice/notice");
		
		}
		return mv;
	}
	
}
