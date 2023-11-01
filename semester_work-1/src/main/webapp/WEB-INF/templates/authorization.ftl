<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Авторизация</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="styles/reg.css">
</head>
<body>
<div class="container">
    <form class="form-center-content" method="post">
        <div class="form-signin-heading">Вход</div>
        <label>
            <input class="form-control" name="email" type="email" placeholder="ваш email" required>
        </label>
        <label>
            <input class="form-control" name="password" type="password" placeholder="ваш пароль" required>
        </label>
        <label>
            <input class="remember-me" type="checkbox" name="remember" value="on">Запомнить меня
        </label>
        <input class="auth-button" type="submit" value="Авторизоваться">
    </form>
    <form action="registration">
        <input class="login-button" type="submit" value="Назад на страницу регистрации">
    </form>
</div>
</body>
</html>