package cn.edu.ecit.cl.wang.sys.po;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public abstract class BaseTree implements Serializable {

	private static final long serialVersionUID = -2329706295668731972L;

	private String id;

	private String text;

	private String state;

	private Boolean checked;
	
	private String iconCls;
	
	private String parentId;

	private List<? extends BaseTree> children;

	private Map<String, Object> attributes;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public List<? extends BaseTree> getChildren() {
		return children;
	}

	public void setChildren(List<? extends BaseTree> children) {
		this.children = children;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
}
