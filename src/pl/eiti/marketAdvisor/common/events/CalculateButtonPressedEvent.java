/**
 * 
 */
package pl.eiti.marketAdvisor.common.events;

/**
 * @author mc
 * Class representing event of pressing the calculate button by user.
 */
public class CalculateButtonPressedEvent extends AppEvent {
  private final int volumeInDollars;
  
  public CalculateButtonPressedEvent(int volumeInDollars) {
    this.volumeInDollars = volumeInDollars;
  }

  /** @return the volumeInDollars */
  public int getVolumeInDollars() {
    return volumeInDollars;
  }
}
