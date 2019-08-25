package com.elite.daangn;

import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.elite.service.BoardService;
import com.elite.vo.DaangnAnswerVO;
import com.elite.vo.DaangnChatVO;
import com.elite.vo.DaangnProductVO;
import com.elite.vo.SessionVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
public class BoardController {
	@Resource(name="boardService")
	private BoardService boardService;

	@RequestMapping(value="/board.carrot", method=RequestMethod.GET)
	public String board(){
		return "/board/board";
	}
	
	@RequestMapping(value="/board_chat_proc.carrot", method=RequestMethod.GET, produces = "application/text; charset=utf8")
	@ResponseBody
	public  String board_chat_proc(String pid, HttpSession session, String id, String sid, String rid){
		Gson gson = new GsonBuilder().create();
		String json = "";
		ArrayList<DaangnChatVO> list = boardService.getChatList(pid,id,sid,rid);
		json = gson.toJson(list);
		return json;
	}
	
	@RequestMapping(value="/board_content.carrot", method=RequestMethod.GET, produces = "application/text; charset=utf8")
	public ModelAndView board_content(String pid,HttpSession session){
		ModelAndView mv = new ModelAndView();
		SessionVO svo = (SessionVO) session.getAttribute("svo");
		String uid = svo.getId();
		
		boolean result = boardService.getBoardContent2(pid,uid);
		DaangnProductVO vo = boardService.getBoardContent(pid);
		ArrayList<DaangnProductVO> list = boardService.getProductList();
		
		mv.addObject("result",result);
		mv.addObject("svo", svo);
		mv.addObject("pid", pid);
		mv.addObject("list", list);
		mv.addObject("vo", vo);
		mv.setViewName("/board/board_content");

		return mv;
	}
	
	@RequestMapping(value="/insert_chat.carrot", method=RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public String insert_chat(String message, String pid, String id, String sid, String rid){
		boolean result = boardService.getInsertChat(message, pid, id, sid, rid);
		if(result){
			return "true";
		}else{
			return "false";
		}
	}
	
	@RequestMapping(value="/board_chat2.carrot", method=RequestMethod.GET)
	public String board_chat2(){
		return "/board/board_chat2";
	}
	
	@RequestMapping(value="/chat_member_list.carrot", method=RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public String chat_member_list(String pid){
		Gson gson = new GsonBuilder().create();
		String json = "";
		ArrayList<DaangnChatVO> list = boardService.getChatMemberList(pid);
		json = gson.toJson(list);
		
		/*System.out.println(json);*/
		
		return json;
	}
	
}
