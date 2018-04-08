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
                    <el-button type="primary" @click="handleAdd">新增</el-button>
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
                    <el-button type="danger" size="small" @click="stuInfDel(scope.$index, scope.row)">删除</el-button>
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


    </section>
</template>

<script>
    import util from '../../common/js/util'
    //import NProgress from 'nprogress'
    import {getStuInfoList, removeStu, batchRemoveStu, editStu, addStu} from '../../api/api';

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
                sels: []//列表选中列
            }
        },
        computed: {
            getStuInfoList: function () {
                var self = this, data = self.table.data, page = self.table.page, size = self.table.size, keyword = self.search.keyword;
                var v = data.filter(function (p) {
                    return (p.infoName && p.infoName.indexOf(keyword) !== -1 )
                        || (p.stuName && p.stuName.indexOf(keyword) !== -1 )
                        || (p.stuNumber && p.stuNumber.indexOf(keyword) !== -1 )
                        || (p.theTime && p.theTime.indexOf(keyword) !== -1 )
                        || (p.endTime && p.endTime.indexOf(keyword) !== -1 )
                        || (p.stuContent && p.stuContent.indexOf(keyword) !== -1 )
                        || (p.stuReason && p.stuReason.indexOf(keyword) !== -1 )
                        || (p.stuOff && p.stuOff.indexOf(keyword) !== -1 );
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
            handleSizeChange (v){
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
            stuInfDel: function (index, row) {
                this.$confirm('确认删除该记录吗?', '提示', {
                    type: 'warning'
                }).then(() => {
                    this.listLoading = true;
                    //NProgress.start();
                    let para = {stuNumber: row.stuNumber};
                    removeStu(para).then((res) => {
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
                                this.$message({
                                    message: '提交成功',
                                    type: 'success'
                                });
                                this.$refs['editForm'].resetFields();
                                this.editFormVisible = false;
                                this.getInfos();
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
                                this.getInfos();
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