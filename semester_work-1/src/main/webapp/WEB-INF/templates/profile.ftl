<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="styles/profile.css">
</head>
<body>
<#include "nav.ftl"/>
<div class="content">
    <div class="user-page">
        <div class="photo-block">
            <#if photo??>
                <img src="img/${photo.getOriginalFileName()}" alt="profile_picture" width="200">
            <#else>
                <img src="img/no_photo.jpg" alt="profile_picture" width="200">
            </#if>
        </div>
        <div class="user-info-block">
            <p class="user-name">Имя: ${user.getName()}</p>
            <p class="user-lastname">Фамилия: ${user.getLastName()}</p>
            <p class="user-email">Email: ${user.getEmail()}</p>
        </div>
    </div>
    <form action="places-list">
        <input class="back-button" type="submit" value="Назад к просмотру мест">
    </form>
    <form action="profile-edit">
        <input class="edit-button" type="submit" value="Редактировать профиль">
    </form>
</div>
</body>
</html>