package pl.eiti.marketAdvisor.model;

import java.util.ArrayList;
import matlabcontrol.*;
import pl.eiti.marketAdvisor.common.ChartPoint;

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
  }
  
  /**
   * Method responsible for calculating chart points for
   * particular input data (this means simulation state).
   * @return List of points for chart.
   */
  ArrayList<ChartPoint> getChartPoints(int volumeInDollars) {
      try{
		  return chartPointsGenerator.getChartPoints(volumeInDollars);
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
   * The method computes the chart point that satisfies constraints of desired decision.
   * @param decision Desired decision.
   * @return Point on chart associated with given decision. 
   */
  /*ChartPoint getChartPointForDecision(DecisionParameters decision) {
	  return decisionPointTranslator.getChartPoint(decision);
  }
  */
  /**
   * The method computes the chart point that satisfies constraints of desired decision.
   * @param point Point selected by user on the chart.
   * @return Decision parameters that will result in given point (result and risk).
   */
  /*DecisionParameters getDecisionForChartPoint(ChartPoint point) {
  	return decisionPointTranslator.getDecision(point);
  }*/

}