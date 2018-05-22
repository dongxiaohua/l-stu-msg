<template>
  <section>
    <!--工具条-->
    <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
      <el-form :inline="true" :model="filters">
        <el-form-item>
          <el-input v-model="filters.UserName" placeholder="用户名"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="getUsers">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleAdd">新增</el-button>
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
      <el-table-column prop="userName" width="180" label="用户名" sortable>
      </el-table-column>
      <el-table-column prop="passWord" width="180" label="密码" sortable>
      </el-table-column>
      <el-table-column prop="rights" min-width="110" label="权限">
      </el-table-column>
      <el-table-column prop="status" min-width="110" label="状态">
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
        <el-form-item label="用户名" prop="userName">
          <el-input v-model="editForm.userName" placeholder=""></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="passWord">
          <el-input v-model="editForm.passWord"></el-input>
        </el-form-item>
        <el-form-item label="权限" prop="rights">
          <el-input v-model="editForm.rights"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click.native="editFormVisible = false">取消</el-button>
        <el-button type="primary" @click.native="editSubmit" :loading="editLoading">提交</el-button>
      </div>
    </el-dialog>

    <!--新增页面-->
    <el-dialog title="新增" v-model="addFormVisible" :close-on-click-modal="false">
      <el-form :model="addForm" label-width="80px" :rules="addFormRules" ref="addForm">
        <el-form-item label="用户名" prop="userName">
          <el-input v-model="addForm.userName" placeholder=""></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="passWord">
          <el-input v-model="addForm.passWord"></el-input>
        </el-form-item>
        <el-form-item label="权限" prop="rights">
          <el-input v-model="addForm.rights"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click.native="addFormVisible = false">取消</el-button>
        <el-button type="primary" @click.native="addSubmit" :loading="addLoading">提交</el-button>
      </div>
    </el-dialog>

  </section>
</template>

<script>
    //import NProgress from 'nprogress'
    import {addStuUser, batchRemoveUser, editStuUser, getStuUserList} from '../../api/api';

    export default {
        data() {
            return {
                filters: {
                    UserName: ''
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
                },
                addFormVisible: false,//编辑界面是否显示
                addLoading: false,
                addFormRules: {
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
                addForm: {
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
                    return (p.userName && p.userName.indexOf(keyword) !== -1)
                        || (p.passWord && p.passWord.indexOf(keyword) !== -1)
                        || (p.rights && p.rights.indexOf(keyword) !== -1);
                });
                self.total = v.length;
                v = v.slice((page - 1) * size, page * size);
                return v;
            }
        },
        methods: {
            handleSizeChange(v) {
                this.table.size = v;
            },
            handleCurrentChange(val) {
                this.table.page = val;
            },
            //搜索 获取用户列表
            getUsers() {
                let para = {
                    userName: this.filters.UserName
                };
                this.listLoading = true;
                //获取数据接口
                getStuUserList(para).then((data) => {
                    this.table.data = data;
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
                    userName: '',
                    passWord: '',
                    rights: ''
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
                            editStuUser(para).then((res) => {
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
                            addStuUser(para).then((res) => {
                                this.addLoading = false;
                                console.log(res);
                                //NProgress.done();
                                this.$message({
                                    message: '提交成功',
                                    type: 'success'
                                });
                                this.$refs['addForm'].resetFields();
                                this.addFormVisible = false;
                                this.getUsers();
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
                    batchRemoveUser(para).then((res) => {
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