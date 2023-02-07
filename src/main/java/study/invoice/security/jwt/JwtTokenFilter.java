package study.invoice.security.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import study.invoice.security.userpincal.UserDetailService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtTokenFilter extends OncePerRequestFilter {
    private final Logger log = LoggerFactory.getLogger(JwtTokenFilter.class);
    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private UserDetailService userDetailService;

    public JwtTokenFilter() {
    }

    public JwtTokenFilter(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            String token = getJwt(request);
            if(token != null && tokenProvider.validateToken(token)){
                String userName =  tokenProvider.getUserNameByToken(token);
                UserDetails userDetails = userDetailService.loadUserByUsername(userName);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        catch (Exception ex){
            logger.error("Can not get token", ex);
        }

        filterChain.doFilter(request, response);
    }

    private String getJwt(HttpServletRequest request){
        String authorHeader = request.getHeader("Authorization");
        if(authorHeader != null && authorHeader.startsWith("Bearer")){
            return authorHeader.replace("Bearer", "").trim();
        }
        return null;
    }
}
