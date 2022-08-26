<h5 id="description">Description</h5>

<p>Sometimes, it's useful to have a tool that can help you share a piece of code with other programmers. 
Actually, there is a website called Pastebin that does exactly that. 
A huge downside of Pastebin is that every piece of code you share automatically becomes available for the public. 
This could present a problem since many programmers work under the NDA (Non-disclosure agreement)
which prohibits the use of such services to prevent the source code from leaking.</p>

<p>If there is a team of programmers who work in the same company and want to exchange pieces of code,
they can solve this problem by using their own implementation of Pastebin. 
Such a web service is supposed to be accessible only locally, not via the Internet. 
In this project, you will write a service just like that.</p>

<p>As you're working on the project, you will implement two types of interfaces: API and web interface.
The API interface should be accessed through endpoints that start with <code class="language-json">/api</code> 
while web interface endpoints should start with <code class="language-json">/</code>. 
The API interface should return data as <code class="language-json">JSON</code>, while the web interface should use
<code class="language-json">HTML</code>, <code class="language-json">JS</code>, 
and<code class="language-json">CSS</code>.</p>



<h5 id="description">Objectives</h5>
<p>In the first stage, you need to develop a program that returns the same
piece of code via the API and the web interface.</p>
<p>Implement two endpoints:</p>

<ul>
	<li>
        <code class="language-json">GET /code</code> that should:
        <ol>
            <li>Return HTML that contains tags <code class="language-json">&lt;pre></code> with a piece of code
                inside and the title <code class="language-json"> Code</code>;
            </li>
            <li>Respond with a header<code class="language-json">Content-Type</code> with the value
                <code class="language-json">text/html</code>.
            </li>
        </ol>
    </li>
    <li> 
        <code class="language-json">GET /api/code </code> that should: 
        <ol>
            <li>
                Return a JSON object with the single field<code class="language-json">code</code> that contains the same
                piece of code.
            </li>
            <li>Respond with a header<code class="language-json">Content-Type</code> with the value
                <code class="language-json">application/json</code>.
            </li>
        </ol>
    </li>
</ul>

<p>In the upcoming stages,<code class="language-json">Content-Type</code>of all the API endpoints 
should<code class="language-json">application/json</code>, and of all the web endpoints should be equal to
<code class="language-json">text/html</code>.</p>

<p>Try to avoid duplicating the piece of code you return. Use the same object to construct both requests.</p>
<p>If you need some extra guidance on setting headers to the response, check out a comprehensive tutorial at baeldung.com.</p>

<h5 id="examples">Examples</h5>

<p><strong>Example 1: </strong><code class="language-json">POST /api/recipe</code> request with the following body:</p>
<p>Request:<code class="language-json">GET /code</code><br>
Response (you can use your own piece of code:</p>
<pre class="language-json">&lt;html> 
    &lt;head>
        &lt;title>Code&lt;/title>
    &lt;/head>
    &lt;body>
        &lt;pre>
    public static void main(String[] args) {
        SpringApplication.run(CodeSharingPlatform.class, args);
    }&lt;/pre>
    &lt;/body>
    &lt;/html></pre>
<p>Notice that the piece of code doesn't contain spaces before the lines:<code class="language-json">&lt;pre></code>
tags render such spaces, so keep that in mind.</p>

<p><strong>Example 2: </strong></p>
<p>Request:<code class="language-json">GET /api/code</code><br>Response:</p>

<pre><code class="language-html">{
    "code": "public static void main(String[] args) {\n    SpringApplication.run(CodeSharingPlatform.class, args);\n}"
}</code></pre>