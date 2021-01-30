<html lang="en" xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">
<head>
  <title>Login</title>
  <meta charset="UTF-8">
  <style>
    #login_form {
      position: absolute;
      left: 50%;
      top: 50%;
      transform: translate(-50%, -50%);
    }

    .login_item {
      margin-bottom: 10px;
    }
  </style>
</head>
<body>
  <div id="login_form">
    <form method="post" th:action="@{/login/auth}" th:object="${loginForm}">
      <img class="login_item" th:src="@{/images/book_icon.png}">
      <br/>
      <input class="login_item" type="text" placeholder="enter username" th:field="*{username}">
      <br/>
      <input class="login_item" type="password" placeholder="password" th:field="*{password}">
      <br/>
      <input class="login_item" type="submit" value="login">
      <a href="/login/register">Register<a>
    </form>
  </div>
</body>
</html>
