package pl.eiti.marketAdvisor.view;

import java.util.concurrent.BlockingQueue;

import pl.eiti.marketAdvisor.common.events.AppEvent;

public class View {
  private final BlockingQueue<AppEvent> eventQueue;
	  
  /**
   * Connects this class to Controller using blockingQueue.
   * @param blockingQueue queue used for communication with Controller class.
   */
  public View(final BlockingQueue<AppEvent> blockingQueue) {
    this.eventQueue = blockingQueue;
  }
}
