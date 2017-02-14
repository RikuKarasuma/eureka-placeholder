package xyz.eureka.software.manager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.eureka.software.counter.Counter;
import xyz.eureka.software.counter.ReadVisitors;

public final class StatReader extends HttpServlet 
{
	private static final long serialVersionUID = 8837067891822756796L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		int pre_cal = 0;
		final int instance_page_visits = Counter.getPageVisits(),
				  saved_page_visits = new ReadVisitors().getVisitors(),
				  new_page_visits = ( (pre_cal = (instance_page_visits - saved_page_visits) ) < 0 ) ? 0 : pre_cal ;
		
		resp.getOutputStream().println("Instance Page visits: " + instance_page_visits + "\n" + 
									   "Saved Page visits: " + saved_page_visits + "\n" +
									   "New Page visits: " + new_page_visits);
	}
}
