<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title></title>
    <script type="text/javascript" src="/syntaxhighlighter/src/shCore.js"></script>
    <script type="text/javascript" src="/syntaxhighlighter/scripts/shBrushJava.js"></script>
    <link type="text/css" rel="stylesheet" href="/syntaxhighlighter/styles/shCoreDefault.css"/>
    <script type="text/javascript">SyntaxHighlighter.all();</script>
</head>
<body>
<%
    String log = (String) request.getAttribute("log");
%>
<script type="syntaxhighlighter" class="brush: java;">
<![CDATA[
<%=log%>
]]>
</script>

</body>
</html>

