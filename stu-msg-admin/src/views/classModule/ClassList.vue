<template>
  <section>
    <!--工具条-->
    <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
      <el-form :inline="true" :model="filters">
        <el-form-item>
          <el-input v-model="filters.classNum" placeholder="班级编号"></el-input>
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
    <el-table :data="getClassMessageList" highlight-current-row v-loading="listLoading" @selection-change="selsChange" style="width: 100%;">
      <el-table-column type="selection" width="55">
      </el-table-column>
      <el-table-column type="index" width="60">
      </el-table-column>
      <el-table-column prop="className" label="班级名称" sortable>
      </el-table-column>
      <el-table-column prop="classGrade" label="年级" sortable>
      </el-table-column>
      <el-table-column prop="classNum" label="班级编号" sortable>
        <template scope="scope">
          <router-link :to="'/class_info_list?classNum='+scope.row.classNum">{{ scope.row.classNum }}</router-link>
        </template>
      </el-table-column>
      <el-table-column prop="classDepartment" label="所属学部" min-width="100" sortable>
      </el-table-column>
      <el-table-column label="操作" width="150">
        <template scope="scope">
          <el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
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
        <el-form-item label="班级名称" prop="className">
          <el-input v-model="editForm.className" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="年级" prop="classGrade">
          <el-input v-model="editForm.classGrade"></el-input>
        </el-form-item>
        <el-form-item label="班级编号" prop="classNum">
          <el-input v-model="editForm.classNum" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="所属学部" prop="classDepartment">
          <el-input v-model="editForm.classDepartment" auto-complete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click.native="editFormVisible = false">取消</el-button>
        <el-button type="primary" @click.native="editSubmit" :loading="editLoading">提交</el-button>
      </div>
    </el-dialog>

    <!--新增界面-->
    <el-dialog title="新增" v-model="addFormVisible" :close-on-click-modal="false">
      <el-form :model="addForm" label-width="80px" :rules="addFormRules" ref="addForm">
        <el-form-item label="班级名称" prop="className">
          <el-input v-model="addForm.className"></el-input>
        </el-form-item>
        <el-form-item label="所属学部" prop="classDepartment">
          <el-input v-model="addForm.classDepartment"></el-input>
        </el-form-item>
        <el-form-item label="年级" prop="classGrade">
          <el-input v-model="addForm.classGrade"></el-input>
        </el-form-item>
        <el-form-item label="班级编号" prop="classNum">
          <el-input v-model="addForm.classNum"></el-input>
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
    import util from '../../common/js/util'
    //import NProgress from 'nprogress'
    import {getClassMessageList, addClass, editClass} from '../../api/api';

    export default {
        data() {
            return {
                filters: {
                    classNum: ''
                },
                search: {
                    keyword: ''
                },
                table: {
                    loading: false,
                    total: 0,
                    size: 25,
                    page: 1,
                    pageSize: 10,
                    data: []
                },
                listLoading: false,
                sels: [],//列表选中列

                editFormVisible: false,//编辑界面是否显示
                editLoading: false,
                editFormRules: {
                    className: [
                        {required: true, message: '请输入班级名称', trigger: 'blur'}
                    ], classDepartment: [
                        {required: true, message: '请输入所属学部', trigger: 'blur'}
                    ], classGrade: [
                        {required: true, message: '请输入年级', trigger: 'blur'}
                    ], classNum: [
                        {required: true, message: '请输入班级编号', trigger: 'blur'}
                    ]
                },
                //编辑界面数据
                editForm: {
                    id: 0,
                    className: '',
                    classDepartment: '',
                    classGrade: '',
                    classNum: ''
                },

                addFormVisible: false,//新增界面是否显示
                addLoading: false,
                addFormRules: {
                    className: [
                        {required: true, message: '请输入班级名称', trigger: 'blur'}
                    ], classDepartment: [
                        {required: true, message: '请输入所属学部', trigger: 'blur'}
                    ], classGrade: [
                        {required: true, message: '请输入年级', trigger: 'blur'}
                    ], classNum: [
                        {required: true, message: '请输入班级编号', trigger: 'blur'}
                    ]
                },
                //新增界面数据
                addForm: {
                    className: '',
                    classDepartment: '',
                    classGrade: '',
                    classNum: ''
                }

            }
        },
        computed: {
            getClassMessageList: function () {
                var self = this, data = self.table.data, page = self.table.page, size = self.table.size, keyword = self.search.keyword;
                var v = data.filter(function (p) {
                    return (p.className && p.className.indexOf(keyword) !== -1)
                        || (p.classGrade && p.classGrade.indexOf(keyword) !== -1)
                        || (p.classDepartment && p.classDepartment.indexOf(keyword) !== -1)
                        || (p.classNum && p.classNum.indexOf(keyword) !== -1);
                });
                self.total = v.length;
                v = v.slice((page - 1) * size, page * size);
                return v;
            },
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
                    classNum: this.filters.classNum
                };
                this.listLoading = true;
                //获取数据接口
                getClassMessageList(para).then((data) => {
                    console.log(data);
                    this.table.data = data;
                    this.listLoading = false;
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
                    className: '',
                    classDepartment: '',
                    classGrade: '',
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
                            editClass(para).then((res) => {
                                this.editLoading = false;
                                //NProgress.done();
                                if (res.data.code === "SUCCESS") {
                                    this.$message({
                                        message: '提交成功',
                                        type: 'success'
                                    });
                                } else {
                                    this.$message({
                                        message: '你没有权限',
                                        type: 'warning'
                                    });
                                }
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

                            let para = Object.assign({}, this.addForm);
//							para.birth = (!para.birth || para.birth === '') ? '' : util.formatDate.format(new Date(para.birth), 'yyyy-MM-dd');
                            addClass(para).then((res) => {
                                this.addLoading = false;
                                if (res.data && res.data.code === 'SUCCESS') {
                                    this.$message({
                                        message: '提交成功',
                                        type: 'success'
                                    });
                                } else {
                                    this.$message({
                                        message: '你没有权限',
                                        type: 'warning'
                                    });
                                }
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