package com.chinahitech.shop.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 读取配置文件
 */
@Configuration
@Data
@Component
public class AlipayConfig {

    /**
     * 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
     */
    @Value("${alipay.appId}")
    private String appId;

    /**
     * 商户私钥，您的PKCS8格式RSA2私钥
     */
    @Value("${alipay.privateKey}")
    private String privateKey;

    /**
     * 应用公钥,
     */
    @Value("${alipay.appPublicKey}")
    private String appPublicKey;

    /**
     * 支付宝公钥,
     */
    @Value("${alipay.alipayPublicKey}")
    private String alipayPublicKey;

    /**
     * 服务器异步通知页面路径需http://格式的完整路径，不能加?id=123这类自定义参数
     */
    @Value("${alipay.notifyUrl}")
    private String notifyUrl;

    /**
     * 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数
     */
    @Value("${alipay.returnUrl}")
    private String returnUrl;

    /**
     * 签名方式
     */
    @Value("${alipay.signType}")
    private String signType;

    /**
     * 字符编码格式
     */
    @Value("${alipay.charset}")
    private String charset;

    /**
     * 支付宝网关
     */
    @Value("${alipay.gatewayUrl}")
    private String gatewayUrl;

    /**
     * 支付宝网关
     */
    @Value("${alipay.logPath}")
    private String logPath;


    public AlipayConfig()
    {
    }

//    public AlipayConfig(String appId, String privateKey, String publicKey, String notifyUrl, String returnUrl, String signType, String charset, String gatewayUrl, String logPath)
//    {
//        this.appId = appId;
//        this.privateKey = privateKey;
//        this.publicKey = publicKey;
//        this.notifyUrl = notifyUrl;
//        this.returnUrl = returnUrl;
//        this.signType = signType;
//        this.charset = charset;
//        this.gatewayUrl = gatewayUrl;
//        this.logPath = logPath;
//    }

    public String getAppId()
    {
        return appId;
    }

    public void setAppId(String appId)
    {
        this.appId = appId;
    }

    public String getPrivateKey()
    {
        return privateKey;
    }

    public void setPrivateKey(String privateKey)
    {
        this.privateKey = privateKey;
    }


    public String getNotifyUrl()
    {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl)
    {
        this.notifyUrl = notifyUrl;
    }

    public String getReturnUrl()
    {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl)
    {
        this.returnUrl = returnUrl;
    }

    public String getSignType()
    {
        return signType;
    }

    public void setSignType(String signType)
    {
        this.signType = signType;
    }

    public String getCharset()
    {
        return charset;
    }

    public void setCharset(String charset)
    {
        this.charset = charset;
    }

    public String getGatewayUrl()
    {
        return gatewayUrl;
    }

    public void setGatewayUrl(String gatewayUrl)
    {
        this.gatewayUrl = gatewayUrl;
    }

    public String getLogPath()
    {
        return logPath;
    }

    public void setLogPath(String logPath)
    {
        this.logPath = logPath;
    }

    @Override
    public String toString() {
        return "AlipayConfig{" +
                "appId='" + appId + '\'' +
                ", privateKey='" + privateKey + '\'' +
                ", appPublicKey='" + appPublicKey + '\'' +
                ", alipayPublicKey='" + alipayPublicKey + '\'' +
                ", notifyUrl='" + notifyUrl + '\'' +
                ", returnUrl='" + returnUrl + '\'' +
                ", signType='" + signType + '\'' +
                ", charset='" + charset + '\'' +
                ", gatewayUrl='" + gatewayUrl + '\'' +
                ", logPath='" + logPath + '\'' +
                '}';
    }

    public String getAppPublicKey() {
        return appPublicKey;
    }

    public void setAppPublicKey(String appPublicKey) {
        this.appPublicKey = appPublicKey;
    }

    public String getAlipayPublicKey() {
        return alipayPublicKey;
    }

    public void setAlipayPublicKey(String alipayPublicKey) {
        this.alipayPublicKey = alipayPublicKey;
    }

}
