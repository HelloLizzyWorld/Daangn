package com.elite.service;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elite.dao.DaangnMypageDAO;
import com.elite.vo.DaangnMemberVO;
import com.elite.vo.DaangnProductVO;
import com.elite.vo.DaangnReviewVO;

@Service("mypageService")
public class MypageServiceImpl implements MypageService {

	@Autowired
	private DaangnMypageDAO mypageDAO;

	
	@Override
	public int getChoice(String pid, String uid) {
		return mypageDAO.getChoice(pid, uid);
	}
	
	@Override
	public boolean getLike(String pid, String uid) {
		/* System.out.println("111"); */
		return mypageDAO.getLike(pid, uid);
	}
	
	@Override
	public boolean delLike(String pid, String uid) {
		/* System.out.println("111"); */
		return mypageDAO.delLike(pid, uid);
	}
	
	/*@Override
	public ArrayList<DaangnProductVO> getCartBox(String uid) {
		return mypageDAO.getCartBox(uid);
	}
*/
	@Override
	public boolean getDelete(String cid) {
		boolean result = false;

		result = mypageDAO.getDelete(cid);
		return result;
	}
	@Override
	public DaangnMemberVO getBox(String uid,DaangnMemberVO vo){
		
		return mypageDAO.getBox(uid,vo);
	}
	@Override
	public boolean getUpdate(DaangnMemberVO vo, HttpServletRequest request) throws Exception {
		
		boolean result = false;
		String root_path = "", att_path = "";
		UUID uuid = null;

		if (vo.getFile1().getOriginalFilename() != "") {
			root_path = request.getSession().getServletContext().getRealPath("/");

			att_path = "\\resources\\upload\\";

			uuid = UUID.randomUUID();

			vo.setOmfile(vo.getFile1().getOriginalFilename());
			vo.setMfile(uuid + "_" + vo.getFile1().getOriginalFilename());

			result = mypageDAO.getUpdate(vo);

			
				String fname = root_path + att_path + uuid + "_" + vo.getFile1().getOriginalFilename();
				File file = new File(fname);
				vo.getFile1().transferTo(file);
		
		}
		return result;
	}
	
	@Override
	public int getPass_check(String id, String pass){
		return mypageDAO.getPass_check(id, pass);
	}
	
	@Override
	public DaangnMemberVO getInfo(String id){
		return mypageDAO.getInfo(id);
	}
	
	@Override
	public int getInfoUpdate(DaangnMemberVO vo){
		
		return mypageDAO.getInfoUpdate(vo);
	}
	
	@Override
	public int getInfoCancel(String id){
		return mypageDAO.getInfoCancel(id);
	}
	
	//구매목록
	@Override
	public ArrayList<DaangnProductVO> getPurchaseList(String id){
		
		return mypageDAO.getPurchaseList(id);
	}
	
	@Override
	public DaangnProductVO getReview(String pid){
		return mypageDAO.getReview(pid);
	}
	
	@Override
	public int getReviewInsert(String state, String chkArray, String detail, String uid, String pid){
		return mypageDAO.getReviewInsert(state, chkArray, detail, uid, pid);
	}
	
	@Override
	public int getOrderDelete(String pid){
		return mypageDAO.getOrderDelete(pid);
	}
	
	@Override
	public int getOrderInsert(String pid, String id){
		return mypageDAO.getOrderInsert(pid, id);
	}
	
	@Override
	public ArrayList<DaangnProductVO> getPurchaseListFinish(String id){
		return mypageDAO.getPurchaseListFinish(id);
	}
	
	//내가남긴거래후기
	@Override
	public DaangnReviewVO getReviewFinish(String id, String pid){
		return mypageDAO.getReviewFinish(id,pid);
	}
	
}
