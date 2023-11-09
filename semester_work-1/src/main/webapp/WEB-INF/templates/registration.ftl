<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Регистрация</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="styles/reg.css">
<#--    <script type="application/javascript" src="/js/validation.js"></script>-->
</head>
<body>

<div class="container">
    <form class="form" method="post" action="registration">
        <div class="form-signin-heading">Регистрация</div>
        <label>
            <input class="form__email" id="email-form" name="email" type="email" placeholder="ваш email" required>
        </label>
        <label>
            <input class="form__name" id="name-form" name="name" type="text" placeholder="ваше имя" required>
        </label>
        <label>
            <input class="form__lastname" id="lastname-form" name="lastName" type="text" placeholder="ваша фамилия" required>
        </label>
        <label>
            <input class="form__password" id="password-form" name="password" type="password" placeholder="ваш пароль" required>
        </label>
        <input class="login-button" type="submit" value="Зарегистрироваться">
    </form>
    <script>
        const form = document.querySelector('.form');
        const emailInput = form.querySelector('.form__email');
        const passwordInput = form.querySelector('.form__password');
        const nameInput = form.querySelector('.form__name');
        const lastNameInput = form.querySelector('.form__lastname');

        form.addEventListener('submit', (evt) => {
            evt.preventDefault();

            const email = emailInput;
            const password = passwordInput;
            const name = nameInput;
            const lastName = lastNameInput;

            if (!email || !password || !name || !lastName) {
                alert('Пожалуйста, заполните все поля');
                return;
            }

            if (!isValidLogin(email.value)) {
                alert('Неверный формат электронной почты');
            }

            if (!isValidPassword(password)) {
                alert('Пароль должен быть длиной не менее 8 символов и содержать как минимум одну заглавную букву, одну строчную букву, одну цифру, спецсимвол');
            }
            form.submit();
        });

        function isValidLogin(login) {
            const pattern = /^[A-Z0-9._%+-]+@[A-Z0-9-]+.+.[A-Z]{2,4}$/i;
            return pattern.test(login);
        }

        function isValidPassword(password) {
            const pattern = /^(?=.*[A-Z].*[A-Z])(?=.*[!@#$&*])(?=.*[0-9].*[0-9])(?=.*[a-z].*[a-z].*[a-z]).{8,}$/;
            return pattern.test(password);
        }
    </script>
    <form action="auth"><input class="auth-button" type="submit" value="У меня уже есть аккаунт"></form>
</div>
</body>
</html>