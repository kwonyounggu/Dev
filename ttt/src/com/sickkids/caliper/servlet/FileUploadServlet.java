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
	    sqlDao=(SQLDao)app.getAttribute("tttsqlDao");
	}

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		super.doGet(request, response);
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		HttpSession session = request.getSession(false);

		String retString="";
		File uploadFile=null;
		FileLibraryBean fb=new FileLibraryBean();
		String action="";//add, edit, delete

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
				for (FileItem item : items) 
				{
					System.out.println("item="+item.getFieldName());
					
					if (item.isFormField()) 
					{
						System.out.println("form field="+item.getFieldName()+" "+ item.getString());
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
					}
				}
				fb.setSubmissionTime(Utils.currentTimestamp());
				fb.setSubmitterId(trb.getUserId());
				
				if(action.equals("add"))
				{
					System.out.println("INFO (add): "+fb);
					fb.setFileId((int) (sqlDao.getGenericLong("select max(file_id) from file_library")+1));
					sqlDao.updateInsertGenericSqlCmd(fb.getInsertStmt());
				}
				else if(action.equals("edit"))
				{
					System.out.println("INFO (edit): "+fb);
					if(fb.getFileNameSubmitted().equals(""))
					{
						//update fileName, fileType, description
					}
					else
					{
						//update all except for the file_id 
					}
				}
				else if(action.equals("delete"))
				{
					
				}
				retString=fb.toString();
			}
			response.getWriter().print(retString);			
		} 

		catch(Exception e)
		{			
			if(uploadFile!=null && uploadFile.exists()) 
			{
				uploadFile.delete();
			}

			Utils.logger.severe("(op=ajax_action_file_upload for ajax request): msg="+e+" from FileUploadServlet.java");
			response.getWriter().write("ajax_action_file_upload:Error: "+e.getMessage()+"<br/>"+Message.inform_to_admin_about_exception+"<br/>E-Mail: "+Utils.csr_email_address);		
		}
		response.getWriter().close();
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
		fname = parentDir + System.getProperty("file.separator") + fname + fileExt.toLowerCase();
		System.out.println("2nd fname="+fname+" ext="+fileExt);

		File temp = new File(fname);

		return temp;
	}
}