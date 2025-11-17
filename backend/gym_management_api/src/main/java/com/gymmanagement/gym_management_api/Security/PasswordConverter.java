package com.gymmanagement.gym_management_api.Security;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PasswordConverter implements AttributeConverter<Password, String> {

    @Override
    public String convertToDatabaseColumn(Password attribute) {
        return attribute != null ? attribute.getHashed() : null;
    }

    @Override
    public Password convertToEntityAttribute(String dbData) {
        return dbData != null ? Password.ofHashed(dbData) : null;
    }
}

