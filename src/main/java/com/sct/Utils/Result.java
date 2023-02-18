package com.sct.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Result {

	private Map<String, Object> rsmap;

	public Map<String, Object> getRsmap() {
		return rsmap;
	}

	public Result() {
		rsmap = new HashMap();
	}

	public void setRsmap(Map<String, Object> rsmap) {
		this.rsmap = rsmap;
	}

	public void setRsmap(String key, Map data) {
		rsmap.put(key, data);
	}
	
	public void setRsmap(String key, Object data) {
		rsmap.put(key, data);
	}

	public void setRsmap(String key, List data) {
		this.rsmap.put(key, data);
	}

	public void setRsmap(String key, ArrayList data) {
		rsmap.put(key, data);
	}

	public void setMSG(String ms) {
		rsmap.put("msg", ms);
	}

}
