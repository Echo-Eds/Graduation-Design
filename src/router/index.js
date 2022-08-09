import {
  createRouter,
  createWebHistory
} from 'vue-router'

import Layout from '../layout/Layout.vue'
import SysLayout from '../layout/SysLayout.vue'
import MyInfoLayout from '../layout/MyInfoLayout.vue'
import ProfessorInfoLayout from '../layout/ProfessorInfoLayout.vue'
import ProfessorDetailsLayout from '../layout/ProfessorDetailsLayout.vue'
import SkillsTrainingLayout from '../layout/SkillsTrainingLayout.vue'
import MedicalResource from "../layout/MedicalResourceLayout.vue";
import MedicineLayout from '../layout/MedicineLayout.vue';
const routes = [
  //后端部分
  {
    path: '/Mag',
    name: 'Layout',
    component: Layout,
    //重定向
    redirect: '/Mag/userManage',
    //嵌套路由,一个数组，可以放很多子路由
    children: [{
        //用户
        path: '/Mag/userManage',
        name: 'UserManage',
        component: () => import("@/views/management/UserManage")
      },
      //专家
      {
        path: '/Mag/professorManage',
        name: 'professorManage',
        component: () => import("@/views/management/ProfessorManage")
      },
      //admin
      {
        path: '/Mag/adminInfo',
        name: 'adminInfo',
        component: () => import("@/views/management/AdminInfo")
      },
      //medicine
      {
        path: '/Mag/medicineManage',
        name: 'MedicineManage',
        component: () => import("@/views/management/MedicineManage")
      },
      //medicineResource
      {
        path: '/Mag/medicineResourceManage',
        name: 'MedicineResourceManage',
        component: () => import("@/views/management/MedicineResourceManage")
      },
      {
        path: '/Mag/cart',
        name: 'CartMag',
        component: () => import("@/views/management/cartMag")
      }
    ]
  },
  {
    path: '/userLogin',
    name: 'UserLogin',
    component: () => import( /* webpackChunkName: "about" */ '../views/login/UserLogin.vue')
  },
  {
    path: '/userRegister',
    name: 'UserRegister',
    component: () => import("@/views/login/UserRegister")
  },
  {
    path: '/adminLogin',
    name: 'AdminLogin',
    component: () => import("../views/login/adminlogin.vue")
  },
  {
    path: '/professorLogin',
    name: 'ProfessorLogin',
    component: () => import("../views/login/ProfessorLogin.vue")
  },
  {
    path: '/professorRegister',
    name: 'ProfessorRegister',
    component: () => import("../views/login/ProfessorResister.vue")
  },
  //前端部分:
  {
    path: '/',
    name: 'SysLayout',
    component: SysLayout,
    redirect: '/sys/index',
    children: [{
      path: '/sys/index',
      name: 'Index',
      component: () => import('@/views/index')
    }, ]
  },
  //医师信息：
  {
    path: '/professorDetails',
    name: 'ProfessorDetails',
    component: ProfessorDetailsLayout,
    redirect: '/professorDetails/all',
    children: [{
        path: '/professorDetails/all',
        name: 'allProfessor',
        component: () => import('../views/profdetails/ProfessorDetials.vue')
      },
      {
        path: '/professorDetails/0',
        name: 'professorDetailsTpye1',
        component: () => import('../views/profdetails/professorType1.vue')
      },
      {
        path: '/professorDetails/1',
        name: 'professorDetailsTpye2',
        component: () => import('../views/profdetails/professorType2.vue')
      },
      {
        path: '/professorDetails/dep0',
        name: 'professorDetailsDep1',
        component: () => import('../views/profdetails/professorDepartment1.vue')
      },
      {
        path: '/professorDetails/dep1',
        name: 'professorDetailsDep2',
        component: () => import('../views/profdetails/professorDepartment2.vue')
      },
      //详情页
      {
        path: '/professorDetails/details',
        name: 'Details',
        component: () => import('../views/profdetails/details.vue')
      },

    ]
  },
  {
    path: '/professorDetails/search',
    name: 'professorSearchRes',
    component: () => import('../views/profdetails/professorSearchRes.vue')
  },
  //技能培训
  {
    path: '/skills_training',
    name: 'SkillsTraining',
    redirect: '/skills_training/main',
    component: SkillsTrainingLayout,
    children: [{
        path: '/skills_training/main',
        name: 'skillsTraining_main',
        component: () => import('../views/skillsTraining/skillsTraining_main.vue')
      },
      {
        path: '/skills_training/0',
        name: 'trainingType1',
        component: () => import('../views/skillsTraining/trainingType1.vue')
      },
      {
        path: '/skills_training/1',
        name: 'trainingType2',
        component: () => import('../views/skillsTraining/trainingType2.vue')
      },
      {
        path: '/skills_training/details',
        name: 'skill_details',
        component: () => import('../views/skillsTraining/details.vue')
      }
    ]
  },
  //医药资源
  {
    path: '/medicalResource',
    name: 'MedicalResource',
    redirect: '/medicalResource/main',
    component: MedicalResource,
    children: [
      {
        path: '/medicalResource/main',
        name: 'MedicalResourceMain',
        component: () => import('../views/medicalResource/medicalResourceDetails.vue')
      },
      {
        path: '/medicalResource/0',
        component:() =>import('../views/medicalResource/resourceTpye1.vue')
      },
      {
        path: '/medicalResource/1',
        component: () => import('../views/medicalResource/resourceType2.vue')
      }
    ]
  },
  {
    path: '/medicalResource/searchRes',
    name: 'medicalResourceRes',
    component: () => import('../views/medicalResource/medicalSearchRes.vue')
  },
  {
    path: '/skills_training/searchRes',
    name: "SearchRes",
    component: () =>import('../views/skillsTraining/skillsTrainingRes.vue')
  },
  //药品信息
  {
    path: '/MedicineInfo',
    name: 'MedicineInfo',
    redirect: '/MedicineInfo/main',
    component: MedicineLayout,
    children: [
      {
        path: '/MedicineInfo/main',
        component: () =>import('../views/medicine/medicineDetials.vue')
      },
      {
        path: '/MedicineInfo/0',
        component: () =>import('../views/medicine/medicineType1.vue')
      },
      {
        path: '/MedicineInfo/1',
        component: () =>import('../views/medicine/medicineType2.vue')
      },
      {
        path: '/MedicineInfo/details',
        component: () =>import('../views/medicine/detials.vue')
      },
      
    ]
  },
  {
    path: '/MedicineInfo/searchRes',
    name: 'MedicineSearchRes',
    component: () => import('../views/medicine/medicineSearchRes.vue')
  },
  //询问专家
  {
    path: '/consult',
    name: 'consult',
    component: () => import('../views/consult/consultList.vue')
  },
  {
    path: '/consult/content',
    name: 'Content',
    component: () => import('../views/consult/ContentPage.vue')
  },
  //医药资讯
  {
    path: '/medicalInformation',
    name: 'MedicalInformation',
    component: () => import('../views/medicalInformation/medicalInformationList.vue')
  },
  {
    path: '/medicalInformation/detial',
    name: 'medicalInformationDetial',
    component: () =>import('../views/medicalInformation/medicanInformationDetial.vue')
  },
  //个人中心
  {
    path: '/myInfo',
    name: 'MyInfoLayout',
    component: MyInfoLayout,
    redirect: '/myinfo/myInfoCenter',
    children: [{
        path: '/myInfo/myInfoCenter',
        name: 'MyInfoCenter',
        component: () => import('@/views/myinfo/myInfoCenter')
      },
      {
        path: '/myInfo/myInfoLssue',
        name: 'MyInfoLssue',
        component: () => import('@/views/myinfo/myInfoLssue')
      },
      {
        path: '/myInfo/myOrder',
        name: 'MyOrder',
        component: () => import('@/views/myinfo/myOrder')
      },
      {
        path: '/myInfo/myLocation',
        name: 'MyLocation',
        component: () => import('@/views/myinfo/myLocation')
      },
      {
        path: '/myInfo/myCollect',
        name: 'MyCollect',
        component: () => import('@/views/myinfo/myCollect')
      },
    ]
  },
  {
    path: '/profInfo',
    name: 'ProfessorInfoCenter',
    component: ProfessorInfoLayout,
    redirect: '/profInfo/profInfoCenter',
    children: [{
        path: '/profInfo/profInfoCenter',
        name: 'ProfessorInfoCenter',
        component: () => import('../views/profinfo/professorInfoCenter.vue')
      },
      {
        path: '/profInfo/profInfoLssue',
        name: 'ProfessorInfoLssue',
        component: () => import('../views/profinfo/profInfoLssue.vue')
      },
      {
        path: '/profInfo/releaseInfo',
        component: () => import('../views/profinfo/releaseInfo.vue')
      }
    ]
  },
  //购物车
  {
    path: '/cart',
    name: 'Cart',
    component: () => import('../views/shopping/cart.vue')
  },
  {
    path: '/order',
    name: 'order',
    component: () => import('../views/shopping/order.vue')
  },
  {
    path: '/orderPayed',
    name: 'orderPayed',
    component: () => import('../views/shopping/orderPayed.vue')
  }


]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router