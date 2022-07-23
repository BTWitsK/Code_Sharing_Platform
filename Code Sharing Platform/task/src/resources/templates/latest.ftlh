<html lang="en">
<head>
    <title>Latest</title>
</head>
<body>
    <table>
        <#list snippets as snippet>
            <tr>
                <td>${snippet.date}</td>
                <td>${snippet.name}</td>
            </tr>
        </#list>
    </table>
</body>
</html>
