<template>
    <section>
        <!--工具条-->
        <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
            <el-form :inline="true" :model="filters">
                <!--<el-form-item>-->
                <!--<el-input v-model="filters.StuNumber" placeholder="学号"></el-input>-->
                <!--</el-form-item>-->
                <!--<el-form-item>-->
                <!--<el-button type="primary" v-on:click="getUsers">查询</el-button>-->
                <!--</el-form-item>-->
                <el-form-item>
                    <router-link :to="'/form'"
                                 style="background-color: #717AD2;color: #f4f4f4;padding: 10px;border-radius:5px;">新增
                    </router-link>
                </el-form-item>
            </el-form>
        </el-col>

        <!--列表-->
        <el-table :data="getStuUserList" highlight-current-row v-loading="listLoading" @selection-change="selsChange"
                  style="width: 100%;">
            <el-table-column type="selection" width="55">
            </el-table-column>
            <el-table-column type="index" width="60">
            </el-table-column>
            <el-table-column prop="id" width="120" label="用户ID" sortable>
            </el-table-column>
            <el-table-column prop="userName" width="120" label="用户名称" sortable>
            </el-table-column>
            <el-table-column prop="passWord" label="用户密码" sortable>
            </el-table-column>
            <el-table-column prop="rights" min-width="110" label="权限">
            </el-table-column>
            <el-table-column label="操作" width="150">
                <template scope="scope">
                    <el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                    <el-button type="danger" size="small" @click="stuAdminDel(scope.$index, scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <!--工具条-->
        <el-col :span="24" class="toolbar">
            <!--<el-button type="danger" @click="batchRemove" :disabled="this.sels.length===0">批量删除</el-button>-->
            <!--分页-->
            <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="table.page"
                    :page-sizes="[10, 25, 50, 100]"
                    :page-size="table.size"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="table.data.length" style="float:right">
            </el-pagination>
        </el-col>

        <!--编辑界面-->
        <el-dialog title="编辑" v-model="editFormVisible" :close-on-click-modal="false">
            <el-form :model="editForm" label-width="80px" :rules="editFormRules" ref="editForm">
                <el-form-item label="用户名称" prop="infoName">
                    <el-input v-model="editForm.userName" placeholder=""></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="stuNumber">
                    <el-input v-model="editForm.passWord"></el-input>
                </el-form-item>
                <el-form-item label="权限">
                    <el-input v-model="editForm.rights"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="editFormVisible = false">取消</el-button>
                <el-button type="primary" @click.native="editSubmit" :loading="editLoading">提交</el-button>
            </div>
        </el-dialog>


    </section>
</template>

<script>
    import util from '../../common/js/util'
    //import NProgress from 'nprogress'
    import {getStuUserList, removeStuUser, batchRemoveUser, editStuUser} from '../../api/api';

    export default {
        data() {
            return {
                filters: {
                    StuNumber: ''
                },
                search: {
                    keyword: ''
                },
                table: {
                    loading: false,
                    total: 0,
                    size: 10,
                    page: 1,
                    pageSize: 10,
                    data: []
                },
                listLoading: false,
                sels: [],//列表选中列

                editFormVisible: false,//编辑界面是否显示
                editLoading: false,
                editFormRules: {
                    userName: [
                        {required: true, message: '请输入用户名', trigger: 'blur'}
                    ],
                    passWord: [
                        {required: true, message: '请输入密码', trigger: 'blur'}
                    ],
                    rights: [
                        {required: true, message: '请输入权限', trigger: 'blur'}
                    ]
                },
                //编辑界面数据
                editForm: {
                    userName: '',
                    passWord: '',
                    rights: ''
                }

            }
        },
        computed: {
            getStuUserList: function () {
                var self = this, data = self.table.data, page = self.table.page, size = self.table.size,
                    keyword = self.search.keyword;
                var v = data.filter(function (p) {
                    return (p.userName && p.userName.indexOf(keyword) !== -1 )
                        || (p.passWord && p.passWord.indexOf(keyword) !== -1 )
                        || (p.rights && p.rights.indexOf(keyword) !== -1 );
                });
                self.total = v.length;
                v = v.slice((page - 1) * size, page * size);
                return v;
            },
        },
        methods: {
            handleSizeChange (v){
                this.table.size = v;
            },
            handleCurrentChange(val) {
                this.table.page = val;
            },
            //搜索 获取用户列表
            getUsers() {
                let p = this.$route.query;
                if (p.stuNumber === undefined) {
                    return;
                }
                let para = {
                    stuNumber: p.stuNumber
                };
                this.listLoading = true;
                //获取数据接口
                getStuUserList(para).then((response) => {
                    var d = response.data;
                    if (d && d.code === 'SUCCESS') {
                        this.table.data = d.data;
                    } else {

                        util.warning(d.msg);
                    }
                    this.listLoading = false;
                });
            },
            //删除
            stuAdminDel: function (index, row) {
                this.$confirm('确认删除该用户吗?', '提示', {
                    type: 'warning'
                }).then(() => {
                    this.listLoading = true;
                    //NProgress.start();
                    let para = {stuNumber: row.stuNumber};
                    removeStuInfo(para).then((res) => {
                        this.listLoading = false;
                        //NProgress.done();
                        this.$message({
                            message: '删除成功',
                            type: 'success'
                        });
                        this.getUsers();
                    });
                }).catch(() => {

                });
            },
            //显示编辑界面
            handleEdit: function (index, row) {
                this.editFormVisible = true;
                this.editForm = Object.assign({}, row);
            },
            //显示新增界面
            handleAdd: function () {
                this.addFormVisible = true;
                this.addForm = {
                    stuName: '',
                    stuSex: -1,
                    stuAge: 0,
                    stuNumber: '',
                    classNum: ''
                };
            },
            //编辑
            editSubmit: function () {
                this.$refs.editForm.validate((valid) => {
                    if (valid) {
                        this.$confirm('确认提交吗？', '提示', {}).then(() => {
                            this.editLoading = true;
                            //NProgress.start();
                            let para = Object.assign({}, this.editForm);
//							para.birth = (!para.birth || para.birth === '') ? '' : util.formatDate.format(new Date(para.birth), 'yyyy-MM-dd');
                            editStuInfo(para).then((res) => {
                                this.editLoading = false;
                                //NProgress.done();
                                this.$message({
                                    message: '提交成功',
                                    type: 'success'
                                });
                                this.$refs['editForm'].resetFields();
                                this.editFormVisible = false;
                                this.getUsers();
                            });
                        });
                    }
                });
            },
            //新增
            addSubmit: function () {
                this.$refs.addForm.validate((valid) => {
                    if (valid) {
                        this.$confirm('确认提交吗？', '提示', {}).then(() => {
                            this.addLoading = true;
                            //NProgress.start();
                            let para = Object.assign({}, this.addForm);
                            console.log(para);
//							para.birth = (!para.birth || para.birth === '') ? '' : util.formatDate.format(new Date(para.birth), 'yyyy-MM-dd');
                            addStu(para).then((res) => {
                                this.addLoading = false;
                                console.log(res);
                                //NProgress.done();
                                this.$message({
                                    message: '提交成功',
                                    type: 'success'
                                });
                                this.$refs['addForm'].resetFields();
                                this.addFormVisible = false;
                                console.log("=========");
                                this.getUsers();
                                console.log("====");
                            });
                        });
                    }
                });
            },
            selsChange: function (sels) {
                this.sels = sels;
            },
            //批量删除
            batchRemove: function () {
                var ids = this.sels.map(item => item.id).toString();
                this.$confirm('确认删除选中记录吗？', '提示', {
                    type: 'warning'
                }).then(() => {
                    this.listLoading = true;
                    //NProgress.start();
                    let para = {ids: ids};
                    batchRemoveStu(para).then((res) => {
                        this.listLoading = false;
                        //NProgress.done();
                        this.$message({
                            message: '删除成功',
                            type: 'success'
                        });
                        this.getUsers();
                    });
                }).catch(() => {

                });
            }
        },
        mounted() {
            this.getUsers();
        }
    }

</script>
<style scoped>

</style>