package com.elite.service;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.elite.dao.DaangnNoticeDAO;
import com.elite.vo.DaangnAnswerVO;
import com.elite.vo.DaangnNoticeVO;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService{
	
	int pageSize, reqPage, dbCount;
	
	@Autowired
	private DaangnNoticeDAO noticeDAO;
	
	/*@Override
	public ArrayList<DaangnNoticeVO> getTotalList(){
		return noticeDAO.noticeTotalList();
	}*/
	
	@Override
	public DaangnNoticeVO getContent(String nid){
		DaangnNoticeVO vo= null;
		
		if(!nid.equals(null) && nid !=""){
			vo=noticeDAO.noticeContent(nid);
			if(vo !=null){
				noticeDAO.getHitsResult(nid);
			}
		}
		return vo;
	}
	
	@Override
	public boolean getQuestion(DaangnAnswerVO vo, HttpServletRequest request)throws Exception{
		boolean result = false;
		String root_path = "", att_path = "", aimg = "", oaimg = "";
		UUID uuid = null;
		CommonsMultipartFile[] tmp = {vo.getAnswer_img1(),vo.getAnswer_img2(),vo.getAnswer_img3(),vo.getAnswer_img4()};
		String[] uuid_list = new String[tmp.length];
		
		root_path = request.getSession().getServletContext().getRealPath("/");
		att_path = "\\resources\\upload\\";
		
		for (int i = 0; i <= tmp.length - 1; i++) {
			if (null != tmp[i] && tmp[i].getOriginalFilename() != "") {
	        	uuid_list[i] = UUID.randomUUID().toString();
	
	            oaimg += tmp[i].getOriginalFilename() + ","; //오리지날
	            aimg += uuid_list[i] + "_" + tmp[i].getOriginalFilename() + ","; //변한거
	            
	          //마지막 ,(쉼표) 떼기
	  	      vo.setAfile(aimg.substring(0, aimg.length() - 1));
	  	      vo.setOafile(oaimg.substring(0, oaimg.length() - 1));
	        }
	      }
	      
	      
	      result = noticeDAO.noticeQuestion(vo);
	      
	      if(result){
	    	  for (int i = 0; i < tmp.length; i++) {
	              if (null != tmp[i] && tmp[i].getOriginalFilename() != "") {
	                 String fname = root_path + att_path + uuid_list[i] + "_" + tmp[i].getOriginalFilename();
	                 //System.out.println("fname2 = " + fname2);
	                 File file = new File(fname);
	                 tmp[i].transferTo(file);
	              }
	          } 
	      }
        

		return result;
	}
	
	@Override
	public ArrayList<DaangnAnswerVO> getQuestList(String id){
		return noticeDAO.noticeQuestList(id);
	}
	
	@Override
	public DaangnAnswerVO getCheckAnswer(String aid){
		return noticeDAO.noticeCheckAnswer(aid);
	}
	
	@Override
	public DaangnAnswerVO getAnswerContent(String aid){
		return noticeDAO.getAdminAnswerCheck(aid);
	}
	
	@Override
	public boolean getReturnQuestion(DaangnAnswerVO vo){
		boolean result = false;
		int val = noticeDAO.getAdminQuestionReturn(vo);
		
		if(val != 0) result = true;
		
		return result; 
	}
	
	
	//공지사항 이전글 다음글
	@Override
	public ArrayList<DaangnNoticeVO> noticeTotalListPre(String nid) {
		
		return noticeDAO.noticeTotalListPre(nid);
	}
	
	//관리자 공지사항 insert
	@Override
	public boolean getInsertResult(DaangnNoticeVO vo,HttpServletRequest request)throws Exception {
		boolean result = false;
		String root_path="",att_path="",bfname="";
		if(vo.getFile1().getOriginalFilename() != null && vo.getFile1().getOriginalFilename() !=""){
			root_path = request.getSession().getServletContext().getRealPath("/");
			att_path="\\resources\\upload\\";
			
			UUID uuid = UUID.randomUUID();
			bfname=uuid+"_"+vo.getFile1().getOriginalFilename();
			
			vo.setNfile(bfname);
			vo.setOnfile(vo.getFile1().getOriginalFilename());
			
		}
		
		result= noticeDAO.getInsertResult(vo);
		if(result && vo.getFile1().getOriginalFilename()!=""){
			File file = new File(root_path+att_path+bfname);
			vo.getFile1().transferTo(file);
		}
		return result;
	}
	
	@Override
	public int getPageSize() {
		return pageSize;
	}

	@Override
	public int getReqPage() {
		return reqPage;
	}

	@Override
	public int getDbCount() {
		return dbCount;
	}
	
	@Override
	public ArrayList<DaangnAnswerVO> getAnswerList(String rpage){
		
		//페이징 처리 - startCount, endCount 구하기
		int startCount = 0;
		int endCount = 0;
		int pageCount = 1;	//전체 페이지 수
		pageSize = 5;	//한페이지당 게시물 수
		reqPage = 1;	//요청페이지	
		dbCount = noticeDAO.getDbCount();	//DB에서 가져온 전체 행수
		
		//총 페이지 수 계산
		if(dbCount % pageSize == 0){
			pageCount = dbCount/pageSize;
		}else{
			pageCount = dbCount/pageSize+1;
		}
		
		//요청 페이지 계산
		if(rpage != null){
			reqPage = Integer.parseInt(rpage);
			startCount = (reqPage-1) * pageSize+1;
			endCount = reqPage *pageSize;
		}else{
			reqPage = 1;
			startCount = 1;
			endCount = pageSize;
		}
		
		ArrayList<DaangnAnswerVO> list = noticeDAO.getAdminAnswerList(startCount, endCount);
		
		return list;
	}

	@Override
	public DaangnNoticeVO getAdminContent(String nid) {
		
		return noticeDAO.getAdminContent(nid);
	}

	@Override
	public boolean getAdminNoticeUpdate(DaangnNoticeVO vo, HttpServletRequest request)throws Exception {
	
		boolean result = false;
		String root_path="",att_path="",bfname="";
		if(vo.getFile1().getOriginalFilename() != null && vo.getFile1().getOriginalFilename() !=""){
			root_path = request.getSession().getServletContext().getRealPath("/");
			att_path="\\resources\\upload\\";
			
			UUID uuid = UUID.randomUUID();
			bfname=uuid+"_"+vo.getFile1().getOriginalFilename();
			
			vo.setNfile(bfname);
			vo.setOnfile(vo.getFile1().getOriginalFilename());
			
		}
		
		result= noticeDAO.getAdminNoticeUpdate(vo);
		if(result && vo.getFile1().getOriginalFilename()!=""){
			File file = new File(root_path+att_path+bfname);
			vo.getFile1().transferTo(file);
		}
		return result;

	
	}

	@Override
	public boolean getCheckDelete(String[] stArray) {
		
		return noticeDAO.getCheckDeleteResult(stArray);
	}

	@Override
	public boolean getAdminNoticeDelete(String nid) {
	
		return noticeDAO.getAdminNoticeDelete(nid);
	}


	/*@Override
	public ArrayList<DaangnNoticeVO> noticeSearch(String keyword){
	
		return noticeDAO.noticeSearch(keyword);
	}*/

	}

	




