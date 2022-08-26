<h5 id="description">Description</h5>

<p>It is difficult to imagine a web application without the database. Imagine that every time you quit the program,
all the data just disappears into thin air: no one wants that to happen! In this stage, we will make sure no data gets 
lost and store all the uploaded code snippets in the database for further reference. </p>

<p>We recommend you use the H2 database in the disk-based storage mode, not in-memory. 
Here are the dependencies you need for this database:</p>

<pre class="language-json">runtimeOnly 'com.h2database:h2'
implementation 'org.springframework.boot:spring-boot-starter-data-jpa'</pre>

<p>Use the following configuration in your project:</p>

<pre class="language-json">spring.datasource.url=jdbc:h2:file:../snippets
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password

spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update

spring.h2.console.enabled=true
spring.h2.console.settings.trace=false
spring.h2.console.settings.web-allow-others=false</pre>


<p>Notice how every code snippet is just black. It can be quite hard to read without syntax highlighting. 
Luckily, there is a library called highlight.js that can help you fix this. See the usage section to understand how to
use it. Note that in order for it to work, your code snippets should be inside the tags 
<code class="language-json">&lt;pre>&lt;code>...&lt;/code>&lt;/pre></code>, so make sure your code snippets are 
generated just like that. Put the following lines inside the <code class="language-json">&lt;head></code>
 tags of your HTML, and viola: every code snippet is now highlighted!</p>

<pre class="language-json">&lt;link rel="stylesheet"
       href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
&lt;script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js">&lt;/script>
&lt;script>hljs.initHighlightingOnLoad();&lt;/script></pre>

<h5 id="description">Objectives</h5>
<p>In this stage, your program should support all the same endpoints as in the previous stage. 
There are two differences: first, all the saved code snippets should be available after reloading the server, and 
second, they should be highlighted.</p>

<ul>
	<li>
        <code class="language-json">GET /api/code/N</code>should return JSON with the N-th uploaded code snippet.
    </li>
	<li>
        <code class="language-json">GET /code/N</code>should return HTML that contains the N-th uploaded code snippet.
    </li>
	<li>
        <code class="language-json">POST /api/code/new</code>should take a JSON object with a single field
        <code class="language-json">code</code>,  use it as the current code snippet, and return JSON with
        a single field<code class="language-json">id</code>. ID is the unique number of the snippet that helps you 
        access it via the endpoint <code class="language-json">GET /code/N</code>.
    </li>
	<li>
        <code class="language-json">GET /code/new</code>should be the same as in the previous stage.
    </li>
	<li>
        <code class="language-json">GET /api/code/latest</code>should return a JSON array with 10 most recently uploaded
        code snippets sorted from the newest to the oldest. 
    </li>
	<li>
        <code class="language-json">GET /code/latest</code>should return HTML that contains 10 most recently uploaded
        code snippets. Use the title<code class="language-json">Latest</code> for this page.
    </li>
</ul>

<h5 id="examples">Examples</h5>
<p>In the following examples, consider that no code snippets have been uploaded beforehand.</p>
<p><strong>Example 1:</strong></p>
<p>Request:<code class="language-json">POST /api/code/new</code> with the following body:<br>
<pre><code class="language-html">{ "code": "class Code { ..." }</code></pre>
<p>Response:<code class="language-json">{ "id" : "1" }</code>.</p>
<p>Another request<code class="language-json">POST /api/code/new</code> with the following body:</p>
<pre><code class="language-html">{ "code": "public static void ..." }</code></pre>
<p>Response:<code class="language-json">{ "id" : "2" }</code>.</p>

<p><strong>Example 2: </strong></p>
<p>Request:<code class="language-json">GET /api/code/1</code><br>Response:</p>

<pre><code class="language-html">{
    "code": "class Code { ...",
    "date": "2020/05/05 11:59:12"
}</code></pre>

<p><strong>Example 3: </strong></p>
<p>Request:<code class="language-json">GET /api/code/2</code>
<br>Response:
<pre><code class="language-html">{
    "code": "public static void ...",
    "date": "2020/05/05 12:00:43"
}</code></pre>

<p><strong>Example 4: </strong></p>
<p>Request:<code class="language-json">GET /api/code/latest</code>
<br>Response:
<pre><code class="language-html">[
    {
        "code": "public static void ...",
        "date": "2020/05/05 12:00:43"
    },
    {
        "code": "class Code { ...",
        "date": "2020/05/05 11:59:12"
    }
]</code></pre>

<p><strong>Example 5: </strong></p>
<p>Request:<code class="language-json">GET /code/latest</code><br>
Response:
<img src="https://ucarecdn.com/30756489-c79d-48df-b837-6f2a14f5a996/">
