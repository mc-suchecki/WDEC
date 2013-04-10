package pl.eiti.marketAdvisor.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

/**
 * @author Maciej 'mc' Suchecki
 * Class representing main window of an application.
 */
public class MarketAdvisorFrame extends JFrame {
  private static final long serialVersionUID = 1L;
  private final JButton refreshButton;
  private final JLabel statusLabel;

  /** Main window constructor. */
  MarketAdvisorFrame() {
    //setting window parameters
    super("Market Advisor");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    //setting layout and look
    setFocusable(true);
    setLayout(new BorderLayout());
    JPanel bottom = new JPanel();
    bottom.setLayout(new BorderLayout());
    add(BorderLayout.SOUTH, bottom);
    JPanel controlPanel = new JPanel();       
    controlPanel.setLayout(new GridLayout(6, 2));
    add(BorderLayout.CENTER, controlPanel);
    
    //creating labels and spinners					   init min max  step
    controlPanel.add(new JLabel("Quantity:"));
    SpinnerModel quantityModel = new SpinnerNumberModel(100, 1, 1000, 1);
    JSpinner quantitySpinner = new JSpinner(quantityModel);
    controlPanel.add(quantitySpinner);
    controlPanel.add(new JLabel("Quality"));
    SpinnerModel qualityModel = new SpinnerNumberModel(50, 1, 100, 1);
    JSpinner qualitySpinner = new JSpinner(qualityModel);
    controlPanel.add(qualitySpinner);
    controlPanel.add(new JLabel("Price:"));
    SpinnerModel priceModel = new SpinnerNumberModel(10, 1, 100, 1);
    JSpinner priceSpinner = new JSpinner(priceModel);
    controlPanel.add(priceSpinner);
    SpinnerModel adsModel = new SpinnerNumberModel(0, 0, 100000, 1000);
    controlPanel.add(new JLabel("Television ads:"));
    JSpinner tvAdsSpinner = new JSpinner(adsModel);
    controlPanel.add(tvAdsSpinner);
    controlPanel.add(new JLabel("Internet ads:"));
    JSpinner internetAdsSpinner = new JSpinner(adsModel);
    controlPanel.add(internetAdsSpinner);
    controlPanel.add(new JLabel("Newspapers ads:"));
    JSpinner newspaperAdsSpinner = new JSpinner(adsModel);
    controlPanel.add(newspaperAdsSpinner);

    //creating other widgets
    statusLabel = new JLabel("Input values and click 'Refresh' to continue.");
    bottom.add(BorderLayout.LINE_START, statusLabel);
    refreshButton = new JButton("Refresh");
    bottom.add(BorderLayout.LINE_END, refreshButton);
	    
    //configuring look
    setResizable(false);
    setSize(812, 455);
    setVisible(true);
    setLocationRelativeTo(null);
  }
}