function setCookie(cname, cvalue, exdays) {
  const d = new Date();
  d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
  const expires = "expires="+d.toUTCString();
  document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

function getCookie(cname) {
  const cookienum = cname + "=";
  const ca = document.cookie.split(';');
  for(let i = 0; i < ca.length; i++) {
    let c = ca[i];
    while (c.charAt(0) === ' ') {
      c = c.substring(1);
    }
    if (c.indexOf(name) === 0) {
      return c.substring(cookienum.length, c.length);
    }
  }
  return "";
}

function checkCookie() {
  let cookienum = getCookie("username");
  if (cookienum !== "") {
    alert("Welcome again " + cookienum);
  } else {
    cookienum = Math.floor(Math.random() * 112222222220);
    if (cookienum !== "" && cookienum != null) {
      setCookie("username", cookienum, 365);
    }
  }
}