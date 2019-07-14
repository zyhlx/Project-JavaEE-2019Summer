<%@ page contentType="text/html;charset=GBK" %>
<HTML>
<BODY>
你选择的结果是: <center>
  <%
  String str = "";
  if(session.getAttribute("s1") != null){
	  str = (String)session.getAttribute("s1");
	  byte  b[]=str.getBytes("ISO-8859-1");
     str=new String(b);
	 out.print(str + "<br>");
  }
  if(session.getAttribute("s2")!= null){
	  str = (String)session.getAttribute("s2");
	  byte  b[]=str.getBytes("ISO-8859-1");
     str=new String(b);
	 out.print(str + "<br>");
  }
  if(session.getAttribute("s3")!=null){
	  str =  (String)session.getAttribute("s3");
	  byte  b[]=str.getBytes("ISO-8859-1");
     str=new String(b);
	 out.print(str + "<br>");
  }
  if(session.getAttribute("s4")!=null){
	  str =  (String)session.getAttribute("s4");
	  byte  b[]=str.getBytes("ISO-8859-1");
     str=new String(b);
	 out.print(str + "<br>");
  }
  if(session.getAttribute("s5")!=null){
	  str =  (String)session.getAttribute("s5");
	  byte  b[]=str.getBytes("ISO-8859-1");
     str=new String(b);
	 out.print(str + "<br>");
  }
  if(session.getAttribute("s6")!=null){
	  str =  (String)session.getAttribute("s6");
	  byte  b[]=str.getBytes("ISO-8859-1");
     str=new String(b);
	 out.print(str + "<br>");
  }
  %>  </center>
</BODY>
</HTML>
