package pl.eiti.marketAdvisor.common;

/**
 * @author Jakub Świątkowski
 * Data structure representing point on the risk-result chart.
 */
public class ChartPoint {
  
	private final int resultInPennies; 
	private final float riskValue;
	private final boolean isOptimal;
	
	/** Constructor - initialises values. */
	public ChartPoint(int argResultInPennies, float argRiskValue, boolean argIsOptimal){
		resultInPennies = argResultInPennies;
		riskValue = argRiskValue;
		isOptimal = argIsOptimal;
	}
	
	/** Getters for data stored in object. */
	int getResultInPennies(){ return resultInPennies; }
	float getRiskValue(){ return riskValue; }
	boolean isOptimal(){ return isOptimal; }
}
