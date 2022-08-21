package io.github.ViniciusSantos01.Security;

import io.github.ViniciusSantos01.entity.UserEntiry;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtService {

    @Value("{security.jwt.expiration}")
    private String expiration;

    @Value("{security.jwt.sign-key}")
    private String signKey;

    public String tokenGenerator(UserEntiry userEntiry){

        long expString = Long.valueOf(expiration);
        LocalDateTime dateHourExpiration = LocalDateTime.now().plusMinutes(expString);
        Instant instant = dateHourExpiration.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);
        return Jwts
                .builder()
                .setSubject(userEntiry.getLogin())
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, signKey)
                .compact();

    }

    private Claims getClaims (String token) throws ExpiredJwtException {
        return Jwts
                .parser()
                .setSigningKey(signKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validToken (String token) {

        try {
            Claims claims = getClaims(token);
            Date expirationDate = claims.getExpiration();
            LocalDateTime date = expirationDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            return LocalDateTime.now().isAfter(date);
        }catch (Exception e){
            return false;
        }
    }

    public String getUserLogin (String token) throws ExpiredJwtException {

        return (String) getClaims(token).getSubject();

    }

}
