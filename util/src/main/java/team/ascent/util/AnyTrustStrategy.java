package team.ascent.util;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import org.apache.http.conn.ssl.TrustStrategy;
/**
 * 
 * @ClassName: AnyTrustStrategy
 * @Description:信任所有
 * @author  作者 E-mail <a href="mailto:szg@51box.cn">石志刚</a>
 * @version 创建时间：2015年11月19日下午8:40:16
 */
public 	class AnyTrustStrategy implements TrustStrategy{
    public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        return true;
    }
}