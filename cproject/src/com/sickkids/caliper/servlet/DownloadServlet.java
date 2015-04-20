package com.sickkids.caliper.servlet;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sickkids.caliper.dao.SQLDao;
import com.sickkids.caliper.database.ConnectionPool;

/**
 * Servlet implementation class DownloadServlet
 */
public class DownloadServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	private ServletContext app=null;
	private ConnectionPool cp = null;
	
	
	public DownloadServlet()
	{
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void init(ServletConfig config) 
	{
	    app = config.getServletContext();
	    cp=(ConnectionPool)app.getAttribute("connectionPool");
	}
	//This will implement a dynamic download from database to an excel file in the user's system. 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		String filename = request.getPathInfo();
		
		
	    response.setHeader("content-type", "application/csv");
	    response.setHeader("content-disposition", "attachment;filename=\"" + filename + "\"");
	    
	    /*
	    List<List<String>> csv = new ArrayList<List<String>>();
	    csv.add(Arrays.asList("title1", "title1", "title1"));
	    csv.add(Arrays.asList("1", "2", "3"));
	    csv.add(Arrays.asList("1.111", "2.111", "3.111"));
	    */
	    String analyte=filename.substring(1, filename.length()-4);
	    //System.out.println("download servlet with filename="+analyte);
	    List<List<String>> csv = new SQLDao(cp).getRawDataList(analyte);
	    writeCsv(csv, ',', response.getOutputStream());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		super.doPost(request, response);
	}
	public static <T> void writeCsv (List<List<T>> csv, char separator, OutputStream output) throws IOException
	{
	    BufferedWriter writer = null;
	    try 
	    {
	        writer = new BufferedWriter(new OutputStreamWriter(output, "UTF-8"));
	        for (List<T> row : csv) 
	        {
	            StringBuilder line = new StringBuilder();
	            for (Iterator<T> iter = row.iterator(); iter.hasNext();) 
	            {
	                String field = String.valueOf(iter.next()).replace("\"", "\"\"");
	                if (field.indexOf(separator) > -1 || field.indexOf('"') > -1) 
	                {
	                    field = '"' + field + '"';
	                }
	                line.append(field);
	                if (iter.hasNext()) 
	                {
	                    line.append(separator);
	                }
	            }
	            writer.write(line.toString());
	            writer.newLine();
	        }
	    } 
	    finally 
	    {
	        if (writer != null) try { writer.close(); } catch (IOException ignore) {}
	    }
	}

}
