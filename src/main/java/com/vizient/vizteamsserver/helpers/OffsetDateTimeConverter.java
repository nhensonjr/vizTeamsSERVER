package com.vizient.vizteamsserver.helpers;

import javax.persistence.AttributeConverter;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class OffsetDateTimeConverter implements AttributeConverter<OffsetDateTime, String> {
    private static final DateTimeFormatter FORMATTER_TO_DB = DateTimeFormatter.ofPattern(
        "yyyy-MM-dd HH:mm:ss.nnnnnnnnn xxx");

    private static final DateTimeFormatter FORMATTER_FROM_DB = DateTimeFormatter.ofPattern(
        "[yyyy-MM-dd HH:mm:ss.nnnnnnnnn xxx]" + "[yyyy-MM-dd HH:mm:ss.nnnnnnn xxx]");

    @Override
    public String convertToDatabaseColumn(OffsetDateTime attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.format(FORMATTER_TO_DB);
    }

    @Override
    public OffsetDateTime convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        return OffsetDateTime.parse(dbData, FORMATTER_FROM_DB);
    }
}
