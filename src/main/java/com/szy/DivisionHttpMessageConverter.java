package com.szy;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.lang.reflect.Type;

public class DivisionHttpMessageConverter extends FastJsonHttpMessageConverter4{

	@Override
    protected void writeInternal(Object obj, //
                                 Type type, //
                                 HttpOutputMessage outputMessage //
    ) throws IOException, HttpMessageNotWritableException {
		HttpHeaders headers = outputMessage.getHeaders();
		headers.add("Access-Control-Allow-Origin", "*");
		super.writeInternal(obj, type, outputMessage);
    }
}
