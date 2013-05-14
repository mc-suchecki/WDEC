package pl.eiti.marketAdvisor.common;

public class DecisionParameters {
	private final int volume;
	private final int priceInPennies;
	private final int quality;
	private final int internetAdv;
	private final int magazineAdv;
	private final int tvAdv;
	
	/** Constructor - initializes values. */
	public DecisionParameters(int volume, double price, double quality, double internetAdv, double magazineAdv, double tvAdv){
		this.volume = volume;
		this.priceInPennies = (int)(price);
		this.quality = (int)quality;
		this.internetAdv = (int)internetAdv;
		this.magazineAdv = (int)magazineAdv;
		this.tvAdv = (int)tvAdv;
	}

	public int getVolume() {
		return volume;
	}
	public int getPriceInPennies() {
		return priceInPennies;
	}

	public int getQuality() {
		return quality;
	}

	public int getInternetAdv() {
		return internetAdv;
	}

	public int getMagazineAdv() {
		return magazineAdv;
	}

	public int getTvAdv() {
		return tvAdv;
	}

}