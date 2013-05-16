package pl.eiti.marketAdvisor.model;

import java.util.ArrayList;

import matlabcontrol.MatlabConnectionException;
import matlabcontrol.MatlabInvocationException;
import matlabcontrol.MatlabProxy;
import pl.eiti.marketAdvisor.common.ChartPoint;
import pl.eiti.marketAdvisor.common.DecisionParameters;

/**
 * @author Jakub Świątkowski
 * Class responsible for calculating points from decisions and the other way.
 */
public class DecisionPointTranslator {

  /** Container with previously calculated points. */
  private ArrayList<ChartPoint> chartPoints;

  /**
   * Method for setting the data set for active chart.
   * @param chartPoints the chartPoints to set
   */
  public void setActiveChartPoints(final ArrayList<ChartPoint> chartPoints) {
    this.chartPoints = chartPoints;
  }

  /**
   * The method computes the chart point for given decisions parameters
   * @param decision Desired parameters.
   * @return Point on chart associated with given parameters. 
   */
  ChartPoint getChartPoint(final DecisionParameters decision) throws MatlabConnectionException, MatlabInvocationException {

    int nmbrOfReturnedVals = 2;
    String matlabCommand = "compute_risk_and_result";	
    double[] decisions = {decision.getPriceInPennies(),decision.getQuality(),decision.getInternetAdv(),decision.getMagazineAdv(),decision.getTvAdv(),decision.getVolume()}; 
    Object[] argument = {decisions};

    MatlabProxy proxy = MatlabProxySingleton.getInstance();
    Object[] returnedValues = proxy.returningFeval(matlabCommand, nmbrOfReturnedVals, argument);

    double[] riskTab = (double []) returnedValues[0];
    double[] resultTab = (double []) returnedValues[1];

    double risk = riskTab[0];
    int result = (int)resultTab[0];

    return new ChartPoint(result,risk,decision.getPriceInPennies(),decision.getQuality(),decision.getInternetAdv(),decision.getMagazineAdv(),decision.getTvAdv());
  }

  /**
   * Finds point with nearest coordinates to clicked and returns decision parameters associated with it.
   * @param point Point which user is willing to achieve.
   * @return Decision parameters which result in given point.
   */
  DecisionParameters getDecision(final double x, final double y) {
    double min = Double.MAX_VALUE;
    ChartPoint closest = null;

    //find nearest point by looking at result
    for(ChartPoint point : chartPoints) {
      final double diff = Math.abs(point.getResult() - y);
      if (diff < min) {
        min = diff;
        closest = point;
      }
    }
    
    return new DecisionParameters(closest.getResult(), closest.getPriceInPennies(), closest.getQuality(), closest.getInternetAdv(), closest.getMagazineAdv(), closest.getTvAdv());
  }

}
