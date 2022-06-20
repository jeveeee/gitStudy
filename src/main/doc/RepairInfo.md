1 报修信息接口
=================

1.1 报修信息列表接口
-----------------

1.1.1 接口说明

```
url：/api/repairInfo/list/{pageNum}/{pageSize}
协议：http
请求方式：GET
```


1.1.2请求参数

|参数名称 |是否必须 |类型| 描述 |
|-------------|-------------|-------------|:--------------:|
|pageNum	|必须	|int	|页码|
|pageSize	|必须	|int	|每页几条数据|
| id	 |可选|	Integer	|主键|
| type	 |可选|	Integer	|报修类型|
| status	|可选|	Integer	|报修单状态|
| repairDate	|可选|	Date	|报修时间|
| address	|可选|	String	|报修地址|
| description	|可选|	String	|故障描述|
| imageUrl	|可选|	String	|故障图片|
| message	|可选|	String	|留言|
| evaluate	|可选|	String	|意见和建议|
| reason	|可选|	String	|故障原因|
| consumables	|可选|	String	|故障耗材|
| applicantName	|可选|	String	|申请人姓名|
| remarks	|可选|	String	|备注|
| mobile	|可选|	String	|申请人联系电话|
| star	|可选|	Integer	|星级别评级|
| radioUrl	|可选|	String	|语音url|
| evaluateDate	|可选|	Date	|评价时间|
| openid	|可选|	String	|openid|
| campus	|可选|	Integer	|学校|
| finishTime	|可选|	String	|维修完成时间|
| allocatedTime	|可选|	String	|任务分配时间|
| detail	|可选|	String	|故障处理说明|
| repairCancelReason	|可选|	String	|报修取消原因|
| repairCancelReasonRemark	|可选|	String	|报修取消原因备注|
| repairCancelDate	|可选|	Date	|报修取消时间|
| location	|可选|	Integer	|位置名称|
| level	|可选|	Integer	|维修级别|


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
            "type": "",
            "status": "",
            "repairDate": "",
            "address": "",
            "description": "",
            "imageUrl": "",
            "message": "",
            "evaluate": "",
            "reason": "",
            "consumables": "",
            "applicantName": "",
            "remarks": "",
            "mobile": "",
            "star": "",
            "radioUrl": "",
            "evaluateDate": "",
            "openid": "",
            "campus": "",
            "finishTime": "",
            "allocatedTime": "",
            "detail": "",
            "repairCancelReason": "",
            "repairCancelReasonRemark": "",
            "repairCancelDate": "",
            "location": "",
            "level": "",
}]
```

1.2 报修信息添加接口
-----------------

1.2.1接口说明

```
url：/api/repairInfo/add
协议：http
请求方式：POST
```


1.2.2请求参数

|参数名称 |是否必须 |类型| 描述 |
|-------------|-------------|-------------|:--------------:|
| id	|可选|	Integer	|主键|
| type	|可选|	Integer	|报修类型|
| status	|可选|	Integer	|报修单状态|
| repairDate	|可选|	Date	|报修时间|
| address	|可选|	String	|报修地址|
| description	|可选|	String	|故障描述|
| imageUrl	|可选|	String	|故障图片|
| message	|可选|	String	|留言|
| evaluate	|可选|	String	|意见和建议|
| reason	|可选|	String	|故障原因|
| consumables	|可选|	String	|故障耗材|
| applicantName	|可选|	String	|申请人姓名|
| remarks	|可选|	String	|备注|
| mobile	|可选|	String	|申请人联系电话|
| star	|可选|	Integer	|星级别评级|
| radioUrl	|可选|	String	|语音url|
| evaluateDate	|可选|	Date	|评价时间|
| openid	|可选|	String	|openid|
| campus	|可选|	Integer	|学校|
| finishTime	|可选|	String	|维修完成时间|
| allocatedTime	|可选|	String	|任务分配时间|
| detail	|可选|	String	|故障处理说明|
| repairCancelReason	|可选|	String	|报修取消原因|
| repairCancelReasonRemark	|可选|	String	|报修取消原因备注|
| repairCancelDate	|可选|	Date	|报修取消时间|
| location	|可选|	Integer	|位置名称|
| level	|可选|	Integer	|维修级别|

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

1.3 报修信息修改接口
-----------------

1.3.1接口说明

```
url：/api/repairInfo/edit
协议：http
请求方式：POST
```


1.3.2请求参数

|参数名称 |是否必须 |类型| 描述 |
|-------------|-------------|-------------|:--------------:|
| id	|可选|	Integer	|主键|
| type	|可选|	Integer	|报修类型|
| status	|可选|	Integer	|报修单状态|
| repairDate	|可选|	Date	|报修时间|
| address	|可选|	String	|报修地址|
| description	|可选|	String	|故障描述|
| imageUrl	|可选|	String	|故障图片|
| message	|可选|	String	|留言|
| evaluate	|可选|	String	|意见和建议|
| reason	|可选|	String	|故障原因|
| consumables	|可选|	String	|故障耗材|
| applicantName	|可选|	String	|申请人姓名|
| remarks	|可选|	String	|备注|
| mobile	|可选|	String	|申请人联系电话|
| star	|可选|	Integer	|星级别评级|
| radioUrl	|可选|	String	|语音url|
| evaluateDate	|可选|	Date	|评价时间|
| openid	|可选|	String	|openid|
| campus	|可选|	Integer	|学校|
| finishTime	|可选|	String	|维修完成时间|
| allocatedTime	|可选|	String	|任务分配时间|
| detail	|可选|	String	|故障处理说明|
| repairCancelReason	|可选|	String	|报修取消原因|
| repairCancelReasonRemark	|可选|	String	|报修取消原因备注|
| repairCancelDate	|可选|	Date	|报修取消时间|
| location	|可选|	Integer	|位置名称|
| level	|可选|	Integer	|维修级别|

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

1.4 通过id查询报修信息
-----------------

1.4.1接口说明

```
url：/api/repairInfo/queryById
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
            "type": "",
            "status": "",
            "repairDate": "",
            "address": "",
            "description": "",
            "imageUrl": "",
            "message": "",
            "evaluate": "",
            "reason": "",
            "consumables": "",
            "applicantName": "",
            "remarks": "",
            "mobile": "",
            "star": "",
            "radioUrl": "",
            "evaluateDate": "",
            "openid": "",
            "campus": "",
            "finishTime": "",
            "allocatedTime": "",
            "detail": "",
            "repairCancelReason": "",
            "repairCancelReasonRemark": "",
            "repairCancelDate": "",
            "location": "",
            "level": "",
}
```

1.5 通过id删除报修信息
-----------------

1.5.1接口说明

```
url：/api/repairInfo/deleteById
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

1.6 导出报修信息接口
-----------------

1.6.1接口说明

```
url：/api/repairInfo/exportExcel
协议：http
请求方式：POST
```


1.6.2请求参数

|参数名称 |是否必须 |类型| 描述 |
|-------------|-------------|-------------|:--------------:|
| id	|可选|	Integer	|主键|
| type	|可选|	Integer	|报修类型|
| status	|可选|	Integer	|报修单状态|
| repairDate	|可选|	Date	|报修时间|
| address	|可选|	String	|报修地址|
| description	|可选|	String	|故障描述|
| imageUrl	|可选|	String	|故障图片|
| message	|可选|	String	|留言|
| evaluate	|可选|	String	|意见和建议|
| reason	|可选|	String	|故障原因|
| consumables	|可选|	String	|故障耗材|
| applicantName	|可选|	String	|申请人姓名|
| remarks	|可选|	String	|备注|
| mobile	|可选|	String	|申请人联系电话|
| star	|可选|	Integer	|星级别评级|
| radioUrl	|可选|	String	|语音url|
| evaluateDate	|可选|	Date	|评价时间|
| openid	|可选|	String	|openid|
| campus	|可选|	Integer	|学校|
| finishTime	|可选|	String	|维修完成时间|
| allocatedTime	|可选|	String	|任务分配时间|
| detail	|可选|	String	|故障处理说明|
| repairCancelReason	|可选|	String	|报修取消原因|
| repairCancelReasonRemark	|可选|	String	|报修取消原因备注|
| repairCancelDate	|可选|	Date	|报修取消时间|
| location	|可选|	Integer	|位置名称|
| level	|可选|	Integer	|维修级别|

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


1.7 导入报修信息接口
-----------------

1.7.1接口说明

```
url：/api/repairInfo/importExcel
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
