const form = document.querySelector('.form-center-content');
const loginInput = form.getElementById('email-form').value;
const passwordInput = form.getElementById('password-form').value;

console.log(loginInput);
console.log(passwordInput);

form.addEventListener('submit', (evt) => {
    evt.preventDefault();

    const login = loginInput;
    const password = passwordInput;

    if (!login || !password) {
        alert('Пожалуйста, заполните все поля');
        return;
    }

    if (!isValidLogin(login)) {
        alert('Логин может содержать только буквы на латинице и цифры');
        return;
    }

    if (!isValidPassword(password)) {
        alert('Пароль должен содержать как минимум одну заглавную букву, одну строчную букву и одну цифру');
        return;
    }


    form.submit();
});

function isValidLogin(login) {
    const pattern = /^[a-zA-Z0-9]+$/;
    return pattern.test(login);
}

function isValidPassword(password) {
    const pattern = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[a-zA-Z0-9]{8,20}$/;
    return pattern.test(password);
}