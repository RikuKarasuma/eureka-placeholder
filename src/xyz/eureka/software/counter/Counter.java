package xyz.eureka.software.counter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class Counter extends HttpServlet 
{
	
	private static final long serialVersionUID = -7652640747959799818L;
	private static int pageVisits = 0;
	
	
	// Test path = HOMEPATH 
	// Actual code = System.getenv("OPENSHIFT_HOMEDIR");
	
	
	/**
	 *  Increments page visits via an AJAX call.
	 */
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		pageVisits++;
		System.out.println(pageVisits);
	}
	
	/**
	 * Gets pages visits then resets the number.
	 * @return int - page visits.
	 */
	public static int getPageVisitsAndReset()
	{
		final int page_visits = pageVisits;
		pageVisits = 0;
		return page_visits;
	}
	
	/**
	 * Returns just the page visits.
	 * @return int - page visits.
	 */
	public static int getPageVisits()
	{
		return pageVisits;
	}
	
	/**
	 * Sets page visits.
	 * @param page_visits - New page visits.
	 */
	public static void setPageVisits(int page_visits)
	{
		pageVisits = page_visits;
	}
}
