<%--
  Created by IntelliJ IDEA.
  User: oguz
  Date: 02.07.2017
  Time: 15:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Upload Photo</title>
</head>
<body>
<h3>File Upload:</h3>
Select a file to upload: <br />
<form method="POST" action="${pageContext.request.contextPath}/uploadPhotoService" enctype="multipart/form-data">
    <input type="file" name="files" /><br/>
    <input type="file" name="files" /><br/>
    <input type="file" name="files" /><br/>
    <input type="submit" value="Submit" />
</form>

</body>
</html>
