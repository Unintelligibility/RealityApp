# 接口说明

#### 登录

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

