package pl.eiti.marketAdvisor.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import pl.eiti.marketAdvisor.common.ChartPoint;
import pl.eiti.marketAdvisor.common.DecisionParameters;
import pl.eiti.marketAdvisor.common.events.AppEvent;
import pl.eiti.marketAdvisor.common.events.CalculateButtonPressedEvent;
import pl.eiti.marketAdvisor.common.events.ChartPointClickedEvent;

/**
 * @author Maciej 'mc' Suchecki
 * Class representing main window of an application.
 */
public class MarketAdvisorFrame extends JFrame {
  private static final long serialVersionUID = 1L;
  private int volumeSettingForLastPlot;
  private final JButton bottomButton;
  private final JLabel statusLabel;
  private final JFXPanel fxPanel;
  private final JTabbedPane tabbedPane;
  private final BlockingQueue<AppEvent> eventQueue;
  
  //spinners displaying decision parameters
  private JSpinner quantitySpinner;
  private JSpinner qualitySpinner;
  private JSpinner priceSpinner;
  private JSpinner tvAdsSpinner;
  private JSpinner internetAdsSpinner;
  private JSpinner newspaperAdsSpinner;

  /**
   * Main window constructor.
   * @param queue BlockingQueue used for communication with Controller class.
   */
  MarketAdvisorFrame(BlockingQueue<AppEvent> queue) {
    //setting window parameters
    super("Market Advisor");
    setFocusable(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //acquiring upper View class reference
    this.eventQueue = queue;

    //setting tabs and layout
    setLayout(new BorderLayout());
    JPanel bottom = new JPanel();
    bottom.setLayout(new BorderLayout());
    add(BorderLayout.SOUTH, bottom);
    tabbedPane = new JTabbedPane();
    add(BorderLayout.CENTER, tabbedPane);

    //setting up first tab
    JPanel initialValuesPanel = new JPanel();
    tabbedPane.addTab("Initial values", initialValuesPanel);
    initialValuesPanel.setLayout(new BorderLayout());
    JPanel centerPanel = new JPanel();
    initialValuesPanel.add(BorderLayout.CENTER, centerPanel);
    centerPanel.add(new JLabel("Volume:"));         //init min max  step
    SpinnerModel volumeModel = new SpinnerNumberModel(100, 1, 100000, 1);
    final JSpinner volumeSpinner = new JSpinner(volumeModel);
    centerPanel.add(volumeSpinner);

    //setting up second tab
    JPanel chartPanel = new JPanel();
    tabbedPane.addTab("Chart", chartPanel);
    tabbedPane.setEnabledAt(1, false);
    chartPanel.setLayout(new BorderLayout());
    JPanel leftPanel = new JPanel();
    JPanel decisionParamsPanel = new JPanel();
    leftPanel.add(Box.createVerticalGlue());
    leftPanel.add(decisionParamsPanel);
    leftPanel.add(Box.createVerticalGlue());
    fxPanel = new JFXPanel();
    chartPanel.add(BorderLayout.EAST, leftPanel);
    chartPanel.add(BorderLayout.WEST, fxPanel);
    decisionParamsPanel.setLayout(new GridLayout(6, 2));
    decisionParamsPanel.add(new JLabel("Quantity:")); //init min max  step
    SpinnerModel quantityModel = new SpinnerNumberModel(100, 1, 1000, 1);
    quantitySpinner = new JSpinner(quantityModel);
    decisionParamsPanel.add(quantitySpinner);
    decisionParamsPanel.add(new JLabel("Quality"));
    SpinnerModel qualityModel = new SpinnerNumberModel(50, 1, 100, 1);
    qualitySpinner = new JSpinner(qualityModel);
    decisionParamsPanel.add(qualitySpinner);
    decisionParamsPanel.add(new JLabel("Price:"));
    SpinnerModel priceModel = new SpinnerNumberModel(10, 1, 100, 1);
    priceSpinner = new JSpinner(priceModel);
    decisionParamsPanel.add(priceSpinner);
    SpinnerModel adsModel = new SpinnerNumberModel(0, 0, 100000, 1000);
    decisionParamsPanel.add(new JLabel("Television ads:"));
    tvAdsSpinner = new JSpinner(adsModel);
    decisionParamsPanel.add(tvAdsSpinner);
    decisionParamsPanel.add(new JLabel("Internet ads:"));
    internetAdsSpinner = new JSpinner(adsModel);
    decisionParamsPanel.add(internetAdsSpinner);
    decisionParamsPanel.add(new JLabel("Newspapers ads:"));
    newspaperAdsSpinner = new JSpinner(adsModel);
    decisionParamsPanel.add(newspaperAdsSpinner);

    //creating bottom widgets
    bottom.add(Box.createRigidArea(new Dimension(5, 0)));
    statusLabel = new JLabel("Input values and click 'Calculate' to continue.");
    bottom.add(BorderLayout.LINE_START, statusLabel);
    bottom.add(Box.createRigidArea(new Dimension(5, 0)));
    bottomButton = new JButton("Calculate");
    bottom.add(BorderLayout.LINE_END, bottomButton);
    bottom.add(Box.createRigidArea(new Dimension(5, 0)));
    bottomButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        statusLabel.setText("Calculating...");
        volumeSettingForLastPlot = (int) volumeSpinner.getValue();
        CalculateButtonPressedEvent event = new CalculateButtonPressedEvent(volumeSettingForLastPlot);
        eventQueue.add(event);
      }
    });

    //configuring look
    setResizable(true);
    setSize(1100, 600);
    setVisible(true);
    setLocationRelativeTo(null);
  }

  /**
   * Method for drawing the chart from given points and displaying it.
   * @param points ArrayList of data to draw.
   */
  public void drawChart(final ArrayList<ChartPoint> points) {
    Platform.runLater(new Runnable() {
      @Override public void run() {
        BorderPane pane = new BorderPane();
        Scene scene = new Scene(pane, 800, 600);

        //configuring the chart
        final NumberAxis xAxis = new NumberAxis(0.0, 1.0, 0.1);
        final NumberAxis yAxis = new NumberAxis(11.43*volumeSettingForLastPlot, 18.1*volumeSettingForLastPlot, 100.0);
        xAxis.setLabel("Risk");
        yAxis.setLabel("Result");
        final ScatterChart<Number, Number> scatterChart = new ScatterChart<>(xAxis, yAxis);
        scatterChart.setTitle("Optimal risk/result pairs");
        scatterChart.setLegendVisible(false);
        Series<Number, Number> series = new XYChart.Series<>();
        series.setName("pairs");

        //TODO remove this
        //XYChart.Data<Number, Number> samplePoint = new XYChart.Data<Number, Number>(0.5, 1500);
        //series.getData().add(samplePoint);
        
        //drawing recieved points
        for(ChartPoint point : points) {
          XYChart.Data<Number, Number> pointData = 
          new XYChart.Data<Number, Number>(point.getRiskValue(), point.getResult());
          series.getData().add(pointData);
        }
        scatterChart.getData().addAll(series);
        pane.setCenter(scatterChart);

        //attaching mouse click handler and tooltip for every node
        for (XYChart.Series<Number, Number> s : scatterChart.getData()) {
          for (XYChart.Data<Number, Number> d : s.getData()) {
            Tooltip.install(d.getNode(), new Tooltip(
                String.format("Result: %2.1f\nRisk: %2.1f%%", 
                d.getYValue().doubleValue(),
                d.getXValue().doubleValue()*100)));

            d.getNode().setOnMouseClicked(new EventHandler<MouseEvent>() {
              @Override
              public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                  Node chartPlotBackground = scatterChart.lookup(".chart-plot-background");
                  double x = mouseEvent.getSceneX() - xSceneShift(chartPlotBackground);
                  double y = mouseEvent.getSceneY() - ySceneShift(chartPlotBackground);    
                  double xValue = (Double) xAxis.getValueForDisplay(x);
                  double yValue = (Double) yAxis.getValueForDisplay(y);
                  ChartPointClickedEvent event = new ChartPointClickedEvent(xValue, yValue);
                  eventQueue.add(event);
                }
              }
            });
          }
        }

        fxPanel.setScene(scene);
        statusLabel.setText("Plot calculated. To create plot with new values, go to first tab and click Recalculate.");
        bottomButton.setText("Recalculate");
      }
    });

    //switching to chart tab and enabling it
    tabbedPane.setEnabledAt(1, true);
    tabbedPane.setSelectedIndex(1);
  }
  
  /**
   * @param decision
   */
  public void updateDecisionParameters(DecisionParameters decision) {
    quantitySpinner.setValue(decision.getVolume());
    qualitySpinner.setValue(decision.getQuality());
    priceSpinner.setValue(decision.getPriceInPennies()/100);
    tvAdsSpinner.setValue(decision.getTvAdv());
    internetAdsSpinner.setValue(decision.getInternetAdv());
    newspaperAdsSpinner.setValue(decision.getMagazineAdv());
  }
  
  //recursive methods for calculating the coordinates of clicked point
  private double xSceneShift(Node node) {
    return node.getParent() == null ? 0 : node.getBoundsInParent().getMinX() + xSceneShift(node.getParent());
  }
  private double ySceneShift(Node node) {
    return node.getParent() == null ? 0 : node.getBoundsInParent().getMinY() + ySceneShift(node.getParent());
  }
 
}