package pl.eiti.marketAdvisor.model;

import matlabcontrol.MatlabConnectionException;
import matlabcontrol.MatlabInvocationException;
import matlabcontrol.MatlabProxy;
import matlabcontrol.MatlabProxyFactory;

/**
 * @author Jakub Swiatkowski
 * Class responsible for handling MatlabProxy for lifetime of the program.
 */
public class MatlabProxySingleton {
	private static volatile MatlabProxy ourInstance = null;
	public static MatlabProxy getInstance() throws MatlabConnectionException, MatlabInvocationException{
		if(ourInstance == null) {
			synchronized (MatlabProxySingleton.class) {
				if(ourInstance == null) {
					//Create a proxy, which we will use to control MATLAB
				    MatlabProxyFactory factory = new MatlabProxyFactory();
				    ourInstance = factory.getProxy();
				}
			}
		}
		return ourInstance;
	}
	private MatlabProxySingleton() {
	}
}
