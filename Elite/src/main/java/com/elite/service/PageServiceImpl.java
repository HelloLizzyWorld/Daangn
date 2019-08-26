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

	// 공지사항 페이징
	@Override
	public int getSearchDbCount(String keyword) {
		return dbCount = noticeDAO.searchCount(keyword);
	}

	// id가 필요한 페이징 처리 데이터 갯수
	@Override
	public int getDbCount(String countName, String id) {
		if (countName.equals("answer")) { // 문의 답변
			dbCount = noticeDAO.getDbCount(id);
		} else if (countName.equals("cart")) { // 관심 목록
			dbCount = mypageDAO.getDbCount(id);
		} else if (countName.equals("sales")) { // 판매 목록
			dbCount = productDAO.getSalesDbCount(id);
		}
		return dbCount;
	}

	// 상품 목록 갯수
	@Override
	public int getProductDbCount(String countName, String state, String keyword) {
		if (countName.equals("product")) {
			// dbCount = productDAO.getProductDbCount(state, keyword);
			if (state.equals("1") || state.equals("2")) {
				// 전체 & 검색
				dbCount = productDAO.getProductDbCount12(state, keyword);
			} else if (state.equals("3") || state.equals("4")) {
				// 카테고리 & 시도
				dbCount = productDAO.getProductDbCount34(state, keyword);
			} else if (state.equals("5")) {
				// 시군구
				dbCount = productDAO.getProductDbCount5(state, keyword);
			}
		}
		return dbCount;
	}

	// 공지사항 검색
	@Override
	public HashMap<String, Object> getSearchList(String rpage, String keyword) {

		HashMap<String, Object> hm = new HashMap<String, Object>();
		int startCount = 0;
		int endCount = 0;
		int pageCount = 1; // 전체 페이지 수

		pageSize = 5; // 한페이지당 게시물 수
		reqPage = 1; // 요청페이지
		dbCount = getSearchDbCount(keyword); // DB에서 가져온 전체 행수

		// 총 페이지 수 계산
		if (dbCount % pageSize == 0) {
			pageCount = dbCount / pageSize;
		} else {
			pageCount = dbCount / pageSize + 1;
		}

		// 요청 페이지 계산
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
		int pageCount = 1; // 전체 페이지 수
		pageSize = 5; // 한페이지당 게시물 수
		reqPage = 1; // 요청페이지
		dbCount = getDbCount(countName); // DB에서 가져온 전체 행수

		// 총 페이지 수 계산
		if (dbCount % pageSize == 0) {
			pageCount = dbCount / pageSize;
		} else {
			pageCount = dbCount / pageSize + 1;
		}

		// 요청 페이지 계산
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

	// 문의 답변, 관심 상품, 내가 판매한 상품 페이징 처리
	@Override
	public HashMap<String, Object> getPageList(String rpage, String countName, String id) {
		HashMap<String, Object> hm = new HashMap<String, Object>();

		int startCount = 0;
		int endCount = 0;
		int pageCount = 1; // 전체 페이지 수

		pageSize = 5; // 한페이지당 게시물 수
		reqPage = 1; // 요청페이지
		dbCount = getDbCount(countName, id); // DB에서 가져온 전체 행수

		// 총 페이지 수 계산
		if (dbCount % pageSize == 0) {
			pageCount = dbCount / pageSize;
		} else {
			pageCount = dbCount / pageSize + 1;
		}

		// 요청 페이지 계산
		if (rpage != null) {
			reqPage = Integer.parseInt(rpage);
			startCount = (reqPage - 1) * pageSize + 1;
			endCount = reqPage * pageSize;
		} else {
			reqPage = 1;
			startCount = 1;
			endCount = pageSize;
		}

		if (countName.equals("answer")) { // 문의 답변
			hm.put("list", noticeDAO.getAdminAnswerList2(startCount, endCount, id));
		} else if (countName.equals("cart")) { // 관심 목록
			hm.put("list", mypageDAO.productTotalList(startCount, endCount, id));
		} else if (countName.equals("sales")) { // 판매 목록
			ArrayList<DaangnProductVO> list = new ArrayList<DaangnProductVO>();
			list = productDAO.getSalesList(startCount, endCount, id);

			for (DaangnProductVO vo : list) {

				if (!vo.getPtype().equals("판매")) { // 판매가 아닐경우 ptype이 가격
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

	// 물품 목록 - 페이징 처리
	@Override
	public HashMap<String, Object> getProductePageList(String rpage, String countName, String state, String keyword) {
		HashMap<String, Object> hm = new HashMap<String, Object>();

		int startCount = 0;
		int endCount = 0;
		int pageCount = 1; // 전체 페이지 수
		pageSize = 8; // 한페이지당 게시물 수
		reqPage = 1; // 요청페이지
		dbCount = getProductDbCount(countName, state, keyword); // DB에서 가져온 전체
																// 목록 수

		// 총 페이지 수 계산
		if (dbCount % pageSize == 0) {
			pageCount = dbCount / pageSize;
		} else {
			pageCount = dbCount / pageSize + 1;
		}

		// 요청 페이지 계산
		if (rpage != null) {
			reqPage = Integer.parseInt(rpage);
			startCount = (reqPage - 1) * pageSize + 1;
			endCount = reqPage * pageSize;
		} else {
			reqPage = 1;
			startCount = 1;
			endCount = pageSize;
		}
        // DAO는 mapper에 연동 될 데이터만 가져오기 위해 business logic은 Service단에서 수행
		if (countName.equals("product")) {
			ArrayList<DaangnProductVO> list = new ArrayList<DaangnProductVO>();

			if (state.equals("1") || state.equals("2")) {
				// 전체 & 검색
				list = productDAO.getProductList12(state, keyword, startCount, endCount);
			} else if (state.equals("3") || state.equals("4")) {
				// 카테고리 & 시도
				list = productDAO.getProductList34(state, keyword, startCount, endCount);
			} else if (state.equals("5")) {
				// 시군구
				list = productDAO.getProductList5(state, keyword, startCount, endCount);
			}

			for (DaangnProductVO vo : list) {
				String priceType = vo.getPtype();
                // 판매 종류가 ‘판매’ 이외일 때 해당 판매 종류를 필드명 pType에 넣어 목록에 띄워 줄 예정
				if (!priceType.equals("판매"))
					vo.setPprice(priceType);
                // pid를 가져와 해당하는 물품의 file을 가져옴(fileArray)
				String pid = vo.getPid();
				ArrayList<DaangnFileVO> fileArray = productDAO.getProductFileList(pid);
				vo.setFileArray(fileArray);
			}
			hm.put("list", list);
		}
		return hm;
	}

}
