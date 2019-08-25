package com.elite.vo;

import java.util.ArrayList;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class DaangnProductVO {
	String pid, id, pcate, ptitle, ptype, pcontent, pdate, pstate, pprice, name, address, addr_sido, addr_sigungu, cart,
			msg, mfile, omfile, cid, pfile, opfile, bdate;

	int phits, count, rno;
	CommonsMultipartFile file1;
	ArrayList<DaangnFileVO> fileArray;

	public String getAddr_sido() {
		return addr_sido;
	}

	public void setAddr_sido(String addr_sido) {
		this.addr_sido = addr_sido;
	}

	public String getAddr_sigungu() {
		return addr_sigungu;
	}

	public void setAddr_sigungu(String addr_sigungu) {
		this.addr_sigungu = addr_sigungu;
	}

	public ArrayList<DaangnFileVO> getFileArray() {
		// System.out.println(fileArray.size());
		return fileArray;
	}

	public void setFileArray(ArrayList<DaangnFileVO> fileArray) {
		this.fileArray = fileArray;
	}

	public String getOpfile() {
		return opfile;
	}

	public void setOpfile(String opfile) {
		this.opfile = opfile;
	}

	public String getPfile() {
		return pfile;
	}

	public void setPfile(String pfile) {
		this.pfile = pfile;
	}

	public CommonsMultipartFile getFile1() {
		return file1;
	}

	public void setFile1(CommonsMultipartFile file1) {
		this.file1 = file1;
	}

	public int getRno() {
		return rno;
	}

	public void setRno(int rno) {
		this.rno = rno;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getMfile() {
		return mfile;
	}

	public void setMfile(String mfile) {
		this.mfile = mfile;
	}

	public String getOmfile() {
		return omfile;
	}

	public void setOmfile(String omfile) {
		this.omfile = omfile;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPcate() {
		return pcate;
	}

	public void setPcate(String pcate) {
		this.pcate = pcate;
	}

	public String getPtitle() {
		return ptitle;
	}

	public void setPtitle(String ptitle) {
		this.ptitle = ptitle;
	}

	public String getPtype() {
		return ptype;
	}

	public void setPtype(String ptype) {
		this.ptype = ptype;
	}

	public String getPcontent() {
		return pcontent;
	}

	public void setPcontent(String pcontent) {
		this.pcontent = pcontent;
	}

	public String getPdate() {
		return pdate;
	}

	public void setPdate(String pdate) {
		this.pdate = pdate;
	}

	public String getPstate() {
		return pstate;
	}

	public void setPstate(String pstate) {
		this.pstate = pstate;
	}

	public String getPprice() {
		return pprice;
	}

	public void setPprice(String pprice) {
		this.pprice = pprice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCart() {
		return cart;
	}

	public void setCart(String cart) {
		this.cart = cart;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getPhits() {
		return phits;
	}

	public void setPhits(int phits) {
		this.phits = phits;
	}

	public String getBdate() {
		return bdate;
	}

	public void setBdate(String bdate) {
		this.bdate = bdate;
	}

}
