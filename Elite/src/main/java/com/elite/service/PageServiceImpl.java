package com.elite.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elite.dao.DaangnMemberDAO;
import com.elite.dao.DaangnMypageDAO;
import com.elite.dao.DaangnNoticeDAO;
import com.elite.dao.DaangnProductDAO;
import com.elite.vo.DaangnFileVO;
import com.elite.vo.DaangnProductVO;

@Service("pageService")
public class PageServiceImpl implements PageService {

	int pageSize, reqPage, dbCount;

	@Autowired
	private DaangnNoticeDAO noticeDAO;

	@Autowired
	private DaangnMemberDAO memberDAO;

	@Autowired
	private DaangnProductDAO productDAO;

	@Autowired
	private DaangnMypageDAO mypageDAO;

	@Override
	public int getPageSize() {
		return pageSize;
	}

	@Override
	public int getPageSize(int number) {
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
	public int getDbCount(String countName) {
		if (countName.equals("answer")) {
			dbCount = noticeDAO.getDbCount();
		} else if (countName.equals("notice")) {
			dbCount = noticeDAO.getNoticeCount();
		} else if (countName.equals("member")) {
			dbCount = memberDAO.getDbcount();
		}
		return dbCount;
	}

	// �������� ����¡
	@Override
	public int getSearchDbCount(String keyword) {
		return dbCount = noticeDAO.searchCount(keyword);
	}

	// id�� �ʿ��� ����¡ ó�� ������ ����
	@Override
	public int getDbCount(String countName, String id) {
		if (countName.equals("answer")) { // ���� �亯
			dbCount = noticeDAO.getDbCount(id);
		} else if (countName.equals("cart")) { // ���� ���
			dbCount = mypageDAO.getDbCount(id);
		} else if (countName.equals("sales")) { // �Ǹ� ���
			dbCount = productDAO.getSalesDbCount(id);
		}
		return dbCount;
	}

	// ��ǰ ��� ����
	@Override
	public int getProductDbCount(String countName, String state, String keyword) {
		if (countName.equals("product")) {
			// dbCount = productDAO.getProductDbCount(state, keyword);
			if (state.equals("1") || state.equals("2")) {
				// ��ü & �˻�
				dbCount = productDAO.getProductDbCount12(state, keyword);
			} else if (state.equals("3") || state.equals("4")) {
				// ī�װ� & �õ�
				dbCount = productDAO.getProductDbCount34(state, keyword);
			} else if (state.equals("5")) {
				// �ñ���
				dbCount = productDAO.getProductDbCount5(state, keyword);
			}
		}
		return dbCount;
	}

	// �������� �˻�
	@Override
	public HashMap<String, Object> getSearchList(String rpage, String keyword) {

		HashMap<String, Object> hm = new HashMap<String, Object>();
		int startCount = 0;
		int endCount = 0;
		int pageCount = 1; // ��ü ������ ��

		pageSize = 5; // ���������� �Խù� ��
		reqPage = 1; // ��û������
		dbCount = getSearchDbCount(keyword); // DB���� ������ ��ü ���

		// �� ������ �� ���
		if (dbCount % pageSize == 0) {
			pageCount = dbCount / pageSize;
		} else {
			pageCount = dbCount / pageSize + 1;
		}

		// ��û ������ ���
		if (rpage != null) {
			reqPage = Integer.parseInt(rpage);
			startCount = (reqPage - 1) * pageSize + 1;
			endCount = reqPage * pageSize;
		} else {
			reqPage = 1;
			startCount = 1;
			endCount = pageSize;
		}

		hm.put("list", noticeDAO.noticeSearch(keyword, startCount, endCount));
		return hm;

	}

	@Override
	public HashMap<String, Object> getPageList(String rpage, String countName) {
		HashMap<String, Object> hm = new HashMap<String, Object>();

		int startCount = 0;
		int endCount = 0;
		int pageCount = 1; // ��ü ������ ��
		pageSize = 5; // ���������� �Խù� ��
		reqPage = 1; // ��û������
		dbCount = getDbCount(countName); // DB���� ������ ��ü ���

		// �� ������ �� ���
		if (dbCount % pageSize == 0) {
			pageCount = dbCount / pageSize;
		} else {
			pageCount = dbCount / pageSize + 1;
		}

		// ��û ������ ���
		if (rpage != null) {
			reqPage = Integer.parseInt(rpage);
			startCount = (reqPage - 1) * pageSize + 1;
			endCount = reqPage * pageSize;
		} else {
			reqPage = 1;
			startCount = 1;
			endCount = pageSize;
		}

		if (countName.equals("answer")) {
			hm.put("list", noticeDAO.getAdminAnswerList(startCount, endCount));
		} else if (countName.equals("notice")) {
			hm.put("list", noticeDAO.noticeTotalList(startCount, endCount));
		} else if (countName.equals("member")) {
			hm.put("list", memberDAO.getTotalList(startCount, endCount));
		}

		return hm;
	}

	// ���� �亯, ���� ��ǰ, ���� �Ǹ��� ��ǰ ����¡ ó��
	@Override
	public HashMap<String, Object> getPageList(String rpage, String countName, String id) {
		HashMap<String, Object> hm = new HashMap<String, Object>();

		int startCount = 0;
		int endCount = 0;
		int pageCount = 1; // ��ü ������ ��

		pageSize = 5; // ���������� �Խù� ��
		reqPage = 1; // ��û������
		dbCount = getDbCount(countName, id); // DB���� ������ ��ü ���

		// �� ������ �� ���
		if (dbCount % pageSize == 0) {
			pageCount = dbCount / pageSize;
		} else {
			pageCount = dbCount / pageSize + 1;
		}

		// ��û ������ ���
		if (rpage != null) {
			reqPage = Integer.parseInt(rpage);
			startCount = (reqPage - 1) * pageSize + 1;
			endCount = reqPage * pageSize;
		} else {
			reqPage = 1;
			startCount = 1;
			endCount = pageSize;
		}

		if (countName.equals("answer")) { // ���� �亯
			hm.put("list", noticeDAO.getAdminAnswerList2(startCount, endCount, id));
		} else if (countName.equals("cart")) { // ���� ���
			hm.put("list", mypageDAO.productTotalList(startCount, endCount, id));
		} else if (countName.equals("sales")) { // �Ǹ� ���
			ArrayList<DaangnProductVO> list = new ArrayList<DaangnProductVO>();
			list = productDAO.getSalesList(startCount, endCount, id);

			for (DaangnProductVO vo : list) {

				if (!vo.getPtype().equals("�Ǹ�")) { // �ǸŰ� �ƴҰ�� ptype�� ����
					vo.setPprice(vo.getPtype());
				}

				String pid = vo.getPid();
				ArrayList<DaangnFileVO> fileArray = productDAO.getProductFileList(pid);
				vo.setFileArray(fileArray);
			}
			hm.put("list", list);
		}
		return hm;
	}

	// ��ǰ ��� - ����¡ ó��
	@Override
	public HashMap<String, Object> getProductePageList(String rpage, String countName, String state, String keyword) {
		HashMap<String, Object> hm = new HashMap<String, Object>();

		int startCount = 0;
		int endCount = 0;
		int pageCount = 1; // ��ü ������ ��
		pageSize = 8; // ���������� �Խù� ��
		reqPage = 1; // ��û������
		dbCount = getProductDbCount(countName, state, keyword); // DB���� ������ ��ü
																// ��� ��

		// �� ������ �� ���
		if (dbCount % pageSize == 0) {
			pageCount = dbCount / pageSize;
		} else {
			pageCount = dbCount / pageSize + 1;
		}

		// ��û ������ ���
		if (rpage != null) {
			reqPage = Integer.parseInt(rpage);
			startCount = (reqPage - 1) * pageSize + 1;
			endCount = reqPage * pageSize;
		} else {
			reqPage = 1;
			startCount = 1;
			endCount = pageSize;
		}
        // DAO�� mapper�� ���� �� �����͸� �������� ���� business logic�� Service�ܿ��� ����
		if (countName.equals("product")) {
			ArrayList<DaangnProductVO> list = new ArrayList<DaangnProductVO>();

			if (state.equals("1") || state.equals("2")) {
				// ��ü & �˻�
				list = productDAO.getProductList12(state, keyword, startCount, endCount);
			} else if (state.equals("3") || state.equals("4")) {
				// ī�װ� & �õ�
				list = productDAO.getProductList34(state, keyword, startCount, endCount);
			} else if (state.equals("5")) {
				// �ñ���
				list = productDAO.getProductList5(state, keyword, startCount, endCount);
			}

			for (DaangnProductVO vo : list) {
				String priceType = vo.getPtype();
                // �Ǹ� ������ ���Ǹš� �̿��� �� �ش� �Ǹ� ������ �ʵ�� pType�� �־� ��Ͽ� ��� �� ����
				if (!priceType.equals("�Ǹ�"))
					vo.setPprice(priceType);
                // pid�� ������ �ش��ϴ� ��ǰ�� file�� ������(fileArray)
				String pid = vo.getPid();
				ArrayList<DaangnFileVO> fileArray = productDAO.getProductFileList(pid);
				vo.setFileArray(fileArray);
			}
			hm.put("list", list);
		}
		return hm;
	}

}
