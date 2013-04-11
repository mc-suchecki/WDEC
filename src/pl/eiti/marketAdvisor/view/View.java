package pl.eiti.marketAdvisor.view;

import java.util.concurrent.BlockingQueue;
import javax.swing.SwingUtilities;
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
  
  /** Method for showing main window of the game. */
  public void showFrame() {
    SwingUtilities.invokeLater(new Runnable() {
      @Override public void run() {
        frame = new MarketAdvisorFrame();
      }
    });
  }
}