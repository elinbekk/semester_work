<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<#include "nav.ftl"/>
<div class="user-page">
    <p class="user-name">Имя: ${user.getName()}</p>
    <p class="user-lastname">Фамилия: ${user.getLastName()}</p>
    <p class="user-email">Email: ${user.getEmail()}</p>

    <div class="edit-form">
        <#--        <label>-->
        <#--            <input type="text" class="edit-name" value="${user.getName()}">-->
        <#--        </label>-->
        <#--        <label>-->
        <#--            <input type="text" class="edit-lastname" value="${user.getLastName()}">-->
        <#--        </label>-->
        <#--        <label>-->
        <#--            <input type="text" class="edit-email" value="${user.getEmail()}">-->
        <#--        </label>-->
        <#--        <label for="avatar">Choose a profile picture:</label>-->
        <form action="edit" method="post" enctype="multipart/form-data">
            <input type="file" id="avatar" name="avatar" accept="image/*"/>
            <input class="edit-button" type="submit" value="Обновить фото профиля">
        </form>
    </div>
    <#--<button class="button-delete" value="${place.placeId}">
        <i class="heart"></i>
        <span>delete</span>
    </button>-->
</div>
<#--<script>
    $(".button-delete").on('click', function () {
        let placeId = $(this).val().toString();

        $.ajax({
            type: "POST",
            url: "",
            data: {"placeId": placeId},
            success: function (result) {
                $('#'+placeId).hide(300);
                console.log(result);
            }
        });
    });
</script>-->
</body>
</html>