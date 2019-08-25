package com.elite.service;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.elite.vo.DaangnMemberVO;
import com.elite.vo.DaangnProductVO;
import com.elite.vo.DaangnReviewVO;

public interface MypageService {
	
	//���������� ���ƿ� insert, delete ����
	int getChoice(String pid,String uid);
	//���� ������ ����
	boolean getLike(String pid, String uid);
	
	//��Ʈ �����
	boolean delLike(String pid,String uid);
	
	//���� �Ѹ���
	/*ArrayList<DaangnProductVO> getCartBox(String uid);*/
	
	//���� ����
	boolean getDelete(String cid);

	//box �ҷ�����
	DaangnMemberVO getBox(String uid,DaangnMemberVO vo);
	//������ ����
	boolean getUpdate(DaangnMemberVO vo,HttpServletRequest request) throws Exception;
	
	//���� ���
	ArrayList<DaangnProductVO> getPurchaseList(String id);
	DaangnProductVO getReview(String pid);
	int getReviewInsert(String state, String chkArray, String detail, String uid, String pid);
	//�ŷ��ıⰡ �Ϸ�Ǹ� order�� �ִ� pid�� ����!
	int getOrderDelete(String pid);
	//�ŷ��ıⰡ �Ϸ�Ǹ� order_reivew�� ���
	int getOrderInsert(String pid, String id);
	//�ŷ��ı�Ϸ���
	ArrayList<DaangnProductVO> getPurchaseListFinish(String id);
	//�����ۼ��Ѱŷ��ı�
	DaangnReviewVO getReviewFinish(String id, String pid);
	

	int getPass_check(String id, String pass);
	DaangnMemberVO getInfo(String id);
	int getInfoUpdate(DaangnMemberVO vo);
	int getInfoCancel(String id);
}
