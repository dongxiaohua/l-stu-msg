<template>
    <el-form ref="form" :model="form" label-width="80px" :rules="addFormRules" @submit.prevent="onSubmit" style="margin:20px;width:60%;min-width:600px;">
        <el-form-item label="信息名称" prop="infoName">
            <el-input v-model="form.infoName" placeholder="如：荣誉，请假，记过，挂科等"></el-input>
        </el-form-item>
        <el-form-item label="学生学号" prop="stuNumber">
            <el-input v-model="form.stuNumber"></el-input>
        </el-form-item>
        <el-form-item label="内容" prop="stuContent">
            <el-input type="textarea" v-model="form.stuContent"></el-input>
        </el-form-item>
        <el-form-item label="开始时间">
            <el-col :span="11">
                <el-date-picker type="date" placeholder="选择日期" v-model="form.theTime" style="width: 100%;"></el-date-picker>
            </el-col>
        </el-form-item>
        <el-form-item label="结束时间">
            <el-col :span="11">
                <el-date-picker type="date" placeholder="选择日期" v-model="form.endTime" style="width: 100%;"></el-date-picker>
            </el-col>
        </el-form-item>
        <el-form-item label="是否注销">
            <el-radio-group v-model="form.stuOff">
                <el-radio class="radio" :label="1">是</el-radio>
                <el-radio class="radio" :label="0">否</el-radio>
            </el-radio-group>
        </el-form-item>
        <el-form-item label="原因 / 详情">
            <el-input type="textarea" v-model="form.stuReason"></el-input>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="onSubmit">立即创建</el-button>
            <el-button @click.native.prevent>取消</el-button>
        </el-form-item>
    </el-form>
</template>

<script>

    import {addStuInfo} from '../../api/api';
    export default {
        data() {
            return {
//			    创建info数据
                form: {
                    infoName: '',
                    stuNumber: '',
                    theTime: '',
                    endTime: '',
                    stuContent: '',
                    stuOff: '',
                    stuReason: ''
                },
//				必填设置
                addFormRules: {
                    infoName: [
                        {required: true, message: '请输入名称', trigger: 'blur'}
                    ], stuNumber: [
                        {required: true, message: '请输入学号', trigger: 'blur'}
                    ], stuContent: [
                        {required: true, message: '请输入班级编号', trigger: 'blur'}
                    ]
                }
            }
        },
        methods: {
            onSubmit() {
                this.$confirm('确认提交吗？', '提示', {}).then(() => {
                    let para = Object.assign({}, this.form);
                    console.log(para);
                    addStuInfo(para).then((res) => {
                        console.log(res);
                        this.$message({
                            message: '提交成功',
                            type: 'success'
                        });
                        this.$refs['form'].resetFields();
                    });
                });
            }
        }
    }

</script>