package marketAdvisor;

import java.util.ArrayList;


public class MarketRiskResultCollector {
	ArrayList<RiskResult>  getRiskResultChart(){
		ArrayList<RiskResult> list= new ArrayList<RiskResult>();
		list.add(new RiskResult(10000,0.5f,false));
		list.add(new RiskResult(20000,0.9f,false));
		list.add(new RiskResult(15000,0.7f,true));
		list.add(new RiskResult(16000,0.8f,false));
		list.add(new RiskResult(5000,0.2f,false));
		list.add(new RiskResult(8000,0.3f,false));
		list.add(new RiskResult(11000,0.55f,false));
		return list;
	}
}
