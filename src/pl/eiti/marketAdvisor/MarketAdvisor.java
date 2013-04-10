package pl.eiti.marketAdvisor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import pl.eiti.marketAdvisor.common.events.AppEvent;
import pl.eiti.marketAdvisor.controller.Controller;
import pl.eiti.marketAdvisor.model.Model;
import pl.eiti.marketAdvisor.view.View;

/**
 * @author Maciej 'mc' Suchecki
 * Market advisor application main class - creates objects from MVC pattern and links them.
 */
public class MarketAdvisor {
  public static void main(String[] args) {
    try {
      //create and link MVC objects
      final BlockingQueue<AppEvent> eventQueue = new LinkedBlockingQueue<AppEvent>();
      final Model model = new Model();
      final View view = new View(eventQueue);
      final Controller controller = new Controller(view, model, eventQueue);
      
      //create and run controller thread
      final Thread controllerThread = new Thread() {
        public void run() {
		  controller.run();
	    }
	  };
      controllerThread.run();

	} catch(Exception e) {
      e.printStackTrace();
      System.exit(1);
	}
  }
}