package pl.eiti.marketAdvisor.model;

public class RiskResult{
	private int resultInPennies; 
	private float riskValue;
	boolean isOptimal;
	
	RiskResult(int argResultInPennies, float argRiskValue, boolean argIsOptimal){
		resultInPennies = argResultInPennies;
		riskValue = argRiskValue;
		isOptimal = argIsOptimal;
	}
	
	int getResultInPennies(){ return resultInPennies; }
	float getRiskValue(){ return riskValue; }
	boolean isOptimal(){ return isOptimal; }
}
