<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>testdata 리스트</title>
</head>
<body>
	<h1>testdata 리스트</h1>

	<c:forEach items="${testdatas}" var="testdata">
		<div>
			idx : ${testdata.idx}
			<br />
			작성날짜 : ${testdata.regDate}
			<br />
			갱신날짜 : ${testdata.lastDate}
			<br />
			제목 : ${testdata.title}
			<br />
			내용 : ${testdata.description}
		</div>
		<hr />
	</c:forEach>



</body>
</html>