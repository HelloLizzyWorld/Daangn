package com.elite.service;

import java.util.HashMap;

public interface PageService {

	int getPageSize();
	int getPageSize(int number);
	int getReqPage();
	int getDbCount();
	int getDbCount(String countName);
	int getDbCount(String countName, String id);
	int getProductDbCount(String countName, String state, String keyword);
	int getSearchDbCount(String keyword);
	HashMap<String,Object> getSearchList(String rpage,String keyword);
	HashMap<String, Object> getPageList(String rpage, String countName);
	HashMap<String, Object> getPageList(String rpage, String countName, String id);
	HashMap<String, Object> getProductePageList(String rpage, String countName, String state, String keyword);
	
}
