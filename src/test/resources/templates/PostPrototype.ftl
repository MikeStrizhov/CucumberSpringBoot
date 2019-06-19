<?xml version="1.0" encoding="UTF-8"?>
<Pet>
    <id>${msgId}</id>
    <Category>
        <id>0</id>
        <name>string</name>
    </Category>
    <name>${petName}</name>
    <photoUrl>
        <photoUrl>string</photoUrl>
    </photoUrl>
    <tag>
        <#list tag as item>
        <Tag>
            <id>${item}</id>
            <name>string</name>
        </Tag>
        </#list>
    </tag>
    <status>available</status>
</Pet>