package com.elite.service;


import java.util.ArrayList;

import com.elite.vo.DaangnCartVO;
import com.elite.vo.DaangnChatVO;
import com.elite.vo.DaangnProductVO;

public interface BoardService {

	DaangnProductVO getBoardContent(String pid);
	boolean getBoardContent2(String pid,String uid);
	ArrayList<DaangnProductVO> getProductList();
	ArrayList<DaangnChatVO> getChatList(String pid, String id, String sid, String rid);
	boolean getInsertChat(String message, String pid, String id, String sid, String rid);
	ArrayList<DaangnChatVO> getChatMemberList(String pid);
}
