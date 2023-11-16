<#import "macro.ftl" as macro>
<@macro.profilePage>
    <div class="button-block">
        <form action="places-list" method="get">
            <input class="back-button" type="submit" value="Назад к просмотру мест">
        </form>
        <form action="profile-edit">
            <input class="edit-button" type="submit" value="Редактировать профиль">
        </form>
    </div>
</@macro.profilePage>