var cnt=1;
	function sortfunc(n){
			document.getElementById("val").value=n;
		var xhttp = new XMLHttpRequest();
 	xhttp.open('POST', 'ProcessServlet',true);
 	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
 	var form = document.getElementById("sortForm");
 	var formData = new FormData(form);
 	var txt = "sort="+formData.get("sort")+"&option="+formData.get("option");
 	xhttp.send(txt);
 	xhttp.onreadystatechange = function() {

         if(this.readyState === 4 && this.status === 200) {

              document.getElementById("iframeTable").src += "";
         }
     };
 	}

	function searchfunc(){
 	var form = document.getElementById("searchForm");
 	$.ajax({
		type:"POST",
		url:"ProcessServlet",
		data:$("#searchForm").serialize(),
		success: function(data,status){
			alert(data);
			alert($("#searchForm").serialize());
			document.getElementById("iframeTable").src += "";
		}
	});
 	}

	function addsearchbar(pos){
		var div = document.createElement('DIV');

	div.setAttribute("id","div"+cnt);
	if(cnt>1)
		div.appendChild(addoperator(cnt));
	div.appendChild(addColumn(cnt));
	if(pos<cnt){
		document.getElementById("div"+pos).insertAdjacentElement("afterend",div);
	}
	else{
	document.getElementById("searchbar").appendChild(div);
	}
	document.getElementById("count").value = cnt;
	}

	function addoperator(a){
		if(a!='1'){
		var col = document.createElement("SELECT");
		col.setAttribute("name","operator"+a);
		col.setAttribute("id","operator"+a);
		var opt = document.createElement("OPTION");
		opt.innerHTML = "Choose an Option";
		col.appendChild(opt);
		opt = document.createElement("OPTION");
		opt.setAttribute("value","AND");
		opt.innerHTML = "AND";
		col.appendChild(opt);
		opt = document.createElement("OPTION");
		opt.setAttribute("value","OR");
		opt.innerHTML = "OR";
		col.appendChild(opt);
		return col
		}

	}

	function addColumn(a){
		var val = ["oid","oname","oenroll","odept","ocollege","ogender","oemail","ophone","ophy","ochem","comp","omath","oeng"];
		var inr = ["StudentID","Name","Enroll No","Dept","College","Gender","Email","Phone","Physics","Chemistry","Computer","Mathematics","English"];

		var col = document.createElement("SELECT");
		col.setAttribute("name","column"+a);
		col.setAttribute("id","column"+a);
		col.setAttribute("onchange","checktype("+a+",true)");
		var opt = document.createElement("OPTION");
		opt.innerHTML = "Choose an Option";
		col.appendChild(opt);

		for(var i=0;i<13;i++){
		opt = document.createElement("OPTION");
		opt.setAttribute("value",val[i]);
		opt.innerHTML = inr[i];
		col.appendChild(opt);
		}
		return col;
	}

	function stringcomp(a){

		var div = document.getElementById("div"+a);
		var val = ["equals","notequals","contains","startswith","endswith"];
		var inr = ["Equals","NotEqual","Contains","Starts With","Ends With"];

		var col = document.createElement("SELECT");
		col.setAttribute("name","comparatorstring"+a);
		col.setAttribute("id","comparatorstring"+a);
		var opt = document.createElement("OPTION");
		opt.innerHTML = "Choose an Option";
		col.appendChild(opt);

		for(var i=0;i<5;i++){
		opt = document.createElement("OPTION");
		opt.setAttribute("value",val[i]);
		opt.innerHTML = inr[i];
		col.appendChild(opt);
		}
		div.appendChild(col);

		addinputs(a);

	}

	function intcomp(a){

		var div = document.getElementById("div"+a);
		var val = ["equals","notequals","greater","lesser"];
		var inr = ["Equals","NotEqual","Greater Than","Lesser Than"];

		var col = document.createElement("SELECT");
		col.setAttribute("name","comparatorint"+a);
		col.setAttribute("id","comparatorint"+a);
		var opt = document.createElement("OPTION");
		opt.innerHTML = "Choose an Option";
		col.appendChild(opt);

		for(var i=0;i<4;i++){
		opt = document.createElement("OPTION");
		opt.setAttribute("value",val[i]);
		opt.innerHTML = inr[i];
		col.appendChild(opt);
		}
		div.appendChild(col);

		addinputs(a);

	}

	function addinputs(a){

		var div = document.getElementById("div"+a);
		var inp = document.createElement("INPUT");
		inp.setAttribute("type","text");
		inp.setAttribute("name","value"+a);
		inp.setAttribute("id","value"+a);
		inp.setAttribute("value","");
		inp.style.display = "inline";
		div.appendChild(inp);

		var btn = document.createElement("INPUT");
		btn.setAttribute("type","button");
		btn.setAttribute("id","addbutton"+a);
		btn.setAttribute("onclick","addsearchbar("+a+")");
		btn.setAttribute("value","Add");
		btn.style.display = "inline";
		div.appendChild(btn);

		/*if(a!='1'){
	 btn = document.createElement("INPUT");
	 btn.setAttribute("type","button");
	 btn.setAttribute("id","delbutton"+a);
	 btn.setAttribute("onclick","delsearchbar("+a+")");
	 btn.setAttribute("value","Remove");
	 btn.style.display = "inline";
	 div.appendChild(btn);
 }*/
		cnt++;
	}

	function checktype(val,bol){
		var value = document.getElementById("column"+val).value;
		if(cnt>val && Boolean(bol)){
		var obj = document.getElementById("div"+val);
		obj.removeChild(obj.childNodes[obj.childNodes.length - 3]);
		obj = document.getElementById("value"+val);
		obj.remove();
		obj = document.getElementById("addbutton"+val);
		obj.remove();

		cnt--;
		}
		if(value == 'oenroll' || value == 'ophy' || value == 'ochem' || value == 'ocomp' || value == 'omath' || value == 'oeng'){
			intcomp(val);
		}
		else{
			stringcomp(val);
		}
	}

	/* function divorder(){
		var i, q=1;
		var c = document.getElementById("searchbar").childNodes;
		for(i=0;i<c.length;i++){
	  c[i].id = "div"+(i+1);
	}
}

	function divorderext(){
		var c = document.getElementById("searchbar").childNodes;
		var div = document.getElementById(c[i].id).childNodes;
		for(var j=0;j<div.length;j++){
	 var t = div[j].id;
	 var n = q.toString();
	 //alert(n);
	 var res = t.substr(0,t.length-1);
	 res =	res.concat(n);

	 document.getElementById(div[j].id).id = res;
	 if(j==div.length-1){
		 q++;
	 }
		}
	}*/

	function submitsearch(){
		//divorder();
		var c = document.getElementById("searchbar").childNodes;
		var url = "";
		for(var i=0;i<c.length;i++){
		var txt = c[i].id;
		if(i!=0)
			url = url+",";
		url = url+txt.substr(3);
		}
		url = "&divorder="+url;

 	$.ajax({
		type:"POST",
		url:"ProcessServlet",
		data:$("#advanceSearchForm").serialize()+url,
		success: function(data,status){
			alert(data);
		//	alert($("#advanceSearchForm").serialize());
			document.getElementById("iframeTable").src += "";
		}
	});
 	}

	function delsearchbar(a){

	}

	function cancelSearch(){
		$.ajax({
		type:"POST",
		url:"ProcessServlet",
		data:$("#cancelsearchform").serialize(),
		success: function(data,status){
			//alert(data);
		//	alert($("#cancelsearchform").serialize());
			document.getElementById("iframeTable").src += "";
		}
	});
	document.getElementById("searchForm").reset();
	}

	function pagination(page){
		$.ajax({
		type:"POST",
		url:"ProcessServlet",
		data: "page="+page ,
		success: function(data,status){
			//alert(data);
		//	alert($("#cancelsearchform").serialize());
			document.getElementById("iframeTable").src += "";
		}
	});
	}

	window.onload = initList();

	function initList(){
			$(document).ready(function(){
				addsearchbar(1);
			});
		$.get("ProcessServlet",function(data,status){
			alert(data);
			alert(status);
			document.getElementById("iframeTable").src += "";
		});
	}
