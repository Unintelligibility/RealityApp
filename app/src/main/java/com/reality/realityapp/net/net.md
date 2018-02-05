网络请求统一返回格式
{
    resultCode,     1为成功，其他为失败
    resultMessage,  结果信息，尽量详细点
    data,           当前具体业务逻辑的数据（JSON对象或者JSON数组，根据具体业务逻辑定）
}

具体业务逻辑数据格式——data的格式
用户注册：（第一行为请求方法，第二行为传递的字段，第三行及以下为data格式）
POST
    username，password
    data：{
            _id,
            password,
            username,
            icon,
    }
其中_id实际类型为long（json中都是字符串无类型，但是接受_id并转换需要类型）

用户登录：
POST
    username，password
    data：{
            _id,
            password,
            username,
            icon,
    }