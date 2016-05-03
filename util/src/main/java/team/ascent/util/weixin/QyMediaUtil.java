package team.ascent.util.weixin;

import java.io.File;

import team.ascent.util.HttpUtil;
import team.ascent.util.JsonUtil;
import team.ascent.util.weixin.response.media.TempMediaResult;


/**
 * 
 * @ClassName: QyMediaUtil
 * @Description:企业号素材通用接口
 * @author  作者 E-mail <a href="mailto:szg@51box.cn">石志刚</a>
 * @version 创建时间：2015年12月2日下午4:47:04
 */
public class QyMediaUtil {
    private static final String QY_WEIXIN_PREFIX="https://qyapi.weixin.qq.com/cgi-bin/";
    private static JsonUtil json = JsonUtil.nonDefaultMapper();
    /**
     * 
     * @param token
     * @param file
     * @return
     */
    public static TempMediaResult uploadTempMedia(String token,File file){
        String type="";
        String fileName=file.getName();
        String suffix=fileName.substring(fileName.lastIndexOf(".")+1).toLowerCase();
        if(suffix.equals("jpg")||suffix.equals("png")){
            type="image";
        }else if(suffix.equals("amr")){
            type="voice";
        }else if(suffix.equals("mp4")){
            type="video";
        }else{
            type="file";
        }
        String url=QY_WEIXIN_PREFIX+"media/upload?access_token="+token+"&type="+type;
        String res=HttpUtil.postMediaFile(url,file);
        System.out.println(res);
        return json.fromJson(res, TempMediaResult.class);
    }
}
