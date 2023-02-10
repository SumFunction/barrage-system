//导入axios实例
import service from '..'
//向外暴露获取数据的请求
export function commitBarage(data) {   //data为传递的参数，可选，看url请求时是否需要
    //在axios实例中配置请求方法，请求url地址，请求参数等等
    //（不需要再写域名，请求时会自动将域名合并进去） 
    return service({
        method: 'post',
        data: data,
        url: `/barrage`
    })
}
