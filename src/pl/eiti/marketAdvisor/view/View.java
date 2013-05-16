package pl.eiti.marketAdvisor.view;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import javax.swing.SwingUtilities;
import pl.eiti.marketAdvisor.common.ChartPoint;
import pl.eiti.marketAdvisor.common.DecisionParameters;
import pl.eiti.marketAdvisor.common.events.AppEvent;

public class View {
  /** References to other classes. */
  private final BlockingQueue<AppEvent> eventQueue;
  private MarketAdvisorFrame frame;
  
  /**
   * Connects this class to Controller using blockingQueue.
   * @param blockingQueue queue used for communication with Controller class.
   */
  public View(final BlockingQueue<AppEvent> blockingQueue) {
    this.eventQueue = blockingQueue;
  }
  
  /** Method for showing main window of the app. */
  public void showFrame() {
    SwingUtilities.invokeLater(new Runnable() {
      @Override public void run() {
        frame = new MarketAdvisorFrame(eventQueue);
      }
    });
  }
  
  /** Method for drawing the chart in app frame. */
  public void drawChart(final ArrayList<ChartPoint> points) {
    frame.drawChart(points);
  }

  /** Method for updating the values in app frame. */
  public void updateDecisionParameters(DecisionParameters decision) {
    frame.updateDecisionParameters(decision);
  }
}