<%@ page language="java" %>

<html>
	<head>
	<title>DataPot</title>
	<link rel="stylesheet" type="text/css" href="style.css">
	<script>
	function check(){
	var res;
	var xhttp = new XMLHttpRequest();
	xhttp.open('POST', 'LoginServlet',true);
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhttp.responseType = 'text';
	var str = document.getElementById("username").value;
	var nam = str;
	str = "username="+str;
	xhttp.send(str);
	 xhttp.onreadystatechange = function() {
        if(this.readyState === 4 && this.status === 200) {  
            res = this.responseText;
			// alert(res.slice(0,7)== "appuser");
		if(res.slice(0,7)== "appuser"){
			//alert("index.jsp");
			window.location.assign("index.jsp");	
			}
		else if(res.slice(0,6)== "sfuser"){
			window.location.assign("https://login.salesforce.com/services/oauth2/authorize?response_type=code&"+
				"client_id=3MVG9n_HvETGhr3BzPtvc1N9xjPnbBLekNaC77rhtCZkTZAqFb1rSi5pqi9DcSTTCMiugcrPbGW0tYe_TW42F&"+
				"redirect_uri=http://localhost:8080/ExternalServlet&login_hint="+nam);
				//alert("tosf");
		}else {
		document.getElementById("err").innerHTML = "Invalid User";
		}
		}
		}	 
	}
	</script>
	
	</head>
	
	<body style="background-color:#ededed">
	<div class="user_name" align="center">
	<br>
	<h2>DataPot</h2><br><br>
	<p>Username:</p><br>
	<form>
	<input type="email" name="username" placeholder="eg:admin(or)John Pit" id="username" class="text_box" value=""><br>
	<h6 id="err" style="color:red"></h6><br>
	<input type="button" value="Next" onclick="check()" class="sub-button">
	</form><br><br>
	</div>
	</body>
</html>
	
	