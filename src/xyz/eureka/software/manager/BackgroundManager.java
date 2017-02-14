package xyz.eureka.software.manager;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import xyz.eureka.software.counter.Counter;
import xyz.eureka.software.counter.ReadVisitors;
import xyz.eureka.software.counter.SaveVisitors;

@WebListener
public final class BackgroundManager implements ServletContextListener 
{
	private ScheduledExecutorService scheduler;

	@Override
	public void contextInitialized(ServletContextEvent event) 
	{
		System.out.println("Initializing");
		
		// Get saved page visits.
		final int page_visits = new ReadVisitors().getVisitors();
		// Set page visits for incrementing.
		Counter.setPageVisits(page_visits);
		
		// Create new scheduled thread.
		scheduler = Executors.newSingleThreadScheduledExecutor();
		
		// Schedule saving visitors to file every thirty minutes. 
		scheduler.scheduleAtFixedRate(new SaveVisitors(), 30, 30, TimeUnit.MINUTES);
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event)
	{
		System.out.println("Deinitializing");
		
		// Destroy scheduled thread.
		scheduler.shutdownNow();
		
		// Save visitors to file before shutting down.
		new SaveVisitors().run();
	}
}
