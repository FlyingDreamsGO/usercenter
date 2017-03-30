package com.beauty.usercenter.common.spring;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * 具体参照 Spring 框架中的 StringHttpMessageConverter 类（@see
 * org.springframework.http.converter.StringHttpMessageConverter)来写，主要修订了以下功能：<br>
 * 1. 默认输出编码由 ISO-8859-1 换成 UTF-8；<br>
 * 2. 在 MEDIA_TYPE 找不到的情况下，系统自动补全给客户端的编码设置，如中括号部分：text/plain;【charset=UTF-8】<br>
 * 3. 去除 writeAcceptCharset 支持，不再在HTTP头上添加 Accept 头信息；<br>
 * Created by kenan@xiaokakeji.com
 * on 2017/3/19.
 */
public class StringHttpMessageConverter extends AbstractHttpMessageConverter<String> {
    private static final String MEDIA_TYPE_TEXT = "text";
    private static final String MEDIA_TYPE_PLAIN = "plain";

    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    private final Charset defaultCharset;

    /**
     * A default constructor that uses {@code "ISO-8859-1"} as the default
     * charset.
     *
     * @see #StringHttpMessageConverter(Charset)
     */
    public StringHttpMessageConverter() {
        this(DEFAULT_CHARSET);
    }

    /**
     * A constructor accepting a default charset to use if the requested content
     * type does not specify one.
     */
    public StringHttpMessageConverter(Charset defaultCharset) {
        super(new MediaType(MEDIA_TYPE_TEXT, MEDIA_TYPE_PLAIN, defaultCharset), MediaType.ALL);
        this.defaultCharset = defaultCharset;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return String.class.equals(clazz);
    }

    @Override
    protected String readInternal(Class<? extends String> clazz, HttpInputMessage inputMessage) throws IOException {
        Charset charset = getContentTypeCharset(inputMessage.getHeaders().getContentType());
        return StreamUtils.copyToString(inputMessage.getBody(), charset);
    }

    @Override
    protected Long getContentLength(String s, MediaType contentType) {
        Charset charset = getContentTypeCharset(contentType);
        try {
            return (long) s.getBytes(charset.name()).length;
        } catch (UnsupportedEncodingException ex) {
            // should not occur
            throw new IllegalStateException(ex);
        }
    }

    @Override
    protected void writeInternal(String s, HttpOutputMessage outputMessage) throws IOException {
        Charset charset = null;
        MediaType contentType = outputMessage.getHeaders().getContentType();
        if (contentType != null && contentType.getCharset() != null) {
            charset = contentType.getCharset();
        } else {
            charset = this.defaultCharset;
            if (contentType != null) {
                outputMessage.getHeaders().setContentType(
                        new MediaType(contentType.getType(), contentType.getSubtype(), this.defaultCharset));
            } else {
                outputMessage.getHeaders().setContentType(
                        new MediaType(MEDIA_TYPE_TEXT, MEDIA_TYPE_PLAIN, defaultCharset));
            }
        }

        StreamUtils.copy(s, charset, outputMessage.getBody());
    }

    private Charset getContentTypeCharset(MediaType contentType) {
        if (contentType != null && contentType.getCharset() != null) {
            return contentType.getCharset();
        } else {
            return this.defaultCharset;
        }
    }
}
