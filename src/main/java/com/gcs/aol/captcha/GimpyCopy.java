package com.gcs.aol.captcha;

import com.octo.captcha.image.ImageCaptcha;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public class GimpyCopy extends ImageCaptcha implements Serializable {
	private static final long serialVersionUID = 7232946777528201656L;
	
	private String response;
 
    GimpyCopy(String question, BufferedImage challenge, String response) {
        super(question, challenge);
        this.response = response;
    }
 
    public final Boolean validateResponse(final Object response) {
        return (null != response && response instanceof String) ? validateResponse((String) response) : Boolean.FALSE;
    }

    private final Boolean validateResponse(final String response) {
        // 主要改的这里
        return new Boolean(response.toLowerCase().equals(this.response.toLowerCase()));
    }
}
