package team.ascent.util.weixin.request.menu;

import java.util.ArrayList;
import java.util.List;



/**
 * @author <a href="mailto:1933549135@qq.com">Amr</a>
 * @date  2016年4月30日 上午12:05:21 
 */
public class Menu {
	
	private List<Button> button;
	
	


	public List<Button> getButton() {
		return button;
	}


	public void setButton(List<Button> button) {
		this.button = button;
	}


	public Menu() {
		super();
		this.button = new ArrayList<Button>();
	}
	
	

}
