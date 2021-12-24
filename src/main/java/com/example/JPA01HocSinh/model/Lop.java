package com.example.JPA01HocSinh.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table
public class Lop {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int lopID;
	
	@Column
	@Size(max = 10, message = "Ten lop khong duoc qua 10 ky tu")
	private String tenLop;
	
	@Column
	@Max(value = 20, message = "Si so toi da 20 hoc sinh")
	private int siSo;
	
	@OneToMany(mappedBy = "lop")
//	@JsonManagedReference
	@JsonIgnoreProperties(value="lop")
	private List<HocSinh> listHocSinh;

	public int getLopID() {
		return lopID;
	}

	public void setLopID(int lopID) {
		this.lopID = lopID;
	}

	public String getTenLop() {
		return tenLop;
	}

	public void setTenLop(String tenLop) {
		this.tenLop = tenLop;
	}

	public int getSiSo() {
		return siSo;
	}

	public void setSiSo(int siSo) {
		this.siSo = siSo;
	}

	public List<HocSinh> getListHocSinh() {
		return listHocSinh;
	}

	public void setListHocSinh(List<HocSinh> listHocSinh) {
		this.listHocSinh = listHocSinh;
	}
	
	
}
