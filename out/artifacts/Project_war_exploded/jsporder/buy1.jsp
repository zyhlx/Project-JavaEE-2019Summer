<%@ page contentType="text/html;charset=GBK" %>
<HTML>
<BODY>
<%
if (request.getParameter("c1") != null ){
	session.setAttribute("s1", request.getParameter("c1")); 
}
if (request.getParameter("c2") != null ){
	session.setAttribute("s2", request.getParameter("c2")); 
}
if (request.getParameter("c3") != null ){
	session.setAttribute("s3", request.getParameter("c3")); 
}
%>
�������˦��,һ��ʮ��:<br>
<FORM METHOD="POST" action="buy1.jsp">
  <p><input type="checkbox" name="c1" value="����">����</p>
  <p><input type="checkbox" name="c2" value="ţ��">ţ��</p>
  <p><input type="checkbox" name="c3" value="����">����</p>
  <p><input type="submit" value="�ύ" name="B1">
  <input type="reset" value="ȫ����д" name="B2">
	<a href="buy2.jsp">�����</a>
	<a href="display.jsp">�鿴���ﳵ</a> </P>
</FORM>
</BODY>
</HTML>
