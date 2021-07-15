package kr.co.domain;

import java.io.Serializable;

public class ProdStockDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String prodName;
	private int prodStockSSize;
	private int prodStockMSize;
	private int prodStockLSize;

	public ProdStockDTO() {
		// TODO Auto-generated constructor stub
	}

	public ProdStockDTO(String prodName, int prodStockSSize, int prodStockMSize, int prodStockLSize) {
		super();
		this.prodName = prodName;
		this.prodStockSSize = prodStockSSize;
		this.prodStockMSize = prodStockMSize;
		this.prodStockLSize = prodStockLSize;
	}

	public String getprodName() {
		return prodName;
	}

	public void setprodName(String prodName) {
		this.prodName = prodName;
	}

	public int getProdStockSSize() {
		return prodStockSSize;
	}

	public void setProdStockSSize(int prodStockSSize) {
		this.prodStockSSize = prodStockSSize;
	}

	public int getProdStockMSize() {
		return prodStockMSize;
	}

	public void setProdStockMSize(int prodStockMSize) {
		this.prodStockMSize = prodStockMSize;
	}

	public int getProdStockLSize() {
		return prodStockLSize;
	}

	public void setProdStockLSize(int prodStockLSize) {
		this.prodStockLSize = prodStockLSize;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
