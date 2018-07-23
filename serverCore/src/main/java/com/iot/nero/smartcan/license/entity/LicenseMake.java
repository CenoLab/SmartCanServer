package com.iot.nero.smartcan.license.entity;




        import java.io.*;
        import java.text.ParseException;
        import java.text.SimpleDateFormat;
        import java.util.Properties;
        import java.util.prefs.Preferences;

        import javax.security.auth.x500.X500Principal;

        import com.iot.nero.smartcan.license.core.LicenseManagerHolder;
        import de.schlichtherle.license.CipherParam;
        import de.schlichtherle.license.DefaultCipherParam;
        import de.schlichtherle.license.DefaultKeyStoreParam;
        import de.schlichtherle.license.DefaultLicenseParam;
        import de.schlichtherle.license.KeyStoreParam;
        import de.schlichtherle.license.LicenseContent;
        import de.schlichtherle.license.LicenseManager;
        import de.schlichtherle.license.LicenseParam;

/**
 * 生成证书
 * @author Leon Lee.
 */
public class LicenseMake
{
    private String licPath;
    private String issued ;
    private String notBefore ;
    private String notAfter ;
    private String consumerType;
    private int consumerAmount ;
    private String info ;
    /**
     * 私钥的别名
     */
    private String priAlias ;
    /**
     * 该密码生成密钥对的密码
     */
    private String privateKeyPwd ;
    /**
     * 使用keytool生成密钥对时设置的密钥库的访问密码
     */
    private String keyStorePwd ;
    private String subject ;
    private String priPath ;

    // X500Princal是一个证书文件的固有格式，详见API
    private final static X500Principal DEFAULTHOLDERANDISSUER = new X500Principal("CN=yang, OU=autobrain, O=autobrain, L=tianjin, ST=tianjin, C=ch");

    public LicenseMake(){}
    public LicenseMake(String confPath)
    {
        initParam(confPath);
    }

    /**
     * 读取属性文件
     * @param confPath 属性文件路径
     */
    public void initParam(String confPath)
    {
        // 获取参数
        Properties prop = new Properties();
        InputStream in =LicenseMake.class.getResourceAsStream(confPath);

        try
        {
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //common param
        priAlias = prop.getProperty("private.key.alias");
        privateKeyPwd = prop.getProperty("private.key.pwd");
        keyStorePwd= prop.getProperty("key.store.pwd");
        subject = prop.getProperty("subject");
        priPath = prop.getProperty("priPath");
        licPath = prop.getProperty("licPath");
        // license content
        issued = prop.getProperty("issuedTime");
        notBefore = prop.getProperty("notBefore");
        notAfter = prop.getProperty("notAfter");
        consumerType = prop.getProperty("consumerType");
        consumerAmount = Integer.valueOf(prop.getProperty("consumerAmount"));
        info = prop.getProperty("info");
    }

    /**
     * 初始化证书的相关参数
     * @return
     */
    private LicenseParam initLicenseParams()
    {
        Class<LicenseMake> clazz=LicenseMake.class;
        Preferences pre = Preferences.userNodeForPackage(clazz);
        // 设置对证书内容加密的对称密码
        CipherParam cipherParam = new DefaultCipherParam(keyStorePwd);
        // 参数1,2从哪个Class.getResource()获得密钥库;
        //参数3密钥库的别名;
        //参数4密钥库存储密码;
        //参数5密钥库密码
        KeyStoreParam privateStoreParam = new DefaultKeyStoreParam(clazz, priPath, priAlias, keyStorePwd, privateKeyPwd);
        // 返回生成证书时需要的参数
        LicenseParam licenseParam = new DefaultLicenseParam(subject,pre, privateStoreParam, cipherParam);
        return licenseParam;
    }

    /**
     * 通过外部配置文件构建证书的的相关信息
     * @return
     * @throws ParseException
     */
    public LicenseContent buildLicenseContent() throws ParseException
    {
        LicenseContent content=new LicenseContent();
        SimpleDateFormat formate=new SimpleDateFormat("yyyy-MM-dd");
        content.setConsumerAmount(consumerAmount);
        content.setConsumerType(consumerType);
        content.setHolder(DEFAULTHOLDERANDISSUER);
        content.setIssuer(DEFAULTHOLDERANDISSUER);
        content.setIssued(formate.parse(issued));
        content.setNotBefore(formate.parse(notBefore));
        content.setNotAfter(formate.parse(notAfter));
        content.setInfo(info);
        content.setExtra(new Object());
        return content;
    }


    /**
     * 生成证书，在证书发布者端执行
     * @throws Exception
     */
    public void create() throws Exception
    {
        LicenseManager licenseManager=LicenseManagerHolder.getLicenseManager(initLicenseParams());
        LicenseContent content=buildLicenseContent();
        licenseManager.store(content, new File(licPath));
        System.out.println("证书发布成功");
    }


    public static void main(String[] args) {
        LicenseMake clicense=new LicenseMake("/config/licenseMakeConf.properties");
        try {
            clicense.create();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

