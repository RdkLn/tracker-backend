package dev.rdkln.tracker.security;

public interface JwtService {

    public String generateToken(JwtUserDto dto);

    public Object verify(String token);

}
