package com.sickkids.caliper.convertdata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import com.sickkids.caliper.dao.DiagnosticTestResultBean;
import com.sickkids.caliper.dao.DiagnosticTestResultDao;
import com.sickkids.caliper.dao.PatientDao;
import com.sickkids.caliper.dao.RawDataFromAbbottBean;
import com.sickkids.caliper.dao.RawDataFromAbbottDao;

public class ExcelToMsAccess
{
	//The following is used if you want to use you ODBC DSN
	//private static final String URL = "jdbc:odbc:TestDB";
	private static final String USERNAME = "";
	private static final String PASSWORD = "";
	private static final String DRIVER = "sun.jdbc.odbc.JdbcOdbcDriver";
	//private static final String URL = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=C:\\Dev\\MyDoc\\sickKids\\caliper\\rawdata\\CALIPERProjectDatabaseA.mdb;}";
	private static final String URL = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=C:\\Dev\\MyDoc\\sickKids\\caliper\\rawdata\\CALIPERAnalyteDatabaseAbbott.mdb;}";
	
	private static String csvFile1="C:\\Dev\\MyDoc\\sickKids\\caliper\\rawdata\\11076_from_CD_June14_2011\\20110614_11076.csv";
	private static String csvFile2="C:\\Dev\\MyDoc\\sickKids\\caliper\\rawdata\\30443_from_CD_June14_2011\\20110614_30443.csv";
	
	 private static Map fieldMap = new HashMap();
	 static 
	 {
		 fieldMap.put("_B-hCG", "hCG");
		 fieldMap.put("_Estradiol", "Estradiol");
		 fieldMap.put("_FT3", "[Free T3]");//Ms Access having field names with spaces needs to use [field name]
		 fieldMap.put("_FT4", "[Free T4]");
		 fieldMap.put("_LH", "LH");
		 fieldMap.put("_TT3", "TT3");
		 fieldMap.put("2GTesto", "Testosterone");
		 fieldMap.put("AFP_2", "AFP");
		 fieldMap.put("B12 II", "[Vitamin B12]");
		 fieldMap.put("CORTISOL", "Cortisol");
		 fieldMap.put("C-Peptide", "[C-peptide]");
		 fieldMap.put("Ferritin", "Ferritin");
		 fieldMap.put("Folate II", "Folate");
		 fieldMap.put("FSH", "FSH");
		 fieldMap.put("Insulin", "Insulin");
		 fieldMap.put("iPTH", "iPTH");
		 fieldMap.put("Progest", "Progesterone");
		 fieldMap.put("Prolactin", "Prolactin");
		 fieldMap.put("SHBG", "SHBG");
		 fieldMap.put("TnI II", "[Troponin I]");
		 fieldMap.put("Total HCY", "Homocystine");
		 fieldMap.put("TSH", "TSH");
		 fieldMap.put("TT4", "TT4");
		 fieldMap.put("VitD_25OH", "[25 OH Vitamin D]");
	 }
	
	
	public static void SubjectQuestionnaire() throws Exception
	{
		Connection connection = null;
		try
		{
			Class.forName(DRIVER);
			connection=DriverManager.getConnection(URL,USERNAME,PASSWORD);
			
			Statement stmt=connection.createStatement();
			
			ResultSet rs=stmt.executeQuery("select * from Subject_Questionnaire;");
			//ResultSet rs=stmt.executeQuery("desc Subject_Questionnaire;");
			ResultSetMetaData rsmd=rs.getMetaData();
			
			int colCount=rsmd.getColumnCount();
			//System.out.println("INFO: the number of columns="+colCount);
			for(int i=1;i<=colCount;i++)
			{
				System.out.println(i+": "+rsmd.getColumnName(i));
			}
			while(rs.next())		
			{
				for(int i=1;i<colCount;i++)
					System.out.print(rs.getObject(i)+" ");
				System.out.println();
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(connection!=null && !connection.isClosed()) 
			{
				System.out.println("INFO: without problem, it is being closed...");
				connection.close();
			}
		}
	}
	//two files csvFile1 and csvFile2 are added to the database without problem
	//24 additional analytes are added to the existing database, CALIPERAnalyteDatabaseAbbott.mdb, table name=Blood_Collection_Requisition_Information
	public static void AddingDataToMsAccess() throws Exception
	{
		Connection connection = null;
		String str="";
		String query="";
		try
		{
			Class.forName(DRIVER);
			connection=DriverManager.getConnection(URL,USERNAME,PASSWORD);
			
			Statement stmt=connection.createStatement();
			
			ResultSet rs=stmt.executeQuery("select * from Blood_Collection_Requisition_Information;");

			ResultSetMetaData rsmd=rs.getMetaData();
			
			int colCount=rsmd.getColumnCount();
			//System.out.println("INFO: the number of columns="+colCount);
			for(int i=1;i<=colCount;i++)
			{
				//System.out.println(i+": "+rsmd.getColumnName(i));
				System.out.println(i+": "+rsmd.getColumnName(i));
			}
			/*while(rs.next())		
			{
				for(int i=1;i<colCount;i++)
					System.out.print(rs.getObject(i)+" ");
				System.out.println();
			}*/
			BufferedReader in = new BufferedReader(new FileReader(new File(csvFile1)));
			
			
			DiagnosticTestResultBean db=null;
			
			for (int i = 1; (str = in.readLine()) != null; i++)
			{
				
				if(i==1) continue;//just pass the 1st line			
				String[] temp = str.split(",");
				if(temp.length<8 || temp[0].equals("4e817975")|| temp[0].equals("6089aaec")) continue;

				//System.out.println("ID="+temp[1]);
				//System.out.println(temp[6]+": "+fieldMap.get(temp[6]));
				//System.out.println("ASSAY NO="+temp[7]);
				
				//System.out.print("data="+temp[8]+":");
				String[] temp2=temp[8].split(" ");
				//System.out.print(temp2[0]+":"+temp2[1]+":len="+temp2.length);
				
				String dataValue="";
				if(temp2.length==3) dataValue=temp2[1];
				else dataValue=temp2[0];
				
				//System.out.println("\t datavalue="+dataValue);
				
				query="update Blood_Collection_Requisition_Information set "+fieldMap.get(temp[6])+"='"+Double.parseDouble(dataValue)+"' WHERE [Participation ID Number]="+ Long.parseLong(temp[1]);
				int count = stmt.executeUpdate(query);
				if(count==0)//insert
				{
					System.out.println("INFO:[ not existing id="+temp[1]+" complete date="+temp[16]+" cal date="+temp[17]+"]");
					String[] temp3=temp[16].split(" ");
					String[] temp4=temp3[0].split("\\.");//date
					String[] temp5=temp3[1].split(":");//time
					
					String[] temp6=temp[17].split(" ");
					String[] temp7=temp6[0].split("\\.");//date
					String[] temp8=temp6[1].split(":");//time
					
					Date completeDate=new Date(Integer.parseInt(temp4[2])-1900,Integer.parseInt(temp4[0])-1,Integer.parseInt(temp4[1]));
					Time completeTime=new Time(Integer.parseInt(temp5[0]),Integer.parseInt(temp5[1]),0);
					Date labDate2=new Date(Integer.parseInt(temp7[2])-1900,Integer.parseInt(temp7[0])-1,Integer.parseInt(temp7[1]));
					//System.out.println("INFO: date="+collectionDate+" time="+collectionDate+" lab date="+labDate);
					
					query="insert into Blood_Collection_Requisition_Information "+
						  "([Participation ID Number],[Sample Collection Date],[Sample Collection Time],[Sample Received in Lab],Study,Instrument,"+fieldMap.get(temp[6])+") values"+
						  "("+Long.parseLong(temp[1])+",#"+completeDate+"#,#"+completeTime+"#,#"+labDate2+"#,"+1+","+"'Abbott'"+","+Double.parseDouble(dataValue)+")";
					int count2 = stmt.executeUpdate(query);
					System.err.println("INFO: "+count2+" query="+query); 
				}
				System.out.println(i+" "+count+" id="+temp[1]+" assay="+temp[6]+" query="+query);
				

			}
			in.close();
		}
		catch(SQLException e)
		{
			System.err.println("ERROR: "+e.getSQLState()+", "+e.getMessage()+", SQL="+query);
			e.printStackTrace();
		}
		catch(Exception e)
		{
			System.err.println("ERROR: "+e.getMessage()+", SQL="+query);
			e.printStackTrace();
		}
		finally
		{
			if(connection!=null && !connection.isClosed()) 
			{
				System.out.println("INFO: without problem, it is being closed...");
				connection.close();
			}
		}
	}
	//Just convert acel raw file into mysql
	public static void ConvertAcelDataToMySQL() throws Exception
	{
		Connection connection = null;
		String str="";
		String query="";
		try
		{
			String userName = "root";
            String password = "19621111";
            String url = "jdbc:mysql://localhost:3306/caliper";
            Class.forName ("com.mysql.jdbc.Driver").newInstance ();
            connection = (Connection) DriverManager.getConnection (url, userName, password);
            System.out.println ("Database connection established");
            
			RawDataFromAbbottDao ddao=new RawDataFromAbbottDao(connection);
			
			BufferedReader in = new BufferedReader(new FileReader(new File(csvFile2)));
			
			
			RawDataFromAbbottBean db=null;
			
			for (int i = 1; (str = in.readLine()) != null; i++)
			{
				
				if(i==1) continue;//just pass the 1st line			
				String[] temp = str.split(",");
				if(temp.length<8 || temp[0].equals("4e817975")|| temp[0].equals("6089aaec")) continue;

				db=new RawDataFromAbbottBean();
				db.setCp(temp[0]);
				db.setSid(Long.parseLong(temp[1]));
				db.setPid(temp[2]);
				db.setName(temp[3]);
				db.setGender(temp[4]);
				db.setDob(temp[5]);
				db.setAssay(temp[6]);
				db.setAssay_number(Integer.parseInt(temp[7]));
				db.setResult(temp[8]);
				db.setInterpretation(temp[9]);
				db.setInstrument_response(temp[10]);
				db.setNormal_range(temp[11]);
				db.setDilution(temp[12]);
				db.setFlags(temp[13]);
				db.setCodes(temp[14]);
				db.setModule_sn(temp[15]);
				db.setCompleted_time(temp[16]);
				db.setCal_time(temp[17]);
				db.setOperator_id(temp[18]);
				db.setReagent_lot(temp[19]);
				db.setReagent_sn(temp[20]);
				db.setComment("");
				db.setLocation("");
				db.setDoctor("");
				db.setDraw_time("");
				db.setRemarks("ABBOTT1 in Mr. Chan's");
				
				ddao.create(db);
			}
			in.close();
		}
		catch(SQLException e)
		{
			System.err.println("ERROR: "+e.getSQLState()+", "+e.getMessage());
			e.printStackTrace();
		}
		catch(Exception e)
		{
			System.err.println("ERROR: "+e.getMessage());
			e.printStackTrace();
		}
		finally
		{
			if(connection!=null && !connection.isClosed()) 
			{
				System.out.println("INFO: without problem, it is being closed...");
				connection.close();
			}
		}
	}
	public static void main(String[] args) throws Exception
	{
		//SubjectQuestionnaire();
		//AddingDataToMsAccess();
		ConvertAcelDataToMySQL();
		
	}
}
