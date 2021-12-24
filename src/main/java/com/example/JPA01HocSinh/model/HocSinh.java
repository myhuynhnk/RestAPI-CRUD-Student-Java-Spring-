package com.example.JPA01HocSinh.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table
public class HocSinh {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int hocSinhID;
	
	@Column
	@NotNull
	@Size(max = 20, message = "Ten khong duoc qua 20 ky tu")
	private String hoTen;
	
	@Column
	@Min(value = 2002, message ="Nam sinh phai trong khoang 2002 - 2014")
	@Max(value = 2014, message ="Nam sinh phai trong khoang 2002 - 2014")
	private int namSinh;
	
	@Column
	private String queQuan;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name ="lopID", foreignKey = @ForeignKey(name = "fk_lop_hocsinh"))
//	@JsonBackReference
	@JsonIgnoreProperties(value ="listHocSinh")
	private Lop lop;

	public int getHocSinhID() {
		return hocSinhID;
	}

	public void setHocSinhID(int hocSinhID) {
		this.hocSinhID = hocSinhID;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public int getNamSinh() {
		return namSinh;
	}

	public void setNamSinh(int namSinh) {
		this.namSinh = namSinh;
	}

	public String getQueQuan() {
		return queQuan;
	}

	public void setQueQuan(String queQuan) {
		this.queQuan = queQuan;
	}

	public Lop getLop() {
		return lop;
	}

	public void setLop(Lop lop) {
		this.lop = lop;
	}
	
	
	
	
}
