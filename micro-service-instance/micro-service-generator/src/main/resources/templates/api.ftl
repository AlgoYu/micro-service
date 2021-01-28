import { request } from "@/api/Request.js"

// 增加${moduleName}
function add(data,callback){
    request("post","/${instanceName}/add",data,callback);
}

// 根据ID删除${moduleName}
function deleteById(data,callback){
    request("delete","/${instanceName}/deleteById",data,callback);
}

// 根据ID更新${moduleName}
function modifyById(data,callback){
    request("put","/${instanceName}/modifyById",data,callback);
}

// 根据ID获取${moduleName}
function getById(data,callback){
    request("get","/${instanceName}/getById",data,callback);
}

// 获取所有${moduleName}
function list(callback){
    request("get","/${instanceName}/list",null,callback);
}

// 获取所有${moduleName}
function paging(data,callback){
    request("get","/${instanceName}/paging",data,callback);
}

// 导出API对象
export default {
    add,
    deleteById,
    modifyById,
    getById,
    list,
    paging
}