package com.elite.service;

import java.util.ArrayList;

import com.elite.vo.DaangnMemberVO;
import com.elite.vo.SessionVO;

public interface MemberService {

	//회원가입
	boolean getInsertResult(DaangnMemberVO vo);
	
	//로그인
	SessionVO getLoginResult(DaangnMemberVO vo);
	
	//로그인 성공시 리셋
	int getLoginSuccess(String id);
	
	//로그인 실패시 카운트 증가
	int getLoginFailCount(String id);
	
	//로그인 5번 계정 잠금
	DaangnMemberVO getLoginAccountLock(String id);
	
	int getUpdateAccountLock(String id);
	
	//블랙리스트 로그인
	//SessionVO getBlackLogin(DaangnMemberVO vo);
	
	//아이디중복쳌
	int getResultIdCheck(String id);
	
	//이메일중복쳌
	int getResultEmailCheck(String email);
	
	//닉임 중복첵
	int getResultNickCheck(String nick);
	//회원리스트
	/*ArrayList<DaangnMemberVO> getTotalList();*/
	
	//회원개인정보
	DaangnMemberVO getMemberContent(String id);
	
	//블랙리스트
	int getMemberWarn(String id);
	
	//로그인 1분동안 막기!
	SessionVO getLoginlock(String id);


}
