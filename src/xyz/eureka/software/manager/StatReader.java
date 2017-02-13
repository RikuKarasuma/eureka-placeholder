package xyz.eureka.software.manager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.eureka.software.counter.Counter;
import xyz.eureka.software.counter.ReadVisitors;

public class StatReader extends HttpServlet 
{
	private static final long serialVersionUID = 8837067891822756796L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		
		resp.getOutputStream().println("Current Page visits: "+ Counter.getPageVisits());
		resp.getOutputStream().println("Saved Page visits: "+ new ReadVisitors().getVisitors());
	}
}
