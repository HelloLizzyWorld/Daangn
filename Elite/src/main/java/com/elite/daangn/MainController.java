package com.elite.daangn;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.elite.service.ProductService;
import com.elite.vo.DaangnProductVO;

@Controller
public class MainController {
	@Resource(name = "productService")
	private ProductService productService;

	@RequestMapping(value = "/index.carrot", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		ArrayList<DaangnProductVO> list = new ArrayList<DaangnProductVO>();
		list = productService.getProductList("0");
		mv.addObject("list", list);
		mv.setViewName("index");
		return mv;
	}

	@RequestMapping(value = "/header.carrot", method = RequestMethod.GET)
	public String header() {
		return "header";
	}

	@RequestMapping(value = "/footer.carrot", method = RequestMethod.GET)
	public String footer() {
		return "footer";
	}

	@RequestMapping(value = "/introduction.carrot", method = RequestMethod.GET)
	public String introduction() {
		return "/main/introduction";
	}

	@RequestMapping(value = "/review.carrot", method = RequestMethod.GET)
	public String review() {
		return "/main/review";
	}

	@RequestMapping(value = "/top_keywords.carrot", method = RequestMethod.GET)
	public String top_keywords() {
		return "/main/top_keywords";
	}

	@RequestMapping(value = "/hot_articles.carrot", method = RequestMethod.GET)
	public String hot_article() {
		return "/main/hot_articles";
	}
	@RequestMapping(value = "/error.carrot", method = RequestMethod.GET)
	public String error() {
		return "error_page";
	}
	@RequestMapping(value = "/black_page.carrot", method = RequestMethod.GET)
	public String black() {
		return "black_page";
	}
	@RequestMapping(value = "/test.carrot", method = RequestMethod.GET)
	public String test() {
		return "test";
	}
	
}
