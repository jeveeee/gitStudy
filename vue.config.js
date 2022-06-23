module.exports = {
    devServer: {
        open: true,
        host: '42.192.119.156',
        port: 9002,
        https: false,
        hotOnly: false,
        proxy: { // 配置跨域
            '/api': {//要访问的跨域的api的域名
                target: '42.192.119.156:9001/',
                ws: true,
                changOrigin: true,
                pathRewrite: {
                    '^/api': ''
                }
            }
        },
        before: app => { }
    },
    configureWebpack: {
        externals: {
            vue: "Vue", // 注意 vue需要外部引入。放到 echarts前面，防止 console 报错
            "element-plus": "ElementPlus",
            'vuex': 'Vuex',
            'vue-router': 'VueRouter',
            'axios': 'axios',
        }
        
    },
}