let login = (event) => {
	//submit(画面遷移)を妨害
	event.preventDefault();
	//jsonStringにuserNameとpasswordを代入
	let jsonString = {
			'userName': $('input[name=userName]').val(),
			'password': $('input[name=password]').val()
	};
	//データのやり取り(非同期通信)
	$.ajax({
		//データを送信
		type: 'POST',
		//データの送り先
		url: '/ecsite/api/login',
		//JSON文字列に変換
		data: JSON.stringify(jsonString),
		contentType: 'application/json',
		datatype: 'json',
		scriptCharset: 'utf-8'
	})
	//非同期通信で返す答え
	.then((result) => {
		//JSONを再びオブジェクト形式(JSで使いやすい形)に変換
		let user = JSON.parse(result);
		$('#welcome').text(` -- ようこそ！ ${user.fullName} さん`);
		$('#hiddenUserId').val(user.id);
		//ログイン後入力欄を空にする
		$('input[name=userName]').val('');
		$('input[name=password]').val('');
	}, () => {
		//エラーだった場合、コンソールに表示
		console.error('Error: ajax connection failed.');
	}	
	);
};

let addCart = (event) => {
	
	let tdList = $(event.target).parent().parent().find('td');
	
	let id = $(tdList[0]).text();
	let goodsName = $(tdList[1]).text();
	let price = $(tdList[2]).text();
	let count = $(tdList[3]).find('input').val();
	
	if (count === '0' || count === ''){
		alert('注文数が０または空欄です。')
		return;
	}
	
	let cart = {
		'id': id,
		'goodsName': goodsName,
		'price': price,
		'count': count
	};
	cartList.push(cart);
	
	let tbody = $('#cart').find('tbody');
	//カートを初期化
	$(tbody).children().remove();
	
	cartList.forEach(function(cart, index){
		let tr = $('<tr />');
		
		$('<td />',{ 'text': cart.id }).appendTo(tr);
		$('<td />',{ 'text': cart.goodsName }).appendTo(tr);
		$('<td />',{ 'text': cart.price }).appendTo(tr);
		$('<td />',{ 'text': cart.count }).appendTo(tr);
		let tdButton = $('<td />');
		$('<button />',{
			'text': 'カート削除',
			'class':'removeBtn',
		}).appendTo(tdButton);
		
		$(tdButton).appendTo(tr);
		$(tr).appendTo(tbody);
	});
	
	$('.removeBtn').on('click', removeCart);
};

let removeCart = (event) => {
	//おじいさん要素を探してtdListに代入 constは書き換え禁止
	const tdList = $(event.target).parent().parent().find('td');
	//idにtdList配列0番目のテキストを代入
	let id = $(tdList[0]).text();
	
	cartList = cartList.filter(function(cart){
		return cart.id !== id;
	});
	//削除
	$(event.target).parent().parent().remove();
};

let buy = (event) => {
	//データのやり取り
	$.ajax({
		type: 'POST',
		url: '/ecsite/api/purchase',
		data: JSON.stringify({
			"userId": $('#hiddenUserId').val(),
			"cartList": cartList
		}),
		contentType: 'application/json',
		datatype: 'json',
		scriptCharset: 'utf-8'
	})
	//非同期通信で返す答え
	.then((result) => {
		alert('購入しました。');
	}, () =>{
		//エラーだった場合、コンソールに表示
		console.error('Error: ajax connection failed.');
	}
	);
};

let showHistory = () => {
	//DB
	$.ajax({
		type: 'POST',
		url : '/ecsite/api/history',
		//JSの値(UserId)をJSON文字列に変換
		data: JSON.stringify({ "userId": $('#hiddenUserId').val() }),
		contentType: 'application/json',
		datatype: 'json',
		scriptCharset: 'utf-8'
	})
	.then((result) => {
		//JSONを再びオブジェクト形式(JSで使いやすい形)に変換
		let historyList = JSON.parse(result);
		let tbody = $('#historyTable').find('tbody');
		//最新の履歴を出したいので初期化
		$(tbody).children().remove();
		//履歴を取得(historyで履歴、indexで要素番号)
		historyList.forEach((history, index) => {
			let tr = $('<tr />');
			
			$('<td />', { 'text': history.goodsName }).appendTo(tr);
			$('<td />', { 'text': history.itemCount }).appendTo(tr);
			$('<td />', { 'text': history.createdAt }).appendTo(tr);
			
			$(tr).appendTo(tbody);
		});
		//ダイアログで表示
		$("#history").dialog("open");
	}, () => {
		//エラーだった場合、コンソールに表示
		console.error('Error: ajax connection failed.');
	}
	);
}