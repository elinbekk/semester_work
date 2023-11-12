<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Регистрация</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link href="styles/registration.css" rel="stylesheet" type="text/css">
    <#--    <script type="application/javascript" src="/js/validation.js"></script>-->
</head>
<body>
<div class="form">
    <div class="form-signin-heading">Регистрация</div>
    <#if message??>
        <div><p>${message}</p></div>
    </#if>
    <form class="form-input" method="post" action="registration">
        <label>
            <input class="form__userdata" id="email-form" name="email" type="email" placeholder="ваш email" required>
        </label>
        <label>
            <input class="form__userdata" id="name-form" name="name" type="text" placeholder="ваше имя" required>
        </label>
        <label>
            <input class="form__userdata" id="lastname-form" name="lastName" type="text" placeholder="ваша фамилия"
                   required>
        </label>
        <label>
            <input class="form__userdata" id="password-form" name="password" type="password" placeholder="ваш пароль"
                   required>
        </label>
        <input class="login-button" type="submit" value="Зарегистрироваться">
    </form>
    <form action="auth"><input class="auth-button" type="submit" value="У меня уже есть аккаунт"></form>
</div>
<script>
    const emailInput = document.getElementById("email-form");
    const passwordInput = document.getElementById('password-form');
    const nameInput = document.getElementById('name-form');
    const lastNameInput = document.getElementById('lastname-form');
    const button = document.querySelector('.login-button');
    const form = document.querySelector(".form-input");
    button.addEventListener('submit', (event) => {
        event.preventDefault();

        const email = emailInput.value;
        const password = passwordInput.value;

        console.log(email);
        if (!isValidEmail(email)) {
            alert('Неверный формат электронной почты');
        }

        if (!isValidPassword(password)) {
            alert('Пароль должен содержать не менее 8 символов(минимум одну прописную букву английского алфавита, строчную букву, одну цифру, спецсимвол)');
        }
        if (isValidPassword() && isValidEmail()) {
            button.form.submit();
        }
    });

    function isValidEmail(email) {
        const pattern = /^[A-Z0-9._%+-]+@[A-Z0-9-]+.+.[A-Z]{2,4}$/i;
        return pattern.test(email);
    }

    function isValidPassword(password) {
        const pattern = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/i;
        return pattern.test(password);
    }
</script>
</body>
</html>