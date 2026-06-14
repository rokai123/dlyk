import axios from 'axios'
axios.defaults.baseURL = 'http://localhost:8092/'
axios.defaults.timeout = 5000
// post请求
export function doPost(url,data){
    return axios.post(url,data)
}