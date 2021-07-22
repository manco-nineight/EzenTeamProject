package kr.co.domain;

import java.io.Serializable;

public class ProductStockVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String prodName;
	private int prodStockSSize;
	private int prodStockMSize;
	private int prodStockLSize;
	
	private ProductVO productVO;
	
	public ProductStockVO() {
		// TODO Auto-generated constructor stub
	}

	public ProductStockVO(String prodName, int prodStockSSize, int prodStockMSize, int prodStockLSize, ProductVO productVO) {
		super();
		this.prodName = prodName;
		this.prodStockSSize = prodStockSSize;
		this.prodStockMSize = prodStockMSize;
		this.prodStockLSize = prodStockLSize;
		this.productVO = productVO;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
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
	

	public ProductVO getProductVO() {
		return productVO;
	}

	public void setProductVO(ProductVO productVO) {
		this.productVO = productVO;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ProductStockVO [prodName=" + prodName + ", prodStockSSize=" + prodStockSSize + ", prodStockMSize="
				+ prodStockMSize + ", prodStockLSize=" + prodStockLSize + ", productVO=" + productVO + "]";
	}
	
	
	
}
