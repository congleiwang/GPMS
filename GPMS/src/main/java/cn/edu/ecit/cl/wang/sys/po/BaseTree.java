package cn.edu.ecit.cl.wang.sys.po;

import java.util.List;

import com.baomidou.mybatisplus.annotations.TableField;

public abstract class BaseTree {

	@TableField(exist = false)
	private String id;

	@TableField(exist = false)
	private List<? extends BaseTree> children;

	@TableField(exist = false)
	private String parentId;

	@TableField(exist = false)
	private Boolean leaf;

	@TableField(exist = false)
	private Boolean checked;

	public boolean hasChildren() {
		return (children != null) && (!children.isEmpty());
	}

	public Boolean getLeaf() {
		return (leaf == null) ? (!hasChildren()) : leaf;
	}

	public List<? extends BaseTree> getChildren() {
		return children;
	}

	public void setChildren(List<? extends BaseTree> children) {
		this.children = children;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
