<#-- @ftlvariable name="user" type="ru.ifmo.jbinternship.oauth2.domain.UserProfile" -->
<#-- @ftlvariable name="error" type="java.lang.String" -->


<#import "/spring.ftl" as spring/>
<#macro page>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>OAuth2.0 Google Example</title>
    </head>
    <body>
    <header>
        <a href="/">Home</a> | <#if user??>Logged in as <b>${user.getName()}</b> <#else>Not logged in</#if>
    </header>
    <div class="middle">
        <main>
            <#nested/>
        </main>
    </div>
    <#if error??>
        <p style="color: red">${error}</p>
    </#if>
    </body>
    </html>
</#macro>
