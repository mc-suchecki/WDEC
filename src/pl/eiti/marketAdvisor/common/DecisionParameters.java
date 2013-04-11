package pl.eiti.marketAdvisor.common;

/**
 * @author Jakub Świątkowski
 * Data structure representing parameters defining decision in
 * the simulation. Contains also risk and revenue associated with
 * this decision to simplify communication and reduce calculations.
 */
public class DecisionParameters {

  private final int productVolume;
  private final int productPrice;
  private final int productQuality;
  private final int internetAdvertising;
  private final int magazineAdvertising;
  private final int tvAdvertising;
  private final double risk;
  private final int revenue;

  /** Constructor - initialises values. */
  public DecisionParameters(int volume, int price, int quality, int internetAds,
		  int magazineAds, int tvAds, double risk, int revenue) {
    this.productVolume = volume;
    this.productPrice = price;
    this.productQuality = quality;
    this.internetAdvertising = internetAds;
    this.magazineAdvertising = magazineAds;
    this.tvAdvertising = tvAds;
    this.risk = risk;
    this.revenue = revenue;
  }
  
  /**
	 * @return the productVolume
	 */
	public int getProductVolume() {
		return productVolume;
	}

	/**
	 * @return the productPrice
	 */
	public int getProductPrice() {
		return productPrice;
	}

	/**
	 * @return the productQuality
	 */
	public int getProductQuality() {
		return productQuality;
	}

	/**
	 * @return the internetAdvertising
	 */
	public int getInternetAdvertising() {
		return internetAdvertising;
	}

	/**
	 * @return the magazineAdvertising
	 */
	public int getMagazineAdvertising() {
		return magazineAdvertising;
	}

	/**
	 * @return the tvAdvertising
	 */
	public int getTvAdvertising() {
		return tvAdvertising;
	}

	/**
	 * @return the risk
	 */
	public double getRisk() {
		return risk;
	}

	/**
	 * @return the revenue
	 */
	public int getRevenue() {
		return revenue;
	}
}
