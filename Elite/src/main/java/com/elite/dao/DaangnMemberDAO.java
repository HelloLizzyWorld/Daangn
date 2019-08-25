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

	//ȸ������
	public boolean getInsertResult(DaangnMemberVO vo){
		boolean result=false;
			int val=sqlSession.insert(namespace+".join",vo);
			if(val !=0)result=true;
		return result;
	}
	
	//�α���
	public SessionVO getLoginResult(DaangnMemberVO vo){
		return sqlSession.selectOne(namespace+".login", vo);
	}
	
	//�α��� ������ ����
	public int getLoginSuccess(String id){
		return sqlSession.update(namespace+".login_success", id);
	}
	
	//�α��� ���н� ī��Ʈ ����
	public int getLoginFailCount(String id){
		return sqlSession.update(namespace+".login_fail_count",id);
	}
	
	//�α��� 5�� ���н� ���� ���
	public DaangnMemberVO getLoginAccountLock(String id){
		
		
		return sqlSession.selectOne(namespace+".login_account_lock",id);
	}
	
	public int getUpdateAccountLock(String id){
		return sqlSession.update(namespace+".login_update_accountlock",id);
	}
	//������Ʈ ���ٱ��� 
	/*public SessionVO getBlackLogin(DaangnMemberVO vo){
		return sqlSession.selectOne(namespace+".blackLogin",vo);
	}*/
	
	//���̵� �ߺ��n
	public int getResultIdCheck(String id){
		return sqlSession.selectOne(namespace+".id_check",id);
	}
	
	//�̸��� �ߺ��n
	public int getResultEmailCheck(String email){
		int val = sqlSession.selectOne(namespace+".email_check",email);
		
		return val;
	}
	
	//�г��� �ߺ��n
	public int getResultNickCheck(String nick){
		int val = sqlSession.selectOne(namespace+".nick_check",nick);
		return val;
	}
	
	
	//������ ȸ������
	public ArrayList<DaangnMemberVO> getTotalList(int startCount, int endCount){
		List<DaangnMemberVO>list= new ArrayList<DaangnMemberVO>();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", startCount);
		param.put("end", endCount);
		
		list= sqlSession.selectList(namespace+".listAll", param);
		return (ArrayList<DaangnMemberVO>)list;
	}
	//ȸ����������
	public DaangnMemberVO getMemberContent(String id){
		DaangnMemberVO vo = new DaangnMemberVO();
			vo= sqlSession.selectOne(namespace+".memberContent",id);
			
		return vo;
	}
	//������Ʈ ���
	public int getMemberWarn(String id){
		int val=sqlSession.update(namespace+".memberWarn",id);
		return val;
	}
	
	//member_dbcount
	public int getDbcount(){
		return sqlSession.selectOne(namespace+".member_dbCount");
	}

	
	// ä���� ��� ����� ������ ��������
	public ArrayList<DaangnMemberVO> getChatList(ArrayList<DaangnChatVO> chatIDList) {
		List<DaangnMemberVO> chatList = new ArrayList<DaangnMemberVO>();
		chatList = sqlSession.selectList(namespace+".getChatList", chatIDList);
		return (ArrayList<DaangnMemberVO>)chatList;
	}
	
	public SessionVO getLoginlock(String id){
		return sqlSession.selectOne(namespace+".getloginlock",id);
	}
	
}
