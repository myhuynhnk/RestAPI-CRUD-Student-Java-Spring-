package com.example.JPA01HocSinh.services;

import java.util.List;

import com.example.JPA01HocSinh.model.Lop;

public interface ILopService {
	boolean kiemTraLop(int lopid);
	List<Lop> getAll();
}
