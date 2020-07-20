package helpers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.List;


class CookieHelperTest {

    CookieHelper cookieHelper = new CookieHelper();


    @Test
    void parseCookies() {
        //List interface arrayList implement list
        List<HttpCookie> expectedCookies = new ArrayList<>();
        expectedCookies.add(new HttpCookie("cookieName","cookieValue"));
        expectedCookies.add(new HttpCookie("cookieName1","cookieValue1"));
        expectedCookies.add(new HttpCookie("cookieName2","cookieValue2"));

        List<HttpCookie> actualal = cookieHelper.parseCookies("cookieName=cookieValue;cookieName1=cookieValue1;cookieName2=cookieValue2;");

        assertEquals(expectedCookies,actualal);
    }

    @Test
    void cookieStringIsNull(){
        List<HttpCookie> expectedCookies = new ArrayList<>();
        List<HttpCookie> actualal = cookieHelper.parseCookies(null);

        assertEquals(expectedCookies,actualal);
    }

    @Test
    void cookieStringIsEmpty() {
        List<HttpCookie> expectedCookies = new ArrayList<>();
        List<HttpCookie> actualal = cookieHelper.parseCookies("");

        assertEquals(expectedCookies,actualal);
    }

    @Test
    void cookieIsFindByName(){
        List<HttpCookie> expectedCookies = new ArrayList<>();
        expectedCookies.add(new HttpCookie("cookieName","cookieValue"));

        List<HttpCookie> actualal = cookieHelper.parseCookies("cookieName=cookieValue");

        assertEquals(expectedCookies,actualal);
    }



}