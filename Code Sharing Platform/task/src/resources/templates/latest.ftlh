<html lang="en">
<head>
    <title>Latest</title>
</head>
<body>
    <table>
        <#list snippets as snippet>
            <tr>
                <td>${snippet.date}</td>
                <td>${snippet.code}</td>
            </tr>
        </#list>
    </table>
</body>
</html>
