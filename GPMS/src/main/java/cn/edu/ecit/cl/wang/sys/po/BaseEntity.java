package cn.edu.ecit.cl.wang.sys.po;

import java.io.Serializable;
import java.sql.Timestamp;

import com.baomidou.mybatisplus.annotations.TableField;

public class BaseEntity implements Serializable{
	
	private static final long serialVersionUID = -6242583925436638418L;

	/**
	 * 创建人
	 */
	@TableField("CREATOR")
	private Long creator;

	/**
	 * 创建时间
	 */
	@TableField("CREATE_AT")
	private Timestamp createAt;
	
	/**
	 * 最近修改人
	 */
	@TableField("MODER")
	private Long moder;
	
	/**
	 * 最近修改时间
	 */
	@TableField("MOD_AT")
	private Timestamp modAt;
	
	/**
	 * 修改次数
	 */
	@TableField("MOD_COUNT")
	private Integer modCount;
	
	/**
	 * 是否删除：1已删除 0未删除	
	 */
	@TableField("IS_DEL")
	private String isDel;

	public Long getCreator() {
		return creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}

	public Timestamp getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Timestamp createAt) {
		this.createAt = createAt;
	}

	public Long getModer() {
		return moder;
	}

	public void setModer(Long moder) {
		this.moder = moder;
	}

	public Timestamp getModAt() {
		return modAt;
	}

	public void setModAt(Timestamp modAt) {
		this.modAt = modAt;
	}

	public Integer getModCount() {
		return modCount;
	}

	public void setModCount(Integer modCount) {
		this.modCount = modCount;
	}

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

}
