function setCookie(cname, cvalue, exdays) {
  var d = new Date();
  d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
  var expires = "expires="+d.toUTCString();
  document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

function getCookie(cname) {
  var cookienum = cname + "=";
  var ca = document.cookie.split(';');
  for(var i = 0; i < ca.length; i++) {
    var c = ca[i];
    while (c.charAt(0) == ' ') {
      c = c.substring(1);
    }
    if (c.indexOf(name) == 0) {
      return c.substring(cookienum.length, c.length);
    }
  }
  return "";
}

function checkCookie() {
  var cookienum = getCookie("username");
  if (cookienum != "") {
    alert("Welcome again " + cookienum);
  } else {
    cookienum = Math.floor(Math.random() * 112222222220);
    if (cookienum != "" && cookienum != null) {
      setCookie("username", cookienum, 365);
    }
  }
}