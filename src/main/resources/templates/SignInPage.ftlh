<#import "commons.ftlh" as c>

<@c.page>
    <#if user??>
        <p>You are already signed in as ${user.getName()}.</p>
        <a href="/">Proceed to home page</a>
    <#else>
        <a href="/signinAction">Sign in using Google Account</a>
    </#if>
</@c.page>