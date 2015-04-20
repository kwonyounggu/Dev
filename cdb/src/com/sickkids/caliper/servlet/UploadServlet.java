package com.sickkids.caliper.servlet;

import java.io.IOException;
import java.io.PrintWriter;

//import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class for Servlet: UploadServlet
 * 
 */
public class UploadServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet
{
	private static final long serialVersionUID = -8353883071916004130L;

	public UploadServlet()
	{
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{	
		MultipartRequest parts=null;
		PrintWriter out=null;
		try
		{
			String loc=this.getServletContext().getRealPath("/bin/assets/data/");
			System.out.println("INFO: file location to write: "+loc);
			parts = new MultipartRequest( request, loc );
	        out = response.getWriter();
	
	        out.print( "SUCCESS,"+loc );

		}
		catch(Exception e)
		{
			e.printStackTrace();
			out.print(e.getMessage());//this will go the client gui
		}
		finally
		{
			out.close();
		}
	}
}