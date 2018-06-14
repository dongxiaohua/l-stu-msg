import Login from "./views/Login.vue";
import NotFound from "./views/404.vue";
import Home from "./views/Home.vue";
import Main from "./views/Main.vue";
import StuList from "./views/stuModule/StuList.vue";
import Message from "./views/stuModule/SetStuMes.vue";
import InfoList from "./views/stuModule/InfoList.vue";
import ClassInfoList from "./views/classModule/ClassInfoList.vue";
import ClassList from "./views/classModule/ClassList.vue";
import Page5 from "./views/classModule/Page5.vue";
import classMes from "./views/classModule/ClassInfo.vue";
import UserAdmin from "./views/adminModule/UserAdmin.vue";

let routes = [
    {
        path: '/login',
        component: Login,
        name: '',
        hidden: true
    },
    {
        path: '/404',
        component: NotFound,
        name: '',
        hidden: true
    },
    //{ path: '/main', component: Main },
    {
        path: '/',
        component: Home,
        name: '学生信息管理',
        iconCls: 'el-icon-message',//图标样式class
        children: [
            {path: '/main', component: Main, name: '主页', hidden: true},
            {path: '/stuList', component: StuList, name: '学生信息列表'},
            {path: '/form', component: Message, name: '学生后勤信息创建'},
            {path: '/info_list', component: InfoList, name: '学生后勤信息列表', hidden: true}
            // { path: '/user', component: user, name: '列表' },
        ]
    },
    {
        path: '/',
        component: Home,
        name: '班级信息管理',
        iconCls: 'fa fa-id-card-o',
        children: [
            {path: '/ClassList', component: ClassList, name: '班级列表'},
            {path: '/classMes', component: classMes, name: '班级信息创建'},
            {path: '/class_info_list', component: ClassInfoList, name: '学生后勤信息列表', hidden: true},
            {path: '/page5', component: Page5, name: '学院列表', hidden: true}
        ]
    },
    {
        path: '/',
        component: Home,
        name: '',
        iconCls: 'fa fa-address-card',
        leaf: true,//只有一个节点
        children: [
            {path: '/UserAdmin', component: UserAdmin, name: '管理员管理模块'}
        ]
    },
    // {
    //     path: '/',
    //     component: Home,
    //     name: 'Charts',
    //     iconCls: 'fa fa-bar-chart',
    //     children: [
    //         { path: '/echarts', component: echarts, name: 'echarts' }
    //     ]
    // },
    {
        path: '*',
        hidden: true,
        redirect: {path: '/404'}
    }
];

export default routes;