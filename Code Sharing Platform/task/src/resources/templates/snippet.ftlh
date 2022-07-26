<!DOCTYPE html>
<html lang="en">
<head>
    <title>Code</title>
    <link rel="stylesheet"
          href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
<span id="load_date">${snippet.getFormattedDate()}</span>
<#if snippet.isRestricted()>
    <#if snippet.restriction == "TimeAndViews">
        <span id="time_restriction">The code will be available for ${snippet.time} seconds</span>
        <span id="views_restriction">${snippet.views} more views allowed</span>
    <#elseif snippet.restriction == "Time">
        <span id="time_restriction">The code will be available for ${snippet.time} seconds</span>
    <#elseif snippet.restriction == "Views">
        <span id="views_restriction">${snippet.views} more views allowed</span>
    </#if>
</#if>
</div>
<pre id="code_snippet" style="background-color:#9facab;
border-radius:4px;
font-size:16px;
padding:15px;
margin:5px;"><code>${snippet.code}</code></pre></p>
</body>
</html>