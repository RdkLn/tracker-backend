package dev.rdkln.tracker.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.UUID;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

@Service("jjwtService")
public class JwtServiceImpl implements JwtService {

    private SecretKey key;

    public JwtServiceImpl() {
        key = Jwts.SIG.HS256.key().build();
    }

    @Override
    public String generateToken(JwtUserDto dto) {
        // TODO: Faltan los datos del usuario
        // TODO: Cambiar fecha de expiracion para
        Date expirationDate = Date.from(LocalDateTime.now().plusMinutes(15).toInstant(ZoneOffset.UTC));
        return Jwts.builder()
                .issuer("fitness-tracker")
                .issuedAt(Date.from(Instant.now()))
                .expiration(expirationDate)
                .subject("test-user")
                .id(UUID.randomUUID().toString())
                .signWith(key)
                .compact();
    }

    @Override
    public Object verify(String token) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'verify'");
    }

    public Object parseToken(String token) {
        Jwt<?, ?> jwt;
        try {
            jwt = Jwts.parser().verifyWith(key).build().parse(token);
            return jwt;
        } catch (Exception e) {
            throw new JwtException("Error parsing token: "+token,e.getCause());
        }
    }


}
