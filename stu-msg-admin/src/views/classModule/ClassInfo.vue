<template>
    <el-form ref="form" :model="form" label-width="80px" :rules="addClassFormRules" @submit.prevent="onSubmit" style="margin:20px;width:60%;min-width:600px;">
        <el-form-item label="班级编号" prop="classNum">
            <el-input v-model="form.classNum"></el-input>
        </el-form-item>
        <el-form-item label="时间" prop="theTime">
            <el-col :span="11">
                <el-date-picker type="date" placeholder="选择日期" v-model="form.theTime" style="width: 100%;"></el-date-picker>
            </el-col>
        </el-form-item>
        <el-form-item label="班级称号" prop="classNum">
            <el-input v-model="form.classTitle"></el-input>
        </el-form-item>
        <el-form-item label="详情" prop="classContent">
            <el-input type="textarea" v-model="form.classContent"></el-input>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="onSubmit">立即创建</el-button>
            <el-button @click.native.prevent>取消</el-button>
        </el-form-item>
    </el-form>
</template>

<script>

    import {addClassInfo} from '../../api/api';
    export default {
        data() {
            return {
//			    创建班级数据
                form: {
                    classNum: '',
                    theTime: '',
                    classTitle:'',
                    classContent: ''
                },
//				必填设置
                addClassFormRules: {
                    classNum: [
                        {required: true, message: '请输入班级名称', trigger: 'blur'}
                    ],
                    theTime: [
                        {required: true, message: '请输入所属学部', trigger: 'blur'}
                    ],
                    classContent: [
                        {required: true, message: '请输入年级', trigger: 'blur'}
                    ]
                }
            }
        },
        methods: {
            onSubmit() {
                this.$confirm('确认提交吗？', '提示', {}).then(() => {
                    let para = Object.assign({}, this.form);
                    console.log(para);
                    addClassInfo(para).then((res) => {
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