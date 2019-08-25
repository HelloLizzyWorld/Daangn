package com.elite.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.elite.vo.DaangnChatVO;
import com.elite.vo.DaangnFileVO;
import com.elite.vo.DaangnNoticeVO;
import com.elite.vo.DaangnJusoVO;
import com.elite.vo.DaangnProductVO;
import com.elite.vo.DaangnReviewVO;

@Repository
public class DaangnProductDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	private static String namespace = "mapper.product";

	// 상품등록
	public String getInsertResult(DaangnProductVO vo) {
		String result = "";
		int val = sqlSession.insert(namespace + ".insert_product", vo);
		if (val != 0)
			result = vo.getPid();
		return result;
	}

	// 상품 파일 등록
	public int getInsertFile(List<DaangnFileVO> insertFileList) {
		return sqlSession.insert(namespace + ".insert_product_file", insertFileList);
	}

	// 상품 목록 갯수
/*	public int getProductDbCount(String state, String keyword) {
		int count = 0;
		Map<String, Object> param = new HashMap<String, Object>();

		param.put("state", state);

		if (state.equals("1") || state.equals("2")) {
			// 전체 & 검색
			param.put("keyword", keyword);
			count = sqlSession.selectOne(namespace + ".productDbCount12", param);
		} else if (state.equals("3") || state.equals("4")) {
			// 카테고리 & 시도
			param.put("keyword", keyword);
			count = sqlSession.selectOne(namespace + ".productDbCount345", param);
		}else if(state.equals("5")){
			//시군구
			param.put("keyword1", keyword.substring(0,2));
			param.put("keyword2", keyword.substring(2));
			count = sqlSession.selectOne(namespace + ".productDbCount345", param);
		}
		System.out.println("product db count : " + count);
		return count;
	}*/
	
	// 상품 목록 갯수 state 1,2
	public int getProductDbCount12(String state, String keyword) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("state", state);
		param.put("keyword", keyword);
		return sqlSession.selectOne(namespace + ".productDbCount12", param);
	}

	// 상품 목록 갯수 state 3,4
	public int getProductDbCount34(String state, String keyword) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("state", state);
		param.put("keyword", keyword);
		return sqlSession.selectOne(namespace + ".productDbCount345", param);
	}

	// 상품 목록 갯수 state 5
	public int getProductDbCount5(String state, String keyword) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("state", state);
		param.put("keyword1", keyword.substring(0, 2));
		param.put("keyword2", keyword.substring(2));
		return sqlSession.selectOne(namespace + ".productDbCount345", param);
	}

	// 상품 목록 - 메인 페이지 state 0
	public ArrayList<DaangnProductVO> getProductList(String state) {
		List<DaangnProductVO> list = new ArrayList<DaangnProductVO>();
		Map param = new HashMap<String, String>();
		param.put("state", state);
		list = sqlSession.selectList(namespace + ".productList012", param);
		return (ArrayList<DaangnProductVO>) list;
	}
	
	// 상품 목록 - 페이징 처리
/*	public ArrayList<DaangnProductVO> getProductList(String state, String keyword, int startCount, int endCount) {
		List<DaangnProductVO> list = new ArrayList<DaangnProductVO>();

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("state", state);
		param.put("start", startCount);
		param.put("end", endCount);

		if (state.equals("1") || state.equals("2")) {
			// 전체 & 검색
			param.put("keyword", keyword);
			list = sqlSession.selectList(namespace + ".productList012", param);
		} else if (state.equals("3") || state.equals("4")) {
			// 카테고리 & 시도
			param.put("keyword", keyword);
			list = sqlSession.selectList(namespace + ".productList345", param);
		}else if(state.equals("5")){
			//시군구
			System.out.println("sido : " +keyword.substring(0,2));
			System.out.println("sigungu : " +keyword.substring(2));
			param.put("keyword1", keyword.substring(0,2));
			param.put("keyword2", keyword.substring(2));
			list = sqlSession.selectList(namespace + ".productList345", param);
		}

		return (ArrayList<DaangnProductVO>) list;
	}*/
	
	// 상품 목록 state 1,2
	public ArrayList<DaangnProductVO> getProductList12(String state, String keyword, int startCount, int endCount) {
		List<DaangnProductVO> list = new ArrayList<DaangnProductVO>();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("state", state);
		param.put("start", startCount);
		param.put("end", endCount);
		param.put("keyword", keyword);
		list = sqlSession.selectList(namespace + ".productList012", param);
		return (ArrayList<DaangnProductVO>) list;
	}

	// 상품 목록 state 3,4
	public ArrayList<DaangnProductVO> getProductList34(String state, String keyword, int startCount, int endCount) {
		List<DaangnProductVO> list = new ArrayList<DaangnProductVO>();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("state", state);
		param.put("start", startCount);
		param.put("end", endCount);
		param.put("keyword", keyword);
		list = sqlSession.selectList(namespace + ".productList345", param);
		return (ArrayList<DaangnProductVO>) list;
	}

	// 상품 목록 state 5
	public ArrayList<DaangnProductVO> getProductList5(String state, String keyword, int startCount, int endCount) {
		List<DaangnProductVO> list = new ArrayList<DaangnProductVO>();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("state", state);
		param.put("start", startCount);
		param.put("end", endCount);
		param.put("keyword1", keyword.substring(0, 2));
		param.put("keyword2", keyword.substring(2));
		list = sqlSession.selectList(namespace + ".productList345", param);
		return (ArrayList<DaangnProductVO>) list;
	}

	// 내가 판매한 상품 리스트 갯수
	public int getSalesDbCount(String id) {
		return sqlSession.selectOne(namespace + ".getSalesDbCount", id);
	}

	// 내가 판매한 상품 리스트
	public ArrayList<DaangnProductVO> getSalesList(int startCount, int endCount, String id) {
		List<DaangnProductVO> list = new ArrayList<DaangnProductVO>();

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		param.put("start", startCount);
		param.put("end", endCount);

		list = sqlSession.selectList(namespace + ".sales_list", param);
		return (ArrayList<DaangnProductVO>) list;
	}

	// 상품 파일 리스트
	public ArrayList<DaangnFileVO> getProductFileList(String pid) {
		List<DaangnFileVO> fileArray = new ArrayList<DaangnFileVO>();
		fileArray = sqlSession.selectList(namespace + ".product_file_list", pid);
		return (ArrayList<DaangnFileVO>) fileArray;
	}

	// 상품 상태 변경
	public int getPstateUpdate(String pid, String pstate) {
		boolean result = false;
		// String ps_name = "";

		/*
		 * if(pstate.equals("0")) ps_name="판매중"; if(pstate.equals("1"))
		 * ps_name="예약중"; if(pstate.equals("2")) ps_name="판매완료";
		 */

		Map param = new HashMap<String, String>();
		param.put("pid", pid);
		param.put("pstate", pstate);

		return sqlSession.update(namespace + ".pstate_update", param);
	}

	// 상품 날짜 변경
	public int getPdateUpdate(String pid) {
		return sqlSession.update(namespace + ".pdate_update", pid);
	}

	// 상품 수정 전 내용 불러오기
	public DaangnProductVO getProductContent(String pid) {
		return sqlSession.selectOne(namespace + ".product_content", pid);
	}

	// 상품 파일이 존재하는지 확인
	public int getnumberOfFiles(String pid) {
		return sqlSession.selectOne(namespace + ".number_of_files", pid);
	}

	// 상품 파일 삭제
	public void getProductFileDelete(String pid) {
		sqlSession.delete(namespace + ".product_file_delete", pid);
	}

	// 상품 수정
	public int getUpdateResult(DaangnProductVO vo) {
		return sqlSession.update(namespace + ".update_product", vo);
	}

	// 상품삭제
	public void getProductDelete(String pid) {
		sqlSession.delete(namespace + ".product_delete", pid);
	}

	// 시도 가져오기
	public ArrayList<DaangnJusoVO> getSido() {
		List<DaangnJusoVO> sido = new ArrayList<DaangnJusoVO>();
		sido = sqlSession.selectList(namespace + ".getSido");
		return (ArrayList<DaangnJusoVO>) sido;
	}

	// 시군구 가져오기
	public ArrayList<DaangnJusoVO> getSigungu(String sido) {
		List<DaangnJusoVO> sigungu = new ArrayList<DaangnJusoVO>();
		sigungu = sqlSession.selectList(namespace + ".getSigungu", sido);
		return (ArrayList<DaangnJusoVO>) sigungu;
	}

	// 동 가져오기
	public ArrayList<DaangnJusoVO> getHname(String sigungu) {
		List<DaangnJusoVO> hname = new ArrayList<DaangnJusoVO>();
		hname = sqlSession.selectList(namespace + ".getHname", sigungu);
		return (ArrayList<DaangnJusoVO>) hname;
	}

	// 판매 할 상품 채팅한 목록 가져오기
	public ArrayList<DaangnChatVO> getChatIDList(String pid, String rid) {
		List<DaangnChatVO> chatIDList = new ArrayList<DaangnChatVO>();

		Map param = new HashMap<String, String>();
		param.put("pid", pid);
		param.put("rid", rid);

		chatIDList = sqlSession.selectList(namespace + ".getChatIDList", param);
		return (ArrayList<DaangnChatVO>) chatIDList;
	}

	// 리뷰 등록
	public int getReviewResult(DaangnReviewVO vo) {
		int val = sqlSession.insert(namespace + ".getReviewResult", vo);
		return val;
	}

	// 구매 등록
	public int getOrderResult(DaangnReviewVO vo) {
		int val = sqlSession.insert(namespace + ".getOrderResult", vo);
		return val;
	}
}
