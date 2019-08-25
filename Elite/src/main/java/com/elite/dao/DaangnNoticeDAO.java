package com.elite.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.elite.vo.DaangnAnswerVO;
import com.elite.vo.DaangnNoticeVO;

@Repository
public class DaangnNoticeDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	private static String namespace = "mapper.notice";
	
	//notice search
	
	public ArrayList<DaangnNoticeVO>noticeSearch(String keyword, int startCount, int endCount ){
		List<DaangnNoticeVO>list = new ArrayList<DaangnNoticeVO>();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", startCount);
		param.put("end", endCount);
		param.put("keyword", keyword);
		
		list = sqlSession.selectList(namespace+".notice_search",param);
		return(ArrayList<DaangnNoticeVO>)list;
	}
	//공지사항 페이징
	public int searchCount(String keyword){
		return sqlSession.selectOne(namespace+".searchCount",keyword);
	}
	

	
	//admin_notice_list_prev,next
	public ArrayList<DaangnNoticeVO> noticeTotalListPre(String nid){
		List<DaangnNoticeVO> list = new ArrayList<DaangnNoticeVO>();
		list = sqlSession.selectList(namespace+".prev",nid);
		return (ArrayList<DaangnNoticeVO>)list;
	}
	
	public ArrayList<DaangnNoticeVO> noticeTotalList(int startCount, int endCount){
		List<DaangnNoticeVO> list = new ArrayList<DaangnNoticeVO>();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", startCount);
		param.put("end", endCount);
		
		list = sqlSession.selectList(namespace+".notice_list", param);
		
		return (ArrayList<DaangnNoticeVO>)list;
	}
	
	//notice_content
	public DaangnNoticeVO noticeContent(String nid){
		DaangnNoticeVO vo = sqlSession.selectOne(namespace+".notice_content", nid);
		return vo;
	}
	public DaangnNoticeVO getAdminContent(String nid){
		DaangnNoticeVO vo = sqlSession.selectOne(namespace+".notice_admin_content",nid);
		return vo;
	}
	
	//notice_question
	public boolean noticeQuestion(DaangnAnswerVO vo){
		boolean result = false;
		int val = sqlSession.insert(namespace+".notice_question", vo);
		if(val != 0){
			result = true;
		}
		
		return result;
	}
	
	//notice_question list
	public ArrayList<DaangnAnswerVO> noticeQuestList(String id){
		List<DaangnAnswerVO> list = new ArrayList<DaangnAnswerVO>();
		list = sqlSession.selectList(namespace+".notice_question_list", id);
		
		return (ArrayList<DaangnAnswerVO>)list;
	}
	
	//notice_check_answer
	public DaangnAnswerVO noticeCheckAnswer(String aid){
		DaangnAnswerVO avo = new DaangnAnswerVO();

		avo = sqlSession.selectOne(namespace+".notice_check_answer", aid);
		
		return avo;
	}
	
	//admin_answer_list
	public ArrayList<DaangnAnswerVO>getAdminAnswerList(int startCount, int endCount){
		List<DaangnAnswerVO> list = new ArrayList<DaangnAnswerVO>();
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", startCount);
		param.put("end", endCount);
		
		list = sqlSession.selectList(namespace+".admin_answer_list", param);
		
		return (ArrayList<DaangnAnswerVO>)list;
	}
	
	public ArrayList<DaangnAnswerVO>getAdminAnswerList2(int startCount, int endCount, String id){
		List<DaangnAnswerVO> list = new ArrayList<DaangnAnswerVO>();
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", startCount);
		param.put("end", endCount);
		param.put("id", id);
		
		list = sqlSession.selectList(namespace+".admin_answer_list2", param);
	
		return (ArrayList<DaangnAnswerVO>)list;
	}
	
	//admin_answer_content
	public DaangnAnswerVO getAdminAnswerCheck(String aid){
		DaangnAnswerVO vo = new DaangnAnswerVO();
		
		vo = sqlSession.selectOne(namespace+".admin_answer_check", aid);
		
		return vo;
	}
	
	//admin_question_return
	public int getAdminQuestionReturn(DaangnAnswerVO vo){
		return sqlSession.update(namespace+".admin_question_return", vo);
	}
	//admin_notice_insert
	public boolean getInsertResult(DaangnNoticeVO vo){
		boolean result =false;
		int val=sqlSession.insert(namespace+".admin_notice_insert",vo);
		if(val !=0)result=true;
		return result;
	}
	//admin_notice_update
	
	public boolean getAdminNoticeUpdate(DaangnNoticeVO vo){
		boolean result =false;
		int val= sqlSession.update(namespace+".admin_notice_update",vo);
		if(val !=0 )result = true;
		return result;
	}
	//공지사항 삭제
	
	public boolean getAdminNoticeDelete(String nid){
		boolean result =false;
		int val= sqlSession.delete(namespace+".admin_notice_delete",nid);
		if(val != 0) result= true;
		return result;
	}
	
	//공지사항 체크박스 삭제 
	
		public boolean getCheckDeleteResult(String[] stArray){
			boolean result=false;
			int val=sqlSession.delete(namespace+".check_delete",stArray);
			if(val !=0)result=true;
			return result;
		}

	//answer_dbCount
	public int getDbCount(){
		return sqlSession.selectOne(namespace+".answer_dbCount");
	}
	public int getDbCount(String id){
		return sqlSession.selectOne(namespace+".answer_dbCount2", id);
	}
	//notice_dbCount
	public int getNoticeCount(){
		return sqlSession.selectOne(namespace+".notice_dbCount");
	}
	//공지사항 조회수
	public void getHitsResult(String nid){
		sqlSession.update(namespace+".hits",nid);
	}
	
	
}	





