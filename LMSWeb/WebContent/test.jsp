<%@include file="boot.htm" %>


<script>
var root = 'https://jsonplaceholder.typicode.com';
$(document).ready(function(){
    $("button").click(function(){
    	$.ajax({
    		  url: root + '/posts/1',
    		  method: 'GET',
    		  
    		}).done(function(data) {
    		  console.log(data);
    		});
    	$.ajax('http://jsonplaceholder.typicode.com/posts', {
    		  method: 'POST',
    		  data: {
    		    title: 'foo',
    		    body: 'bar',
    		    userId: 1
    		  }
    		}).then(function(data) {
    		  console.log(data);
    		});
    });
});
</script>
</head>
<body>

<div id="div1"><h2>Let jQuery AJAX Change This Text</h2></div>

<button>Get External Content</button>
