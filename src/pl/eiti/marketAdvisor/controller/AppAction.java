package pl.eiti.marketAdvisor.controller;

import pl.eiti.marketAdvisor.common.events.AppEvent;

/**
 * @author Maciej 'mc' Suchecki
 * Objects implementing that interface define Controller's answer to specific AppEvents.
 */
public interface AppAction {
  abstract public void execute(AppEvent e);
}