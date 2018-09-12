package com.util;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Servlet implementation class StatusServlet
 */
@WebServlet("/status")
public class StatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;




	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String output = request.getParameter("stringParameter");
		System.out.println(output);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String output = request.getParameter("stringParameter");
		//System.out.println(output);
		String vehicles = request.getParameter("selectedVehicles");
		System.out.println(vehicles);
		String[] arrOfStr = vehicles.split(",");
		JSONObject obj = new JSONObject();
		JSONArray jsonarr = new JSONArray();
		  LinkedHashMap<String,String> hm=new LinkedHashMap<String,String>();  
		for (String a : arrOfStr)
		{
			System.out.println("aaa"+a);
			
			String asd  = a.substring(1, a.length()-1);
			String ssd = asd.substring(1, asd.length()-1);
		//	String ssd =  "open : Approve.Done";
			System.out.println("expected : "+ssd);
			String[] key = ssd.split(":");
			for (int i =0 ; i<key.length-1; i++)
			{
				//String arr[] = b.split(" ", 2);

				String firstWord = key[i];
				
				String theRest = key[i+1];
				//String theRest1 = theRest.trim();
				obj.put(firstWord, theRest);
				hm.put(firstWord, theRest);
				
			System.out.println("key :"+firstWord);
			System.out.println("value :"+ theRest);
			
			}
			
			/*String[] arrofdot = a.split(".");
			
			for (String b : arrofdot)
			{
				System.out.println("ffff"+b);
			}
			// System.out.println("xxx "+a.replaceAll("[^a-zA-Z0-9]", " ")); 
			System.out.println("aaaaaaadddddddd");
			
			System.out.println("xxx "+a.replaceAll("[^a-zA-Z0-9]", ""));
			String s=a.replaceAll("[^a-zA-Z0-9]", " ");
			String s1=s.trim();
			System.out.println("qqq ="+s1);
			String[] words=s1.split(" ");
			System.out.println("Length "+words.length);
			int length=words.length;
			ArrayList<String> list=new ArrayList<>();
			for(String w:words){
				list.add(w);
				System.out.println("words "+w);
			}
			System.out.println("list length = "+list.size());
			System.out.println("first = "+list.get(3));

			String str1=a;
			String[] arrOfStr1 = str1.split(":");
			for (String a1 : arrOfStr1){
				//System.out.println("aaa1"+a1);	 
			}*/
		}
		jsonarr.add(obj);
		System.out.println("json Array : "+jsonarr);
		System.out.println("json object : "+obj);
		
		GenerateBPMN gs = new GenerateBPMN();
		gs.data1(obj,hm);
		/*TaskManagement tsk = new TaskManagement();
		try {
			tsk.main1(hm);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
*/
	}
}