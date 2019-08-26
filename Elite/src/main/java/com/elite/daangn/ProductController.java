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

	// 상품 등록 페이지
	@RequestMapping(value = "/product_write.carrot", method = RequestMethod.GET)
	public String product_write() {
		return "/product/product_write";
	}

	// 상품 등록 과정
	@RequestMapping(value = "/product_write_proc.carrot", method = RequestMethod.POST)
	@ResponseBody
	public String product_write_proc(DaangnProductVO vo, HttpSession session, HttpServletRequest request,
			@RequestParam("fileList") List<MultipartFile> fileList) throws Exception {
		// vo : form에 있는 data, session : 세션에 저장되어 있는 로그인한 사용자의 데이터
		// request : 클라이언트에서 요청하는 정보(file), fileList : FormData에 추가했던 파일 배열
		String success = "";
		SessionVO svo = (SessionVO) session.getAttribute("svo");//session에 저장되어 있는 정보
		vo.setId(svo.getId());

		boolean result = productService.productInsert(vo, request, fileList);
		if (result)
			success = "success";
		return success;
	}

	// 상품 목록
	@RequestMapping(value = "/product_list.carrot", method = RequestMethod.GET)
	public ModelAndView product_list(String state, String rpage, String keyword) {
		// state : 검색 상태 종류, rpage:페이지, keyword:검색 할 키워드
		// state : 1 - 전체 목록, 2 - 전체 검색, 3 - 카테고리 별 검색, 4 - 시도 검색, 5 - 시군구 검색
		ModelAndView mv = new ModelAndView();
		ArrayList<DaangnProductVO> list = new ArrayList<DaangnProductVO>();
		ArrayList<DaangnJusoVO> sigunguList = new ArrayList<DaangnJusoVO>();
		// 페이지 수와 목록 상태, 검색할 키워드를 매개변수로 전달하여 해당 목록의 데이터를 가져옴
		list = (ArrayList<DaangnProductVO>) pageService.getProductePageList(rpage, "product", state, keyword).get("list");

		// select box 시도 데이터 가져오기
		ArrayList<DaangnJusoVO> sido = productService.getSido();
		mv.addObject("sido", sido);
		
		// state가 시도  혹은 시군구이고 keyword가 존재할 때 시군구 목록 데이터
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

	// 상품 판매 상태 변경
	@RequestMapping(value = "/product_state_change.carrot", method = RequestMethod.GET)
	@ResponseBody
	public String product_state_change(String pid, String pstate) {
		int result = productService.getPstateUpdate(pid, pstate);
		return String.valueOf(result);
	}

	// 상품 판매 날짜 수정
	@RequestMapping(value = "/product_update_date.carrot", method = RequestMethod.GET)
	@ResponseBody
	public String product_update_date(String pid) {
		int result = productService.getPdateUpdate(pid);
		return String.valueOf(result);
	}

	// 상품 삭제
	@RequestMapping(value = "/product_delete.carrot", method = RequestMethod.GET)
	public String product_delete(String pid) {
		productService.getProductDelete(pid);
		return "redirect:/mypage_sales.carrot";
	}

	// 상품 수정 전 내용 불러오기
	@RequestMapping(value = "/product_update.carrot", method = RequestMethod.GET)
	public ModelAndView product_update(String pid) {
		ModelAndView mv = new ModelAndView("/product/product_update");
		DaangnProductVO vo = productService.getProductContent(pid);
		mv.addObject("vo", vo);
		return mv;
	}

	// 상품 파일 삭제
	@RequestMapping(value = "/product_file_delete.carrot", method = RequestMethod.GET)
	@ResponseBody
	public int product_file_delete(String pid) {
		int result = productService.getProductFileDelete(pid);
		return result;
	}

	// 상품 수정 과정(상품 등록과 유사)
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

	// 해당 시도에 따른 시군구 가져오기
	@ResponseBody
	@RequestMapping(value = "/juso_sigungu.carrot", method = RequestMethod.GET, produces = "application/text; charset=utf8")
	public String getSigungu(String sido) {
		ArrayList<DaangnJusoVO> sigunguList = new ArrayList<DaangnJusoVO>();
		sigunguList = productService.getSigungu(sido);

		Gson gson = new GsonBuilder().create();
		String sigungu = gson.toJson(sigunguList);

		return sigungu;
	}

	// 해당 시군구에 따른 동 가져오기
	@ResponseBody
	@RequestMapping(value = "/juso_hname.carrot", method = RequestMethod.GET, produces = "application/text; charset=utf8")
	public String getHname(String sigungu) {
		ArrayList<DaangnJusoVO> hnameList = new ArrayList<DaangnJusoVO>();
		hnameList = productService.getHname(sigungu);
		
		Gson gson = new GsonBuilder().create();
		String hname = gson.toJson(hnameList);

		return hname;
	}

	// 판매하는 상품 정보 가져오기
	@ResponseBody
	@RequestMapping(value = "/sale_product_data.carrot", method = RequestMethod.GET, produces = "application/text; charset=utf8")
	public String getSaleProductData(String pid) {
		DaangnProductVO productData = productService.getSaleProductData(pid);

		// ajax로 데이터를 보내야 하므로 JSON 사용
		Gson gson = new GsonBuilder().create();
		String product = gson.toJson(productData);
		return product;
	}

	// 판매 할 상품 채팅 목록 가져오기
	@ResponseBody
	@RequestMapping(value = "/chat_list.carrot", method = RequestMethod.GET, produces = "application/text; charset=utf8")
	public String getChatList(String pid, HttpSession session) {
		SessionVO svo = (SessionVO) session.getAttribute("svo");
		String rid = svo.getId(); // 판매자

		ArrayList<DaangnMemberVO> chatList = new ArrayList<DaangnMemberVO>();
		chatList = productService.getChatList(pid, rid);
		System.out.println("nick name : "+chatList.get(0).getNick());
		// ajax로 데이터를 보내야 하므로 JSON 사용
		Gson gson = new GsonBuilder().create();
		String chat = gson.toJson(chatList);

		return chat;
	}

	// 리뷰 & 구매 등록
	@ResponseBody
	@RequestMapping(value = "/sales_review.carrot", method = RequestMethod.POST)
	public boolean sales_review(DaangnReviewVO vo) {
		boolean result = productService.getSalesReview(vo);
		return result;
	}

}