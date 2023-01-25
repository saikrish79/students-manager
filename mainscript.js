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
    //alert(data);
  //	alert($("#searchForm").serialize());
    document.getElementById("iframeTable").src += "";
  }
});
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
  $.get("ProcessServlet",function(data,status){
  //  alert(data);
  //  alert(status);
    document.getElementById("iframeTable").src += "";
  });
}
