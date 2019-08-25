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

	// ��ǰ���
	@Override
	public boolean productInsert(DaangnProductVO vo, HttpServletRequest request, List<MultipartFile> fileList)
			throws Exception {

		boolean result = false;
		String root_path = "", att_path = "";
		List<DaangnFileVO> insertFileList = new ArrayList<DaangnFileVO>(); // ���� insert�Ҷ� �� List��ü
		// file�� ������ ������ form data�� insert, file ���̺� insert�ϱ� ���� pid�� �ٽ� �޾ƿ�
		String pid = productDAO.getInsertResult(vo);
		
		if (!pid.equals("") && fileList.size() != 0) { // pid�� �����ϰ� file�� ���� ���
			// request��ü���� ����� ��θ� �޾ƿ� 
			root_path = request.getSession().getServletContext().getRealPath("/");
			att_path = "\\resources\\upload\\";
			
			for (MultipartFile fileOne : fileList) { // fileList���� ���� �Ѱ��� ����
				DaangnFileVO fvo = new DaangnFileVO();
				UUID uuid = UUID.randomUUID(); // �ߺ� ����

				String originalfileName = fileOne.getOriginalFilename();
				String saveFileName = uuid + "_" + originalfileName;

				fvo.setPid(pid);
				fvo.setPfile(saveFileName);
				fvo.setOpfile(originalfileName);

				insertFileList.add(fvo);

				String savePath = root_path + att_path + saveFileName; // ���������� ����� ���
				File file = new File(savePath); // ���� ��ü ����
				fileOne.transferTo(file); // upload�� �� ���� ������ ������ ���ϰ�ü�� �Ѱ��� 
				// multiFile.get(i).transferTo(new File(savePath)); �̷��Ե� ����
			}

			int value = productDAO.getInsertFile(insertFileList);// file insert

			if (value != 0)
				result = true;

		} else if (!pid.equals("")) // ������ ���°��
			result = true;
		return result;
	}

	// ���ɵ��
	@Override
	public boolean getLike(String pid) {
		// return productDAO.getLike(pid);
		return true;
	}

	// ��ǰ ��� - ���� ������(���� 7��)
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

	// ��ǰ �Ǹ� ���� ����
	@Override
	public int getPstateUpdate(String pid, String pstate) {
		return productDAO.getPstateUpdate(pid, pstate);
	}

	// ��ǰ �Ǹ� ��¥ ����(����ø���)
	@Override
	public int getPdateUpdate(String pid) {
		return productDAO.getPdateUpdate(pid);
	}

	// ��ǰ ���� �� ���� �ҷ�����
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
	
	//��ǰ ���� ����
	@Override
	public int getProductFileDelete(String pid) {
		int numberOfFiles = productDAO.getnumberOfFiles(pid);
		if(numberOfFiles != 0){
			productDAO.getProductFileDelete(pid);
		}
		return numberOfFiles;
	}

	// ��ǰ ���� ����
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

	// ��ǰ����
	@Override
	public void getProductDelete(String pid) {
		productDAO.getProductDelete(pid);
	}

	// �õ� ������ ��������
	@Override
	public ArrayList<DaangnJusoVO> getSido() {
		return productDAO.getSido();
	}

	// �õ��� ���� �ñ��� ������ ��������
	@Override
	public ArrayList<DaangnJusoVO> getSigungu(String sido) {
		if(sido.length()>2){
			sido = sido.substring(0,2);
		}
		return productDAO.getSigungu(sido);
	}

	// �ñ��� �����Ϳ� ���� �� ������ ��������
	@Override
	public ArrayList<DaangnJusoVO> getHname(String sigungu) {
		return productDAO.getHname(sigungu);
	}

	// �Ǹ��ϴ� ��ǰ ���� ��������
	@Override
	public DaangnProductVO getSaleProductData(String pid) {
		DaangnProductVO productData = productDAO.getProductContent(pid);
		ArrayList<DaangnFileVO> productFile = productDAO.getProductFileList(pid);

		if (productFile.size() != 0) {
			productData.setFileArray(productFile);
		}

		return productData;
	}

	// �Ǹ� �� ��ǰ ä�� ��� ��������
	@Override
	public ArrayList<DaangnMemberVO> getChatList(String pid, String rid) {
		ArrayList<DaangnChatVO> chatIDList = new ArrayList<DaangnChatVO>();
		ArrayList<DaangnMemberVO> chatList = new ArrayList<DaangnMemberVO>();

		chatIDList = productDAO.getChatIDList(pid, rid); // product PK, seller ID

		if (chatIDList.size() != 0) {
			chatList = memberDAO.getChatList(chatIDList); // ä���ڵ��� ����
		}
		return chatList;
	}

	// ���� & ���� ���
	@Override
	public boolean getSalesReview(DaangnReviewVO vo) {
		boolean result = false;

		int reviewResult = productDAO.getReviewResult(vo); // �ŷ� �ı� insert

		if (reviewResult != 0) {
			int orderResult = productDAO.getOrderResult(vo); // �������� ���� ������� insert
			if (orderResult != 0)
				result = true;
		}
		return result;
	}


}
