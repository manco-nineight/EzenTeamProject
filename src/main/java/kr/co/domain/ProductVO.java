package kr.co.domain;

import java.io.Serializable;

public class ProductVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String prodName;
	private int prodBno;
	private int prodCategory;
	private String prodTitle;
	private String prodContent;
	private int prodReadCnt;
	private String prodRegdate;
	private String prodUpdatedate;
	private int prodPrice;
	private int prodSalesCount;

	public ProductVO() {
		// TODO Auto-generated constructor stub
	}

	public ProductVO(String prodName, int prodBno, int prodCategory, String prodTitle, String prodContent,
			int prodReadCnt, String prodRegdate, String prodUpdatedate, int prodPrice, int prodSalesCount) {
		super();
		this.prodName = prodName;
		this.prodBno = prodBno;
		this.prodCategory = prodCategory;
		this.prodTitle = prodTitle;
		this.prodContent = prodContent;
		this.prodReadCnt = prodReadCnt;
		this.prodRegdate = prodRegdate;
		this.prodUpdatedate = prodUpdatedate;
		this.prodPrice = prodPrice;
		this.prodSalesCount = prodSalesCount;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public int getProdBno() {
		return prodBno;
	}

	public void setProdBno(int prodBno) {
		this.prodBno = prodBno;
	}

	public int getProdCategory() {
		return prodCategory;
	}

	public void setProdCategory(int prodCategory) {
		this.prodCategory = prodCategory;
	}

	public String getProdTitle() {
		return prodTitle;
	}

	public void setProdTitle(String prodTitle) {
		this.prodTitle = prodTitle;
	}

	public String getProdContent() {
		return prodContent;
	}

	public void setProdContent(String prodContent) {
		this.prodContent = prodContent;
	}

	public int getProdReadCnt() {
		return prodReadCnt;
	}

	public void setProdReadCnt(int prodReadCnt) {
		this.prodReadCnt = prodReadCnt;
	}

	public String getProdRegdate() {
		return prodRegdate;
	}

	public void setProdRegdate(String prodRegdate) {
		this.prodRegdate = prodRegdate;
	}

	public String getProdUpdatedate() {
		return prodUpdatedate;
	}

	public void setProdUpdatedate(String prodUpdatedate) {
		this.prodUpdatedate = prodUpdatedate;
	}

	public int getProdPrice() {
		return prodPrice;
	}

	public void setProdPrice(int prodPrice) {
		this.prodPrice = prodPrice;
	}

	public int getProdSalesCount() {
		return prodSalesCount;
	}

	public void setProdSalesCount(int prodSalesCount) {
		this.prodSalesCount = prodSalesCount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + prodBno;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductVO other = (ProductVO) obj;
		if (prodBno != other.prodBno)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProductVO [prodName=" + prodName + ", prodBno=" + prodBno + "]";
	}
	
	
}
