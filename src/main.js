import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import VueCookies from "vue-cookies"

import ElementPlus from 'element-plus'


import 'element-plus/dist/index.css'

import zhCn from 'element-plus/es/locale/lang/zh-cn'
//自定义全局CSS
import '@/assets/css/global.css'

//全局注册图标组件
import * as ELIcons from '@element-plus/icons-vue'


const app = createApp(App)
for (let iconName in ELIcons) {
	app.component(iconName, ELIcons[iconName])
}


app.use(VueCookies).use(store).use(router).use(ElementPlus, {
    locale: zhCn
  }).mount('#app')
