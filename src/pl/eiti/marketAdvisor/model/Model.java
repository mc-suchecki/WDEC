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
  private final DecisionPointTranslator decisionPointTranslator;
  
  /** Model constructor - creates needed objects. */
  public Model() {
    this.chartPointsGenerator = new ChartPointsGenerator();
    this.decisionPointTranslator = new DecisionPointTranslator();
  }
  
  /**
   * Method responsible for calculating chart points for
   * particular input data (this means simulation state).
   * @return List of points for chart.
   */
  public ArrayList<ChartPoint> getChartPoints(int volumeInDollars, int pointsNumber) {
    try {
		  ArrayList<ChartPoint> chartPoints = chartPointsGenerator.getChartPoints(volumeInDollars, pointsNumber);
		  decisionPointTranslator.setActiveChartPoints(chartPoints);
		  return chartPoints;
	  }
	  catch(MatlabConnectionException e){
		  e.printStackTrace();
		  return new ArrayList<ChartPoint>();
	  }
	  catch(MatlabInvocationException e){
		  e.printStackTrace();
		  return new ArrayList<ChartPoint>();
	  }  
  }
  
  /**
   * The method computes the chart point for given decisions parameters
   * @param decision Desired parameters.
   * @return Point on chart associated with given parameters. 
   */
  public ChartPoint getChartPointForDecisions(DecisionParameters decision) {
	  try {
	  	return decisionPointTranslator.getChartPoint(decision);		 
	  }
	  catch(MatlabConnectionException e) {
		  e.printStackTrace();
		  return null;
	  }
	  catch(MatlabInvocationException e) {
		  e.printStackTrace();
		  return null;
	  } 
}
  
  /**
   * The method finds the chart point that is placed nearest the clicked point
   * on the chart along with decision associated with it.
   * @param point Point selected by user on the chart.
   * @return Decision parameters that will result in given point (result and risk).
   */
  public DecisionParameters getDecisionForChartPoint(double x, double y) {
  	return decisionPointTranslator.getDecision(x, y);
  }
}