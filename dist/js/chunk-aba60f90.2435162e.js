(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-aba60f90"],{"0846":function(e,t,n){},"395c":function(e,t,n){"use strict";n.r(t);n("b0c0");var o=n("7a23"),c={class:"father"},r=["src"],a={style:{padding:"14px"}},s={class:"bottom"},i=Object(o["createTextVNode"])("查看");function l(e,t,n,l,d,u){var p=Object(o["resolveComponent"])("el-button"),b=Object(o["resolveComponent"])("el-card");return Object(o["openBlock"])(),Object(o["createElementBlock"])("div",c,[(Object(o["openBlock"])(!0),Object(o["createElementBlock"])(o["Fragment"],null,Object(o["renderList"])(d.CardList,(function(e){return Object(o["openBlock"])(),Object(o["createElementBlock"])("div",{class:"children",key:e},[Object(o["createVNode"])(b,{"body-style":{padding:"0px"},class:"el-card"},{default:Object(o["withCtx"])((function(){return[Object(o["createElementVNode"])("img",{src:e.iconPath,class:"img"},null,8,r),Object(o["createElementVNode"])("div",a,[Object(o["createElementVNode"])("span",null,Object(o["toDisplayString"])(e.name),1),Object(o["createElementVNode"])("div",s,[Object(o["createVNode"])(p,{type:"text",class:"button",onClick:function(t){return u.goDetail(e.id)}},{default:Object(o["withCtx"])((function(){return[i]})),_:2},1032,["onClick"])])])]})),_:2},1024)])})),128))])}n("7069");var d=n("b775"),u=(n("2b27"),{name:"",data:function(){return{professorDepartment:"骨科",CardList:[],img:"",profName1:"",imgList:[],profNameList:[]}},mounted:function(){var e=this;d["a"].get("/professorDetails/getProByDepartment",{params:{professorDepartment:this.professorDepartment}}).then((function(t){console.log(t);for(var n=t.data.length,o=0;o<n;o++)e.CardList.push(null===t||void 0===t?void 0:t.data[o])}))},methods:{goDetail:function(e){console.log(e),this.$router.push({path:"/professorDetails/details",query:{professorID:e}})}}}),p=(n("3fa5"),n("6b0d")),b=n.n(p);const f=b()(u,[["render",l],["__scopeId","data-v-3f392daa"]]);t["default"]=f},"3fa5":function(e,t,n){"use strict";n("0846")}}]);
//# sourceMappingURL=chunk-aba60f90.2435162e.js.map