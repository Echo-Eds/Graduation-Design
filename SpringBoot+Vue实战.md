

# SpringBoot+Vue实战



## 本文使用前端为 elementUI+

## git 地址：https://gitee.com/lizeyuan_xiaoli/demo-springboot-vue.git

项目创建：新建文件夹 打开cmd：

`vue create springbootvue`

![image-20220209162619367](https://s2.loli.net/2022/02/11/JVGSWA6E5biwlFZ.png)

选择第三项，![image-20220209162708377](https://s2.loli.net/2022/02/11/nX2BHgcRYkFKjtJ.png)

选择vue3.x, package.josn  一路yes 

后端在idea中新建一个子项目`springboot`

项目结构：

![image-20220209162230381](https://s2.loli.net/2022/02/11/q8EFKzXZNBlg7LW.png)

## 1.后台搭建部分（前端页面）

### 1.1 ElememtUI+引入：

#### 安装

​	控制台 ：`npm install element-plus --save`

#### 在VUE3中完整引入ElementUI+

​	如果你对打包后的文件大小不是很在乎，那么使用完整导入会更方便。

```js
// main.ts
import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from './App.vue'

const app = createApp(App)

app.use(ElementPlus)
app.mount('#app')
```

#### 国际化

Element Plus 提供了全局配置国际化的配置。同样的在main.js导入

```js
import ElementPlus from 'element-plus'
import zhCn from 'element-plus/es/locale/lang/zh-cn'

app.use(ElementPlus, {
  locale: zhCn,
})
```

- 以下是我的main.js完整code:

```javascript
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'


import ElementPlus from 'element-plus'

import 'element-plus/dist/index.css'

import zhCn from 'element-plus/es/locale/lang/zh-cn'
//自定义全局CSS
import '@/assets/css/global.css'
createApp(App).use(store).use(router).use(ElementPlus, {
    locale: zhCn, size: 'small'
  }).mount('#app')

```

#### 全局图标引入：

element支持全局图标，引入方法为：

在`main.js`中导入一下代码：

```js
import * as ELIcons from '@element-plus/icons-vue'


const app = createApp(App)
for (let iconName in ELIcons) {
	app.component(iconName, ELIcons[iconName])
}
```

此时element的图标名字就能直接使用：在输入输入框中写入属性：`perfix-icon = "icon-name"`例如：

```vue
<el-input
            v-model="form.password"
            placeholder="请输入密码"
            prefix-icon="Key"
            show-password
          ></el-input>
```

出现的效果：

![image-20220222160726877](https://s2.loli.net/2022/02/22/3UOcsApB1kuDPQ6.png)





### 1.2 前端代码：

​	后台管理页面分为三部分：Header,Aside,Home. 其可以在组件中定义并引入例如组件 Header.vue: 可以在App.vue 中引入：

```javascript
<script>
  import Header from "@/components/Header";
  /*import Aside from "@/components/Aside"; */
  export default {
    name: "Layout",
    components: {
        Header,
        //Aside,
    }
  }
</script>
```



#### code.components:

+ `Header.vue`

  ```html
  <template>
      <div class="header">
          <div class="header-a">后台管理</div>
          <div style="flex: 1"></div>
          <div class="header-b">
              <el-dropdown>
                  <span class="el-dropdown-link">
                    张三
                    <el-icon class="el-icon--right">
                      <arrow-down />
                    </el-icon>
                  </span>
                  <template #dropdown>
                      <el-dropdown-menu>
                          <el-dropdown-item>个人信息</el-dropdown-item>
                          <el-dropdown-item>退出系统</el-dropdown-item>
                      </el-dropdown-menu>
                  </template>
              </el-dropdown>
          </div>
      </div>
  </template>
  
  <style scoped>
  
      .header {
          height: 50px;
          line-height: 50px;
          border: 1px solid #cccccc;
          display: flex;
      }
  
      .header-a {
          width: 200px;
          padding-left: 30px;
          font-weight: bolder;
      }
  
      .header-b {
          width: 100px;
      }
  
      .el-dropdown-link {
          cursor: pointer;
          color: var(--el-color-primary);
          display: flex;
          align-items: center;
          line-height: 50px;
      }
  </style>
  <script>
      import {ArrowDown}  from '@element-plus/icons-vue'
      export default {
          name: "Header",
          components: {
              ArrowDown
          }
      }
  
  </script>
  ```

  

+ `Aside.vue`

  ```html
  <template>
    <div>
      
          <el-menu
            id = "el-menu"
            default-active="2"
            class="el-menu-vertical-demo"
          >
            <el-sub-menu index="1">
              <template #title>
                <el-icon><location /></el-icon>
                <span>Navigator One</span>
              </template>
              <el-menu-item-group title="Group One">
                <el-menu-item index="1-1">item one</el-menu-item>
                <el-menu-item index="1-2">item one</el-menu-item>
              </el-menu-item-group>
              <el-menu-item-group title="Group Two">
                <el-menu-item index="1-3">item three</el-menu-item>
              </el-menu-item-group>
              <el-sub-menu index="1-4">
                <template #title>item four</template>
                <el-menu-item index="1-4-1">item one</el-menu-item>
              </el-sub-menu>
            </el-sub-menu>
            <el-menu-item index="2">
              <el-icon><icon-menu /></el-icon>
              <span>Navigator Two</span>
            </el-menu-item>
            <el-menu-item index="3" disabled>
              <el-icon><document /></el-icon>
              <span>Navigator Three</span>
            </el-menu-item>
            <el-menu-item index="4">
              <el-icon><setting /></el-icon>
              <span>Navigator Four</span>
            </el-menu-item>
          </el-menu>
    </div>
  </template>
  
  <style scoped>
  
  #el-menu {
    width: 200px; 
    height: calc(100vh - 50px);
  }
  </style>
  <script>
  import {
    Location,
    Document,
    Menu as IconMenu,
    Setting,
  } from '@element-plus/icons-vue'
  export default {
      name: "Aside",
      components: {
          Location,
          Document,
          Setting,
          IconMenu
      }
  }
  </script>
  ```

  

+ `Table.vue(列表复用组件)`

  ```html
  <template>
    <div>
      <el-table :data="tableData" stripe>
        <el-table-column prop="id" label="ID" />
        <el-table-column prop="username" label="姓名" />
        <el-table-column prop="password" label="密码" />
        <el-table-column prop="nickName" label="昵称" />
        <el-table-column prop="age" label="年龄" />
        <el-table-column prop="sex" label="性别" />
        <el-table-column prop="address" label="地址" />
  
        <el-table-column fixed="right" label="操作" width="120">
          <el-popconfirm title="确定删除？">
            <template #reference>
              <el-button
                type="text"
                size="small"
                @click.prevent="deleteRow(scope.$index)"
              >
                Remove
              </el-button>
            </template>
          </el-popconfirm>
        </el-table-column>
      </el-table>
  
      <div class="demo-pagination-block" style="margin:10px 0;">
        <el-pagination
          v-model:currentPage="currentPage4"
          :page-sizes="[5, 10, 20]"
          :page-size="5"
          layout="total, sizes, prev, pager, next, jumper"
          :total="23"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
        >
        </el-pagination>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    name: "Table",
    components: {},
    data() {
      return {
  
        currentPage4: 1,
  
        total: 20,
  
        tableData: [
  
        ],
      };
    },
    methods: {
      deleteRow() {
  
      },
      handleSizeChange() {
  
      },
      handleCurrentChange() {
        
      }
    },
  };
  </script>
  ```

#### code.Home:

home.vue

```html
<template>
    <div class="home">
        <div>
            <el-button type="primary" @click="add">新增</el-button>
            <el-button type="primary">导出</el-button>

        </div>
        <div>
            <el-input
                    style="margin: 10px 20px 10px 0; width: 20%"
                    v-model="search"
                    placeholder="Please input"
            />
            <el-button type="primary">查询</el-button>
        </div>

        <!-- 表格 -->
        <div>
            <Table></Table>
        </div>

        <el-dialog
                v-model="dialogVisible"
                title="新增用户"
                width="30%"
                :before-close="hadleClose"

        >
            <el-form ref="formRef" :model="form" label-width="120px">
                <el-form-item label="ID" style="width: 80%">
                    <el-input v-model="form.id"></el-input>
                </el-form-item>
                <el-form-item label="用户名" style="width: 80%">
                    <el-input v-model="form.username"></el-input>
                </el-form-item>
                <el-form-item label="密码" style="width: 80%">
                    <el-input type="password" v-model="form.password"></el-input>
                </el-form-item>
                <el-form-item label="昵称" style="width: 80%">
                    <el-input v-model="form.nickName"></el-input>
                </el-form-item>
                <el-form-item label="性别" style="width: 80%">
                    <el-radio-group v-model="form.sex" size="small">
                        <el-radio-button label="男"></el-radio-button>
                        <el-radio-button label="女"></el-radio-button>
                        <el-radio-button label="不便透露"></el-radio-button>
                    </el-radio-group>

                </el-form-item>
                <el-form-item label="年龄" style="width: 80%">
                    <el-input v-model="form.age"></el-input>
                </el-form-item>
                <el-form-item label="地址" style="width: 80%">
                    <el-input v-model="form.address"></el-input>
                </el-form-item>
            </el-form>
            <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">Cancel</el-button>
        <el-button type="primary" @click= "save"
        >Confirm</el-button
        >
      </span>
            </template>
        </el-dialog>
    </div>
</template>
<script>
    import Table from "@/components/Table.vue";

    export default {


        name: "Home",
        components: {
            Table,
        },
        data() {
            return {
                form: {},
                search: '',
                dialogVisible: false,

            }
        },
        methods: {
            add() {
                this.dialogVisible = true;
                //清空表单域（表单所有对象）
                this.from = {}
            },
            save() {

            }
        }
    };
</script>

<style scoped>
    .home {
        padding: 10px;
        /*float: right;*/
        width: calc(100% - 200px);
    }

    .button {
        margin: 10px;
    }
</style>
```



#### APP.vue:

```html
<template>
  <div>
     <Header></Header>
     <div style="display: flex">
        <Aside></Aside> 
        <router-view class="router-v"> 
        </router-view>
     </div>
  </div>
</template>

<style>
.router-v {
  flex: 1;
  position: relative;

}
</style>

<script>
  import Header from "@/components/Header";
  import Aside from "@/components/Aside";
  export default {
    name: "Layout",
    components: {
        Header,
        Aside,
    }
  }
</script>

```

各种组件在app.vue中导入



#### 路由：

路由管理在router/index.js文件下：

```js
const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  }
]
```

path是页面的路径（例如：localhost:8080/login)，name是页面名称



#### 静态资源：

​	静态资源导入一般在../assets/目录下，通常是静态图片、全局css之类

​	![image-20220209172138066](https://s2.loli.net/2022/02/11/SVAIJPHl7qgCnoi.png)



### 1.2 前端静态页面：

#### 1.2.1 运行：

控制台输入 `npm run serve ` (在当前项目路径下) 启动项目 此处参见 package.json:

```js
 "scripts": {
    "serve": "vue-cli-service serve --open",
    "build": "vue-cli-service build"
  },
```

![image-20220209172953525](https://s2.loli.net/2022/02/11/jmSfWbRO9eYN4rP.png)此图即为运行成功！

#### 1.2.2 前端预览界面

![image-20220209173025039](https://s2.loli.net/2022/02/11/CBNy3lEgxhk8PWv.png)

![image-20220209173141579](https://s2.loli.net/2022/02/11/YTWDciZSXrQfNb7.png)





## 2.后端基础搭建

### 2.1.1：项目概览

 在当前项目中新建一个子项目：![image-20220211094608969](https://s2.loli.net/2022/02/11/obZ5fcldgwiv2Iz.png)

选择SprinMaven项目：![image-20220211094704983](https://s2.loli.net/2022/02/11/oYTEdt1bN6A42uk.png)

最后两个项目分离，合并

![image-20220211094737773](https://s2.loli.net/2022/02/11/QyIMa2JBWmCLYPt.png)

其中config中是各种配置文件，例如mybatisPlus 返回结果集的配置

### 2.1.2代码的执行：

***html发出请求——通过Ajax(jq,vue)将数据传递到java——Controller控制器（Servlet、拦截数据、跳转到其他组件）——service业务逻辑层（逻辑判断和数据的简单处理）——Dao数据层（Java和database的数据交互CRUD）——调用对应的实体类pojo（数据库表的对象封装）***



### 2.2 code部分

#### 2.2.1 config:

- application.yml:

  ```yaml
  server:
    port: 9090
  spring:
    datasource:
      driver-class-name: com.mysql.cj.jdbc.Driver
      url: jdbc:mysql://localhost:3306/springbootvue?useUnicode=true&characterEncoding=utf8&useSSL=true
      username: root
      password: 123456
  
  #log
  logging:
    file:
      name: a.log
  ```

- mybatisPlusConfig:

  ```java
  @Configuration
  @MapperScan("com.springboot.demo.mapper")
  public class MybaitsPlusConfig {
      @Bean
      public MybatisPlusInterceptor mybatisPlusInterceptor() {
          MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
          interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
          return interceptor;
      }
  }
  ```

- result.java:

  ```java
  package com.springboot.demo.Config;
  
  /**
   * @author LiZeyuan
   * @date 2022/1/26 12:37
   * 返回前台数据包装类
   */
  public class Result<T> {
      private String code;
      private String msg;
      private T data;
  
  
      public Result() {
      }
  
      public Result(T data) {
          this.data = data;
      }
  
      public String getCode() {
          return code;
      }
  
      public void setCode(String code) {
          this.code = code;
      }
  
      public String getMsg() {
          return msg;
      }
  
      public void setMsg(String msg) {
          this.msg = msg;
      }
  
      public T getData() {
          return data;
      }
  
      public void setData(T data) {
          this.data = data;
      }
  
      public static Result success() {
          Result result = new Result<>();
          result.setCode("0");
          result.setMsg("success");
          return result;
      }
  
      public static <T> Result<T> success(T data) {
          Result<T> result = new Result<T>(data);
          result.setCode("0");
          result.setMsg("success");
          return result;
      }
  
      public static Result error(String code,String msg) {
          Result result = new Result<>();
          result.setCode(code);
          result.setMsg(msg);
          return result;
      }
  }
  ```









## 3 通过ajax进行交互

### 3.1 引入axios

在前端src中新建utils工具类，新建`request.js`文件

```js
import axios from 'axios'

const request = axios.create({
    baseURL: '/api',  // 注意！！ 这里是全局统一加上了 '/api' 前缀，也就是说所有接口都会加上'/api'前缀在，页面里面写接口的时候就不要加 '/api'了，否则会出现2个'/api'，类似 '/api/api/user'这样的报错，切记！！！
    timeout: 5000,
    // s
})

// request 拦截器
// 可以自请求发送前对请求做一些处理
// 比如统一加token，对请求参数统一加密
request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=utf-8';

    // config.headers['token'] = user.token;  // 设置请求头
    return config
}, error => {
    return Promise.reject(error)
});

// response 拦截器
// 可以在接口响应后统一处理结果
request.interceptors.response.use(
    response => {
        let res = response.data;
        // 如果是返回的文件
        if (response.config.responseType === 'blob') {
            return res
        }
        // 兼容服务端返回的字符串数据
        if (typeof res === 'string') {
            res = res ? JSON.parse(res) : res
        }
        return res;
    },
    error => {
        console.log('err' + error) // for debug
        return Promise.reject(error)
    }
)
export default request
```



控制台命令 ` npm i axios -S`

![image-20220211144527340](https://s2.loli.net/2022/02/11/tBzJFqyPKRb6vTL.png)

在home.vue中的新增按钮中，添加一个save方法：

```html
<el-button type="primary" @click="save"
        >确定</el-button
```



```javascript
save() {
                request.post("/user",this.from).then(res => {
                    console.log(res)
                })
            }
```

到conntroller中打断点进行测试

![image-20220211152048556](https://s2.loli.net/2022/02/11/r1tq9wuGNWcCLOh.png)

发现无法post ,因为后端yml为9090端口

在save方法中加上`http://localhost:9090/`出现networkerror

![image-20220211155711765](https://s2.loli.net/2022/02/11/KRFtkgIvs9Yc8be.png)

此时为跨域问题

### 3.2 vue跨域

#### 3.2.1Spring boot解决跨域问题(待研究)

在controller中添加注解

`@CrossOrigin(originPatterns = "*", methods = {RequestMethod.GET, RequestMethod.POST})`

参见https://www.cnblogs.com/antLaddie/p/14751540.html#_label1_2

#### 3.2.2 vue前端解决跨域

***在vue子项目跟目录中新建vue.config.js文件***

```js
// 跨域配置
module.exports = {
    devServer: {                //记住，别写错了devServer//设置本地前端默认端口  选填
        port: 9876,
        proxy: {                 //设置代理，必须填
            '/api': {              //设置拦截器  拦截器格式   斜杠+拦截器名字，名字可以自己定
                                    //把前端里面的值替换为/api  //for example: before: http://localhost9090/user after: /api/user
                target: 'http://localhost:9090',     //代理的目标地址
                changeOrigin: true,              //是否设置同源，输入是的
                pathRewrite: {                   //路径重写
                    '^/api': ''                     //选择忽略拦截器里面的内容，将/api改为''
                }
            }
        }
    }
}
```

***此时home.vue里面的save方法就会变为，跨域成功***

```js
save() {
    request.post("/user",this.from).then(res => {
        console.log(res)
    })
```



## 4 简单的CURD(无service层)

### 4.1 add

***重启vue工程（此时配置文件发生修改，必须重新 run serve）在controller新增设定默认密码，打断点 debug***

```java
//新增 
@PostMapping
    public Result<?> save(@RequestBody User user) {
        //在后台中给用户设定默认密码
        if (user.getPassword() == null) {
            user.setPassword("123456");
        }
        userMapper.insert(user);
        return Result.success();
    }
```

![image-20220214131908752](https://s2.loli.net/2022/02/14/Rn5TxGBfmhsrOQL.png)

![image-20220214132143240](https://s2.loli.net/2022/02/14/K5bSIaVHUL7k4oD.png)

***此时数据已经能在后端看到，将数据通过 数据库就能看到***

![image-20220214132224719](https://s2.loli.net/2022/02/14/qibvLH2BPEGRNDI.png)

![image-20220214132312951](https://s2.loli.net/2022/02/14/xqMtQf1BT6LV5PX.png)

**此时数据新增就做好了**

### 4.2 分页查询

在controller中新建findpage方法

```java
@GetMapping
    //分页查询接受参数
//    当前页，总页面数，模糊查询
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "lizeyuan") String search) {

        Page<User> findPage = userMapper.selectPage(new Page<>(pageNum, pageSize),
                Wrappers.<User>lambdaQuery().like(User::getNickName, search));
        return Result.success(findPage);
    }
```

浏览器输入 `http://localhost:9090/user`测试接口，返回数据：

![image-20220214133433510](https://s2.loli.net/2022/02/14/LJRPbaTM1mkyOcV.png)

说明接口正常（此时接口中`@RequestParam(defaultValue = "1")` 是有默认值的，如果不写默认值，则在地址栏中`/user?pageNum="1"&pageSize="1"&search="li"` 进行测试 ）

#### hutool插件使用

***hutool插件 ：Hutool是一个小而全的Java工具类库，通过静态方法封装，降低相关API的学习成本，提高工作效率，使Java拥有函数式语言般的优雅，maven引入：***

```xml
<dependency>
    <groupId>cn.hutool</groupId>
    <artifactId>hutool-all</artifactId>
    <version>5.7.21</version>
</dependency>
```

```java
//查询
@GetMapping
    //分页查询接受参数
//    当前页，总页面数，模糊查询
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "lizeyuan") String search) {

        //hutool插件判断serach是否为空,nike_name为null时查询条件
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery();
        if (StrUtil.isNotBlank(search)){
            wrapper.like(User::getNickName,search);
        }
        Page<User> findPage = userMapper.selectPage(new Page<>(pageNum, pageSize),wrapper);
        return Result.success(findPage);
    }
}
```

####  4.2.1将查询数据渲染到前台

在home.vue中 表格绑定值 `<el-table :data="tableData" stripe>`，在js中进行渲染

在`home.vue`中新建函数 load(),create()

```js
data() {
    return {
        search: '',
        currentPage4: 1,
        pageSize:10,
        tableData: [],
    }
}

//和下面的methods同级
created() {
            this.load();
        },
methods： {
    //加载完成后后端接口获取到的数据在前端渲染
    load() {
      request
        .get("/user", {
            //get将后端接口传递的参数返回到前端
            //params在用get请求时可以带参数传到后台，例如输入框查询
          params: {
            pageNum: this.currentPage4,
            pageSize: this.pageSize,
            search: this.search,
          },
        })
        .then((res) => {
          console.log(res);
          this.tableData = res.data.records;
          this.total = res.data.total;
        });
}
    
//此处用的是get方法 所以值不能转递，要加上params: { } 才能传值
```
####  4.2.2模糊查询

`clearable = true`清除输入内容

在查询按钮新建`@click="load"`方法


```vue
<div>
      <el-input
        style="margin: 10px 20px 10px 0; width: 20%"
        v-model="search"
        placeholder="请输入关键字"
        clearable
      />
      <el-button type="primary" @click="load">查询</el-button>
    </div>
```



>输出结果 1：

![image-20220214155229915](https://s2.loli.net/2022/02/14/FNeGcmgi92yRvnl.png)

> 输出结果2（查询）：

![image-20220214203053074](https://s2.loli.net/2022/02/14/X5kGCW8MhsHq79I.png)



### 4.3编辑数据（update）删除数据（delete）

> 在`home.vue`的表格模块中 有两个函数 `@click="handleEdit(scope.$index,scope.row)` `@click="handleDelete(scope.$index, scope.row)"`两个函数分别对应修改删除

```vue
<!-- 操作 -->
        <el-table-column label="操作">
          <template #default="scope">
            <el-button size="small" @click="handleEdit(scope.$index, scope.row)"
              >编辑</el-button
            >
            <el-button
              size="small"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)"
              >删除</el-button
            >
          </template>
        </el-table-column>
```



#### 4.3.1 update

点击编辑按钮，出现表单，并且表单数据为对应的行数据（把数据库值赋值给表单），此处用到了深拷贝：`JSON.parse(JSON.stringify(row))`：将此行元素深拷贝为一个单独的对象

注意：此时的函数必须要加index参数，否则找不到对应列

`handleEdit(index, rowrow)`:

```js
handleEdit(index, row) {
    //深拷贝
      this.form = JSON.parse(JSON.stringify(row));
      this.dialogVisible = true;
    },
```

点击按钮测试：![image-20220215152856198](https://s2.loli.net/2022/02/15/9r8pv1fKGjHaX5J.png)

此时代表读取表格数据成功

修改函数`save()`

 **注意此时修改为request.put方法**

```js
save() {
      if (this.form.id) {
        //  如果有表格中存在id 那就执行修改
        request.put("/user", this.form).then((res) => {
          console.log(res);
          console.log(this.form);
          //修改成功提示信息
          if (res.code == "0") {
            this.$message({
              type: "success",
              message: "修改成功",
            });
          } else {
            this.$message({
              type: "error",
              message: res.msg,
            });
          }
        });
      } else {
        //不存在id执行新增
        request.post("/user", this.form).then((res) => {
          console.log(res);
          console.log(this.form);
          //新增成功提示信息
          if (res.code == "0") {
            this.$message({
              type: "success",
              message: "新增成功",
            });
          } else {
            this.$message({
              type: "error",
              message: res.msg,
            });
          }
        });
      }
      //重新加载表格并关闭表单
      this.load();
      this.dialogVisible = false;
    },
```

controller：

```java
 //update
    @PutMapping
    public Result<?> update(@RequestBody User user) {
        userMapper.updateById(user);
        return Result.success();
    }
```

运行图：

![11 00_00_00-00_00_30](https://s2.loli.net/2022/02/15/c5Yevhlm4bPd1s8.gif)

#### 4.3.2 delete

> 在删除模块新增二次确认框

`@confirm ="handleDelete(scope.$index, scope.row.id)"`继承了来自button的`@click`方法

想要删除，就要取到每一行的id值，然后返回后台的api进行删除数据

在js的`handleDelete`方法中控制台打印

```vue
<el-popconfirm title="确定删除？" @confirm ="handleDelete(scope.$index, scope.row.id)">
              <template #reference>
                <el-button
                  size="small"
                  type="danger"
                  >删除</el-button
                ></template
              >
            </el-popconfirm>

handleDelete(index,id) {
      console.log(id)
    },
```

运行图：

![image-20220215181119921](https://s2.loli.net/2022/02/15/buJrRTV1M3vWhlQ.png)

说明点击二次确认按钮获取到了ID

> controller层新增delete方法：

```java
//delete api
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable long id) {
        userMapper.deleteById(id);
        return Result.success();
    }
```

`@DeleteMapping("/{id}")`需使用"/{id}"来引入ID 在delete方法中需要加入`@PathVariable`注解

如果引入了两个参数 如：`@DeleteMapping("/{id},/{name}")`则下面的参数也应该是两个：`delete(@PathVariable long id,@PathVariable String name)`

>handleDelete(index,id)传ID到后台

```js
handleDelete(index, id) {
      console.log(id);
      //使用delete方法 把id拼接到user之后，去后端调用delete接口
      request.delete("/user/" + id).then((res) => {
        console.log(res);
        if (res.code == 0) {
          this.$message({
            type: "success",
            message: "delete successfully",
          });
        } else {
          this.$message({
            type: "error",
            message: res.msg,
          });
        }
      });
      this.load();
    },
```

删除效果：

![29 00_00_00-00_00_30](E:\FFOutput\29 00_00_00-00_00_30.gif)



### 4.4 表格的页码跳转和最大行数设置

在分页语句中存在

```vue
<el-pagination
          v-model:currentPage="currentPage4"
          :page-sizes="[5, 10, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
        >
        </el-pagination>
```

其中的两个方法分别对应  *每页条数改变时触发*，*点击页码时触发*

```js
//数据分页
    handleSizeChange(pageSize) {
      //  每页条数改变时触发

      this.pageSize = pageSize;
      this.load();
    },
    handleCurrentChange(pageNum) {
      //点击页码时触发

      this.pageNum = pageNum;
      this.load();
    },
```

两个函数中的变量是接受数据之后计算而得的变量 `pageSize,pageNum`将他们传给data()中固定的数值，就能实现改变![image-20220215171057856](https://s2.loli.net/2022/02/15/P4uUBdnw6RclCqs.png)

运行结果：

![1 00_00_00-00_00_30](https://s2.loli.net/2022/02/15/GbhULVxQygIKEo7.gif)



## 5.整体项目搭建（页面扩展，登陆注册...）

### 5.1 路由的修改（便于后期扩展页面）

**新建一个`Login.vue` 配置路由 发现访问`localhost:9876/login`时带着其他的框架部分，因为我们配置文件时，`import { createApp } from 'vue' import App from './App.vue'`，所有的页面都经过`app.vue`，因此要把`app.vue`的东西转到一个新界面(下文的`Layout.vue`)，把其作为根节点，通过二次路由访问其他界面。**

- 在src路径下新建Layout文件夹，新建`Layout.vue`

```vue
<template>
<div>
     <Header></Header>
     <div style="display: flex">
        <Aside></Aside> 
        <router-view class="router-v"> 
        </router-view>
     </div>
  </div>
</template>

<script>
import Header from "@/components/Header";
  import Aside from "@/components/Aside";
export default {
  name:'Layout',
  components: {
        Header,
        Aside,
    },
  data(){
   return {

   }
  }
}
</script>

<style scoped>
/* .router-v {
  flex: 1;
  position: relative;

} */
</style>
```

- 此时的app.vue

```vue
<template>
<div>
  <router-view>
  </router-view>
</div> 
</template>
<script>
  
  export default {
    name: "APP",
    
  }
</script>

```

**修改`localhost:9876/`节点的路由，并把用户管理的子路由嵌套到'/'路由中**

```js
import Layout from '../layout/Layout.vue'
const routes = [

  {
    path: '/',
    name: 'Layout',
    component: Layout,
    //嵌套路由,一个数组，可以放很多子路由
    children: [{
      path: 'home',
      name: 'Home',
      component: () => import("@/views/Home")
    }]
  },
]
```

**此时表明'/'路由已经为`Layout.vue`了，他承载了我们的基础框架，app启动时，通过`app.vue`里的`<router-view/>`标签找到根路由。**

运行效果图：

![220217221110 00_00_00-00_00_30](https://s2.loli.net/2022/02/17/ov6A9hHierkpPd8.gif)

但是`localhost:9876/`还是空界面，用户管理在`/home`路径，因此要用到重定向：参考vueruter官网：

重定向也是通过 `routes` 配置来完成，下面例子是从 `/home` 重定向到 `/`：

```js
const routes = [{ path: '/home', redirect: '/' }]
```

在我们的路由中加上`redirect: '/home'`就能实现重定向，实现根目录访问

> 进一步的路径修改，实现多个管理界面的切换

把用户管理的名字改为`userManage.vue`并且新建文件夹`management`将其挪进去，在文件夹中新建`professorManage.vue`，重新配置两者的路由

```js
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
      }
    ]
```

在`Aside.vue`中实现点击两个标签切换两个管理窗口：使用`@click = "$router.push()"`连接到新路由地址：

```vue
<!-- @click处填写路由的path值 -->
        <el-menu-item index="user" @click="$router.push('/Mag/userManage')">
          用户管理
        </el-menu-item>
        <el-menu-item
          index="professor"
          @click="$router.push('/Mag/professorManage')"
        >
          专家管理
        </el-menu-item>
```

**实现点击两个选项进入两个不同的表格**

运行图：

![1 00_00_00-00_00_30~1](https://s2.loli.net/2022/02/18/p5OEZkyhofFclv3.gif)



### 5.2 登录注册

新建两个页面`UserLogin.vue`和`UserRegister.vue`并注册路由：

```js
{
    path: '/userLogin',
    name: 'UserLogin',
    component: () => import('../views/login/UserLogin.vue')
  },
  {
    path: '/userRegister',
    name: 'UserRegister',
    component: () => import("@/views/login/UserRegister")
  }
```

#### 5.2.1 Login

在登陆界面设置UI，提交表单，等内容：

![image-20220219180759257](https://s2.loli.net/2022/02/19/VsYcer6qkOX1SNI.png)

按钮处设置`@click = "login"`方法，写入方法体

- 表单验证：

只需要通过 `rules` 属性传入约定的验证规则，并将 `form-Item` 的 `prop` 属性设置为需 form 绑定值对应的字段名即可

```vue
<el-form-item label="用户名" prop="username">
<el-form-item label="密码" prop="password">
rules: {
        username: [
          {
            required: true,
            message: "请输入用户名",
            trigger: "blur",
          },
        ],
        password: [
          {
            min: 6,
            max: 10,
            message: "Length should be 6 to 10",
            trigger: "blur",
          },
        ],
      },
```

- 方法体

```js
login() {
      //表单验证
      this.$refs['formRef'].validate((valid) => {
        if (valid) {
          // 验证通过执行请求
          request.post("/user/login", this.form).then((res) => {
            if (res.code == "0") {
              this.$message({
                type: "success",
                message: "登录成功",
              });
              this.$router.push("/");
            } else {
              this.$message({
                type: "error",
                message: res.msg,
              });
            }
          });
        }
      });
    },
```

> 在后端controller新增接口，并接受前端传来的post请求

```java
//login
    @PostMapping("/login")
    public Result<?> login(@RequestBody User user) {
        User selectOne = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername())
                .eq(User::getPassword, user.getPassword()));
        if (selectOne == null) {
            return Result.error("-1","用户名密码错误");
        }
        return Result.success();
    }
```

测试运行：

![image-20220219182302345](https://s2.loli.net/2022/02/19/PnufUCDBKT41me2.png)

![login 00_00_00-00_00_30](https://s2.loli.net/2022/02/19/2DHgZKecW8VAPEC.gif)



#### 5.2.2 Register

新建注册界面并建立路由：

```json
{
    path: '/userRegister',
    name: 'UserRegister',
    component: () => import("@/views/login/UserRegister")
  }
```

> 用户注册和useradd同理，只需要校验两次密码是否相同，以及用户名是否重复等，注册成功跳转页面

在`@click = "register"`方法中写入函数：

```js
register() {
      if (this.form.password != this.form.confirm) {
        this.$message({
          type: "error",
          message: "两次密码不一致请重新输入",
        });
        return;
      } else {
        //验证值是否通过在提交post请求
        this.$refs["formRef"].validate((valid) => {
          if (valid) {
            request.post("/user/register", this.form).then((resultr) => {
              if (resultr.code == "0") {
                this.$message({
                  type: "success",
                  message: "注册成功！",
                });
                this.$router.push("/userLogin");
              } else {
                this.$message({
                  type: "error",
                  message: resultr.msg,
                });
              }
            });
          }
        });
      }
    }
```

设置前端表单校验，并给后端提供json值供其插入

> controller：需要判断用户名是否唯一，如果用户名重复则取消注册，返回message给前端用户

```java
//register
@PostMapping("/register")
public Result<?> register(@RequestBody User user) {
    //判断用户名是否重复，因为用户名是唯一的
    User selectOne = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername()));
    if (selectOne != null) {
        return Result.error("-1","用户名重复，请换一个");
    }else {
        if (user.getPassword() == null) {
            user.setPassword("123456");
        }
        userMapper.insert(user);
    }
    return Result.success();
}
```

运行结果：

![register8 00_00_00-00_00_30](https://s2.loli.net/2022/02/22/8bAvNB4fqhtu6Wa.gif)



### 5.3 用户的session获取（js获取）

#### 5.3.1 session拦截器

**如果session中无值就自动退出登录**

在`request.js`中配置拦截器

![image-20220303165058064](https://s2.loli.net/2022/03/03/6oGvlTzPc4iF9xY.png)

```js
//获取缓存信息，通过前端进行用户拦截
    let userJSON = sessionStorage.getItem("user")
    if(!userJSON) {
        router.push("/userLogin")
    }
```

同时在登录界面的`lonin()`方法中，加上缓存用户信息：

`sessionStorage.setItem("user", JSON.stringify(res.data))*//缓存用户信息*`

在controller中return中多返回一个selectOne的值：

old:`return Result.success();`

new:`return Result.success(selectOne);`

如图所示：

![image-20220303165433570](https://s2.loli.net/2022/03/03/A2o3gZCJkNq7crP.png)



- 对`Header.vue`中的用户下拉菜单进行双向绑定，获取session中的用户名渲染到页面

```vue
<span class="el-dropdown-link">
          {{ nickName }}
          <el-icon class="el-icon--right">
            <arrow-down />
          </el-icon>
        </span>
<script>
export default{
data() {
    return {
      nickName: JSON.parse(sessionStorage.getItem("user")).nickName,
    };
  },
}
</script>
```

效果如下：

![image-20220303165902236](https://s2.loli.net/2022/03/03/F78AdmqIl1noiGQ.png)



>在header中如果这样写 第一次启动进后台会报一个找不到user.nickName的错误，因为我们只规定了去session中找，那就会找不到，页面无法加载

正确做法是在返回的nickName中加上一个空字符串

`nickName: JSON.parse(sessionStorage.getItem("user") || "{}").nickName,`

如此一来找不到nick就会返回空字符串，就会被拦截进登陆界面了



#### 5.3.2管理员退出登录

```vue
<el-dropdown-item @click="logout"
              >退出系统</el-dropdown-item
            >
<script>

export default {
  
  methods: {
logout() {
    //清除User数据back到Login
  this.$router.push('/userLogin')
  sessionStorage.removeItem("user")
}
  },
};
</script>
```





### 5.4 文件上传、下载

#### 5.4.1

**使用MultipartFile进行文件的上传后端接口实现**

新建fileController:

```java
@RestController
@RequestMapping("/files")
public class FileController {
    @PostMapping("/upload")
    public Result<?> upload(MultipartFile multipartFile) throws IOException {
        //获取文件
        String originalFilename = multipartFile.getOriginalFilename();
        //把文件路径设置到当前项目的files文件夹下，文件名就是originalFilename
        String rootFilePath = System.getProperty("user.dir")
                + "/springboot/src/main/resources/files" + originalFilename;
        //使用hutool工具类的IO工具类把冲前端传过来的文件写入到rootFilePath
        FileUtil.writeBytes(multipartFile.getBytes(), rootFilePath);

        return Result.success();
    }
}
```

在/resources新建/files文件夹

使用postman测试接口效果：

- 选择post 
- form-data选择files
- 提交文件-send

![image-20220413164201450](https://s2.loli.net/2022/04/13/YckoXzAS8t2Kiw7.png)

后台项目启动并debug

![image-20220413164312842](https://s2.loli.net/2022/04/13/GYiX82cLaKnh7sR.png)

逐个检查文件名称 文件路径 上传路径是否正确 图是已经正确写入到`D:\SpringBoot\demoSpringBootVue\springbootvue/springboot/src/main/resources/files/1.png`

目录下



#### 5.4.2 重复文件同时写入（不覆盖）

核心思路就是给文件加入一个唯一标识符

```java
//自定义文件前缀（唯一标识，防止重复覆盖）
        //1:当前时间 多线程不能保证
        System.currentTimeMillis();
        //2：UUID
        String uuid = IdUtil.fastSimpleUUID();
```

给路径加上uuid前缀`String rootFilePath = System.getProperty("user.dir") + "/springboot/src/main/resources/files/" + uuid + "_" + originalFilename;`

重启项目打断点调试，可以看到此时的文件名加上了uuid前缀

![image-20220413165801342](https://s2.loli.net/2022/04/13/2oZeyC1JGrcEVtg.png)

此时的files文件夹：

![image-20220413170030348](https://s2.loli.net/2022/04/13/tQN64BjXJryZKdn.png)



#### 5.4.3 下载

```java
/**
     * 文件下载（后端传到前端）
     */
    @GetMapping("/{uuid}")
    public void getFiles( @PathVariable String uuid, HttpServletResponse response) {
        OutputStream outputStream;  //new Obj of outputStream

        //定义文件上传根路径
        String bathPath = System.getProperty("user.dir") + "/springboot/src/main/resources/files/";
        //获取所有文件名称
        List<String> listFileNames = FileUtil.listFileNames(bathPath);
        //寻找跟参数一致的文件
        String fileName = listFileNames.stream().filter(name -> name.contains(uuid)).findAny().orElse("");
        //如果找到了 就说明文件是可以下载的 通过读取文件路径获取字节流
        try {
            if (StrUtil.isNotEmpty(fileName)) {
                response.addHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
                response.setContentType("application/octet-stream");
                byte[] bytes = FileUtil.readBytes(bathPath + fileName);
                outputStream = response.getOutputStream(); //通过输出流返回文件
                outputStream.write(bytes);
                outputStream.flush();
                outputStream.close();
            }
        } catch (Exception e) {
            System.out.println("文件下载失败");
        }
    }
```

之后设置上传接口返回的文件url，在upload接口中设置端口和ip地址

```java
@Value("${server.port}")
private String port;

private static final String ip = "http://localhost";
```

把设置接口返回结果

`return Result.success(ip + ":" + port + "/files/" + uuid);`

重启服务 发送请求

![image-20220413175948471](C:\Users\18328\AppData\Roaming\Typora\typora-user-images\image-20220413175948471.png)

将返回的url复制到浏览器打开，发现文件下载 接口测试通过

1111






























































