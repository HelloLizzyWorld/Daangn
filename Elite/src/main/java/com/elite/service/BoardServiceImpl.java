package com.elite.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elite.dao.DaangnBoardDAO;
import com.elite.dao.DaangnProductDAO;
import com.elite.vo.DaangnChatVO;
import com.elite.vo.DaangnFileVO;
import com.elite.vo.DaangnProductVO;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

	@Autowired
	private DaangnBoardDAO boardDAO;
	
	@Autowired
	private DaangnProductDAO productDAO;
	
	@Override
	public DaangnProductVO getBoardContent(String pid){
		DaangnProductVO vo = new DaangnProductVO();
		
		if(pid != null && pid != ""){
			vo = boardDAO.getProductContent(pid);
			
			if(vo != null){
				boardDAO.getHits(pid);
				ArrayList<DaangnFileVO> fileArray = productDAO.getProductFileList(pid);
				vo.setFileArray(fileArray);
			}
		}
		
		
		return vo;
	}
	
	@Override
	public boolean getBoardContent2(String pid,String uid){
		boolean result = boardDAO.getBoardContent2(pid,uid);
		return result;
	}
	
	@Override
	public ArrayList<DaangnProductVO> getProductList(){
		ArrayList<DaangnProductVO> list = new ArrayList<DaangnProductVO>();
		list = boardDAO.getProductList();
		
		for(DaangnProductVO vo : list){
			String pid = vo.getPid();
			ArrayList<DaangnFileVO> fileArray = productDAO.getProductFileList(pid);
			vo.setFileArray(fileArray);
		}
		return list;
	}
	
	@Override
	public ArrayList<DaangnChatVO> getChatList(String pid, String id, String sid, String rid){
		return boardDAO.getChatList(pid, id, sid, rid);
	}
	
	@Override
	public boolean getInsertChat(String message, String pid, String id, String sid, String rid){
		boolean result = boardDAO.getInsertChat(message, pid, id, sid, rid);
		return result;
	}
	
	@Override
	public ArrayList<DaangnChatVO> getChatMemberList(String pid){
		
		return boardDAO.getChatMemberList(pid);
	}
	
}
