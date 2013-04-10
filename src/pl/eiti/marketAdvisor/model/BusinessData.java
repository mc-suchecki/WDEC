package pl.eiti.marketAdvisor.model;

public class BusinessData {
	private int productVolume;
	private int productPrice;
	private int productQuality;
	private int advertisingCosts;

	
	BusinessData(int argProductVolume, int argProductPrice, int argProductQuality, int argAdvertisingCosts){
		productVolume = argProductVolume;	
		productPrice = argProductPrice;
		productQuality = argProductQuality;
		advertisingCosts = argAdvertisingCosts;
	}


	public int getProductVolume() {
		return productVolume;
	}


	public void setProductVolume(int productVolume) {
		this.productVolume = productVolume;
	}


	public int getProductPrice() {
		return productPrice;
	}


	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}


	public int getProductQuality() {
		return productQuality;
	}


	public void setProductQuality(int productQuality) {
		this.productQuality = productQuality;
	}


	public int getAdvertisingCosts() {
		return advertisingCosts;
	}


	public void setAdvertisingCosts(int advertisingCosts) {
		this.advertisingCosts = advertisingCosts;
	}
	
}
