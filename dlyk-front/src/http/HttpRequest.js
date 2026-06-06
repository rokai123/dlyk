import axios from 'axios'
axios.defaults.url = 'http://localhost:8092/'
axios.defaults.timeout = 5000
export function doPost(url,data){
    return axios.post(url,data)
}