module.exports = {
    devServer: {
        port: 8888,     // 端口

    },
    lintOnSave: false   // 取消 eslint 验证
};
// 设置代理
// module.exports = {
//     devServer:{
//         proxy:{
//             '/api':{
//                 target:'http://localhost:80',
//                 ws:true,
//                 changeOrigin:true
//             }
//         }
//     }
// }
// 使用方法
// let that = this
// this.axios.get("/api/db.json")
//             .then(function(res){
//                 console.log(res.data.companies)
//                 that.customers=res.data.users
//             })