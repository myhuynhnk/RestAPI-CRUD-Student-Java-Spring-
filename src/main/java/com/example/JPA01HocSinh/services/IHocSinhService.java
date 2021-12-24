package com.example.JPA01HocSinh.services;

import java.util.List;

import com.example.JPA01HocSinh.model.HocSinh;

public interface IHocSinhService {
	List<HocSinh> getAll();
	HocSinh add(HocSinh hocsinh);
	void update(HocSinh hocsinh);
	void moveStudent(int hocsinhid, int lopid);
	void delete(HocSinh hocsinh);
	HocSinh getOne(int hocsinhid);
	boolean kiemTraSiSo(int lopid);
	boolean kiemTraHocSinh(int hocsinhid);
}
