<#import "commons.ftlh" as c>

<@c.page>
    <#if user??>
        <p>Welcome, ${user.getName()}! This is a home page.</p>
        <a href="/logout">Logout</a>
    <#else>
        <p>Welcome, new user! This is a home page.</p>
        <a href="/signin">Sign in</a>
    </#if>
</@c.page>