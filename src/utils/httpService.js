import axios from 'axios';

import { ElLoading, ElMessage } from "element-plus";
import router from '../router';
import md5 from 'js-md5';



const formatParams = (data) => {
    let arr = [];
    for (let name in data) {
        arr.push(encodeURIComponent(name) + "=" + encodeURIComponent(data[name]));
    }
    return arr.join("&");
}

/*设置baseURL*/
let baseURL = '/api';
// process.env.NODE_ENV  用于判断当前运行环境，根据 npm run serve 或 npm run build 
if (process.env.NODE_ENV === 'production') {
    baseURL = 'http://42.192.119.156:9001/';
} else {

}
axios.defaults.baseURL = baseURL;
axios.defaults.timeout = 45000; //超时时间
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
//请求拦截
axios.interceptors.request.use(function (config) {
    //添加token验证机制
    return config
})
//返回拦截
axios.interceptors.response.use(response => {
    const res = response.data    //这是响应返回后的结果
    console.log(res)
    //在这里可以根据返回的状态码对存在响应错误的请求做拦截，提前做处理。
    // 如果自定义代码不是200，则判断为错误。
    if (res.code == 500) {
        // 重新登陆
        ElMessage.error(res.msg);
        return res
    } else if (res.code == 300) {
        router.push("/login");
        // 重新登陆
        ElMessage.error("登录超时");
        return res
    } else if (res.code == 400) {
        router.push("/login");
        // 重新登陆
        ElMessage.error("非法请求");
        return res
    }else {
        return res
    }
}, error => {
    return Promise.reject(error);
})
export const httpService = (url, params, method, headers, loading = false) => {
    if (loading) {
        var loadingInstance = ElLoading.service({
            lock: true,
        });
    }

    if (!headers) {
        headers = {
            'Content-Type': 'application/x-www-form-urlencoded'
        }
    }
    if (method === 'post') {
        return new Promise((resolve, reject) => {
            axios.post(url, params, {
                headers: headers
            }).then(data => {
                if (loading) loadingInstance.close();
                resolve(data);

            }).catch(error => {
                reject(error);
                ElMessage.error('网络不给力');
                if (loading) loadingInstance.close();
            });
        })
    }
    else {
        return new Promise((resolve, reject) => {
            axios.get(url + '?' + formatParams(params)).then(data => {
                console.log(data)
                resolve(data);
                if (loading) loadingInstance.close();
            }).catch(error => {
                ElMessage.error('网络不给力');
                if (loading) loadingInstance.close();
                reject(error);
            });
        })
    }
}