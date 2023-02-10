//引入
import axios from 'axios'
//新建一个axios实例,并做一些简单配置
let service = axios.create({
    baseURL: 'http://localhost:8005/',   //域名，只在这里设置一次
    timeout: 3000     //请求超时时长，时长自己设定 
})
//将实例暴露出去
export default service