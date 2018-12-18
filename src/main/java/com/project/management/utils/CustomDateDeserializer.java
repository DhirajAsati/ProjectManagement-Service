package com.project.management.utils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class CustomDateDeserializer extends JsonDeserializer<Date> {
	public static final CustomDateDeserializer INSTANCE = new CustomDateDeserializer();

	CustomDateDeserializer() {
	}

	@Override
	public Date deserialize(JsonParser parser, DeserializationContext context) throws IOException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String date = parser.getText();
		if (null == date || date.equals("")) {
			return null;
		}
		try {
			return format.parse(date);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
}
