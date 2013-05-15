package pl.eiti.marketAdvisor.controller;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;

import pl.eiti.marketAdvisor.common.ChartPoint;
import pl.eiti.marketAdvisor.common.events.AppEvent;
import pl.eiti.marketAdvisor.common.events.CalculateButtonPressedEvent;
import pl.eiti.marketAdvisor.model.Model;
import pl.eiti.marketAdvisor.view.View;

/**
 * @author Maciej 'mc' Suchecki
 * Controller class (MVC pattern implementation) - responsible for controlling
 * application events and determining what to do in particular situations.
 */
public class Controller implements Runnable {
  /** References to other classes. */
  private final BlockingQueue<AppEvent> eventQueue;
  private final Model model;
  private final View view;
  
  /** Map associating AppEvents with appropriate actions. */
  private final Map<Class<? extends AppEvent>, AppAction> eventActionMap;
  
  /** Controller class constructor. */
  public Controller(final View view, final Model model, final BlockingQueue<AppEvent> queue) {
    //creating and connecting to another objects
    eventQueue = queue;
    this.model = model;
    this.view = view;
    
    //filling events-actions map
    eventActionMap = new HashMap<Class<? extends AppEvent>, AppAction>();
    fillEventActionMap();
  }
  
  /** Main Controller method - responsible for reading objects
   *  from the eventQueue and handling AppEvents found in it
   *  by executing AppAction associated with particular AppEvent. */
  public void run() {
	view.showFrame();
    while(true) {
      try {
        AppEvent event = eventQueue.take();
        AppAction appAction = eventActionMap.get(event.getClass());
        appAction.execute(event);
      } catch(Exception e) {
        e.printStackTrace();
        throw new RuntimeException(e);
      }
    }
  }
 
  /** Method responsible for filling eventActionMap container. */
  private void fillEventActionMap() {
    //handling calculate button pressed event
    eventActionMap.put(CalculateButtonPressedEvent.class, new AppAction() {
      @Override public void execute(AppEvent e) {
        CalculateButtonPressedEvent event = (CalculateButtonPressedEvent) e;
        ArrayList<ChartPoint> pointsList = model.getChartPoints(event.getVolumeInDollars(), 10);
        view.drawChart(pointsList);
      }
    });
    //TODO
  }
}