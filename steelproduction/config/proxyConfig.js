module.exports = {
  proxyList: {
    '/apis': {
      // 测试环境
      target: 'http://127.0.0.1:8080/sp', // 接口域名
      changeOrigin: true, // 是否跨域
      pathRewrite: {
        '^/apis': '', // 需要rewrite重写的,
      },
    },
  },
}
