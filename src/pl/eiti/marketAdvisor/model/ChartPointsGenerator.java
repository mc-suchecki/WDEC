package pl.eiti.marketAdvisor.model;

import java.util.ArrayList;

import pl.eiti.marketAdvisor.common.ChartPoint;

/**
 * @author Jakub Świątkowski
 * Class responsible for generating points for risk-revenue chart.
 */
public class ChartPointsGenerator {
  
	/**
	 * @return List of points for drawing the chart.
	 */
	ArrayList<ChartPoint> getChartPoints() {
	  // TODO
		ArrayList<ChartPoint> list= new ArrayList<ChartPoint>();
		list.add(new ChartPoint(10000,0.5f,false));
		list.add(new ChartPoint(20000,0.9f,false));
		list.add(new ChartPoint(15000,0.7f,true));
		list.add(new ChartPoint(16000,0.8f,false));
		list.add(new ChartPoint(5000,0.2f,false));
		list.add(new ChartPoint(8000,0.3f,false));
		list.add(new ChartPoint(11000,0.55f,false));
		return list;
	}
}
