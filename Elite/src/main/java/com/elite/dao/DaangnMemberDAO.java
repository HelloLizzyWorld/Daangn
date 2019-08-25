package com.elite.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.elite.vo.DaangnChatVO;
import com.elite.vo.DaangnMemberVO;
import com.elite.vo.SessionVO;

@Repository
public class DaangnMemberDAO {

@Autowired
private SqlSessionTemplate sqlSession;	

private static String namespace="mapper.member";

	//회원가입
	public boolean getInsertResult(DaangnMemberVO vo){
		boolean result=false;
			int val=sqlSession.insert(namespace+".join",vo);
			if(val !=0)result=true;
		return result;
	}
	
	//로그인
	public SessionVO getLoginResult(DaangnMemberVO vo){
		return sqlSession.selectOne(namespace+".login", vo);
	}
	
	//로그인 성공시 리셋
	public int getLoginSuccess(String id){
		return sqlSession.update(namespace+".login_success", id);
	}
	
	//로그인 실패시 카운트 증가
	public int getLoginFailCount(String id){
		return sqlSession.update(namespace+".login_fail_count",id);
	}
	
	//로그인 5번 실패시 계정 잠금
	public DaangnMemberVO getLoginAccountLock(String id){
		
		
		return sqlSession.selectOne(namespace+".login_account_lock",id);
	}
	
	public int getUpdateAccountLock(String id){
		return sqlSession.update(namespace+".login_update_accountlock",id);
	}
	//블랙리스트 접근금지 
	/*public SessionVO getBlackLogin(DaangnMemberVO vo){
		return sqlSession.selectOne(namespace+".blackLogin",vo);
	}*/
	
	//아이디 중복쳌
	public int getResultIdCheck(String id){
		return sqlSession.selectOne(namespace+".id_check",id);
	}
	
	//이메일 중복쳌
	public int getResultEmailCheck(String email){
		int val = sqlSession.selectOne(namespace+".email_check",email);
		
		return val;
	}
	
	//닉네임 중복쳌
	public int getResultNickCheck(String nick){
		int val = sqlSession.selectOne(namespace+".nick_check",nick);
		return val;
	}
	
	
	//관리자 회원관리
	public ArrayList<DaangnMemberVO> getTotalList(int startCount, int endCount){
		List<DaangnMemberVO>list= new ArrayList<DaangnMemberVO>();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", startCount);
		param.put("end", endCount);
		
		list= sqlSession.selectList(namespace+".listAll", param);
		return (ArrayList<DaangnMemberVO>)list;
	}
	//회원개인정보
	public DaangnMemberVO getMemberContent(String id){
		DaangnMemberVO vo = new DaangnMemberVO();
			vo= sqlSession.selectOne(namespace+".memberContent",id);
			
		return vo;
	}
	//블랙리스트 경고
	public int getMemberWarn(String id){
		int val=sqlSession.update(namespace+".memberWarn",id);
		return val;
	}
	
	//member_dbcount
	public int getDbcount(){
		return sqlSession.selectOne(namespace+".member_dbCount");
	}

	
	// 채팅한 사람 목록의 데이터 가져오기
	public ArrayList<DaangnMemberVO> getChatList(ArrayList<DaangnChatVO> chatIDList) {
		List<DaangnMemberVO> chatList = new ArrayList<DaangnMemberVO>();
		chatList = sqlSession.selectList(namespace+".getChatList", chatIDList);
		return (ArrayList<DaangnMemberVO>)chatList;
	}
	
	public SessionVO getLoginlock(String id){
		return sqlSession.selectOne(namespace+".getloginlock",id);
	}
	
}
