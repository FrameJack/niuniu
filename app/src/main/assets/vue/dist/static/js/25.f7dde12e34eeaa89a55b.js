webpackJsonp([25],{U8OX:function(t,e,n){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var a=n("c/Tr"),s=n.n(a),i=n("mtWM"),r=n.n(i),o={data:function(){return{personal:[]}},methods:{bjgg:function(){this.ggbj=s()(this.$refs.bjgg.files).map(function(t){return window.URL.createObjectURL(t)}),this.$refs.photo.src=this.ggbj;var t=this.$refs.bjgg.files[0],e=new FormData;e.append("user_name","17301794508"),e.append("mFile",t,t.name),console.log("222222");r.a.post("http://121.42.177.97:8080/red/use/hard_pic.do?",e,{headers:{"Content-Type":"multipart/form-data"}}).then(function(t){console.log("222222")}).catch(function(t){return console.log(t)})}},mounted:function(){var t=this;r.a.post("http://121.42.177.97:8080/red/user/findfriend.do?name=17301794508").then(function(e){t.personal=e.data.data}).catch(function(t){console.log(t)})}},c={render:function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",[n("div",{staticClass:"header-fixed"},[n("div",{staticClass:"weibotitle"},[t._v("\n\t\t\t个人头像\n\t\t")]),t._v(" "),n("router-link",{attrs:{to:"/mine/profile"}},[n("span",{staticClass:"fanhui"},[n("i",{staticClass:"iconfont icon-fanhui"}),t._v(" 个人信息")])]),t._v(" "),n("span",{staticClass:"right_dian"},[t._v(". . .")]),t._v(" "),n("input",{ref:"bjgg",staticClass:"bjgg",attrs:{type:"file",name:"",id:"",accept:"image/*",value:""},on:{change:t.bjgg}})],1),t._v(" "),n("div",{staticClass:"centerwrap"},[n("img",{ref:"photo",attrs:{src:"http://121.42.177.97:8080/red/use/gethard_pic.do?user_name="+t.personal.user_name}})])])},staticRenderFns:[]};var d=n("VU/8")(o,c,!1,function(t){n("Zf58")},"data-v-8867de5a",null);e.default=d.exports},Zf58:function(t,e){}});
//# sourceMappingURL=25.f7dde12e34eeaa89a55b.js.map