package proxy;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.GeneralSecurityException;




public class GoogleProxy {
public void redirectToGoogleLogin(HttpServletResponse response) throws IOException {
        String authorizationUrl = "https://accounts.google.com/o/oauth2/auth?"
                + "client_id=" + SecurityConfig.GOOGLE_CLIENT_ID
                + "&redirect_uri=" + SecurityConfig.GOOGLE_REDIRECT_URI
                + "&response_type=code"
                + "&scope=" + SecurityConfig.GOOGLE_SCOPE;

        response.sendRedirect(authorizationUrl);
    }

    public String getAccessToken(String code,JacksonFactory jsonFactory) throws IOException, GeneralSecurityException {
        
        GoogleTokenResponse tokenResponse = new GoogleAuthorizationCodeTokenRequest(
                new NetHttpTransport(),
                jsonFactory,
                "https://oauth2.googleapis.com/token",
                SecurityConfig.GOOGLE_CLIENT_ID,
                SecurityConfig.GOOGLE_CLIENT_SECRET,
                code,
                SecurityConfig.GOOGLE_REDIRECT_URI
        ).execute();

        return tokenResponse.getAccessToken();
    }
    
}
