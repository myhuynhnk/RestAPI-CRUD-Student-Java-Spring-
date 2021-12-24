package com.example.JPA01HocSinh.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JPA01HocSinh.model.Lop;
import com.example.JPA01HocSinh.repository.ILopRepository;
import com.example.JPA01HocSinh.services.ILopService;

@Service
public class LopService implements ILopService {
	@Autowired
	ILopRepository lopRepository;
	@Override
	public boolean kiemTraLop(int lopid) {
		Optional<Lop> optional = Optional.empty();
		if(lopRepository.findById(lopid) != optional)
			return true;
		return false;
	}
	
	@Override
	public List<Lop> getAll() {
		return lopRepository.findAll();	
	}

}
