package jp.co.internous.ecsite.model.form;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import jp.co.internous.ecsite.model.entity.Purchase;

public class HistoryDto {

	private long id;

	private long userId;

	private long goodsId;

	private String goodsName;

	private long itemCount;

	private long total;

	private String createdAt;
	
	//’è‹`
	public HistoryDto() {
	}
	//—š—ð‚ª‚ ‚é‚Æ‚«
	public HistoryDto(Purchase entity) {
		this.id = entity.getId();
		this.userId = entity.getUserId();
		this.goodsId = entity.getGoodsId();
		this.goodsName = entity.getGoodsName();
		this.itemCount = entity.getItemCount();
		this.total = entity.getTotal();
		
		//“ú•t‚ðŽæ“¾
		Timestamp d = entity.getcreatedAt();
		SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		this.createdAt = f.format(d);
	}

	// getter,setter(id)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	// getter,setter(userId)
	public long getUserId() {
		return userId;
	}

	public void setUserName(long userId) {
		this.userId = userId;
	}

	// getter,setter(goodsId)
	public long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(long goodsId) {
		this.goodsId = goodsId;
	}

	// getter,setter(GoodsName)
	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	// getter,setter(itemCount)
	public long getItemCount() {
		return itemCount;
	}

	public void setItemCount(long itemCount) {
		this.itemCount = itemCount;
	}

	// getter,setter(total)
	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	// getter,setter(createdAt)
	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

}
