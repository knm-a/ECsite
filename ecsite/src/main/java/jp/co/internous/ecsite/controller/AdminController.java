package jp.co.internous.ecsite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.internous.ecsite.model.dao.GoodsRepository;
import jp.co.internous.ecsite.model.dao.UserRepository;
import jp.co.internous.ecsite.model.entity.Goods;
import jp.co.internous.ecsite.model.entity.User;
import jp.co.internous.ecsite.model.form.GoodsForm;
import jp.co.internous.ecsite.model.form.LoginForm;

@Controller
@RequestMapping("/ecsite/admin")
public class AdminController {
	
	//Repository読み込み
	@Autowired
	private UserRepository userRepos;
	@Autowired
	private GoodsRepository goodsRepos;
	
	//トップページに戻る
	@RequestMapping("/")
	public String index() {
		return "adminindex";
	}
	
	//welcomeページ
	@PostMapping("/welcome")
	public String welcome(LoginForm form, Model m) {
		//ユーザー検索
		List<User> users= userRepos.findByUserNameAndPassword(form.getUserName(), form.getPassword());
		//管理者だった場合のみの処理
		if(users !=null && users.size() > 0) {
			boolean isAdmin = users.get(0).getIsAdmin() !=0;
			if(isAdmin) {
			List<Goods> goods = goodsRepos.findAll();
			m.addAttribute("userName", users.get(0).getUserName());
			m.addAttribute("password", users.get(0).getPassword());
			m.addAttribute("goods",goods);
			}
		}
		return "welcome";
	}
	
	//goodsMstページ（商品登録ページ）
	@RequestMapping("/goodsMst")
	public String goodsMst(LoginForm form, Model m) {
		m.addAttribute("userName", form.getUserName());
		m.addAttribute("password", form.getPassword());
		
		return "goodsmst";
	}
	
	//addGoodsページ
	@RequestMapping("/addGoods")
	public String addGoods(GoodsForm goodsForm, LoginForm loginForm, Model m) {
		m.addAttribute("userName", loginForm.getUserName());
		m.addAttribute("password", loginForm.getPassword());
		
		//goods追加
		Goods goods = new Goods();
		goods.setGoodsName(goodsForm.getGoodsName());
		goods.setPrice(goodsForm.getPrice());
		goodsRepos.saveAndFlush(goods);
		
		return "forward:/ecsite/admin/welcome";
	}
	
	//
	@ResponseBody
	@PostMapping("/api/deleteGoods")
	public String deleteApi(@RequestBody GoodsForm f, Model m) {
		
		try {
			goodsRepos.deleteById(f.getId());
		} catch (IllegalArgumentException e) {
			return "-1";
		}
		
		return "1";
	}
	
}
