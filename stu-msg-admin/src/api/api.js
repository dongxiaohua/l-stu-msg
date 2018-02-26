import axios from "axios";

let base = 'http://localhost:8080/stu';
// let base = '';
let header = {
    'content-type': 'application/json;charset=UTF-8'
};


export const requestLogin = params => {
    return axios.post(`${base}/login`, params, {headers: header}).then(res => res.data);
};

export const getUserList = params => {
    return axios.get(`${base}/user/list`, {params: params});
};

/**
 * 获取学生信息数据
 * @param params
 * @returns {V|*}
 */
export const getStuMessageList = params => {
    return axios.get(`${base}/stu/list`, {params: params}).then(ref => ref.data);
};

/**
 * 删除指定信息
 * @param params
 * @returns {*|V}
 */
export const removeStu = params => {
    return axios.get(`${base}/user/remove`, {params: params});
};

/**
 * 批量删除
 * @param params
 * @returns {*|V}
 */
export const batchRemoveStu = params => {
    return axios.get(`${base}/user/batchremove`, {params: params});
};

/**
 * 修改学生信息
 * @param params
 * @returns {*|V}
 */
export const editStu = params => {
    return axios.get(`${base}/stu/edit`, {params: params});
};

/**
 * 添加学生信息
 * @param params
 * @returns {*|V}
 */
export const addStu = params => {
    return axios.get(`${base}/stu/add`, {params: params});
};