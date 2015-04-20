package com.sickkids.caliper.servlet;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.sickkids.caliper.common.MenuLink;
import com.sickkids.caliper.common.Message;
import com.sickkids.caliper.common.Utils;

import com.sickkids.caliper.dao.EventManagementBean;
import com.sickkids.caliper.dao.SQLDao;
import com.sickkids.caliper.database.ConnectionPool;

import com.sickkids.caliper.service.MailInfo;

public class FileUploadServlet extends HttpServlet implements Servlet
{
	private static final long serialVersionUID = -800842603044140522L;
	private ServletConfig config = null;
	private ServletContext app = null;
	private ConnectionPool cp = null;

	public static List<String> emailList = new ArrayList<String>();
	public static List<String> nameList = new ArrayList<String>();

	private SQLDao sqlDao = null;

	public void init(ServletConfig config)
	{
		this.config = config;
		app = config.getServletContext();
		cp = (ConnectionPool) app.getAttribute("connectionPool");
		sqlDao = (SQLDao) app.getAttribute("sqlDao");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		super.doGet(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//Utils.logger.info("doPost() is called in FileUploadServlet.java-----------------1");
		/**********************************************************/
		// Check session out
		/**********************************************************/
		// response.setContentType("text/html");
		HttpSession session = request.getSession(true);
		String admin_email=(String)session.getAttribute("admin_email");

		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		//Utils.logger.info("doPost() is called in FileUploadServlet.java-----------------2");
		File uploadFile = null;
		String op = "";
		try
		{
			// if session timeout, redirecto to the public because this is only
			// called for POST not ajax.
			if (admin_email == null)
			{
				response.sendRedirect(response.encodeRedirectURL(MenuLink.admin_home));
				return;
			}
			
			if (!ServletFileUpload.isMultipartContent(request)) 
			{
				forwardWarningPage(request, response, "You are not trying to upload");
				//Utils.logger.info("doPost() is called in FileUploadServlet.java-----------------3");
			}
			
			List<FileItem> fields = upload.parseRequest(request);
			Iterator<FileItem> it = fields.iterator();
			if (!it.hasNext())
			{
				throw new Exception("No form parameters develivered in the FileUploadServlet");
			}
			//Utils.logger.info("doPost() is called in FileUploadServlet.java-----------------4");
			//MasterFileUploadBean mb = new MasterFileUploadBean();
			EventManagementBean eb= new EventManagementBean();
			while (it.hasNext())
			{
				FileItem fileItem = it.next();
				boolean isFormField = fileItem.isFormField();
				if (isFormField)
				{
					if (fileItem.getFieldName().equals("op")) op = fileItem.getString();
					else eb.setParams(fileItem.getFieldName(), fileItem.getString());
				}
				else
				{	//NOTE: this program can't parse the uploaded file path from IE (Jan 08, 2012).
					//	    To fix the problem, 
					//		1). you have to parse it as in setParams() of EventManagement
					//	    2). uploadFile = rename(new File(path + "/" + eb.getPdf_file()), "events");
					eb.setParams(fileItem.getFieldName(), fileItem.getName());//Note: change inside setParams for file_location -1
					String pdf_file_link = "/";
					String loc = app.getRealPath(pdf_file_link);
					File path = new File(loc + "uploaded_files/pdfs/"+ "events");

					if (!path.exists() || !path.isDirectory()) path.mkdirs();
					uploadFile = rename(new File(path + "/" + eb.getPdf_file()), "events"); //Note: replace fileItem.getName() with eb.getPdf_file() -2
					fileItem.write(uploadFile);// write the coming file with the
												// given name
					pdf_file_link = "uploaded_files/pdfs/" + "events" + "/" + uploadFile.getName();

					eb.setEvent_file_location(pdf_file_link);
				}
			}
			//Utils.logger.info("doPost() is called in FileUploadServlet.java-----------------5");
			// Uncomment if you want to see the contents of parameters received
			System.err.println("INFO: All Contents inside\n\n"+eb.toString()+"\n\nLink="+eb.getEvent_file_location());

			/**********************************************************************************/
			// This stage file writing is done and is to update/insert database
			// with the data.
			/**********************************************************************************/
			//mb.setCentral_submission_time(Utils.currentTimestamp());
			eb.setWritten_date(Utils.currentTimestamp());
			//Utils.logger.info("doPost() is called in FileUploadServlet.java-----------------6");
			sqlDao.eventFileUpload(eb);
			request.setAttribute("eb", eb);
			request.setAttribute("body_panel","/admin/jsp/event_file_upload_success.jsp");
			((RequestDispatcher) request.getRequestDispatcher("/admin/jsp/page_template.jsp")).forward(request, response);
		}
		catch (FileUploadException e)
		{
			if (uploadFile != null && uploadFile.exists())
			{
				uploadFile.delete();
			}

			Utils.logger.severe("(op=" + op + "): msg=" + e
					+ ",\nCustomer IP: " + request.getRemoteAddr()
					+ ",\nfrom (FileUploadException)FileUploadServlet.java");
			emailList.clear();
			nameList.clear();
			emailList.add(Utils.caliper_email_address);
			nameList.add("ADMIN");
			new MailInfo(Utils.caliper_email_address, emailList, nameList,
							Utils.smtp, "FileUpload Failed", "(op=" + op + "): " + e
							+ "<br><br> Generated at "
							+ Utils.currentTimestamp() + "(FileUploadException).");
			forwardErrorPage(request, response, e.toString());
		}
		catch (Exception e)
		{
			if (uploadFile != null && uploadFile.exists())
			{
				uploadFile.delete();
			}

			Utils.logger.severe("(op=" + op + "): msg=" + e + ",\nCustomer IP: " + request.getRemoteAddr() + ",\nfrom (Exception) FileUploadServlet.java");
			emailList.clear();
			nameList.clear();
			emailList.add(Utils.caliper_email_address);
			nameList.add("ADMIN");
			new MailInfo(Utils.caliper_email_address, emailList, nameList,
							Utils.smtp, "FileUpload Failed", "(op=" + op + "): " + e
							+ "<br><br> Generated at "
							+ Utils.currentTimestamp() + "(Exception).");
			forwardErrorPage(request, response, e.toString());
		}
	}

	public void forwardWarningPage(HttpServletRequest request, HttpServletResponse response, String msg) throws ServletException, IOException
	{
		request.setAttribute("warning_msg", msg + " at "+ Utils.currentTimestamp() + "!!!");
		request.setAttribute("body_panel", "error/servlet_warning_page.jsp");
		((RequestDispatcher) request.getRequestDispatcher("/admin/jsp/page_template.jsp")).forward(request, response);
	}

	public void forwardErrorPage(HttpServletRequest request, HttpServletResponse response, String msg) throws ServletException, IOException
	{
		request.setAttribute("error_msg",msg
								+ " false: at "
								+ Utils.currentTimestamp()
								+ "!!!"
								+ "<br><br>"
								+ Message.inform_to_admin_about_exception
								+ "&nbsp;&nbsp;<a href='mailto:"
								+ Utils.caliper_email_address
								+ "?subject=CALIPER ERROR NOTIFICATION' style='color: #FE5734;text-decoration: underline;'>E-MAIL</a>"
								+ "<br><br>Your IP Address: "
								+ request.getRemoteAddr());
		request.setAttribute("body_panel", "error/servlet_error_page.jsp");
		((RequestDispatcher) request.getRequestDispatcher("/admin/jsp/page_template.jsp")).forward(request, response);
	}

	public File rename(File f, String reference_no)
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

		fname = reference_no + "_" + Utils.getDateTimeForFileName();
		fname = parentDir + System.getProperty("file.separator") + fname + fileExt;

		File temp = new File(fname);

		return temp;
	}
}