(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-19877871"],{7860:function(e,t,n){"use strict";n.r(t);n("b0c0");var c=n("7a23"),o=function(e){return Object(c["pushScopeId"])("data-v-40245914"),e=e(),Object(c["popScopeId"])(),e},a={class:"details"},l={class:"pageHeader"},r=["src"],d={class:"info1"},i=o((function(){return Object(c["createElementVNode"])("span",null,"专家姓名：",-1)})),s={class:"info1"},u=o((function(){return Object(c["createElementVNode"])("span",null,"专家科室：",-1)})),b={class:"info1"},p=o((function(){return Object(c["createElementVNode"])("span",null,"年龄：",-1)})),f={class:"info2"},O=o((function(){return Object(c["createElementVNode"])("span",null,"专家简介：",-1)})),j=o((function(){return Object(c["createElementVNode"])("br",null,null,-1)})),m=o((function(){return Object(c["createElementVNode"])("br",null,null,-1)})),N=Object(c["createTextVNode"])("       ");function V(e,t,n,o,V,h){var g=Object(c["resolveComponent"])("el-page-header"),v=Object(c["resolveComponent"])("el-row"),E=Object(c["resolveComponent"])("el-divider"),C=Object(c["resolveComponent"])("el-col"),w=Object(c["resolveComponent"])("el-card");return Object(c["openBlock"])(),Object(c["createElementBlock"])("div",a,[Object(c["createVNode"])(w,null,{default:Object(c["withCtx"])((function(){return[Object(c["createVNode"])(v,null,{default:Object(c["withCtx"])((function(){return[Object(c["createElementVNode"])("div",l,[Object(c["createVNode"])(g,{content:"详情：",onClick:h.goBack},null,8,["onClick"])])]})),_:1}),Object(c["createVNode"])(E),Object(c["createVNode"])(v,null,{default:Object(c["withCtx"])((function(){return[Object(c["createVNode"])(C,{span:12},{default:Object(c["withCtx"])((function(){return[Object(c["createElementVNode"])("div",null,[Object(c["createElementVNode"])("img",{src:V.profIcon,alt:""},null,8,r)])]})),_:1}),Object(c["createVNode"])(C,{span:12},{default:Object(c["withCtx"])((function(){return[Object(c["createElementVNode"])("div",d,[i,Object(c["createElementVNode"])("span",null,Object(c["toDisplayString"])(V.name),1)]),Object(c["createElementVNode"])("div",s,[u,Object(c["createElementVNode"])("span",null,Object(c["toDisplayString"])(V.department),1)]),Object(c["createElementVNode"])("div",b,[p,Object(c["createElementVNode"])("span",null,Object(c["toDisplayString"])(V.age),1)]),Object(c["createElementVNode"])("div",f,[O,j,m,N,Object(c["createElementVNode"])("span",null,Object(c["toDisplayString"])(V.info),1)])]})),_:1})]})),_:1})]})),_:1})])}var h=n("8cf8"),g=n("a9e1"),v=n("6ff1"),E=n("b775"),C=(n("3b02"),{name:"",components:{SysHeader:h["a"],IndexCarousel:g["a"],SysBottom:v["a"]},data:function(){return{id:"",profIcon:"",name:"test",department:"",age:"",info:""}},mounted:function(){this.id=this.$route.query.professorID,console.log("此页面ID"),console.log(this.id),this.load()},methods:{load:function(){var e=this;E["a"].get("/professorDetails/selectOne/"+this.id).then((function(t){console.log(t),e.profIcon=t.data.iconPath,e.name=t.data.name,e.department=t.data.department,e.age=t.data.age})),E["a"].get("/professorDetails/selectDetails",{params:{id:this.id}}).then((function(t){console.log(t.data.details),e.info=t.data.details}))},goBack:function(){this.$router.push("/professorDetails/all")}}}),w=(n("d9ec"),n("6b0d")),D=n.n(w);const k=D()(C,[["render",V],["__scopeId","data-v-40245914"]]);t["default"]=k},bba7:function(e,t,n){},d9ec:function(e,t,n){"use strict";n("bba7")}}]);
//# sourceMappingURL=chunk-19877871.d4fa145d.js.map