package com.sickkids.caliper.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.*;
import java.util.*;

import javax.servlet.http.*;
import org.apache.commons.fileupload.*;

import javax.servlet.ServletException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringEscapeUtils;

import com.sickkids.caliper.common.MenuLink;
import com.sickkids.caliper.common.Message;
import com.sickkids.caliper.common.Utils;

import com.sickkids.caliper.dao.AllRegisteredUserBean;
import com.sickkids.caliper.dao.FileLibraryBean;
import com.sickkids.caliper.dao.SQLDao;

import com.sickkids.caliper.database.ConnectionPool;
//import com.sickkids.caliper.service.FileUploadListener;
//import com.sickkids.caliper.service.ImageFileRenamePolicy;
import com.sickkids.caliper.service.MailInfo;

public class FileUploadServlet extends HttpServlet implements Servlet 
{
	private static final long serialVersionUID = 2740693677625051632L;

	private ServletConfig config=null;
	private ServletContext app=null;
	private ConnectionPool cp = null;
	
	public static List<String> emailList=new ArrayList<String>();
	public static List<String> nameList=new ArrayList<String>();
	
	private SQLDao sqlDao=null;

	public void init(ServletConfig config) 
	{
	    this.config = config;
	    app = config.getServletContext();
	    cp=(ConnectionPool)app.getAttribute("connectionPool");
	    sqlDao=(SQLDao)app.getAttribute("sqlDao");
	}

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		super.doGet(request, response);
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		HttpSession session = request.getSession(false);
		
		String ajaxUpdateResult = "";
		String retString="";
		File uploadFile=null;
		FileLibraryBean fb=new FileLibraryBean();
		String action="";//add, edit, delete
		//0. similar to the normal servlet responses
		//1. session_timeout
		//2. handle data_update through this servlet
		//3. test exceptions
		try 
		{
			AllRegisteredUserBean trb=null;
			
			if(session!=null)
			{
				trb=(AllRegisteredUserBean)session.getAttribute("trb");
			}
			if(trb==null) retString="session_timeout";
			else
			{
				List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
				//String studyId="000000";
				for (FileItem item : items) 
				{
					System.out.println("item="+item.getFieldName());
					
					if (item.isFormField()) 
					{
						System.out.println("form field="+item.getFieldName()+" "+ item.getString());
						ajaxUpdateResult += "Field " + item.getFieldName() + " with value: " + item.getString() + " is successfully read\n\r";
						//if(item.getFieldName().equals("studyId")) studyId=item.getString();
						if(item.getFieldName().equals("fileName")) fb.setFileNameFormal(item.getString());
						else if(item.getFieldName().equals("fileType")) fb.setFileType(item.getString());
						else if(item.getFieldName().equals("description")) fb.setDescription(item.getString());
						else if(item.getFieldName().equals("valid")) fb.setValid(item.getString().equals("true"));
						else if(item.getFieldName().equals("action")) 
						{
							action=item.getString();
							fb.setRemarks(action+" by "+trb.getUserId()+" at "+Utils.currentTimestamp());
						}
					} 
					else 
					{
						System.out.println("non form field="+item.getFieldName()+" "+ item.getName());
						if(item.getFieldName().equals("docFile")) fb.setFileNameSubmitted(item.getName());
						String doc_file_link="/";//from the root of the context such as 8080/ttt/uploaded_files/ttt_doc
						String loc=app.getRealPath(doc_file_link);
						
						File path=new File(System.getProperty("catalina.home")+"/uploaded_files/ttt_doc");
	
						if(!path.exists()||!path.isDirectory()) path.mkdirs();
						uploadFile=rename(new File(path+"/"+item.getName()), fb.getFileType()); //prefixing the file type in the file name such as video_2015020202.mp4
						item.write(uploadFile);//write the coming file with the given name
			
						doc_file_link="uploaded_files/ttt_doc/"+uploadFile.getName();
						fb.setFileNameGenerated(uploadFile.getName());
						fb.setFileLocationPath(doc_file_link);
						fb.setFileSize(uploadFile.length());
						//ajaxUpdateResult += "File " + item.getName() + " is successfully uploaded to "+uploadFile.getPath()+"\n\r";
					}
				}
				fb.setSubmissionTime(Utils.currentTimestamp());
				fb.setSubmitterId(trb.getUserId());
				//temp comment
				//sqlDao.updateInsertGenericSqlCmd(StringEscapeUtils.escapeJava("update ex_study set full_text_pdf1='"+uploadFile.getPath()+"' where study_id="+studyId));
				if(action.equals("add"))
				{
					System.out.println(fb);
				}
				else if(action.equals("edit"))
				{
					
				}
				else if(action.equals("delete"))
				{
					
				}
			}
			
		} 
		catch (FileUploadException e) 
		{
			if(uploadFile!=null && uploadFile.exists()) 
			{
				uploadFile.delete();
			}

			Utils.logger.severe("(op=/ttt/file_upload): msg="+e+",\nCustomer IP: "+request.getRemoteAddr()+",\nfrom FileUploadServlet.java");
			emailList.clear();
			nameList.clear();
			emailList.add(Utils.csr_email_address);
			nameList.add("CSR-ADMIN");
			new MailInfo(Utils.csr_email_address, emailList, nameList, Utils.smtp,"FileUpload Failed", "(op=/ttt/file_upload): "+e+"<br><br> Generated at "+Utils.currentTimestamp()+".");
			forwardErrorPage(request,response,e.toString());
		} 
		catch(Exception e)
		{			
			if(uploadFile!=null && uploadFile.exists()) 
			{
				uploadFile.delete();
			}

			Utils.logger.severe("(op=/ttt/file_upload): msg="+e+",\nCustomer IP: "+request.getRemoteAddr()+",\nfrom FileUploadServlet.java");
			emailList.clear();
			nameList.clear();
			emailList.add(Utils.csr_email_address);
			nameList.add("CSR-ADMIN");
			new MailInfo(Utils.csr_email_address, emailList, nameList,Utils.smtp,"FileUpload Failed", "(op=/ttt/file_upload): "+e+"<br><br> Generated at "+Utils.currentTimestamp()+".");
			forwardErrorPage(request,response,e.toString());
		}
		
		//Utils.delay(5);
		response.getWriter().print(fb);
		response.getWriter().close();
	}
	public void forwardWarningPage(HttpServletRequest request,HttpServletResponse response,String msg)throws ServletException, IOException
	{	
		request.setAttribute("warning_msg", msg+" at "+Utils.currentTimestamp()+"!!!");		
		request.setAttribute("body_panel", "error/servlet_warning_page.jsp");
		((RequestDispatcher)request.getRequestDispatcher("jsp/local_page_template.jsp")).forward(request,response);
	}
	public void forwardErrorPage(HttpServletRequest request,HttpServletResponse response,String msg)throws ServletException, IOException
	{	
		request.setAttribute("error_msg", msg+" false: at "+Utils.currentTimestamp()+"!!!"+
							 "<br><br>"+Message.inform_to_admin_about_exception+"&nbsp;&nbsp;<a href='mailto:"+Utils.csr_email_address+"?subject=EXEMPLAR FILE UPLOAD ERROR NOTIFICATION' style='color: #FE5734;text-decoration: underline;'>E-MAIL</a>"+
							 "<br><br>Your IP Address: "+request.getRemoteAddr());		
		request.setAttribute("body_panel", "error/servlet_error_page.jsp");
		((RequestDispatcher)request.getRequestDispatcher("jsp/local_page_template.jsp")).forward(request,response);
	}
	public File rename(File f, String standard_file_name)
	{

		// Get the parent directory path as in h:/home/user or /home/user
		String parentDir = f.getParent();

		// Get filename without its path location, such as 'index.txt'
		String fname = f.getName();

		// Get the extension if the file has one
		String fileExt = "";
		int i = -1;
		if ((i = fname.indexOf(".")) != -1)
		{

			fileExt = fname.substring(i);
			fname = fname.substring(0, i);
		}

		fname = standard_file_name + "_"+Utils.getDateTimeForFileName();
		System.out.println("1st fname="+fname+" ext="+fileExt);
		fname = parentDir + System.getProperty("file.separator") + fname + fileExt;
		System.out.println("2nd fname="+fname+" ext="+fileExt);

		File temp = new File(fname);

		return temp;
	}
}