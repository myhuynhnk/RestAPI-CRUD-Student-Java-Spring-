package com.example.JPA01HocSinh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.JPA01HocSinh.model.Lop;

@Repository
public interface ILopRepository extends JpaRepository<Lop, Integer> {

}
