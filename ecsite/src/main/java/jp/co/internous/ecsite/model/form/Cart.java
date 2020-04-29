package jp.co.internous.ecsite.model.form;

import java.io.Serializable;

public class Cart implements Serializable {
	private static final long serialVersionUID = 1L;

	private long id;
	private String goodsName;
	private long price;
	private long count;

	// getter,setter(id)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	// getter,setter(goodsName)
	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	// getter,setter(price)
	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	// getter,setter(count)
	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

}
