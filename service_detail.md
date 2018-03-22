# 接口说明

### *Notice*

- 安全性考虑，需要用户登录后的操作例如收藏等都需要在json中传token，eg:  {'token' : '...'}

  token定期会失效，暂定为一小时。若失效得重新请求token

- 登录后会返回token, 前端持有

- 注册成功后需要再次调用登录接口，才能获得token，token失效后需调用 /token 获得新的

- 数据格式为json，记得http请求的header一般要有application/json，如果要改为form等其他，需要对接说明


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
#### <user_id>/read

get

return

```
{
'resultCode':1,
'data':
{
"0": 
{
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
}
```



#### <new_id>/relate	单条新闻详情里的推荐阅读

get

return

```
{

    'resultCode':1,

    'data':

    {

    {

    "_id": "5a705014c3666e341a37bffa",

    "picture": "",

    "title": "陈吉宁当选北京市市长(图/简历)",

    "content": "　　来源：北京青年报\n　　#北青快讯# #关注北京两会#[陈吉宁当选北京市市长]北京市十五届人大一次会议今天上午举行大会选举，陈吉宁当选北京市市长。（北青报记者 李泽伟 王斌）\n　　\n　　陈吉宁，男，汉族，生于1964年2月，吉林梨树人，1984年加入中国共产党，工学博士，教授。\n　　1981.09 清华大学土木与环境工程系学习（1986.07毕业并获学士学位）\n　　1993年获英国帝国理工学院土木系博士学位\n　　1992.12 英国帝国理工学院博士后\n　　1994.12 英国帝国理工学院助理研究员\n　　1998年 回清华大学任教\n　　1999年 清华大学环境科学与工程系主任\n　　2006.02 清华大学副校长\n　　2007.12 清华大学常务副校长\n　　2012.01 清华大学校长\n　　2015.01 环境保护部党组书记\n　　2015.02 中华人民共和国环境保护部部长\n　　2017.05 北京市委副书记，市政府副市长、代市长\n　　第十九届中央委员。\n　　（人民网资料 截至2017年10月）\n　　\n责任编辑：桂强 ",

    "source": "新浪综合",

    "time": "2018年01月30日 11:17",

    "picture_info": ""

    },

    {

    "_id": "5a70501bc3666e341a37bffb",

    "picture": "",

    "title": "中日关系突现转折 安倍的算盘却让人倒吸一口凉气",

    "content": "***",

    "source": "环球网",

    "time": "2018年01月30日 08:16",

    "picture_info": ""

    }...

    }

}

```



#### rank

get

return

```json
{
    'resultCode':1,
    'data':
    {
        'source':"新华社",
        'score':"98.7"
    },
    {
    	'source':"环球网",
    	'score':"97.1"
	}
}
```



#### rankInfo

post

```json
{
    'source':source
}
```

return

```json
{
    'resultCode':1,
    'data':
    {
        'totalNum':10000
    }
}
```



#### interest		用户兴趣选择

post

```
{'likes':['体育','政治'...]}
```

*（！！Notice：请求必须加上authentication，使用的协议为Basic Auth，在请求的auth加上username=<得到的token>，password可以不填*



#### read_info	单条新闻阅读情况，用于推荐算法

Auth: needed 格式同上

post

```
{	
	'title':***
	'news_type': *** (类型：str)
	'news_tags': *** (类型：str)
	'source': *** (类型：str)
    'reading_time': 10 (单位是秒，类型：int)
}
```

#### report	新闻举报

post

```
{
    'news_id': <str>
    'reason': <str>
}
```

#### theme_list 主题列表获取

theme_list

get

return

```python
{
    'resultCode':1,
    'data':
    {
        'theme_title':"两会最佳表情出炉",
        'pic_url':"http://00.imgmini.eastday.com/mobile/20180306/20180306140953_b5c82b1cb55cc76ff1f37f1526b4c657_1.gif",
        'time':"2017-03-18"
    },
    {
    	#...
	},
    #...
}
```

#### \<string : theme_name>/theme_news 获取主题对应新闻列表

theme_news

get

return

```python
{
     'resultCode':1,
     'data':{
         "0":{
         'title':'两会表情包：“汪小兵”来了！这次他活跃在两会现场',
         'source':'中国军网',
         'picture':'http://00.imgmini.eastday.com/mobile/20180306/20180306140953_b5c82b1cb55cc76ff1f37f1526b4c657_1.gif',
         'time':'2018-03-19',
         'content':'html内容',#html格式
         'url':'http://mini.eastday.com/a/n180306140952710.html?qid=360sousuo&vqid=5Lik5Lya5pyA5L2z6KGo5oOF5Ye654KJMjAxODAzMTg'#新闻url，前端视情况决定要不要
     	},
         "1":{
             #...
         }
     }
}
```

#### source 获取排名列表

get

return

```python
{
     'resultCode':1,
    'data':{
        {
        "_id" : '_id:"5ab0dd54a41ffbc0a9fdafee")', 
        "source" : "弋阳公安在线", 
        "count" : 92, 
        "report_time" : 0, 
        "reliability" : 0, 
        "remark" : 92
        },{
             "_id" : '_id:"5ab0dd54a41ffbc0a9fdafee")', 
        	"source" : "每日经济新闻", 
            "count" : 65, 
            "report_time" : 0, 
            "reliability" : 0, 
            "remark" : 65 }
        },
    #....
     }
}
```

#### /\<string:keyword>/\<int:start>/\<int:size>/search

get

return

```python
{
    "resultCode": 1,
    "data": [
       "0":{
            "_id": "5aad49b5413ec79777e274cc",
            "title": " 俄企前高管被吊死在家中 曾是普京的批评者",
            "url": "https://www.myzaker.com/article/5aad21fa1bc8e04b1c00004b/",
            "picture": "http://zkres1.myzaker.com/201803/5aad21f91bc8e04b1c00004a_640.jpg",
            "source": "凤凰社会",
            "time": "2018-03-17",
            "content_text": " 俄企前高管被吊死在家中 曾是普京的批评者\n.死者是 68 岁的尼古拉 · 格鲁什科夫（Nikolai Glushkov），他是俄罗斯国际航空公司前副总裁，12 日被发现吊死在伦敦西南部新马尔登的住所中，尸检结果显示他是因 \" 颈部压迫 \" 而死亡。.1999 年，格鲁什科夫曾因洗钱和欺诈罪在俄罗斯被判入狱 5 年；2006 年，他在被判另一项欺诈罪、缓期执刑期间逃到英国寻求政治避难、并成为俄罗斯总统普京的批评者。.到目前为止，警方没发现他的死亡与前俄罗斯间谍斯克里帕尔中毒事件相关的迹象。（老任）.（原标题：英国又出命案：俄企前高管吊死在伦敦家中警方认定为谋杀）.\n",
            "content": "<div class=\"article_header\">\n<h1> 俄企前高管被吊死在家中 曾是普京的批评者</h1>\n<div class=\"article_tips\"><a href=\"//www.myzaker.com/source/13755\" target=\"_blank\">\n<span class=\"article-logo-container\">\n<div class=\"article-logo-mask\">\n<img class=\"auther-logo\" src=\"https://zkres.myzaker.com/data/image/logo/13755.png\">\n</div>\n</span>\n<span class=\"auther\">凤凰社会</span> <span class=\"time\">2018-03-17</span></a></div>\n</div><div class=\"article_content\">\n<div id=\"content\"><p></p><div class=\"img_box\" id=\"id_imagebox_0\" onclick=\"\"><div class=\"content_img_div perview_img_div\"><img class=\"lazy opacity_0 \" id=\"img_0\" src=\"http://zkres1.myzaker.com/201803/5aad21f91bc8e04b1c00004a_640.jpg\" data-height=\"424\" data-width=\"680\"></div></div>死者是 68 岁的尼古拉 · 格鲁什科夫（Nikolai Glushkov），他是俄罗斯国际航空公司前副总裁，12 日被发现吊死在伦敦西南部新马尔登的住所中，尸检结果显示他是因 \" 颈部压迫 \" 而死亡。<p>1999 年，格鲁什科夫曾因洗钱和欺诈罪在俄罗斯被判入狱 5 年；2006 年，他在被判另一项欺诈罪、缓期执刑期间逃到英国寻求政治避难、并成为俄罗斯总统普京的批评者。</p><p>到目前为止，警方没发现他的死亡与前俄罗斯间谍斯克里帕尔中毒事件相关的迹象。（老任）</p><p>（原标题：英国又出命案：俄企前高管吊死在伦敦家中警方认定为谋杀）</p><div id=\"recommend_bottom\"></div><div id=\"article_bottom\"></div><p id=\"ID_disclaimer\" style=\"text-align:center;color:#666;font-size:10px;\"></p></div>\n</div>",
            "news_type": "首页",
            "news_tags": "普京;伦敦",
           "fake":0,
           "clickbait": 0
        },
        "1":{
            
        }
    ]
}
```

