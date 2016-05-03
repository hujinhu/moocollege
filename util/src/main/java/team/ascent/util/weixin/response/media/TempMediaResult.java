package team.ascent.util.weixin.response.media;

import team.ascent.util.weixin.response.WeiXinResult;


/**
 * 
 * 临时素材上传返回
 * 
 * @author 作者 E-mail <a href="mailto:szg@51box.cn">石志刚</a>
 * @version 1.0.0
 */
public class TempMediaResult extends WeiXinResult{
        private String type;
        private String media_id;
        private String created_at;
        public String getType() {
            return type;
        }
        public void setType(String type) {
            this.type = type;
        }
        public String getMedia_id() {
            return media_id;
        }
        public void setMedia_id(String media_id) {
            this.media_id = media_id;
        }
        public String getCreated_at() {
            return created_at;
        }
        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }
        
}
