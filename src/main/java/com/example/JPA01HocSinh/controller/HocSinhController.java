package com.example.JPA01HocSinh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.JPA01HocSinh.model.HocSinh;
import com.example.JPA01HocSinh.services.IHocSinhService;
import com.example.JPA01HocSinh.services.ILopService;
import com.google.gson.Gson;

@RestController
@RequestMapping("/hocsinh")
public class HocSinhController {
	
	@Autowired
	IHocSinhService hocSinhService;
	
	@Autowired
	ILopService lopService;
	
	@CrossOrigin
	@RequestMapping(value ="/hienthi", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<HocSinh> hienThi(){
		return hocSinhService.getAll();
	}
	
	@CrossOrigin
	@PostMapping(value ="/them", produces = MediaType.APPLICATION_JSON_VALUE)
	public String themHocSinh(@RequestBody String hocsinh) {
		Gson gson = new Gson();
		HocSinh hocSinhMoi = gson.fromJson(hocsinh, HocSinh.class);
		if(!lopService.kiemTraLop(hocSinhMoi.getLop().getLopID()))
			return "Lớp không tồn tại";
		
		else if(!hocSinhService.kiemTraSiSo(hocSinhMoi.getLop().getLopID()))
			return "Sỉ số lớp đã đủ 20 học sinh";
		
		else
			hocSinhService.add(hocSinhMoi);
		return "Thêm thành công học sinh "+hocSinhMoi.getHoTen();
		
	}
	
	@CrossOrigin
	@PutMapping(value ="/sua", produces = MediaType.APPLICATION_JSON_VALUE)
	public String suaHocSinh(@RequestBody String hocsinh) {
		Gson gson = new Gson();
		HocSinh hocSinhMoi  = gson.fromJson(hocsinh, HocSinh.class);// convert tham số truyền vào dạng JSon sang kiểu class
		if(!hocSinhService.kiemTraHocSinh(hocSinhMoi.getHocSinhID()))
			return "Học sinh không tồn tại";
		else
			hocSinhService.update(hocSinhMoi);
		return "Sửa học sinh có mã số "+ hocSinhMoi.getHocSinhID() +"thành công";
	}
	
	@CrossOrigin
	@PutMapping(value = "/chuyenlop")
	public String chuyenLopHocSinh(@RequestParam int hocsinhid, int lopid) {
		if(!hocSinhService.kiemTraHocSinh(hocsinhid))
			return "Học sinh không tồn tại";
		else if(!lopService.kiemTraLop(lopid))
			return "Lớp không tồn tại";
		else
			hocSinhService.moveStudent(hocsinhid, lopid);
		return "Hoc sinh mã số "+hocsinhid + " đã chuyển sang lớp mã số "+lopid +" thành công ";
			
	}
	
	@CrossOrigin
	@DeleteMapping(value ="/xoa/{hocsinhid}")
	public String xoaHocSinh(@PathVariable int hocsinhid) {
		if(!hocSinhService.kiemTraHocSinh(hocsinhid))
			return "Học sinh không tồn tại";
		else
			hocSinhService.delete(hocSinhService.getOne(hocsinhid));
		return "Xóa học sinh có mã số " +hocsinhid +" thành công";
	}
}
