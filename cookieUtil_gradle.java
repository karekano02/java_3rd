

import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.util.Arrays;
import java.util.Optional;


@Component
public final class CookieUtil {
    /**
     * methodName : createResponseCookie
  
     * description : Response Cookie를 생성한다.
     *
     * @param cookieName
     * @param value
     * @param expireTime
     * @return response cookie
     */
    public ResponseCookie createResponseCookie(String cookieName, String value, long expireTime){
        return ResponseCookie
                .from(cookieName, value)
                .httpOnly(true)
//                .secure(true)
                .domain("kcar.com")
                .path("/")
                .maxAge(Duration.ofSeconds(expireTime))
                .build();
    }


    /**
     * methodName : getCookie

     * description : CookieName에 해당하는 Cookie를 구한다.
     *
     * @param request
     * @param cookieName
     * @return cookie
     */
    public Cookie getCookie(HttpServletRequest request, final String cookieName){
        final Cookie[] cookies = request.getCookies();
        return  Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals(cookieName))
                .findFirst()
                .orElse(null);
    }
}
