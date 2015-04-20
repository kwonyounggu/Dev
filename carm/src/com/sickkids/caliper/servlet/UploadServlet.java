package com.sickkids.caliper.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

//import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		System.out.println("INFO: file location to write: doGet---------");
		
		MultipartRequest parts=null;
		PrintWriter out=null;
		try
		{
			String loc=this.getServletContext().getRealPath("/bin/");
			System.out.println("INFO: file location to write: "+loc);
			parts = new MultipartRequest( request, loc );
	        out = response.getWriter();
	
	      //update database for the image file link.
	        out.print( "SUCCESS,"+loc );//this result will be posted into iFrame
	        
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{	
		//doGet(request, response);
		

		HttpSession session = request.getSession(true);
		System.out.println("dir="+session.getAttribute("dir"));
		System.out.println("INFO: file location to write: doPost");
		MultipartRequest parts=null;
		PrintWriter out=null;
		try
		{
			String loc=this.getServletContext().getRealPath("/bin/");
			System.out.println("INFO: file location to write: "+loc);
			parts = new MultipartRequest( request, loc );
	        out = response.getWriter();
	
	        out.print( "SUCCESS,"+loc );
	        /*
	        String title = "Reading All Request Parameters";
	        out.println("<HTML>" +
	                    "<BODY BGCOLOR=\"#FDF5E6\">\n" +
	                    "<H1 ALIGN=CENTER>" + title + "</H1>\n" +
	                    "<TABLE BORDER=1 ALIGN=CENTER>\n" +
	                    "<TR BGCOLOR=\"#FFAD00\">\n" +
	                    "<TH>Parameter Name<TH>Parameter Value(s)");
	        Enumeration paramNames = request.getParameterNames();
	        while(paramNames.hasMoreElements()) {
	          String paramName = (String)paramNames.nextElement();
	          out.println("<TR><TD>" + paramName + "\n<TD>");
	          String[] paramValues = request.getParameterValues(paramName);
	          if (paramValues.length == 1) {
	            String paramValue = paramValues[0];
	            if (paramValue.length() == 0)
	              out.print("<I>No Value</I>");
	            else
	              out.print(paramValue);
	          } else {
	            out.println("<UL>");
	            for(int i=0; i<paramValues.length; i++) {
	              out.println("<LI>" + paramValues[i]);
	            }
	            out.println("</UL>");
	          }
	        }
	        out.println("</TABLE>\n</BODY></HTML>");*/

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