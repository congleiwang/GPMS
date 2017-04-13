<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/base.jsp"%>
<!DOCTYPE>
<html>
<head>
<title>GPMS</title>
<link href="${rootPath }/css/login.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
	function checkForm(thisForm) {
		if (document.getElementById('j_username').value.length < 1) {
			document.getElementById('errmsg').innerHTML = "<font color='red'>请输入用户名</font>";
			document.getElementById('j_username').focus();
			return false;
		}

		if (document.getElementById('j_password').value.length < 1) {
			document.getElementById('errmsg').innerHTML = "<font color='red'>请输入密码</font>";
			document.getElementById('j_password').focus();
			return false;
		}
		return true;
	}
</script>
</head>
<body id="body">
	<div id="head"></div>
	<div id="wrapper">
		<div id="wrappertop"></div>
		<form action="${rootPath }/j_spring_security_check" method="post"
			id="LoginForm" name="LoginForm"
			onSubmit="javascript:return checkForm(this);">
			<div id="wrappermiddle">

				<h2>Login</h2>
				<div id="username_input">
					<div id="username_inputleft"></div>
					<div id="username_inputmiddle">
						<input type="text" id="j_username" class="url" name="j_username"
							placeholder="用户名" autofocus="autofocus" /> <span id="url_user"
							alt="请输入用户名" />
					</div>

					<div id="username_inputright"></div>

				</div>

				<div id="password_input">

					<div id="password_inputleft"></div>

					<div id="password_inputmiddle">
						<input type="password" id="j_password" class="url"
							name="j_password" placeholder="密码"> <span
							id="url_password" src="res/images/icon_lock.png"
							alt="请输入密码" />
					</div>

					<div id="password_inputright"></div>

				</div>

				<div id="submit">
					<input type="submit" id="submit1" value="" />
				</div>


				<div id="links_left">

					<a href="#">Forgot your Password?</a>

				</div>

				<div id="links_right">
					<span class="error" id="errmsg"> <c:if test="${param.error}">
							<%if(request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION")!=null){ %>
							<font color='red'><%=request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION") %></font>
							<%} %>
						</c:if>
					</span>
				</div>

			</div>
		</form>
		<div id="wrapperbottom"></div>
		<div id="powered">
			<p>
				Powered by <a href="http://www.premiumfreebies.eu">cl.wang</a>
			</p>
		</div>
	</div>
</body>
</html>