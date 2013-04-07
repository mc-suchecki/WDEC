package marketAdvisor;

public class BusinessDataRiskResultTranslator {
	BusinessData getBussinesDataForChartPoint(RiskResult point){
		// Dump BusinessData
		return new BusinessData(5000,25,80,4000);
	}
	RiskResult getChartPointForBusinessData(BusinessData data){
		// Dump ChartPoint
		return new RiskResult(10000,0.5f,false);
	}

}
