package com.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.simple.JSONObject;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class GenerateBPMN {

	 
	 public static final String xmlFilePath = "D:\\flowableWorkspace\\TaskManagement\\src\\main\\resources\\flowableWorkflow.bpmn20.xml";
	public  void data1(JSONObject js1,LinkedHashMap<String,String> hm) {
		// TODO Auto-generated method stub
		try{
			int num=1;
			int no=1;
			Set<String> keys = js1.keySet();
            String[] values=new String[keys.size()];
            Object[] keysarr = keys.toArray();
			  
			  
            UserTask ustk[]=new UserTask[hm.size()];
            ExclusiveGateway exgway[]=new ExclusiveGateway[hm.size()];
            SequenceFlow sflow[]=new SequenceFlow[hm.size()*3];
           // ArrayList<ExclusiveGateway> exgway=new ArrayList<ExclusiveGateway>();
            //ArrayList<UserTask> ustk=new ArrayList<UserTask>();
            //ArrayList<SequenceFlow> sflow=new ArrayList<SequenceFlow>();
           
            
            
			
			System.out.println("*************: "+ js1);
			String input="1";
			Process ps=new Process("myProcess","My process",true);
			StartEvent stevt=new StartEvent("startevent1","Start"); 
			SequenceFlow sqflow=new SequenceFlow("flow"+num,"startevent"+num,"usertask"+num);
			FlowableTasklistener ftsk=new FlowableTasklistener("complete", "org.flowable.EmailClass");
			EndEvent ndevt=new EndEvent("endevent1","End");
			
			  DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			  docFactory.setNamespaceAware(true);
			  DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			  Document doc = docBuilder.newDocument();
			  Element rootElement = doc.createElement("definitions");
			  rootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns","http://www.omg.org/spec/BPMN/20100524/MODEL");
			  rootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
		      rootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:xsd", "http://www.w3.org/2001/XMLSchema");
		      rootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:bpmndi", "http://www.omg.org/spec/BPMN/20100524/DI");
		      rootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:omgdc", "http://www.omg.org/spec/DD/20100524/DC");
		      rootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:omgdi", "http://www.omg.org/spec/DD/20100524/DI");
		      rootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:flowable", "http://flowable.org/bpmn");
		      rootElement.setAttribute("typeLanguage", "http://www.w3.org/2001/XMLSchema");
		      rootElement.setAttribute("expressionLanguage", "http://www.w3.org/1999/XPath");
		      rootElement.setAttribute("targetNamespace", "http://www.flowable.org/processdef");
			  doc.appendChild(rootElement);
			  
			  Element pros=doc.createElement("process");
			  rootElement.appendChild(pros);
			  pros.setAttribute("id",ps.getId());
			  pros.setAttribute("name",ps.getName());
			  //pros.setAttribute("", arg1);
			  
			//  String str=(String)ps.isExecutable();
			//  attr3.setValue(str);
			 // employee.setAttributeNode(attr3);
			  
			   Element stEvent=doc.createElement("startEvent");
			   pros.appendChild(stEvent);
			   stEvent.setAttribute("id", stevt.getId());
			   stEvent.setAttribute("name",stevt.getName());
			   
			   Element sqFlow1=doc.createElement("sequenceFlow");
			   pros.appendChild(sqFlow1);
			   sqFlow1.setAttribute("id",sqflow.getId());
			   sqFlow1.setAttribute("sourceRef",sqflow.getSourceRef());
			   sqFlow1.setAttribute("targetRef",sqflow.getTargetRef());
        
			   int excounter=1;
			   int i=0;
			   Iterator it = hm.entrySet().iterator();
			      Object[] arr=hm.keySet().toArray();
			      int f =0;
			      int size=hm.size();
			      String lastkey = null;
	            while (it.hasNext()) 
	            {
	            	boolean flag=false;
	                Map.Entry pair = (Map.Entry)it.next();
	                System.out.println(pair.getKey() + " = " + pair.getValue());
	                System.out.println("Key : "+ pair.getKey() );
	                
	                
	                
	                
	                String svalue =  (String) pair.getValue();
                    String[] splitedvalue =  svalue.split("#");
                  
	                   
					   
	              
				   
	                   if(splitedvalue.length>1)
	                   {
	                	  
	                	   ustk[i] =new UserTask("usertask"+num,(String)pair.getKey());
	                	  
	                	
		                   exgway[f]=new ExclusiveGateway("exclusivegateway"+(f+1),"Exclusive Gateway");
		                   sflow[i]=new SequenceFlow("flow"+(num+1),"usertask"+num,exgway[f].getId());
	                	
						   Element uTask=doc.createElement("userTask");
						   pros.appendChild(uTask);
						   uTask.setAttribute("id","usertask"+num);
						   uTask.setAttribute("name",(String)pair.getKey());
						   
					 	   Element extson=doc.createElement("extensionElements");
						   uTask.appendChild(extson);
						   
						   Element tskListner=doc.createElement("flowable:taskListener");
						   extson.appendChild(tskListner);
						   tskListner.setAttribute("event",ftsk.getEvent());
						   tskListner.setAttribute("class",ftsk.getClasss());
						   
						   
						   Element sqFlow=doc.createElement("sequenceFlow");
						   pros.appendChild(sqFlow);
						   sqFlow.setAttribute("id",sflow[i].getId());
						   sqFlow.setAttribute("sourceRef",sflow[i].getSourceRef());
						   sqFlow.setAttribute("targetRef",sflow[i].getTargetRef());
	               
	                
						   Element exGateway=doc.createElement("exclusiveGateway");
						   pros.appendChild(exGateway);
						   exGateway.setAttribute("id",exgway[f].getId());
						   exGateway.setAttribute("name",exgway[f].getName());
						   no++;
						   f++;
	                   }
	                   else if(num==size)
	                   {
	                	   System.out.println("###############################");
	                	   ustk[i] =new UserTask("usertask"+num,(String)pair.getKey());
	                	   lastkey = (String) pair.getKey();
	                	 
	                	
		                   exgway[f]=new ExclusiveGateway("exclusivegateway"+(f+1),"Exclusive Gateway");
		                   sflow[i]=new SequenceFlow("flow"+(num+1),"usertask"+num,exgway[f].getId());
	                	
						   Element uTask=doc.createElement("userTask");
						   pros.appendChild(uTask);
						   uTask.setAttribute("id","usertask"+num);
						   uTask.setAttribute("name",(String)pair.getKey());
						   
					 	   Element extson=doc.createElement("extensionElements");
						   uTask.appendChild(extson);
						   
						   Element tskListner=doc.createElement("flowable:taskListener");
						   extson.appendChild(tskListner);
						   tskListner.setAttribute("event",ftsk.getEvent());
						   tskListner.setAttribute("class",ftsk.getClasss());
						   
						   
						   Element sqFlow=doc.createElement("sequenceFlow");
						   pros.appendChild(sqFlow);
						   sqFlow.setAttribute("id",sflow[i].getId());
						   sqFlow.setAttribute("sourceRef",sflow[i].getSourceRef());
						   sqFlow.setAttribute("targetRef",sflow[i].getTargetRef());
	               
	                
						   Element exGateway=doc.createElement("exclusiveGateway");
						   pros.appendChild(exGateway);
						   exGateway.setAttribute("id",exgway[f].getId());
						   exGateway.setAttribute("name",exgway[f].getName());
						   no++;
						   f++;
						   System.out.println("******************************");
	                   }
	                   else
	                   {
	                	  
	                	   
	                	   ustk[i] =new UserTask("usertask"+num,(String)pair.getKey());
	                	   
	                	  /* if(num==size)
	                	   {
	                	    sflow[i]=new SequenceFlow("flow"+(num+1),"usertask"+num,ndevt.getId());
	                	    Element uTask=doc.createElement("userTask");
							   pros.appendChild(uTask);
							   uTask.setAttribute("id","usertask"+num);
							   uTask.setAttribute("name",(String)pair.getKey());
							   
						 	   Element extson=doc.createElement("extensionElements");
							   uTask.appendChild(extson);
							   
							   Element tskListner=doc.createElement("flowable:taskListener");
							   extson.appendChild(tskListner);
							   tskListner.setAttribute("event",ftsk.getEvent());
							   tskListner.setAttribute("class",ftsk.getClasss());
							   
							   
							   Element sqFlow=doc.createElement("sequenceFlow");
							   pros.appendChild(sqFlow);
							   sqFlow.setAttribute("id",sflow[i].getId());
							   sqFlow.setAttribute("sourceRef",sflow[i].getSourceRef());
							   sqFlow.setAttribute("targetRef",sflow[i].getTargetRef());
							   
							   ConditionExpression cdnex=new ConditionExpression("tFormalExpression");
							   Element cdnExpr=doc.createElement("conditionExpression"); 
							   sqFlow.appendChild(cdnExpr);
							  // cdnExpr.ssetPrefix("$");
							   cdnExpr.setAttribute("xsi:type",cdnex.getXsitype());
							//   cdnExpr.
							   cdnExpr.appendChild(doc.createCDATASection("${input.equals(\"Endtask\")}"));
	                	   }
	                	   else
	                	   {*/
	                		   if(num<=(size-1))
	                		   {
	                		   
	                		   sflow[i]=new SequenceFlow("flow"+(num+1),"usertask"+num,"usertask"+(num+1));
	                		   
	                		   Element uTask=doc.createElement("userTask");
							   pros.appendChild(uTask);
							   uTask.setAttribute("id","usertask"+num);
							   uTask.setAttribute("name",(String)pair.getKey());
							   
						 	   Element extson=doc.createElement("extensionElements");
							   uTask.appendChild(extson);
							   
							   Element tskListner=doc.createElement("flowable:taskListener");
							   extson.appendChild(tskListner);
							   tskListner.setAttribute("event",ftsk.getEvent());
							   tskListner.setAttribute("class",ftsk.getClasss());
							   
							   
							   Element sqFlow=doc.createElement("sequenceFlow");
							   pros.appendChild(sqFlow);
							   sqFlow.setAttribute("id",sflow[i].getId());
							   sqFlow.setAttribute("sourceRef",sflow[i].getSourceRef());
							   sqFlow.setAttribute("targetRef",sflow[i].getTargetRef());
							   
							   ConditionExpression cdnex=new ConditionExpression("tFormalExpression");
							   Element cdnExpr=doc.createElement("conditionExpression"); 
							   sqFlow.appendChild(cdnExpr);
							  // cdnExpr.ssetPrefix("$");
							   cdnExpr.setAttribute("xsi:type",cdnex.getXsitype());
							//   cdnExpr.
							   cdnExpr.appendChild(doc.createCDATASection("${input.equals(\""+arr[num]+"\")}"));
	                		   }
	                	  // }
	                	  
	                   }
	           
	            
	              
	               
				 //  it.remove(); // avoids a ConcurrentModificationException
				   num++;
				   i++;
				   excounter++;    
	            }
	            
	            
	            int numr=hm.size()+2;
	            int nmr=0;
	            int nm=0;
	            Iterator it1 = hm.entrySet().iterator();
	            
	            while(it1.hasNext()) 
	            { 
	            	
	            	
	                Map.Entry pair = (Map.Entry)it1.next();
	              //  System.out.println(pair.getKey() + " = " + pair.getValue());
	               // System.out.println("Key : "+ pair.getKey() );
	                String svalue =  (String) pair.getValue();
                    String[] splitedvalue =  svalue.split("#");
                    
			       if(splitedvalue.length>1 || svalue.equals((String)hm.get(lastkey)))
			       {
			    	  
			    	   if(svalue.equals((String)hm.get(lastkey)))
			    	   {
			    		   String aaa =  (String)hm.get(lastkey);
			    		   System.out.println("aaaaa : "+ aaa+ "svalue : "+ svalue);
			    		   for(int k=0;k<ustk.length;k++)
		                   {
		                	   
		                	  
		                      if(ustk[k].getName().equals(svalue))
		                      {
		                    	// nmr++;
	 	                    	  SequenceFlow sqflow2=new SequenceFlow("flow"+numr,exgway[nmr].getId(),ustk[k].getId()); 
		                    	  Element sqFlow3=doc.createElement("sequenceFlow");
			   					   pros.appendChild(sqFlow3);
			   					   sqFlow3.setAttribute("id",sqflow2.getId());
			   					   sqFlow3.setAttribute("sourceRef",sqflow2.getSourceRef());
			   					   sqFlow3.setAttribute("targetRef",sqflow2.getTargetRef());
		   					   
			   					  ConditionExpression cdnex=new ConditionExpression("tFormalExpression");
								   Element cdnExpr=doc.createElement("conditionExpression"); 
								   sqFlow3.appendChild(cdnExpr);
								  // cdnExpr.ssetPrefix("$");
								   cdnExpr.setAttribute("xsi:type",cdnex.getXsitype());
								//   cdnExpr.
								   cdnExpr.appendChild(doc.createCDATASection("${input.equals(\""+ustk[k].getName()+"\")}"));
								   
								   
								   
								   
								   
								   
								   Element endEvent=doc.createElement("endEvent");
								   pros.appendChild(endEvent);
								   endEvent.setAttribute("id",ndevt.getId());
								   endEvent.setAttribute("name",ndevt.getName());
								   
								   
								//   System.out.println("*********outside while*******:"+nmr);
									   SequenceFlow sqcflow=new SequenceFlow("flow"+(numr+1),exgway[nmr].getId(),ndevt.getId());
							            Element sqFlow4=doc.createElement("sequenceFlow");
										   pros.appendChild(sqFlow4);
										   sqFlow4.setAttribute("id",sqcflow.getId());
										   sqFlow4.setAttribute("sourceRef",sqcflow.getSourceRef());
										   sqFlow4.setAttribute("targetRef",sqcflow.getTargetRef());
									       
										   ConditionExpression cdnex1=new ConditionExpression("tFormalExpression");
										   Element cdnExpr1=doc.createElement("conditionExpression"); 
										   sqFlow4.appendChild(cdnExpr1);
										  // cdnExpr.ssetPrefix("$");
										   cdnExpr1.setAttribute("xsi:type",cdnex1.getXsitype());
										//   cdnExpr.
										   cdnExpr1.appendChild(doc.createCDATASection("${input.equals(\"Endtask\")}"));
										   
								   
								   
								   
								   
								   
								   
		                      }
		                   }
			    	   }
			    	   else
			    	   {
	                for(int j=0;j<splitedvalue.length;j++)
	                {
	            	   String destin = (String) splitedvalue[j];
	                   System.out.println("value : "+ destin);
	                  
	                  
	                   for(int k=0;k<ustk.length;k++)
	                   {
	                	   
	                	  
	                      if(ustk[k].getName().equals(destin))
	                      {
	                    	 
 	                    	  SequenceFlow sqflow2=new SequenceFlow("flow"+numr,exgway[nmr].getId(),ustk[k].getId()); 
	                    	  Element sqFlow3=doc.createElement("sequenceFlow");
		   					   pros.appendChild(sqFlow3);
		   					   sqFlow3.setAttribute("id",sqflow2.getId());
		   					   sqFlow3.setAttribute("sourceRef",sqflow2.getSourceRef());
		   					   sqFlow3.setAttribute("targetRef",sqflow2.getTargetRef());
	   					   
		   					  ConditionExpression cdnex=new ConditionExpression("tFormalExpression");
							   Element cdnExpr=doc.createElement("conditionExpression"); 
							   sqFlow3.appendChild(cdnExpr);
							  // cdnExpr.ssetPrefix("$");
							   cdnExpr.setAttribute("xsi:type",cdnex.getXsitype());
							//   cdnExpr.
							   cdnExpr.appendChild(doc.createCDATASection("${input.equals(\""+ustk[k].getName()+"\")}"));
							   
	                      }
	                   }
	                   numr++;
	                  
					 nm++;
				
	                 }
			    	   }
	                nmr++;
	             System.out.println("*********Inside while*******:"+nmr);
	              //num++;
	             
			       }
			     //  System.out.println("*********Inside1 while*******:"+nmr);
			  	 
	           }//end while
			   
	           
	            
			  
			   
			   
			  
			 
			  TransformerFactory transformerFactory = TransformerFactory.newInstance();
			  
			  Transformer transformer = transformerFactory.newTransformer();
			
			     DOMSource domSource = new DOMSource(doc);
			 
			    StreamResult streamResult = new StreamResult(new File(xmlFilePath));

			    transformer.transform(domSource, streamResult);
			    
			      System.out.println("Done creating XML File");
			      StreamResult consoleResult = new StreamResult(System.out);
			      transformer.transform(domSource, consoleResult);
			}
			catch(ParserConfigurationException pce){
				
				 pce.printStackTrace();
			  }  catch (TransformerException tfe) {
				  
		          tfe.printStackTrace();

		      }


	}

}
