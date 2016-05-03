package team.ascent.util.weixin.request.contact;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SyncReplacePartyRequest {

	@JsonProperty("media_id")
	private String mediaId;
	@JsonProperty("callback")
	private Callback callback;
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	public Callback getCallback() {
		return callback;
	}
	public void setCallback(Callback callback) {
		this.callback = callback;
	}
}
