<%@ page contentType="text/html;charset=GBK" %>
<HTML>
<BODY>
<%
if (request.getParameter("b1") != null ){
	session.setAttribute("s4", request.getParameter("b1")); 
}
if (request.getParameter("b2") != null ){
	session.setAttribute("s5", request.getParameter("b2")); 
}
if (request.getParameter("b3") != null ){
	session.setAttribute("s6", request.getParameter("b3")); 
}
%>
�������˦��,һ�ɰ˿�:
<form method="POST" action="buy2.jsp">
  <p><input type="checkbox" name="b1" value="����">����</p>
  <p><input type="checkbox" name="b2" value="����">����</p>
  <p><input type="checkbox" name="b3" value="����">����</p>
  <p><input type="submit" value="�ύ" name="x1">
  <input type="reset" value="ȫ����д" name="B2">
	<a href="buy1.jsp">�����</a>
	<a href="display.jsp">�鿴���ﳵ</a>
  </P>
</FORM>
</BODY>
</HTML>
