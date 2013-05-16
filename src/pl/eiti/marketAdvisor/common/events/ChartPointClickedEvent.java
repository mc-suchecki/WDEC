/**
 * 
 */
package pl.eiti.marketAdvisor.common.events;

/**
 * @author mc
 * Class representing event of clicking the point on a chart by user.
 */
public class ChartPointClickedEvent extends AppEvent {
  private final double xCoordinateOfPoint;
  private final double yCoordinateOfPoint;
  
  public ChartPointClickedEvent(double xCoordinate, double yCoordinate) {
    this.xCoordinateOfPoint = xCoordinate;
    this.yCoordinateOfPoint = yCoordinate;
  }

  /** @return the xCoordinateOfPoint*/
  public double getXCoordinateOfPoint() {
    return xCoordinateOfPoint;
  }
  
  /** @return the yCoordinateOfPoint*/
  public double getYCoordinateOfPoint() {
    return yCoordinateOfPoint;
  }
}
