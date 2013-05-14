package pl.eiti.marketAdvisor.common;

/**
 * @author Jakub Swiatkowski
 * Data structure representing point on the risk-result chart.
 */
public class ChartPoint {
	private final int result; 
	private final double riskValue;
	private final int priceInPennies;
	private final int quality;
	private final int internetAdv;
	private final int magazineAdv;
	private final int tvAdv;
	
	/** Default constructor */
	public ChartPoint(){
		this.result = 0;
		this.riskValue = 0;
		this.priceInPennies = 0;
		this.quality = 0;
		this.internetAdv = 0;
		this.magazineAdv = 0;
		this.tvAdv = 0;
	}
	
	/** Constructor - initializes values. */
	public ChartPoint(int result, double riskValue, double price, double quality, double internetAdv, double magazineAdv, double tvAdv){
		this.result = result;
		this.riskValue = riskValue;
		this.priceInPennies = (int)(price*100);
		this.quality = (int)quality;
		this.internetAdv = (int)internetAdv;
		this.magazineAdv = (int)magazineAdv;
		this.tvAdv = (int)tvAdv;
	}

	public int getResult() {
		return result;
	}

	public double getRiskValue() {
		return riskValue;
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
