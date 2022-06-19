1 管理员信息接口
=================

1.1 管理员信息列表接口
-----------------

1.1.1 接口说明

```
url：/api/sysUser/list/{pageNum}/{pageSize}
协议：http
请求方式：GET
```


1.1.2请求参数

|参数名称 |是否必须 |类型| 描述 |
|-------------|-------------|-------------|:--------------:|
|pageNum	|必须	|int	|页码|
|pageSize	|必须	|int	|每页几条数据|
| id	|可选|	Integer	|id|
| userName	|可选|	String	|用户名|
| userType	|可选|	Integer	|用户类型|
| userPassword	|可选|	String	|密码|
| isDelete	|可选|	Integer	|是否删除|
| campus	|可选|	Integer	|单位|


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
            "userName": "",
            "userType": "",
            "userPassword": "",
            "isDelete": "",
            "campus": "",
}]
```

1.2 管理员信息添加接口
-----------------

1.2.1接口说明

```
url：/api/sysUser/add
协议：http
请求方式：POST
```


1.2.2请求参数

|参数名称 |是否必须 |类型| 描述 |
|-------------|-------------|-------------|:--------------:|
| id	|可选|	Integer	|id|
| userName	|可选|	String	|用户名|
| userType	|可选|	Integer	|用户类型|
| userPassword	|可选|	String	|密码|
| isDelete	|可选|	Integer	|是否删除|
| campus	|可选|	Integer	|单位|

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

1.3 管理员信息修改接口
-----------------

1.3.1接口说明

```
url：/api/sysUser/edit
协议：http
请求方式：POST
```


1.3.2请求参数

|参数名称 |是否必须 |类型| 描述 |
|-------------|-------------|-------------|:--------------:|
| id	|可选|	Integer	|id|
| userName	|可选|	String	|用户名|
| userType	|可选|	Integer	|用户类型|
| userPassword	|可选|	String	|密码|
| isDelete	|可选|	Integer	|是否删除|
| campus	|可选|	Integer	|单位|

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

1.4 通过id查询管理员信息
-----------------

1.4.1接口说明

```
url：/api/sysUser/queryById
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
            "userName": "",
            "userType": "",
            "userPassword": "",
            "isDelete": "",
            "campus": "",
}
```

1.5 通过id删除管理员信息
-----------------

1.5.1接口说明

```
url：/api/sysUser/deleteById
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

1.6 导出管理员信息接口
-----------------

1.6.1接口说明

```
url：/api/sysUser/exportExcel
协议：http
请求方式：POST
```


1.6.2请求参数

|参数名称 |是否必须 |类型| 描述 |
|-------------|-------------|-------------|:--------------:|
| id	|可选|	Integer	|id|
| userName	|可选|	String	|用户名|
| userType	|可选|	Integer	|用户类型|
| userPassword	|可选|	String	|密码|
| isDelete	|可选|	Integer	|是否删除|
| campus	|可选|	Integer	|单位|

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


1.7 导入管理员信息接口
-----------------

1.7.1接口说明

```
url：/api/sysUser/importExcel
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
