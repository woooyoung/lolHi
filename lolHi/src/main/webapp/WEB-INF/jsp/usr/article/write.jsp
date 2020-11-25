<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" value="${board.name} 게시물 작성" />
<%@ include file="../part/head.jspf"%>
<div class="write-box con-min-width">
	<div class="con">
		<form class="form-box-type-1" action="doWrite" method="POST">
			<div>
				<div>
					<span>제목</span>
				</div>
				<div>
					<input type="text" maxlength="30" placeholder="제목을 입력해주세요."
						name="title" />
				</div>
			</div>
			<div>
				<div>
					<span> 내용</span>
				</div>
				<div>
					<textarea maxlength="2000" placeholder="내용을 입력해주세요." name="body"></textarea>
				</div>
			</div>
			<div>
				<div>
					<span> 제출</span>
				</div>
				<div>
					<input type="submit" value="작성" />
				</div>
			</div>
		</form>
	</div>
</div>
<div class="sub-menu-bar con-min-width">
	<div class="con">
		<a href="list">리스트</a>
	</div>
</div>
<%@ include file="../part/foot.jspf"%>