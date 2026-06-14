import {ElMessage} from 'element-plus'
export function messageTitle(msg,type){
    ElMessage({
        showClose: true,
        message: msg,
        type: type,
        center: true
    })
}