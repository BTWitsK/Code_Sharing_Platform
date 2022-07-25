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
<#if snippet.isRestricted()>
<p>${snippet.views} more views allowed</p>
<p>The code will be available for ${snippet.seconds} seconds</p>
</#if>
<span id="load_date">${snippet.date}</span>
<pre id="code_snippet"><code>${snippet.code}</code></pre>
</body>
</html>