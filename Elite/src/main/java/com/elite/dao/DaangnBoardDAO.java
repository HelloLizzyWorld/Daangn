package com.elite.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.elite.vo.DaangnChatVO;
import com.elite.vo.DaangnCartVO;
import com.elite.vo.DaangnProductVO;

@Repository
public class DaangnBoardDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	private static String namespace = "mapper.board";
	
	public DaangnProductVO getProductContent(String pid){
		return sqlSession.selectOne(namespace+".board_content", pid);
	}
	
	public boolean getBoardContent2(String pid,String uid){
		boolean result = false;
		Map<String, String> param = new HashMap<String, String>();
		param.put("pid", pid);
		param.put("id", uid);
		int val = sqlSession.selectOne(namespace+".board_content2", param);
		if(val != 0){
			result = true;
		}	
		return result;
	}
	
	
	public void getHits(String pid){
		sqlSession.update(namespace+".hits_update", pid);
	}
	
	public ArrayList<DaangnProductVO> getProductList(){
		List<DaangnProductVO> list = new ArrayList<DaangnProductVO>();
		
		list = sqlSession.selectList(namespace+".product_list");
		
		return (ArrayList<DaangnProductVO>) list;
	}

	public ArrayList<DaangnChatVO> getChatList(String pid, String id, String sid, String rid){
		List<DaangnChatVO> list = new ArrayList<DaangnChatVO>();
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("pid", pid);
		param.put("id", id);
		param.put("sid", sid);
		param.put("rid", rid);
		list = sqlSession.selectList(namespace+".chat_list", param);
		return (ArrayList<DaangnChatVO>) list;
	}
	
	public boolean getInsertChat(String message, String pid, String id, String sid, String rid){
		boolean result = false;
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("message", message);
		param.put("pid", pid);
		param.put("id", id);
		param.put("sid", sid);
		param.put("rid", rid);
		
		int val = sqlSession.insert(namespace+".insert_chat",param);
		
		if(val != 0) result=true;
		
		return result;
		
	}
	
	public ArrayList<DaangnChatVO> getChatMemberList(String pid){
		List<DaangnChatVO> list = new ArrayList<DaangnChatVO>();
		
		list = sqlSession.selectList(namespace+".chat_member_list", pid);
		
		return (ArrayList<DaangnChatVO>) list;
	}
	
}