package com.elite.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.elite.dao.DaangnMemberDAO;
import com.elite.dao.DaangnProductDAO;
import com.elite.vo.DaangnChatVO;
import com.elite.vo.DaangnFileVO;
import com.elite.vo.DaangnJusoVO;
import com.elite.vo.DaangnMemberVO;
import com.elite.vo.DaangnProductVO;
import com.elite.vo.DaangnReviewVO;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private DaangnProductDAO productDAO;

	@Autowired
	private DaangnMemberDAO memberDAO;

	// 상품등록
	@Override
	public boolean productInsert(DaangnProductVO vo, HttpServletRequest request, List<MultipartFile> fileList)
			throws Exception {

		boolean result = false;
		String root_path = "", att_path = "";
		List<DaangnFileVO> insertFileList = new ArrayList<DaangnFileVO>(); // 파일 insert할때 쓸 List객체
		// file을 제외한 나머지 form data를 insert, file 테이블에 insert하기 위해 pid를 다시 받아옴
		String pid = productDAO.getInsertResult(vo);
		
		if (!pid.equals("") && fileList.size() != 0) { // pid가 존재하고 file이 있을 경우
			// request객체에서 저장될 경로를 받아옴 
			root_path = request.getSession().getServletContext().getRealPath("/");
			att_path = "\\resources\\upload\\";
			
			for (MultipartFile fileOne : fileList) { // fileList에서 파일 한개씩 꺼냄
				DaangnFileVO fvo = new DaangnFileVO();
				UUID uuid = UUID.randomUUID(); // 중복 방지

				String originalfileName = fileOne.getOriginalFilename();
				String saveFileName = uuid + "_" + originalfileName;

				fvo.setPid(pid);
				fvo.setPfile(saveFileName);
				fvo.setOpfile(originalfileName);

				insertFileList.add(fvo);

				String savePath = root_path + att_path + saveFileName; // 최종적으로 저장될 경로
				File file = new File(savePath); // 파일 객체 생성
				fileOne.transferTo(file); // upload한 한 개의 파일을 생성된 파일객체로 넘겨줌 
				// multiFile.get(i).transferTo(new File(savePath)); 이렇게도 가능
			}

			int value = productDAO.getInsertFile(insertFileList);// file insert

			if (value != 0)
				result = true;

		} else if (!pid.equals("")) // 파일이 없는경우
			result = true;
		return result;
	}

	// 관심등록
	@Override
	public boolean getLike(String pid) {
		// return productDAO.getLike(pid);
		return true;
	}

	// 상품 목록 - 메인 페이지(상위 7개)
	@Override
	public ArrayList<DaangnProductVO> getProductList(String state) {
		ArrayList<DaangnProductVO> list = new ArrayList<DaangnProductVO>();
		list = productDAO.getProductList(state);
		for (DaangnProductVO vo : list) {
			String pid = vo.getPid();
			ArrayList<DaangnFileVO> fileArray = productDAO.getProductFileList(pid);
			vo.setFileArray(fileArray);
		}
		return list;
	}

	// 상품 판매 상태 변경
	@Override
	public int getPstateUpdate(String pid, String pstate) {
		return productDAO.getPstateUpdate(pid, pstate);
	}

	// 상품 판매 날짜 변경(끌어올리기)
	@Override
	public int getPdateUpdate(String pid) {
		return productDAO.getPdateUpdate(pid);
	}

	// 상품 수정 전 내용 불러오기
	@Override
	public DaangnProductVO getProductContent(String pid) {
		DaangnProductVO vo = new DaangnProductVO();
		vo = productDAO.getProductContent(pid);
		
		String con = vo.getPcontent();
		System.out.println(con);
		con = con.replaceAll("<br>", "\r\n");
		vo.setPcontent(con);
		System.out.println(con);
	
		if(vo != null){
			ArrayList<DaangnFileVO> fileArray = productDAO.getProductFileList(pid);
			vo.setFileArray(fileArray);
		}
		return vo;
	}
	
	//상품 파일 삭제
	@Override
	public int getProductFileDelete(String pid) {
		int numberOfFiles = productDAO.getnumberOfFiles(pid);
		if(numberOfFiles != 0){
			productDAO.getProductFileDelete(pid);
		}
		return numberOfFiles;
	}

	// 상품 수정 과정
	@Override
	public boolean productUpdate(DaangnProductVO vo, HttpServletRequest request, List<MultipartFile> fileList)
			throws Exception {
		boolean result = false;
		String root_path = "", att_path = "";

		List<DaangnFileVO> insertFileList = new ArrayList<DaangnFileVO>();

		int upResult = productDAO.getUpdateResult(vo);

		if (upResult !=0 && fileList.size() != 0) {

			root_path = request.getSession().getServletContext().getRealPath("/");
			att_path = "\\resources\\upload\\";

			for (MultipartFile fileOne : fileList) {
				DaangnFileVO fvo = new DaangnFileVO();
				UUID uuid = UUID.randomUUID();

				String originalfileName = fileOne.getOriginalFilename();
				String saveFileName = uuid + "_" + originalfileName;

				fvo.setPid(vo.getPid());
				fvo.setPfile(saveFileName);
				fvo.setOpfile(originalfileName);

				insertFileList.add(fvo);

				String savePath = root_path + att_path + saveFileName;
				File file = new File(savePath);
				fileOne.transferTo(file);
				// multiFile.get(i).transferTo(new File(savePath));
			}

			int value = productDAO.getInsertFile(insertFileList);

			if (value != 0)
				result = true;

		}else if (upResult !=0) result = true;

		return result;
	}

	// 상품삭제
	@Override
	public void getProductDelete(String pid) {
		productDAO.getProductDelete(pid);
	}

	// 시도 데이터 가져오기
	@Override
	public ArrayList<DaangnJusoVO> getSido() {
		return productDAO.getSido();
	}

	// 시도에 따른 시군구 데이터 가져오기
	@Override
	public ArrayList<DaangnJusoVO> getSigungu(String sido) {
		if(sido.length()>2){
			sido = sido.substring(0,2);
		}
		return productDAO.getSigungu(sido);
	}

	// 시군구 데이터에 따른 동 데이터 가져오기
	@Override
	public ArrayList<DaangnJusoVO> getHname(String sigungu) {
		return productDAO.getHname(sigungu);
	}

	// 판매하는 상품 정보 가져오기
	@Override
	public DaangnProductVO getSaleProductData(String pid) {
		DaangnProductVO productData = productDAO.getProductContent(pid);
		ArrayList<DaangnFileVO> productFile = productDAO.getProductFileList(pid);

		if (productFile.size() != 0) {
			productData.setFileArray(productFile);
		}

		return productData;
	}

	// 판매 할 상품 채팅 목록 가져오기
	@Override
	public ArrayList<DaangnMemberVO> getChatList(String pid, String rid) {
		ArrayList<DaangnChatVO> chatIDList = new ArrayList<DaangnChatVO>();
		ArrayList<DaangnMemberVO> chatList = new ArrayList<DaangnMemberVO>();

		chatIDList = productDAO.getChatIDList(pid, rid); // product PK, seller ID

		if (chatIDList.size() != 0) {
			chatList = memberDAO.getChatList(chatIDList); // 채팅자들의 정보
		}
		return chatList;
	}

	// 리뷰 & 구매 등록
	@Override
	public boolean getSalesReview(DaangnReviewVO vo) {
		boolean result = false;

		int reviewResult = productDAO.getReviewResult(vo); // 거래 후기 insert

		if (reviewResult != 0) {
			int orderResult = productDAO.getOrderResult(vo); // 구매자의 구매 목록으로 insert
			if (orderResult != 0)
				result = true;
		}
		return result;
	}


}
