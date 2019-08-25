package com.elite.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.elite.vo.DaangnNoticeVO;
import com.elite.vo.DaangnAnswerVO;

public interface NoticeService {
	//admin_notice_list / notice_list
	/*ArrayList<DaangnNoticeVO> getTotalList();*/
	// 이전글 다음글
	ArrayList<DaangnNoticeVO> noticeTotalListPre(String nid);
	
	DaangnNoticeVO getContent(String nid);
	
	boolean getQuestion(DaangnAnswerVO vo, HttpServletRequest request) throws Exception;
	
	ArrayList<DaangnAnswerVO> getQuestList(String id);
	
	DaangnAnswerVO getCheckAnswer(String aid);
	
	DaangnAnswerVO getAnswerContent(String aid);
	
	boolean getReturnQuestion(DaangnAnswerVO vo);
	//admin notice insert
	boolean getInsertResult(DaangnNoticeVO vo,HttpServletRequest request) throws Exception;
	//admin notice list
/*	ArrayList<DaangnNoticeVO> getAdminNoticeList();*/
	//admin_notice_content 
	DaangnNoticeVO getAdminContent(String nid);
	//admin_notice_update
	boolean getAdminNoticeUpdate(DaangnNoticeVO vo, HttpServletRequest request) throws Exception;
	
	boolean getAdminNoticeDelete(String nid);

	boolean getCheckDelete(String[] stArray);
	
	ArrayList<DaangnAnswerVO> getAnswerList(String rpage);
	int getPageSize();
	int getReqPage();
	int getDbCount();
	
	/*ArrayList<DaangnNoticeVO> noticeSearch (String keyword);*/
}
