package com.gcs.aol.captcha;

import com.octo.captcha.CaptchaException;
import com.octo.captcha.CaptchaQuestionHelper;
import com.octo.captcha.component.image.wordtoimage.WordToImage;
import com.octo.captcha.component.word.wordgenerator.WordGenerator;
import com.octo.captcha.image.ImageCaptcha;
import com.octo.captcha.image.gimpy.Gimpy;
 
import java.awt.image.BufferedImage;
import java.security.SecureRandom;
import java.util.Locale;
import java.util.Random;
 
public class GimpyCopyFactory extends com.octo.captcha.image.ImageCaptchaFactory {
 
    private Random myRandom = new SecureRandom();
    private WordToImage wordToImage;
    private WordGenerator wordGenerator;
 
	//这个还是用原来的Gimpy
    public static final String BUNDLE_QUESTION_KEY = Gimpy.class.getName(); 
 
    public GimpyCopyFactory(WordGenerator generator, WordToImage word2image) {
        if (word2image == null) {
            throw new CaptchaException("Invalid configuration" + " for a GimpyFactory : WordToImage can't be null");
        }
        if (generator == null) {
            throw new CaptchaException("Invalid configuration" + " for a GimpyFactory : WordGenerator can't be null");
        }
        wordToImage = word2image;
        wordGenerator = generator;
    }
 
    public ImageCaptcha getImageCaptcha() {
        return getImageCaptcha(Locale.getDefault());
    }
 
    public WordToImage getWordToImage() {
        return wordToImage;
    }
 
    public WordGenerator getWordGenerator() {
        return wordGenerator;
    }
 
    public ImageCaptcha getImageCaptcha(Locale locale) {
        Integer wordLength = getRandomLength();
 
        String word = getWordGenerator().getWord(wordLength, locale);
 
        BufferedImage image = null;
        try {
            image = getWordToImage().getImage(word);
        } catch (Throwable e) {
            throw new CaptchaException(e);
        }
        // 这里用我们自己写的GimpyCopy
        ImageCaptcha captcha = new GimpyCopy(CaptchaQuestionHelper.getQuestion(locale, BUNDLE_QUESTION_KEY), image, word);
        return captcha;
    }
 
    protected Integer getRandomLength() {
        Integer wordLength;
        int range = getWordToImage().getMaxAcceptedWordLength() - getWordToImage().getMinAcceptedWordLength();
        int randomRange = range != 0 ? myRandom.nextInt(range + 1) : 0;
        wordLength = new Integer(randomRange + getWordToImage().getMinAcceptedWordLength());
        return wordLength;
    }
}
