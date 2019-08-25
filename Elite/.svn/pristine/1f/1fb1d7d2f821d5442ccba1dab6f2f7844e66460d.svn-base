package com.elite.service;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.elite.vo.DaangnMemberVO;
import com.elite.vo.DaangnProductVO;
import com.elite.vo.DaangnReviewVO;

public interface MypageService {
	
	//컨텐츠에서 좋아요 insert, delete 선택
	int getChoice(String pid,String uid);
	//찜목록 데이터 전송
	boolean getLike(String pid, String uid);
	
	//하트 지우기
	boolean delLike(String pid,String uid);
	
	//찜목록 뿌리기
	/*ArrayList<DaangnProductVO> getCartBox(String uid);*/
	
	//찜목록 삭제
	boolean getDelete(String cid);

	//box 불러오기
	DaangnMemberVO getBox(String uid,DaangnMemberVO vo);
	//프로필 사진
	boolean getUpdate(DaangnMemberVO vo,HttpServletRequest request) throws Exception;
	
	//구매 목록
	ArrayList<DaangnProductVO> getPurchaseList(String id);
	DaangnProductVO getReview(String pid);
	int getReviewInsert(String state, String chkArray, String detail, String uid, String pid);
	//거래후기가 완료되면 order에 있는 pid를 삭제!
	int getOrderDelete(String pid);
	//거래후기가 완료되면 order_reivew에 등록
	int getOrderInsert(String pid, String id);
	//거래후기완료목록
	ArrayList<DaangnProductVO> getPurchaseListFinish(String id);
	//내가작성한거래후기
	DaangnReviewVO getReviewFinish(String id, String pid);
	

	int getPass_check(String id, String pass);
	DaangnMemberVO getInfo(String id);
	int getInfoUpdate(DaangnMemberVO vo);
	int getInfoCancel(String id);
}
