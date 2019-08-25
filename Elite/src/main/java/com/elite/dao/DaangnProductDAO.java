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

	// ��ǰ���
	public String getInsertResult(DaangnProductVO vo) {
		String result = "";
		int val = sqlSession.insert(namespace + ".insert_product", vo);
		if (val != 0)
			result = vo.getPid();
		return result;
	}

	// ��ǰ ���� ���
	public int getInsertFile(List<DaangnFileVO> insertFileList) {
		return sqlSession.insert(namespace + ".insert_product_file", insertFileList);
	}

	// ��ǰ ��� ����
/*	public int getProductDbCount(String state, String keyword) {
		int count = 0;
		Map<String, Object> param = new HashMap<String, Object>();

		param.put("state", state);

		if (state.equals("1") || state.equals("2")) {
			// ��ü & �˻�
			param.put("keyword", keyword);
			count = sqlSession.selectOne(namespace + ".productDbCount12", param);
		} else if (state.equals("3") || state.equals("4")) {
			// ī�װ� & �õ�
			param.put("keyword", keyword);
			count = sqlSession.selectOne(namespace + ".productDbCount345", param);
		}else if(state.equals("5")){
			//�ñ���
			param.put("keyword1", keyword.substring(0,2));
			param.put("keyword2", keyword.substring(2));
			count = sqlSession.selectOne(namespace + ".productDbCount345", param);
		}
		System.out.println("product db count : " + count);
		return count;
	}*/
	
	// ��ǰ ��� ���� state 1,2
	public int getProductDbCount12(String state, String keyword) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("state", state);
		param.put("keyword", keyword);
		return sqlSession.selectOne(namespace + ".productDbCount12", param);
	}

	// ��ǰ ��� ���� state 3,4
	public int getProductDbCount34(String state, String keyword) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("state", state);
		param.put("keyword", keyword);
		return sqlSession.selectOne(namespace + ".productDbCount345", param);
	}

	// ��ǰ ��� ���� state 5
	public int getProductDbCount5(String state, String keyword) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("state", state);
		param.put("keyword1", keyword.substring(0, 2));
		param.put("keyword2", keyword.substring(2));
		return sqlSession.selectOne(namespace + ".productDbCount345", param);
	}

	// ��ǰ ��� - ���� ������ state 0
	public ArrayList<DaangnProductVO> getProductList(String state) {
		List<DaangnProductVO> list = new ArrayList<DaangnProductVO>();
		Map param = new HashMap<String, String>();
		param.put("state", state);
		list = sqlSession.selectList(namespace + ".productList012", param);
		return (ArrayList<DaangnProductVO>) list;
	}
	
	// ��ǰ ��� - ����¡ ó��
/*	public ArrayList<DaangnProductVO> getProductList(String state, String keyword, int startCount, int endCount) {
		List<DaangnProductVO> list = new ArrayList<DaangnProductVO>();

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("state", state);
		param.put("start", startCount);
		param.put("end", endCount);

		if (state.equals("1") || state.equals("2")) {
			// ��ü & �˻�
			param.put("keyword", keyword);
			list = sqlSession.selectList(namespace + ".productList012", param);
		} else if (state.equals("3") || state.equals("4")) {
			// ī�װ� & �õ�
			param.put("keyword", keyword);
			list = sqlSession.selectList(namespace + ".productList345", param);
		}else if(state.equals("5")){
			//�ñ���
			System.out.println("sido : " +keyword.substring(0,2));
			System.out.println("sigungu : " +keyword.substring(2));
			param.put("keyword1", keyword.substring(0,2));
			param.put("keyword2", keyword.substring(2));
			list = sqlSession.selectList(namespace + ".productList345", param);
		}

		return (ArrayList<DaangnProductVO>) list;
	}*/
	
	// ��ǰ ��� state 1,2
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

	// ��ǰ ��� state 3,4
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

	// ��ǰ ��� state 5
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

	// ���� �Ǹ��� ��ǰ ����Ʈ ����
	public int getSalesDbCount(String id) {
		return sqlSession.selectOne(namespace + ".getSalesDbCount", id);
	}

	// ���� �Ǹ��� ��ǰ ����Ʈ
	public ArrayList<DaangnProductVO> getSalesList(int startCount, int endCount, String id) {
		List<DaangnProductVO> list = new ArrayList<DaangnProductVO>();

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		param.put("start", startCount);
		param.put("end", endCount);

		list = sqlSession.selectList(namespace + ".sales_list", param);
		return (ArrayList<DaangnProductVO>) list;
	}

	// ��ǰ ���� ����Ʈ
	public ArrayList<DaangnFileVO> getProductFileList(String pid) {
		List<DaangnFileVO> fileArray = new ArrayList<DaangnFileVO>();
		fileArray = sqlSession.selectList(namespace + ".product_file_list", pid);
		return (ArrayList<DaangnFileVO>) fileArray;
	}

	// ��ǰ ���� ����
	public int getPstateUpdate(String pid, String pstate) {
		boolean result = false;
		// String ps_name = "";

		/*
		 * if(pstate.equals("0")) ps_name="�Ǹ���"; if(pstate.equals("1"))
		 * ps_name="������"; if(pstate.equals("2")) ps_name="�ǸſϷ�";
		 */

		Map param = new HashMap<String, String>();
		param.put("pid", pid);
		param.put("pstate", pstate);

		return sqlSession.update(namespace + ".pstate_update", param);
	}

	// ��ǰ ��¥ ����
	public int getPdateUpdate(String pid) {
		return sqlSession.update(namespace + ".pdate_update", pid);
	}

	// ��ǰ ���� �� ���� �ҷ�����
	public DaangnProductVO getProductContent(String pid) {
		return sqlSession.selectOne(namespace + ".product_content", pid);
	}

	// ��ǰ ������ �����ϴ��� Ȯ��
	public int getnumberOfFiles(String pid) {
		return sqlSession.selectOne(namespace + ".number_of_files", pid);
	}

	// ��ǰ ���� ����
	public void getProductFileDelete(String pid) {
		sqlSession.delete(namespace + ".product_file_delete", pid);
	}

	// ��ǰ ����
	public int getUpdateResult(DaangnProductVO vo) {
		return sqlSession.update(namespace + ".update_product", vo);
	}

	// ��ǰ����
	public void getProductDelete(String pid) {
		sqlSession.delete(namespace + ".product_delete", pid);
	}

	// �õ� ��������
	public ArrayList<DaangnJusoVO> getSido() {
		List<DaangnJusoVO> sido = new ArrayList<DaangnJusoVO>();
		sido = sqlSession.selectList(namespace + ".getSido");
		return (ArrayList<DaangnJusoVO>) sido;
	}

	// �ñ��� ��������
	public ArrayList<DaangnJusoVO> getSigungu(String sido) {
		List<DaangnJusoVO> sigungu = new ArrayList<DaangnJusoVO>();
		sigungu = sqlSession.selectList(namespace + ".getSigungu", sido);
		return (ArrayList<DaangnJusoVO>) sigungu;
	}

	// �� ��������
	public ArrayList<DaangnJusoVO> getHname(String sigungu) {
		List<DaangnJusoVO> hname = new ArrayList<DaangnJusoVO>();
		hname = sqlSession.selectList(namespace + ".getHname", sigungu);
		return (ArrayList<DaangnJusoVO>) hname;
	}

	// �Ǹ� �� ��ǰ ä���� ��� ��������
	public ArrayList<DaangnChatVO> getChatIDList(String pid, String rid) {
		List<DaangnChatVO> chatIDList = new ArrayList<DaangnChatVO>();

		Map param = new HashMap<String, String>();
		param.put("pid", pid);
		param.put("rid", rid);

		chatIDList = sqlSession.selectList(namespace + ".getChatIDList", param);
		return (ArrayList<DaangnChatVO>) chatIDList;
	}

	// ���� ���
	public int getReviewResult(DaangnReviewVO vo) {
		int val = sqlSession.insert(namespace + ".getReviewResult", vo);
		return val;
	}

	// ���� ���
	public int getOrderResult(DaangnReviewVO vo) {
		int val = sqlSession.insert(namespace + ".getOrderResult", vo);
		return val;
	}
}
