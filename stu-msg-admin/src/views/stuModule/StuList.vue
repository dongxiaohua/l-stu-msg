<template>
  <section>
    <!--工具条-->
    <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
      <el-form :inline="true" :model="filters">
        <el-form-item>
          <el-input v-model="filters.StuNumber" placeholder="学号"></el-input>
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
    <el-table :data="getStuMessageList" highlight-current-row v-loading="listLoading" @selection-change="selsChange" style="width: 100%;">
      <el-table-column type="selection" width="55">
      </el-table-column>
      <el-table-column type="index" width="60">
      </el-table-column>
      <el-table-column prop="stuName" label="姓名" width="120" sortable>
      </el-table-column>
      <el-table-column prop="stuSex" label="性别" width="100" :formatter="formatSex" sortable>
      </el-table-column>
      <el-table-column prop="stuAge" label="年龄" width="100" sortable>
      </el-table-column>
      <el-table-column prop="stuNumber" label="学号" width="180" sortable>
        <template scope="scope">
          <router-link :to="'/info_list?stuNumber='+scope.row.stuNumber">{{ scope.row.stuNumber }}</router-link>
        </template>
      </el-table-column>
      <el-table-column prop="classNum" label="所属班级编号" width="180" sortable>
      </el-table-column>
      <el-table-column prop="className" label="所属班级名称" min-width="100" sortable>
      </el-table-column>
      <el-table-column label="操作" width="150">
        <template scope="scope">
          <el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-button type="danger" size="small" @click="stuMesDel(scope.$index, scope.row)">删除</el-button>
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
        <el-form-item label="姓名" prop="stuName">
          <el-input v-model="editForm.stuName" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-radio-group v-model="editForm.stuSex">
            <el-radio class="radio" :label="1">男</el-radio>
            <el-radio class="radio" :label="0">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="年龄">
          <el-input-number v-model="editForm.stuAge" :min="0" :max="200"></el-input-number>
        </el-form-item>
        <!--<el-form-item label="生日">-->
        <!--<el-date-picker type="date" placeholder="选择日期" v-model="editForm.birth"></el-date-picker>-->
        <!--</el-form-item>-->
        <el-form-item label="学号" prop="stuNumber">
          <el-input v-model="editForm.stuNumber" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="班级编号" prop="classNum">
          <el-input v-model="editForm.classNum" auto-complete="off"></el-input>
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
        <el-form-item label="姓名" prop="stuName">
          <el-input v-model="addForm.stuName" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-radio-group v-model="addForm.stuSex">
            <el-radio class="radio" :label="1">男</el-radio>
            <el-radio class="radio" :label="0">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="年龄">
          <el-input-number v-model="addForm.stuAge" :min="0" :max="200"></el-input-number>
        </el-form-item>
        <!--<el-form-item label="生日">-->
        <!--<el-date-picker type="date" placeholder="选择日期" v-model="addForm.birth"></el-date-picker>-->
        <!--</el-form-item>-->
        <el-form-item label="学号" prop="stuNumber">
          <el-input v-model="addForm.stuNumber" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="班级编号" prop="classNum">
          <el-input v-model="addForm.classNum" auto-complete="off"></el-input>
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
    import {getStuMessageList, removeStu, batchRemoveStu, editStu, addStu} from '../../api/api';

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
                    stuName: [
                        {required: true, message: '请输入姓名', trigger: 'blur'}
                    ], stuNumber: [
                        {required: true, message: '请输入学号', trigger: 'blur'}
                    ], classNum: [
                        {required: true, message: '请输入班级编号', trigger: 'blur'}
                    ]
                },
                //编辑界面数据
                editForm: {
                    id: 0,
                    stuName: '',
                    stuSex: -1,
                    stuAge: 0,
                    stuNumber: '',
                    classNumber: ''
                },

                addFormVisible: false,//新增界面是否显示
                addLoading: false,
                addFormRules: {
                    stuName: [
                        {required: true, message: '请输入姓名', trigger: 'blur'}
                    ], stuNumber: [
                        {required: true, message: '请输入学号', trigger: 'blur'}
                    ], classNum: [
                        {required: true, message: '请输入班级编号', trigger: 'blur'}
                    ]
                },
                //新增界面数据
                addForm: {
                    stuName: '',
                    stuSex: -1,
                    stuAge: 0,
                    stuNumber: '',
                    classNumber: ''
                }

            }
        },
        computed: {
            getStuMessageList: function () {
                var self = this, data = self.table.data, page = self.table.page, size = self.table.size, keyword = self.search.keyword;
                var v = data.filter(function (p) {
                    return (p.stuName && p.stuName.indexOf(keyword) !== -1)
                        || (p.stuSex && p.stuSex.indexOf(keyword) !== -1)
                        || (p.stuAge && p.stuAge.indexOf(keyword) !== -1)
                        || (p.stuNumber && p.stuNumber.indexOf(keyword) !== -1)
                        || (p.classNum && p.classNum.indexOf(keyword) !== -1)
                        || (p.className && p.className.indexOf(keyword) !== -1);
                });
                self.total = v.length;
                v = v.slice((page - 1) * size, page * size);
                return v;
            },
        },
        methods: {
            //性别显示转换
            formatSex: function (row, column) {
                return row.stuSex === 1 ? '男' : row.stuSex === 0 ? '女' : '未知';
            },
            handleSizeChange(v) {
                this.table.size = v;
            },
            handleCurrentChange(val) {
                this.table.page = val;
            },
            //搜索 获取用户列表
            getUsers() {
                let para = {
                    stuNumber: this.filters.StuNumber
                };
                this.listLoading = true;
                //获取数据接口
                getStuMessageList(para).then((data) => {
                    this.table.data = data;
                    this.listLoading = false;
                });
            },
            //删除
            stuMesDel: function (index, row) {
                this.$confirm('确认删除该记录吗?', '提示', {
                    type: 'warning'
                }).then(() => {
                    this.listLoading = true;
                    //NProgress.start();
                    let para = {stuNumber: row.stuNumber};
                    removeStu(para).then((res) => {
                        this.listLoading = false;
                        //NProgress.done();
                        if (res.data.code === "SUCCESS") {
                            this.$message({
                                message: '删除成功',
                                type: 'success'
                            });
                        } else {
                            this.$message({
                                message: '你没有权限',
                                type: 'warning'
                            });
                        }
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
                            editStu(para).then((res) => {
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
                            //NProgress.start();
                            let para = Object.assign({}, this.addForm);
                            console.log(para);
//							para.birth = (!para.birth || para.birth === '') ? '' : util.formatDate.format(new Date(para.birth), 'yyyy-MM-dd');
                            addStu(para).then((res) => {
                                this.addLoading = false;
                                console.log(res);
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