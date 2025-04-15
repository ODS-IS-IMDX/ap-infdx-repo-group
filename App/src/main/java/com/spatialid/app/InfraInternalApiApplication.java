// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import lombok.extern.log4j.Log4j2;

/**
 * アプリケーションの起動の起点となる処理を提供するクラス.
 * 
 * @author ukai jun
 * @version 1.1 2024/08/23
 */
@Log4j2
@SpringBootApplication
public class InfraInternalApiApplication {

    /**
     * メイン関数<br>
     * <p>
     * アプリケーションの起動の起点となるメイン処理を提供するメソッド.<br>
     * <p>
     * 
     * @param args 可変長の文字列の配列
     */
    public static void main(String[] args) {
        SpringApplication.run(InfraInternalApiApplication.class, args);
        log.info(InfraInternalApiApplication.buildStartLog());
    }

    /**
     * ログ出力処理<br>
     * <p>
     * 文字コード、クラスパスなどの情報をログ出力するメソッド.<br>
     * <p>
     * 
     * @return String 文字列
     */
    private static String buildStartLog() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
        messageSource.addBasenames("classpath:messages", "classpath:logging");
        return messageSource.getMessage("API_LOG_INFO0001", new Object[] { "API利用者グループAPI" }, Locale.JAPAN);
    }

}
