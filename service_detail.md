# 接口说明

### *Notice*

- 安全性考虑，需要用户登录后的操作例如收藏等都需要在json中传token，eg:  {'token' : '...'}

  token定期会失效，暂定为一小时。若失效得重新请求token

- 登录后会返回token, 前端持有

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
3. {'resultCode': 1, 'data': {'userid': str(userid)}}
```

#### signin

*post*

```
{'username': username,
 'password': password,
 'icon': icon}
```

*return*

