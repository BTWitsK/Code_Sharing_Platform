<html lang="en">
<head>
    <title>Latest</title>
</head>
<body>
    <table>
        <#list snippets as snippet>
            <tr>
                <td><span id="load_date">${snippet.date}</span></td>
                <td><pre id="code_snippet"><code>${snippet.code}</code></pre></td>
            </tr>
        </#list>
    </table>
</body>
</html>
