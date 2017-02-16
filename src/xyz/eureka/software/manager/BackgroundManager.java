package xyz.eureka.software.manager;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import xyz.eureka.software.counter.Counter;
import xyz.eureka.software.counter.ReadVisitors;
import xyz.eureka.software.counter.SaveVisitors;

public final class BackgroundManager implements ServletContextListener 
{
	private ScheduledExecutorService scheduler;

	@Override
	public void contextInitialized(ServletContextEvent event) 
	{
		// Get saved page visits.
		final int page_visits = new ReadVisitors().getVisitors(),
				// Scheduled job delay.
				scheduled_delay = 30;
		
		// Set page visits for incrementing.
		Counter.setPageVisits(page_visits);
		
		// Create new scheduled thread.
		scheduler = Executors.newSingleThreadScheduledExecutor();
		
		// Schedule saving visitors to file every thirty minutes. 
		scheduler.scheduleAtFixedRate(new SaveVisitors(), scheduled_delay, scheduled_delay, TimeUnit.MINUTES);
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event)
	{
		// Destroy scheduled thread.
		scheduler.shutdownNow();
		
		// Save visitors to file before shutting down.
		new SaveVisitors().run();
	}
}
