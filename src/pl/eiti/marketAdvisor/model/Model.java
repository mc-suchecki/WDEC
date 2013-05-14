package pl.eiti.marketAdvisor.model;

import java.util.ArrayList;
import matlabcontrol.*;
import pl.eiti.marketAdvisor.common.ChartPoint;
import pl.eiti.marketAdvisor.common.DecisionParameters;

/**
 * @author Maciej 'mc' Suchecki
 * Class representing model from MVC pattern - responsible for doing all of the calculations.
 */
public class Model {
  /** References to other classes. */
  private final ChartPointsGenerator chartPointsGenerator;

  /** Model constructor - creates needed objects. */
  public Model() {
    this.chartPointsGenerator = new ChartPointsGenerator();
    //getChartPoints(1000,25);
    ChartPoint point = getChartPointForDecisions(new DecisionParameters(20000, 34, 75, 400, 0, 0));
    System.out.println(point);
  }
  
  /**
   * Method responsible for calculating chart points for
   * particular input data (this means simulation state).
   * @return List of points for chart.
   */
  ArrayList<ChartPoint> getChartPoints(int volumeInDollars, int pointsNumber) {
      try{
		  return chartPointsGenerator.getChartPoints(volumeInDollars, pointsNumber);
	  }
	  catch( MatlabConnectionException e){
		  e.printStackTrace();
		  return new ArrayList<ChartPoint>();
	  }
	  catch( MatlabInvocationException e){
		  e.printStackTrace();
		  return new ArrayList<ChartPoint>();
	  }  
  }
  
  /**
   * The method computes the chart point for given decisions parameters
   * @param decision Desired parameters.
   * @return Point on chart associated with given parameters. 
   */
  ChartPoint getChartPointForDecisions(DecisionParameters decisions) {
	  try{
		 return chartPointsGenerator.getChartPointForDecisions(decisions);		 
	  }
	  catch( MatlabConnectionException e){
		  e.printStackTrace();
		  return new ChartPoint();
	  }
	  catch( MatlabInvocationException e){
		  e.printStackTrace();
		  return null;
	  } 
}
  
  /**
   * The method computes the chart point that satisfies constraints of desired decision.
   * @param point Point selected by user on the chart.
   * @return Decision parameters that will result in given point (result and risk).
   */
  /*DecisionParameters getDecisionForChartPoint(ChartPoint point) {
  	return decisionPointTranslator.getDecision(point);
  }*/
}