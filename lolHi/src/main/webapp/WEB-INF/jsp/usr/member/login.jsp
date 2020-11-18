<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" value="회원 로그인" />
<%@ include file="../part/head.jspf"%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>
<script>
	var loginFormSubmitDone = false;
	function loginFormSubmit(form) {
		if (loginFormSubmitDone) {
			alert('처리중입니다.');
			return;
		}
		form.loginId.value = form.loginId.value.trim();
		if (form.loginId.value.length == 0) {
			alert('로그인 아이디를 입력해주세요.');
			form.loginId.focus();
			return;
		}
		form.loginPw.value = form.loginPw.value.trim();
		if (form.loginPw.value.length == 0) {
			alert('로그인 비번을 입력해주세요.');
			form.loginPw.focus();
			return;
		}

		alert(form.loginPw.value);
		form.loginPw.value = sha256(form.loginPw.value);
		alert(form.loginPw.value);
		
		form.submit();
		loginFormSubmitDone = true;
	}
</script>
<form action="doLogin" method="POST"
	onsubmit="loginFormSubmit(this); return false;">
	<div>
		로그인 아이디 : <input type="text" maxlength="30"
			placeholder="로그인 아이디를 입력해주세요." name="loginId" />
	</div>
	<div>
		로그인 비번 : <input type="password" maxlength="30"
			placeholder="로그인 비번을 입력해주세요." name="loginPw" />
	</div>
	<div>
		로그인 : <input type="submit" value="로그인" />
	</div>
</form>
<%@ include file="../part/foot.jspf"%>