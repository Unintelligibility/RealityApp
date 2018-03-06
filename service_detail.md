# 接口说明

### *Notice*

- 安全性考虑，需要用户登录后的操作例如收藏等都需要在json中传token，eg:  {'token' : '...'}

  token定期会失效，暂定为一小时。若失效得重新请求token

- 登录后会返回token, 前端持有

- 注册成功后需要再次调用登录接口，才能获得token，token失效后需调用 /token 获得新的

- 数据格式为json，记得http请求的header一般要有application/json，如果要改为form等其他，需要对接说明

- get方法直接返回data，不加resultCode和resultMessage（没有意义）

------



#### signup  		(此处前面皆省略url地址前缀)

*post*

```python
{'username': username,
 'password': password,
 'icon': icon}
```

*return*

```
1. {'resultCode': 0, 'resultMessage': 'username and password cannot be none'}
2. {'resultCode': 0, 'resultMessage': 'username has been taken'}
3. {'resultCode': 1, 
	'data': {
	'userid': str(userid),
	'username':username,
	'password':password
}}
```

#### signin

*post*

```
{'username': username,
 'password': password,
 'icon': icon}
```

*return*

```
1. {'resultCode': 1, 
'data': {'_id': str(g.uid), 'token': token.decode('ascii'),'selected':boolean}}
2. {'resultCode': 0}
```
#### read

return

```
{
"0": {
"_id": "5a705014c3666e341a37bffa",
"picture": "",
"title": "陈吉宁当选北京市市长(图/简历)",
"content": "　　来源：北京青年报\n　　#北青快讯# #关注北京两会#[陈吉宁当选北京市市长]北京市十五届人大一次会议今天上午举行大会选举，陈吉宁当选北京市市长。（北青报记者 李泽伟 王斌）\n　　\n　　陈吉宁，男，汉族，生于1964年2月，吉林梨树人，1984年加入中国共产党，工学博士，教授。\n　　1981.09 清华大学土木与环境工程系学习（1986.07毕业并获学士学位）\n　　1993年获英国帝国理工学院土木系博士学位\n　　1992.12 英国帝国理工学院博士后\n　　1994.12 英国帝国理工学院助理研究员\n　　1998年 回清华大学任教\n　　1999年 清华大学环境科学与工程系主任\n　　2006.02 清华大学副校长\n　　2007.12 清华大学常务副校长\n　　2012.01 清华大学校长\n　　2015.01 环境保护部党组书记\n　　2015.02 中华人民共和国环境保护部部长\n　　2017.05 北京市委副书记，市政府副市长、代市长\n　　第十九届中央委员。\n　　（人民网资料 截至2017年10月）\n　　\n责任编辑：桂强 ",
"source": "新浪综合",
"time": "2018年01月30日 11:17",
"picture_info": ""
},
"1": {
"_id": "5a70501bc3666e341a37bffb",
"picture": "",
"title": "中日关系突现转折 安倍的算盘却让人倒吸一口凉气",
"content": "***",
"source": "环球网",
"time": "2018年01月30日 08:16",
"picture_info": ""
}...
}
```



#### <new_id>/recommend	单条新闻详情里的推荐阅读

return



