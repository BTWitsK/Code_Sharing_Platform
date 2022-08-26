<h5 id="description">Description</h5>

<p>Now that we have a good basis, let's add some big improvements to our program. 
First, sharing and showing the same piece of code over and over is not going to get you far. 
Let's improve our platform by implementing the feature that will allow us to upload a piece of code.</p>


<p>In this stage, your program must store one piece of code and display it both through the API and the web 
requests just like in the previous stage. This time, it should be possible to change this piece of code with a 
<code class="language-json">POST /api/code/new</code>request.</p>

<p>Let's add another useful feature: showing the date when this particular piece of code was last updated. 
In other words, the API and web requests should include the date.</p>

<p>Before getting to work, take a look at the Java tutorial about formatting dates. 
In your code, you can use any pattern.</p>


<h5 id="description">Objectives</h5>
<p>In this stage, you need to implement the following endpoints.</p>

<ul>
	<li>
        <code class="language-json">GET /api/code</code> should return JSON with the following fields:
        <ol>
            <li><code class="language-json">code</code> that contains the same piece of code;</li>
            <li><code class="language-json">date</code> that contains the date when this piece of code was loaded.</li>
        </ol>
    </li>
    <li> 
        <code class="language-json">GET /code</code> return HTML that contains: 
        <ol>
            <li>
                Tags <code class="language-json">&lt;pre id="code_snippet">... &lt;/pre></code> 
                with the piece of code inside; 
            </li>
            <li>Title<code class="language-json">Code</code>;</li>
            <li>
                Date when this piece of code was loaded inside the tags 
                <code class="language-json">&lt;span id="load_date"> ... &lt;/span></code>.
            </li>
        </ol>
    </li>
</ul>

<p>The code snippet and the date in the API and the web requests should be the same.</p>

<ul>
    <li>
        <code class="language-json">POST /api/code/new</code>should take a JSON object with a single field
        <code class="language-json">code</code>, use it as the 
        current code snippet, and return an empty JSON. In the upcoming stages, it won't be empty.
    </li>
    <li>
        <code class="language-json">GET /code/new</code>should return HTML that contains:
    </li>
</ul>
<ol>
    <li>
        Tags <code class="language-json">&lt;textarea id="code_snippet"> ... &lt;/textarea></code> 
        where you can paste the code snippet; 
    </li>
    <li>Title<code class="language-json">Create</code>;</li>
    <li>
        Date when this piece of code was loaded inside the tags 
        <code class="language-json">&lt;button id="send_snippet" type="submit" onclick="send()">
        Submit&lt;/button></code>.
    </li>
</ol>
<code class="language-json"></code>
<p>Note:<code class="language-json">form</code> doesn't send your data in the JSON format, so you need to specify a special function that does it. 
You can use the XMLHttpRequest to do that. Here's the function:</p>

<pre class="language-json">function send() {
    let object = {
        "code": document.getElementById("code_snippet").value
    };
    
    let json = JSON.stringify(object);
    
    let xhr = new XMLHttpRequest();
    xhr.open("POST", '/api/code/new', false)
    xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    xhr.send(json);
    
    if (xhr.status == 200) {
      alert("Success!");
    }
}</pre>

<p>Don't forget about id's in the tags: they are used to test your code.</p>
<p>Use CSS to customize your HTML as you like it. Customization won't be checked in tests.</p>

<h5 id="examples">Examples</h5>

<p><strong>Example 1:</strong></p>
<p>Request:<code class="language-json">GET /code</code><br>
<p>Response:</p>

<img src="https://ucarecdn.com/4bd5a473-6cfc-4dc5-95cc-349f0ad02950/">

<p><strong>Example 2: </strong></p>
<p>Request:<code class="language-json">GET /api/code</code><br>Response:</p>

<pre><code class="language-html">{
    "code": "public static void ...",
    "date": "2020/01/01 12:00:03"
}</code></pre>

<p><strong>Example 3: </strong></p>
<p>Request:<code class="language-json">POST /api/code/new</code>with the following body
<pre><code class="language-html">{
    "code": "class Code { ..."
}</code></pre>
Response: <code class="language-json">{}</code>.<br>
After that,<code class="language-json">GET /code</code>should contain an updated snippet with an updated date:

<img src="https://ucarecdn.com/0e471b2b-6aca-4226-9a9e-ae0b57e58ede/">

<p><strong>Example 4: </strong></p>
<p>Request:<code class="language-json">GET /code/new</code><br>
Response:
<img src="https://ucarecdn.com/406b6e37-9585-4746-856b-8b2af0a34140/">