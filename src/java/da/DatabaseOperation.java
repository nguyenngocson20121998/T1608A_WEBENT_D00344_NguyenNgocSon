package da;

import bean.examBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import javax.faces.context.FacesContext;



public class DatabaseOperation {

	public static Statement stmtObj;
	public static Connection connObj;
	public static ResultSet resultSetObj;
	public static PreparedStatement pstmt;
        

	public static Connection getConnection(){  
		try{  
			Class.forName("org.apache.derby.jdbc.ClientDriver");   
			String db_url ="jdbc:derby://localhost:1527/Aptech",
					db_userName = "Aptech",
					db_password = "1";
			connObj = DriverManager.getConnection(db_url,db_userName,db_password);  
		} catch(Exception sqlException) {  
			sqlException.printStackTrace();
		}  
		return connObj;
	}

	public static ArrayList<examBean> getfeventlist() {
		ArrayList<examBean> feventlist = new ArrayList<examBean>();  
		try {
			stmtObj = getConnection().createStatement();    
			resultSetObj = stmtObj.executeQuery("select * from exam");    
			while(resultSetObj.next()) {  
				examBean fev = new examBean(); 
				fev.setSubject(resultSetObj.getString("subject"));  
				fev.setStart_time(resultSetObj.getString("start_time"));  
				fev.setExam_date(resultSetObj.getString("exam_date"));  
				fev.setDuration(resultSetObj.getString("duration")); 
                                fev.setClass_room(resultSetObj.getString("class_room"));  
				fev.setFaculty(resultSetObj.getString("faculty"));  
				fev.setStatus(resultSetObj.getString("status"));
				feventlist.add(fev);  
			}   
			connObj.close();
		} catch(Exception sqlException) {
			sqlException.printStackTrace();
		} 
		return feventlist;
	}

	public static String saveEvent(examBean fexam) {
		int saveResult = 0;
		String navigationResult = "";
		try {      
			pstmt = getConnection().prepareStatement("insert into exam (subject, start_time, exam_date, duration, class_room, faculty, status) values (?, ?, ?, ?, ?, ?, ?)");			
			pstmt.setString(1, fexam.getSubject());
			pstmt.setString(2, fexam.getStart_time());
			pstmt.setString(3, fexam.getExam_date());
			pstmt.setString(4, fexam.getDuration());
                        pstmt.setString(5, fexam.getClass_room());
			pstmt.setString(6, fexam.getFaculty());
                        pstmt.setString(7, fexam.getStatus());
			saveResult = pstmt.executeUpdate();
			connObj.close();
		} catch(Exception sqlException) {
			sqlException.printStackTrace();
		}
		if(saveResult !=0) {
			navigationResult = "list_exam.xhtml?faces-redirect=true";
		} else {
			navigationResult = "create_exam.xhtml?faces-redirect=true";
		}
		return navigationResult;
	}
}
