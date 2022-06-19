1 微信用户接口
=================

1.1 微信用户列表接口
-----------------

1.1.1 接口说明

```
url：/api/wxUser/list/{pageNum}/{pageSize}
协议：http
请求方式：GET
```


1.1.2请求参数

|参数名称 |是否必须 |类型| 描述 |
|-------------|-------------|-------------|:--------------:|
|pageNum	|必须	|int	|页码|
|pageSize	|必须	|int	|每页几条数据|
| id	|可选|	Integer	|主键|
| openid	|可选|	String	|openid|
| nickName	|可选|	String	|昵称|
| language	|可选|	String	|语言|
| uId	|可选|	Integer	|用户id|
| city	|可选|	String	|城市|
| gender	|可选|	Integer	|性别|
| province	|可选|	String	|省份|
| avatarUrl	|可选|	String	|头像|
| createDate	|可选|	Date	|创建日期|
| email	|可选|	String	|邮箱|
| unionid	|可选|	String	|unionid|
| createTime	|可选|	Date	|创建时间|
| isDelete	|可选|	Integer	|是的可用|
| type	|可选|	Integer	|类型|
| mobile	|可选|	String	|联系电话|
| cardNumber	|可选|	String	|用户号码|
| realName	|可选|	String	|真实姓名|
| isBindingMobile	|可选|	Integer	|是否绑定手机|
| otherOpenid	|可选|	String	|服务号openId|
| campus	|可选|	String	|单位id|


1.1.3参数特殊说明

|参数名称 |是否必须 |类型| 描述 |
|-------------|-------------|-------------|:--------------:|
|userToken	|必须	|string	|登录token，header传输|
|sign	|必须	|string	|签名sign，header传输|
|appKey	|必须	|string	|应用key，header传输|


1.1.4返回数据

```
[{
            "id": "",
            "openid": "",
            "nickName": "",
            "language": "",
            "uId": "",
            "city": "",
            "gender": "",
            "province": "",
            "avatarUrl": "",
            "createDate": "",
            "email": "",
            "unionid": "",
            "createTime": "",
            "isDelete": "",
            "type": "",
            "mobile": "",
            "cardNumber": "",
            "realName": "",
            "isBindingMobile": "",
            "otherOpenid": "",
            "campus": "",
}]
```

1.2 微信用户添加接口
-----------------

1.2.1接口说明

```
url：/api/wxUser/add
协议：http
请求方式：POST
```


1.2.2请求参数

|参数名称 |是否必须 |类型| 描述 |
|-------------|-------------|-------------|:--------------:|
| id	|可选|	Integer	|主键|
| openid	|可选|	String	|openid|
| nickName	|可选|	String	|昵称|
| language	|可选|	String	|语言|
| uId	|可选|	Integer	|用户id|
| city	|可选|	String	|城市|
| gender	|可选|	Integer	|性别|
| province	|可选|	String	|省份|
| avatarUrl	|可选|	String	|头像|
| createDate	|可选|	Date	|创建日期|
| email	|可选|	String	|邮箱|
| unionid	|可选|	String	|unionid|
| createTime	|可选|	Date	|创建时间|
| isDelete	|可选|	Integer	|是的可用|
| type	|可选|	Integer	|类型|
| mobile	|可选|	String	|联系电话|
| cardNumber	|可选|	String	|用户号码|
| realName	|可选|	String	|真实姓名|
| isBindingMobile	|可选|	Integer	|是否绑定手机|
| otherOpenid	|可选|	String	|服务号openId|
| campus	|可选|	String	|单位id|

1.2.3参数特殊说明

|参数名称 |是否必须 |类型| 描述 |
|-------------|-------------|-------------|:--------------:|
|userToken	|必须	|string	|登录token，header传输|
|sign	|必须	|string	|签名sign，header传输|
|appKey	|必须	|string	|应用key，header传输|

1.2.4返回数据

```
{"code":200,"msg":"添加成功!","obj":null}
```

1.3 微信用户修改接口
-----------------

1.3.1接口说明

```
url：/api/wxUser/edit
协议：http
请求方式：POST
```


1.3.2请求参数

|参数名称 |是否必须 |类型| 描述 |
|-------------|-------------|-------------|:--------------:|
| id	|可选|	Integer	|主键|
| openid	|可选|	String	|openid|
| nickName	|可选|	String	|昵称|
| language	|可选|	String	|语言|
| uId	|可选|	Integer	|用户id|
| city	|可选|	String	|城市|
| gender	|可选|	Integer	|性别|
| province	|可选|	String	|省份|
| avatarUrl	|可选|	String	|头像|
| createDate	|可选|	Date	|创建日期|
| email	|可选|	String	|邮箱|
| unionid	|可选|	String	|unionid|
| createTime	|可选|	Date	|创建时间|
| isDelete	|可选|	Integer	|是的可用|
| type	|可选|	Integer	|类型|
| mobile	|可选|	String	|联系电话|
| cardNumber	|可选|	String	|用户号码|
| realName	|可选|	String	|真实姓名|
| isBindingMobile	|可选|	Integer	|是否绑定手机|
| otherOpenid	|可选|	String	|服务号openId|
| campus	|可选|	String	|单位id|

1.3.3参数特殊说明


|参数名称 |是否必须 |类型| 描述 |
|-------------|-------------|-------------|:--------------:|
|userToken	|必须	|string	|登录token，header传输|
|sign	|必须	|string	|签名sign，header传输|
|appKey	|必须	|string	|应用key，header传输|

1.3.4返回数据

```
{"code":200,"msg":"修改成功!","obj":null}
```

1.4 通过id查询微信用户
-----------------

1.4.1接口说明

```
url：/api/wxUser/queryById
协议：http
请求方式：GET
```


1.4.2请求参数

|参数名称 |是否必须 |类型| 描述 |
|-------------|-------------|-------------|:--------------:|
|id	|必须	|Integer	|id|

1.4.3参数特殊说明


|参数名称 |是否必须 |类型| 描述 |
|-------------|-------------|-------------|:--------------:|
|userToken	|必须	|string	|登录token，header传输|
|sign	|必须	|string	|签名sign，header传输|
|appKey	|必须	|string	|应用key，header传输|


1.4.4返回数据

```
{
            "id": "",
            "openid": "",
            "nickName": "",
            "language": "",
            "uId": "",
            "city": "",
            "gender": "",
            "province": "",
            "avatarUrl": "",
            "createDate": "",
            "email": "",
            "unionid": "",
            "createTime": "",
            "isDelete": "",
            "type": "",
            "mobile": "",
            "cardNumber": "",
            "realName": "",
            "isBindingMobile": "",
            "otherOpenid": "",
            "campus": "",
}
```

1.5 通过id删除微信用户
-----------------

1.5.1接口说明

```
url：/api/wxUser/deleteById
协议：http
请求方式：POST
```


1.5.2请求参数

|参数名称 |是否必须 |类型| 描述 |
|-------------|-------------|-------------|:--------------:|
|id	|必须	|Integer	|id|



1.5.3参数特殊说明



|参数名称 |是否必须 |类型| 描述 |
|-------------|-------------|-------------|:--------------:|
|userToken	|必须	|string	|登录token，header传输|
|sign	|必须	|string	|签名sign，header传输|
|appKey	|必须	|string	|应用key，header传输|



1.5.4返回数据

```
{"code":200,"msg":"删除成功!","obj":null}
```

1.6 导出微信用户接口
-----------------

1.6.1接口说明

```
url：/api/wxUser/exportExcel
协议：http
请求方式：POST
```


1.6.2请求参数

|参数名称 |是否必须 |类型| 描述 |
|-------------|-------------|-------------|:--------------:|
| id	|可选|	Integer	|主键|
| openid	|可选|	String	|openid|
| nickName	|可选|	String	|昵称|
| language	|可选|	String	|语言|
| uId	|可选|	Integer	|用户id|
| city	|可选|	String	|城市|
| gender	|可选|	Integer	|性别|
| province	|可选|	String	|省份|
| avatarUrl	|可选|	String	|头像|
| createDate	|可选|	Date	|创建日期|
| email	|可选|	String	|邮箱|
| unionid	|可选|	String	|unionid|
| createTime	|可选|	Date	|创建时间|
| isDelete	|可选|	Integer	|是的可用|
| type	|可选|	Integer	|类型|
| mobile	|可选|	String	|联系电话|
| cardNumber	|可选|	String	|用户号码|
| realName	|可选|	String	|真实姓名|
| isBindingMobile	|可选|	Integer	|是否绑定手机|
| otherOpenid	|可选|	String	|服务号openId|
| campus	|可选|	String	|单位id|

1.6.3参数特殊说明



|参数名称 |是否必须 |类型| 描述 |
|-------------|-------------|-------------|:--------------:|
|userToken	|必须	|string	|登录token，header传输|
|sign	|必须	|string	|签名sign，header传输|
|appKey	|必须	|string	|应用key，header传输|



1.6.4返回数据

msg为excel文件的url

```
{"code":200,"msg":""}
```


1.7 导入微信用户接口
-----------------

1.7.1接口说明

```
url：/api/wxUser/importExcel
协议：http
请求方式：POST
```


1.7.2请求参数

|参数名称 |是否必须 |类型| 描述 |
|-------------|-------------|-------------|:--------------:|
| file	|  必须  |	文件	|file文件流|

1.7.3参数特殊说明



|参数名称 |是否必须 |类型| 描述 |
|-------------|-------------|-------------|:--------------:|
|userToken	|必须	|string	|登录token，header传输|
|sign	|必须	|string	|签名sign，header传输|
|appKey	|必须	|string	|应用key，header传输|



1.7.4返回数据

```
{"code":200,"msg":"批量导入成功"}
```
