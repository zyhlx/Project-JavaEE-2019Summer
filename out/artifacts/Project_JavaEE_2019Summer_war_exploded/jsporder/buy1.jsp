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
各种肉大甩卖,一律十块:<br>
<FORM METHOD="POST" action="buy1.jsp">
  <p><input type="checkbox" name="c1" value="猪肉">猪肉</p>
  <p><input type="checkbox" name="c2" value="牛肉">牛肉</p>
  <p><input type="checkbox" name="c3" value="羊肉">羊肉</p>
  <p><input type="submit" value="提交" name="B1">
  <input type="reset" value="全部重写" name="B2">
	<a href="buy2.jsp">买点别的</a>
	<a href="display.jsp">查看购物车</a> </P>
</FORM>
</BODY>
</HTML>
