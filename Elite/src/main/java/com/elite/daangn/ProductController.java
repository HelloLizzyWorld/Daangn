package com.elite.daangn;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.elite.service.PageService;
import com.elite.service.ProductService;
import com.elite.vo.DaangnJusoVO;
import com.elite.vo.DaangnMemberVO;
import com.elite.vo.DaangnProductVO;
import com.elite.vo.DaangnReviewVO;
import com.elite.vo.SessionVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
public class ProductController {
	@Resource(name = "productService")
	private ProductService productService;
	@Resource(name = "pageService")
	private PageService pageService;

	// ��ǰ ��� ������
	@RequestMapping(value = "/product_write.carrot", method = RequestMethod.GET)
	public String product_write() {
		return "/product/product_write";
	}

	// ��ǰ ��� ����
	@RequestMapping(value = "/product_write_proc.carrot", method = RequestMethod.POST)
	@ResponseBody
	public String product_write_proc(DaangnProductVO vo, HttpSession session, HttpServletRequest request,
			@RequestParam("fileList") List<MultipartFile> fileList) throws Exception {
		// vo : form�� �ִ� data, session : ���ǿ� ����Ǿ� �ִ� �α����� ������� ������
		// request : Ŭ���̾�Ʈ���� ��û�ϴ� ����(file), fileList : FormData�� �߰��ߴ� ���� �迭
		String success = "";
		SessionVO svo = (SessionVO) session.getAttribute("svo");//session�� ����Ǿ� �ִ� ����
		vo.setId(svo.getId());

		boolean result = productService.productInsert(vo, request, fileList);
		if (result)
			success = "success";
		return success;
	}

	// ��ǰ ���
	@RequestMapping(value = "/product_list.carrot", method = RequestMethod.GET)
	public ModelAndView product_list(String state, String rpage, String keyword) {
		// state : �˻� ���� ����, rpage:������, keyword:�˻� �� Ű����
		// state : 1 - ��ü ���, 2 - ��ü �˻�, 3 - ī�װ� �� �˻�, 4 - �õ� �˻�, 5 - �ñ��� �˻�
		ModelAndView mv = new ModelAndView();
		ArrayList<DaangnProductVO> list = new ArrayList<DaangnProductVO>();
		ArrayList<DaangnJusoVO> sigunguList = new ArrayList<DaangnJusoVO>();
		// ������ ���� ��� ����, �˻��� Ű���带 �Ű������� �����Ͽ� �ش� ����� �����͸� ������
		list = (ArrayList<DaangnProductVO>) pageService.getProductePageList(rpage, "product", state, keyword).get("list");

		// select box �õ� ������ ��������
		ArrayList<DaangnJusoVO> sido = productService.getSido();
		mv.addObject("sido", sido);
		
		// state�� �õ�  Ȥ�� �ñ����̰� keyword�� ������ �� �ñ��� ��� ������
		if (state.equals("4") || state.equals("5") && !keyword.equals("0")) {
			sigunguList = productService.getSigungu(keyword);
			mv.addObject("sigungu", sigunguList);
		}
		mv.addObject("list", list);
		mv.addObject("dbCount", pageService.getDbCount());
		mv.addObject("pageSize", pageService.getPageSize());
		mv.addObject("rpage", pageService.getReqPage());
		mv.addObject("state", state);
		mv.addObject("keyword", keyword);
		mv.setViewName("/product/product_list");
		return mv;
	}

	// ��ǰ �Ǹ� ���� ����
	@RequestMapping(value = "/product_state_change.carrot", method = RequestMethod.GET)
	@ResponseBody
	public String product_state_change(String pid, String pstate) {
		int result = productService.getPstateUpdate(pid, pstate);
		return String.valueOf(result);
	}

	// ��ǰ �Ǹ� ��¥ ����
	@RequestMapping(value = "/product_update_date.carrot", method = RequestMethod.GET)
	@ResponseBody
	public String product_update_date(String pid) {
		int result = productService.getPdateUpdate(pid);
		return String.valueOf(result);
	}

	// ��ǰ ����
	@RequestMapping(value = "/product_delete.carrot", method = RequestMethod.GET)
	public String product_delete(String pid) {
		productService.getProductDelete(pid);
		return "redirect:/mypage_sales.carrot";
	}

	// ��ǰ ���� �� ���� �ҷ�����
	@RequestMapping(value = "/product_update.carrot", method = RequestMethod.GET)
	public ModelAndView product_update(String pid) {
		ModelAndView mv = new ModelAndView("/product/product_update");
		DaangnProductVO vo = productService.getProductContent(pid);
		mv.addObject("vo", vo);
		return mv;
	}

	// ��ǰ ���� ����
	@RequestMapping(value = "/product_file_delete.carrot", method = RequestMethod.GET)
	@ResponseBody
	public int product_file_delete(String pid) {
		int result = productService.getProductFileDelete(pid);
		return result;
	}

	// ��ǰ ���� ����(��ǰ ��ϰ� ����)
	@ResponseBody
	@RequestMapping(value = "/product_update_proc.carrot", method = RequestMethod.POST)
	public String product_update_proc(DaangnProductVO vo, HttpSession session, HttpServletRequest request,
			@RequestParam("fileList") List<MultipartFile> fileList) throws Exception {
		String success = "";
		SessionVO svo = (SessionVO) session.getAttribute("svo");
		vo.setId(svo.getId());

		boolean result = productService.productUpdate(vo, request, fileList);
		if (result)
			success = "success";
		return success;
	}

	// �ش� �õ��� ���� �ñ��� ��������
	@ResponseBody
	@RequestMapping(value = "/juso_sigungu.carrot", method = RequestMethod.GET, produces = "application/text; charset=utf8")
	public String getSigungu(String sido) {
		ArrayList<DaangnJusoVO> sigunguList = new ArrayList<DaangnJusoVO>();
		sigunguList = productService.getSigungu(sido);

		Gson gson = new GsonBuilder().create();
		String sigungu = gson.toJson(sigunguList);

		return sigungu;
	}

	// �ش� �ñ����� ���� �� ��������
	@ResponseBody
	@RequestMapping(value = "/juso_hname.carrot", method = RequestMethod.GET, produces = "application/text; charset=utf8")
	public String getHname(String sigungu) {
		ArrayList<DaangnJusoVO> hnameList = new ArrayList<DaangnJusoVO>();
		hnameList = productService.getHname(sigungu);
		
		Gson gson = new GsonBuilder().create();
		String hname = gson.toJson(hnameList);

		return hname;
	}

	// �Ǹ��ϴ� ��ǰ ���� ��������
	@ResponseBody
	@RequestMapping(value = "/sale_product_data.carrot", method = RequestMethod.GET, produces = "application/text; charset=utf8")
	public String getSaleProductData(String pid) {
		DaangnProductVO productData = productService.getSaleProductData(pid);

		// ajax�� �����͸� ������ �ϹǷ� JSON ���
		Gson gson = new GsonBuilder().create();
		String product = gson.toJson(productData);
		return product;
	}

	// �Ǹ� �� ��ǰ ä�� ��� ��������
	@ResponseBody
	@RequestMapping(value = "/chat_list.carrot", method = RequestMethod.GET, produces = "application/text; charset=utf8")
	public String getChatList(String pid, HttpSession session) {
		SessionVO svo = (SessionVO) session.getAttribute("svo");
		String rid = svo.getId(); // �Ǹ���

		ArrayList<DaangnMemberVO> chatList = new ArrayList<DaangnMemberVO>();
		chatList = productService.getChatList(pid, rid);
		System.out.println("nick name : "+chatList.get(0).getNick());
		// ajax�� �����͸� ������ �ϹǷ� JSON ���
		Gson gson = new GsonBuilder().create();
		String chat = gson.toJson(chatList);

		return chat;
	}

	// ���� & ���� ���
	@ResponseBody
	@RequestMapping(value = "/sales_review.carrot", method = RequestMethod.POST)
	public boolean sales_review(DaangnReviewVO vo) {
		boolean result = productService.getSalesReview(vo);
		return result;
	}

}