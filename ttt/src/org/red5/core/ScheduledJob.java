package org.red5.core;

import org.red5.server.api.scheduling.IScheduledJob;
import org.red5.server.api.scheduling.ISchedulingService;
import org.red5.server.adapter.ApplicationAdapter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ScheduledJob implements IScheduledJob {
	
	protected static Log log = LogFactory.getLog(ScheduledJob.class.getName());
	
	public void execute(ISchedulingService service) {
		// Do something
		log.debug("-----------------------");
		log.debug("Executing Scheduled Job!");
		log.debug("It's time to do something here eg for example reload playlist...");
		//try to reload playlist everytime this is called...
		log.debug("-----------------------");
	}
}