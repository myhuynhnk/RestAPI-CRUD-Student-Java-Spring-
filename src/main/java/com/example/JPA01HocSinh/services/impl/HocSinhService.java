package com.example.JPA01HocSinh.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JPA01HocSinh.model.HocSinh;
import com.example.JPA01HocSinh.model.Lop;
import com.example.JPA01HocSinh.repository.IHocSinhRepository;
import com.example.JPA01HocSinh.repository.ILopRepository;
import com.example.JPA01HocSinh.services.IHocSinhService;

@Service
public class HocSinhService implements IHocSinhService {
	@Autowired
	IHocSinhRepository hocSinhRepository;
	
	@Autowired
	ILopRepository lopRepository;
	
	@Override
	public List<HocSinh> getAll() {
		return hocSinhRepository.findAll();
	}
	
	public boolean kiemTraSiSo(int lopid) {
		// kiểm tra lớp cần truyền vào có tồn tại chưa thông qua ID lớp
		Lop lop = lopRepository.getById(lopid);
		int siSo = lop.getSiSo() + 1;
		lop.setSiSo(siSo);
		
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		Validator validator = validatorFactory.getValidator();
		
		Set<ConstraintViolation<Lop>> violations = validator.validate(lop);
		for(ConstraintViolation<Lop> violation : violations)
			System.out.println(violation.getMessage());
		
		if(violations.size() == 0)
			return true;
		return false;
	}
	
	public void capNhapSiSo(int lopid) {
		// Kiểm tra lớp cần cập nhật có tồn tại chưa thông qua id lớp
		Lop lop = lopRepository.getById(lopid);
		int siSo = 0;
		
		// Duyệt danh sách học sinh
		for(HocSinh hocsinh : hocSinhRepository.findAll()) {
			if(hocsinh.getLop().getLopID() == lopid) //học sinh nào trong bảng hocsinh có lớp ID =  lớp Id cần kiểm tra
				siSo++;
		}
		lop.setSiSo(siSo);
		lopRepository.save(lop);
	}
	
	public boolean kiemTraHocSinh(int hocsinhid) {
		Optional<HocSinh> optional = Optional.empty();
		if(hocSinhRepository.findById(hocsinhid) != optional)
			return true;
		return false;
	}

	@Override
	public HocSinh add(HocSinh hocsinh) {
			
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		Validator validator = validatorFactory.getValidator();
		
		Set<ConstraintViolation<HocSinh>> violations = validator.validate(hocsinh);
		violations.forEach(x -> {System.out.println(x.getMessage());});
		
		if(violations.size() == 0) {
//			if(kiemTraSiSo(hocsinh.getLop().getLopID())) {//kiểm tra lớp thêm vào có đủ số lượng quy định chưa (max =20
				hocSinhRepository.save(hocsinh);
				capNhapSiSo(hocsinh.getLop().getLopID()); // thêm vào xong thì cập nhật lại sỉ số lớp
//			}
		}
		return hocsinh;
		
	}

	@Override
	public void update(HocSinh hocsinh) {	
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		Validator validator = validatorFactory.getValidator();
		
		
		Set<ConstraintViolation<HocSinh>> violations = validator.validate(hocsinh);
		violations.forEach(x -> {System.out.println(x.getMessage());});
		
		if(violations.size() == 0) {
//			kiểm tra học sinh cần sửa có tồn tại trong bảng hocsinh chưa
			HocSinh hocSinhCanSua = hocSinhRepository.getById(hocsinh.getHocSinhID());
			hocSinhCanSua = hocsinh; // alias
			hocSinhRepository.save(hocSinhCanSua);
		}
					
	}

	@Override
	public void moveStudent(int hocsinhid, int lopid) {
//		kiểm tra hoc sinh cần chuyển có tồn tại trong danh sách chưa thông qua id
		HocSinh hocSinhCanChuyen = hocSinhRepository.getById(hocsinhid);
		
//		lấy lớp cũ trước khi chuyển học sinh để cập nhật lại sỉ số sau khi học sinh chuyển đi
		int lopIdCu = hocSinhCanChuyen.getLop().getLopID();
		
//		capNhapSiSo(hocSinhCanChuyen.getLop().getLopID());
		hocSinhCanChuyen.setLop(lopRepository.getById(lopid));
		
		capNhapSiSo(lopIdCu);
		capNhapSiSo(lopid);
		
	}

	@Override
	public void delete(HocSinh hocsinh) {
		int lopIdHienTai = hocsinh.getLop().getLopID();
		hocSinhRepository.delete(hocsinh);
		capNhapSiSo(lopIdHienTai);		
	}

	@Override
	public HocSinh getOne(int hocsinhid) {
		return hocSinhRepository.getById(hocsinhid);
	}

}
