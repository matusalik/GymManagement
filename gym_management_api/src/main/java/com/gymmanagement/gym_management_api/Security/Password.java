package com.gymmanagement.gym_management_api.Security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCrypt;

@AllArgsConstructor
@Getter
public class Password {
    private final String hashed;

    private static void validate(String raw){
        if(raw == null || raw.length() < 8){
            throw new IllegalArgumentException("Password must be at least 8 characters long.");
        }
    }

    public static Password ofRaw(String raw){
        validate(raw);
        return new Password(hash(raw));
    }

    public static Password ofHashed(String hashed){
        return new Password(hashed);
    }

    public static String hash(String raw){
        return BCrypt.hashpw(raw, BCrypt.gensalt());
    }

    public boolean matches(String raw){
        return BCrypt.checkpw(raw, hashed);
    }

    @Override
    public String toString(){
        return "[PROTECTED]";
    }
}
