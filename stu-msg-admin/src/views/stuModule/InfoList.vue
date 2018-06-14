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
          <router-link :to="'/form'" style="background-color: #717AD2;color: #f4f4f4;padding: 10px;border-radius:5px;">新增</router-link>
        </el-form-item>
      </el-form>
    </el-col>

    <!--列表-->
    <el-table :data="getStuInfoList" highlight-current-row v-loading="listLoading" @selection-change="selsChange" style="width: 100%;">
      <el-table-column type="selection" width="55">
      </el-table-column>
      <el-table-column type="index" width="60">
      </el-table-column>
      <el-table-column prop="infoName" width="120" label="信息名称" sortable>
      </el-table-column>
      <el-table-column prop="stuName" width="120" label="学生姓名" sortable>
      </el-table-column>
      <el-table-column prop="stuNumber" label="学生学号" sortable>
      </el-table-column>
      <el-table-column prop="stuOff" label="是否注销" sortable>
        <template scope="scope">
          <span v-if="scope.row.stuOff === 1">是</span>
          <span v-else style="color: red">否</span>
        </template>
      </el-table-column>
      <el-table-column prop="theTime" label="开始时间" sortable>
      </el-table-column>
      <el-table-column prop="endTime" label="结束时间" sortable>
      </el-table-column>
      <el-table-column prop="stuContent" min-width="110" label="内容">
      </el-table-column>
      <el-table-column prop="stuReason" min-width="100" label="详情">
      </el-table-column>
      <el-table-column label="操作" width="150">
        <template scope="scope">
          <el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <!--<el-button type="danger" size="small" @click="stuInfoDel(scope.$index, scope.row)">删除</el-button>-->
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
        <el-form-item label="信息名称" prop="infoName">
          <el-input v-model="editForm.infoName" placeholder="如：荣誉，请假，记过，挂科等"></el-input>
        </el-form-item>
        <el-form-item label="学生学号" prop="stuNumber">
          <el-input v-model="editForm.stuNumber"></el-input>
        </el-form-item>
        <el-form-item label="内容" prop="stuContent">
          <el-input type="textarea" v-model="editForm.stuContent"></el-input>
        </el-form-item>
        <el-form-item label="开始时间">
          <el-col :span="11">
            <el-date-picker type="date" placeholder="选择日期" v-model="editForm.theTime" style="width: 100%;"></el-date-picker>
          </el-col>
        </el-form-item>
        <el-form-item label="结束时间">
          <el-col :span="11">
            <el-date-picker type="date" placeholder="选择日期" v-model="editForm.endTime" style="width: 100%;"></el-date-picker>
          </el-col>
        </el-form-item>
        <el-form-item label="是否注销">
          <el-radio-group v-model="editForm.stuOff">
            <el-radio class="radio" :label="1">是</el-radio>
            <el-radio class="radio" :label="0">否</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="原因 / 详情">
          <el-input type="textarea" v-model="editForm.stuReason"></el-input>
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
    import {getStuInfoList, removeStuInfo, batchRemoveStu, editStuInfo} from '../../api/api';

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
                    infoName: [
                        {required: true, message: '请输入姓名', trigger: 'blur'}
                    ],
                    stuNumber: [
                        {required: true, message: '请输入学号', trigger: 'blur'}
                    ],
                    stuContent: [
                        {required: true, message: '请输入班级编号', trigger: 'blur'}
                    ]
                },
                //编辑界面数据
                editForm: {
                    infoName: '',
                    stuNumber: '',
                    theTime: '',
                    endTime: '',
                    stuContent: '',
                    stuOff: '',
                    stuReason: ''
                }

            }
        },
        computed: {
            getStuInfoList: function () {
                var self = this, data = self.table.data, page = self.table.page, size = self.table.size, keyword = self.search.keyword;
                var v = data.filter(function (p) {
                    return (p.infoName && p.infoName.indexOf(keyword) !== -1)
                        || (p.stuName && p.stuName.indexOf(keyword) !== -1)
                        || (p.stuNumber && p.stuNumber.indexOf(keyword) !== -1)
                        || (p.theTime && p.theTime.indexOf(keyword) !== -1)
                        || (p.endTime && p.endTime.indexOf(keyword) !== -1)
                        || (p.stuContent && p.stuContent.indexOf(keyword) !== -1)
                        || (p.stuReason && p.stuReason.indexOf(keyword) !== -1)
                        || (p.stuOff && p.stuOff.indexOf(keyword) !== -1);
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
            getInfos() {
                let p = this.$route.query;
                if (p.stuNumber === undefined) {
                    return;
                }
                let para = {
                    stuNumber: p.stuNumber
                };
                this.listLoading = true;
                //获取数据接口
                getStuInfoList(para).then((response) => {
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
            stuInfoDel: function (index, row) {
                this.$confirm('确认删除该记录吗?', '提示', {
                    type: 'warning'
                }).then(() => {
                    this.listLoading = true;
                    //NProgress.start();
                    let para = {stuNumber: row.stuNumber};
                    removeStuInfo(para).then((res) => {
                        this.listLoading = false;
                        //NProgress.done();
                        console.log(res.data.code);
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

                        this.getInfos();
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
                        this.getInfos();
                    });
                }).catch(() => {

                });
            }
        },
        mounted() {
            this.getInfos();
        }
    }

</script>

<style scoped>

</style>