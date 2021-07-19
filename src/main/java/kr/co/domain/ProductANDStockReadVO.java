package kr.co.domain;

public class ProductANDStockReadVO {
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
	private String prodStockName;
	private int prodStockSSzie;
	private int prodStockMSize;
	private int prodStockLSize;

	public ProductANDStockReadVO() {
		// TODO Auto-generated constructor stub
	}

	public ProductANDStockReadVO(String prodName, int prodBno, int prodCategory, String prodTitle, String prodContent,
			int prodReadCnt, String prodRegdate, String prodUpdatedate, int prodPrice, int prodSalesCount,
			String prodStockName, int prodStockSSzie, int prodStockMSize, int prodStockLSize) {
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
		this.prodStockName = prodStockName;
		this.prodStockSSzie = prodStockSSzie;
		this.prodStockMSize = prodStockMSize;
		this.prodStockLSize = prodStockLSize;
	}

	public String getProdName() {
		return prodName;
	}

	public int getProdBno() {
		return prodBno;
	}

	public int getProdCategory() {
		return prodCategory;
	}

	public String getProdTitle() {
		return prodTitle;
	}

	public String getProdContent() {
		return prodContent;
	}

	public int getProdReadCnt() {
		return prodReadCnt;
	}

	public String getProdRegdate() {
		return prodRegdate;
	}

	public String getProdUpdatedate() {
		return prodUpdatedate;
	}

	public int getProdPrice() {
		return prodPrice;
	}

	public int getProdSalesCount() {
		return prodSalesCount;
	}

	public String getProdStockName() {
		return prodStockName;
	}

	public int getProdStockSSzie() {
		return prodStockSSzie;
	}

	public int getProdStockMSize() {
		return prodStockMSize;
	}

	public int getProdStockLSize() {
		return prodStockLSize;
	}

}
