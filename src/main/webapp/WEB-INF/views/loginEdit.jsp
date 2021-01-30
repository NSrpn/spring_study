<html lang="en" xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">
<head>
  <title>User edit</title>
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
  <div id="login_edit">
    <form method="post" th:action="@{/login/register}" th:object="${loginEdit}">
      <img class="login_item" th:src="@{/images/user_64.png}"> <p>Register user!</p>
      <br/>
      <input class="login_item" type="text" placeholder="Title" th:field="*{title}">
      <br/>
      <input class="login_item" type="text" placeholder="Username" th:field="*{username}">
      <br/>
      <input class="login_item" type="password" placeholder="Password" th:field="*{password}">
      <br/>
      <input class="login_item" type="submit" value="Сохранить">
    </form>
  </div>
</body>
</html>
