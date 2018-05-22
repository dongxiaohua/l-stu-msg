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
    return axios.get(`${base}/stu/remove`, {params: params});
};

/**
 * 批量删除
 * @param params
 * @returns {*|V}
 */
export const batchRemoveStu = params => {
    return axios.get(`${base}/stu/batchremove`, {params: params});
};

/**
 * 修改学生信息
 * @param params
 * @returns {*|V}
 */
export const editStu = params => {
    return axios.post(`${base}/stu/edit`, params);
};

/**
 * 添加学生信息
 * @param params
 * @returns {*|V}
 */
export const addStu = params => {
    return axios.post(`${base}/stu/add`, params);
};

/**
 * 添加学生info
 * @param params
 * @returns {*}
 */
export const addStuInfo = params => {
    return axios.post(`${base}/info/add-stu`, params);
};

/**
 * 根据学号查询学生所有后勤信息
 * @param params
 * @returns {Promise.<TResult>}
 */
export const getStuInfoList = params => {
    return axios.post(`${base}/info/list`, params);
};

/**
 * 修改学生后勤信息
 * @param params
 * @returns {*}
 */
export const editStuInfo = params => {
    return axios.post(`${base}/info/edit`, params);
};

/**
 * 删除指定信息
 * @param params
 * @returns {*}
 */
export const removeStuInfo = params => {
    return axios.post(`${base}/info/delete`, params);
};

/**
 * 获取班级列表
 * @param params
 * @returns {Promise.<TResult>}
 */
export const getClassMessageList = params => {
    return axios.get(`${base}/class/getClassMessageList`, {params: params}).then(ref => ref.data);
};

/**
 * 添加班级
 * @param params
 * @returns {*}
 */
export const addClass = params => {
    return axios.post(`${base}/class/add`, params);
};

/**
 * 编辑班级信息
 * @param params
 * @returns {*}
 */
export const editClass = params => {
    return axios.post(`${base}/class/edit`, params);
};

/**
 * 获取用户信息列表
 * @param params
 * @returns {AxiosPromise}
 */
export const getStuUserList = params => {
    return axios.get(`${base}/admin/list`, {params: params}).then(ref => ref.data);
};

/**
 * 删除指定用户
 * @param params
 * @returns {AxiosPromise}
 */
export const removeStuUser = params => {
    return axios.get(`${base}/admin/delete`, params);
};

/**
 * 编辑用户信息
 * @param params
 * @returns {AxiosPromise}
 */
export const editStuUser = params => {
    return axios.post(`${base}/admin/edit`, params);
};

/**
 * 添加用户
 * @param params
 * @returns {*|void}
 */
export const addStuUser = params => {
    return axios.post(`${base}/admin/add`, params);
};