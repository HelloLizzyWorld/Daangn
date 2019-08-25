package com.elite.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.elite.vo.DaangnMemberVO;
import com.elite.vo.DaangnProductVO;
import com.elite.vo.DaangnReviewVO;

@Repository
public class DaangnMypageDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;
	private static String namespace = "mapper.mypage";

	
	
	public ArrayList<DaangnProductVO> productTotalList(int startCount, int endCount,String id){
		List<DaangnProductVO> list = new ArrayList<DaangnProductVO>();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", startCount);
		param.put("end", endCount);
		param.put("id", id);
		
		list = sqlSession.selectList(namespace+".cart_list", param);
		
		return (ArrayList<DaangnProductVO>)list;
	}
	
	public int getDbCount(String id){
		return sqlSession.selectOne(namespace+".mypage_dbCount", id);
	}
	
	
	/*public ArrayList<DaangnNoticeVO> noticeTotalList(int startCount, int endCount){
		List<DaangnNoticeVO> list = new ArrayList<DaangnNoticeVO>();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", startCount);
		param.put("end", endCount);
		
		list = sqlSession.selectList(namespace+".notice_list", param);
		
		return (ArrayList<DaangnNoticeVO>)list;
	}*/
	
	
	
	public int getChoice(String pid, String uid){
		int result=0;
		Map<String,String> param = new HashMap<String, String>();

		param.put("pid", pid);
		param.put("uid", uid);
		return sqlSession.selectOne(namespace + ".getChoice", param);
	}
	
	public boolean getLike(String pid, String uid) {
		boolean result = false;
		Map param = new HashMap<String, String>();

		param.put("pid", pid);
		param.put("uid", uid);

		if(pid!="" && pid !=null){
		int val = sqlSession.insert(namespace + ".like", param);
		if (val != 0) {
			result = true;
		}
		}
		return result;
	}
	
	public boolean delLike(String pid, String uid) {
		boolean result = false;
		Map param = new HashMap<String, String>();

		param.put("pid", pid);
		param.put("uid", uid);

		if(pid!="" && pid !=null){
		int val = sqlSession.insert(namespace + ".del_like", param);
		if (val != 0) {
			result = true;
		}
		}
		return result;
	}
	
	
	/*public ArrayList<DaangnProductVO> getCartBox(String uid) {
		 DaangnCartVO vo = new DaangnCartVO(); 
		 return vo; 
		 DaangnCartVO vo = new DaangnCartVO(); 
		List<DaangnProductVO> list = new ArrayList<DaangnProductVO>();
		list = sqlSession.selectList(namespace + ".getCart", uid);
		 return vo; 
		return (ArrayList<DaangnProductVO>)list;
	}*/
	

	public boolean getDelete(String cid) {
		boolean result = false;
		int var = sqlSession.delete(namespace + ".mypage_delete", cid);
		if (var != 0) {
			result = true;
		}
		return result;
	}
	
	public DaangnMemberVO getBox(String uid,DaangnMemberVO vo){
		Map param = new HashMap<String, DaangnMemberVO>();
		
		param.put("id", uid);
		param.put("pass", vo);
		return sqlSession.selectOne(namespace+".getBox",param);
	}
	
	public boolean getUpdate(DaangnMemberVO vo) {
		boolean result = false;
		int val = sqlSession.update(namespace + ".updatePro", vo);
		if (val != 0)
			result = true;
		return result;
	}
	
public int getPass_check(String id, String pass){
		
		Map param = new HashMap<String, String>();
		
		param.put("id", id);
		param.put("pass", pass);
		
		return sqlSession.selectOne(namespace+".pass_check", param);
	}
	
	public DaangnMemberVO getInfo(String id){
		
		DaangnMemberVO vo = sqlSession.selectOne(namespace+".info", id);
		
		return vo;
	}
	
	public int getInfoUpdate(DaangnMemberVO vo){

		
		int result = sqlSession.update(namespace+".infoupdate",vo);
		
		return result; 
	}
	
	public int getInfoCancel(String id){
		
		
		return sqlSession.delete(namespace+".infocancel",id);
	}
	
public ArrayList<DaangnProductVO> getPurchaseList(String id){
		
		List<DaangnProductVO> list = new ArrayList<DaangnProductVO>(); 
		
		list = sqlSession.selectList(namespace+".purchase_list",id);

		return (ArrayList<DaangnProductVO>)list;
	}
	
	public DaangnProductVO getReview(String pid){
		
		return sqlSession.selectOne(namespace+".review",pid);
	}
	
	public int getReviewInsert(String state, String chkArray, String detail, String uid, String pid){
		
		Map param = new HashMap<String, String>();

		param.put("id", uid);
		param.put("rstate", state);
		param.put("rcomment", chkArray);
		param.put("rdetail", detail);
		param.put("pid", pid);
		
		return sqlSession.insert(namespace+".review_insert", param);
	}
	
	public int getOrderDelete(String pid){
		//System.out.println("DAO PID="+pid);
		return sqlSession.delete(namespace+".order_delete", pid);
	}
	
	public int getOrderInsert(String pid, String id){
		
		Map param = new HashMap<String, String>();
		
		param.put("pid", pid);
		param.put("id", id);
		
		return sqlSession.insert(namespace+".order_insert",param);
	}
	
	public ArrayList<DaangnProductVO> getPurchaseListFinish(String id){
			List<DaangnProductVO> list = new ArrayList<DaangnProductVO>(); 
		
		list = sqlSession.selectList(namespace+".purchase_list_finish",id);
			
		return (ArrayList<DaangnProductVO>)list;

	}
	
	public DaangnReviewVO getReviewFinish(String id, String pid){
		
		Map param = new HashMap<String, String>();
		
		param.put("id", id);
		param.put("pid", pid);
		
		return sqlSession.selectOne(namespace+".review_finish",param);
	}
	

}
