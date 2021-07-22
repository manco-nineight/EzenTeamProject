package kr.co.domain;

import java.io.Serializable;

public class ProductVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String prodName;
	private int prodBno;
	private String prodCategory;
	private String prodTitle;
	private String prodContent;
	private int prodReadCnt;
	private String prodRegdate;
	private String prodUpdatedate;
	private int prodPrice;
	private String prodThumbnail;
	
	public ProductVO() {
		// TODO Auto-generated constructor stub
	}

	public ProductVO(String prodName, int prodBno, String prodCategory, String prodTitle, String prodContent,
			int prodReadCnt, String prodRegdate, String prodUpdatedate, int prodPrice, String prodThumbnail) {
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
		this.prodThumbnail = prodThumbnail;
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

	public String getprodCategory() {
		return prodCategory;
	}

	public void setprodCategory(String prodCategory) {
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

	public String getProdThumbnail() {
		return prodThumbnail;
	}

	public void setProdThumbnail(String prodThumbnail) {
		this.prodThumbnail = prodThumbnail;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + prodBno;
		result = prime * result + ((prodName == null) ? 0 : prodName.hashCode());
		result = prime * result + ((prodThumbnail == null) ? 0 : prodThumbnail.hashCode());
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
		if (prodName == null) {
			if (other.prodName != null)
				return false;
		} else if (!prodName.equals(other.prodName))
			return false;
		if (prodThumbnail == null) {
			if (other.prodThumbnail != null)
				return false;
		} else if (!prodThumbnail.equals(other.prodThumbnail))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProductVO [prodName=" + prodName + ", prodBno=" + prodBno + ", prodCategory=" + prodCategory
				+ ", prodTitle=" + prodTitle + ", prodContent=" + prodContent + ", prodReadCnt=" + prodReadCnt
				+ ", prodRegdate=" + prodRegdate + ", prodUpdatedate=" + prodUpdatedate + ", prodPrice=" + prodPrice
				+ ", prodThumbnail=" + prodThumbnail + "]";
	}
	
	
}
