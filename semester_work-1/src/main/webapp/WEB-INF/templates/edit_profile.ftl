<#import "macro.ftl" as macro>
<@macro.profilePage>
    <div class="edit-form">
        <form method="post" enctype="multipart/form-data" id="updateForm">
            <input type="file" id="avatar" name="avatar" accept="image/*" required/>
            <input class="edit-button" type="submit" value="Обновить фото профиля">
        </form>
    </div>
</@macro.profilePage>