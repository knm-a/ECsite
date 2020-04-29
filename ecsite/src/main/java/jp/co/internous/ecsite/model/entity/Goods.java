package jp.co.internous.ecsite.model.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "goods")
public class Goods {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "goods_name")
	private String goodsName;

	@Column(name = "price")
	private long price;
	
	@Column(name = "update_at")
	private Timestamp updatedAt;

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

	//getter,setter(updateAt)
		public Timestamp getUpdatedAt() {
			return updatedAt;
		}
		public void setUpdatedAt(Timestamp updatedAt) {
			this.updatedAt = updatedAt;
		}

}
