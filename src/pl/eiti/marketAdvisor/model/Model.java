package pl.eiti.marketAdvisor.model;

import java.util.ArrayList;

import pl.eiti.marketAdvisor.common.ChartPoint;
import pl.eiti.marketAdvisor.common.DecisionParameters;

/**
 * @author Maciej 'mc' Suchecki
 * Class representing model from MVC pattern - responsible for doing all of the calculations.
 */
public class Model {
  /** References to other classes. */
	private final DecisionPointTranslator decisionPointTranslator;
  private final ChartPointsGenerator chartPointsGenerator;

  /** Model constructor - creates needed objects. */
  public Model() {
    this.decisionPointTranslator = new DecisionPointTranslator();
    this.chartPointsGenerator = new ChartPointsGenerator();
  }
  
  /**
   * Method responsible for calculating chart points for
   * particular input data (this means simulation state).
   * @return List of points for chart.
   */
  ArrayList<ChartPoint> getChartPoints(/*InputData inputData*/) {
    // TODO pass initial state here
	  return chartPointsGenerator.getChartPoints(/*InputData inputData*/);
  }
  
  /**
   * The method computes the chart point that satisfies constraints of desired decision.
   * @param decision Desired decision.
   * @return Point on chart associated with given decision. 
   */
  ChartPoint getChartPointForDecision(DecisionParameters decision) {
	  return decisionPointTranslator.getChartPoint(decision);
  }
  
  /**
   * The method computes the chart point that satisfies constraints of desired decision.
   * @param point Point selected by user on the chart.
   * @return Decision parameters that will result in given point (result and risk).
   */
  DecisionParameters getDecisionForChartPoint(ChartPoint point) {
  	return decisionPointTranslator.getDecision(point);
  }

}