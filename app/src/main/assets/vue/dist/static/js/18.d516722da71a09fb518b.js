webpackJsonp([18],{"07Xq":function(t,e){},"5G9B":function(t,e,n){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var a=n("mtWM"),i=n.n(a),o=(window.localStorage.getItem("myId"),window.localStorage.getItem("myId")),s={data:function(){return{inputone:"",inputtwo:"",bdzhm:!0,zsxm:!0,myId:o}},methods:{bdzh:function(){i.a.get("http://121.42.177.97:8080/red/pay/phone.do",{params:{user_id:this.myId,phone:this.inputtwo,real_name:this.inputone}}).then(function(t){alert("成功")}).catch(function(t){console.log(t)})}},mounted:function(){var t=this;i.a.get("http://121.42.177.97:8080/red/pay/get_pay_phone.do?user_id="+this.myId).then(function(e){if(console.log(e),null==e.data.data)return!1;t.inputtwo=e.data.data,t.bdzhm=!1,t.zsxm=!1}).catch(function(t){console.log(t)})}},l={render:function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",[n("header",{attrs:{id:"sub-header"}},[n("div",{staticClass:"center"},[n("div",{staticClass:"iconfont icon-fanhui",on:{click:function(e){t.$router.back()}}},[n("span",[t._v("钱包")])]),t._v(" "),n("span",[t._v("绑定账户")])])]),t._v(" "),n("div",{staticClass:"weui-cells page dialog js_show cell_top"},[n("div",{staticClass:"weui-cell"},[n("div",{staticClass:"weui-cell__bd"},[t._v("支付宝账号")]),t._v(" "),n("div",{staticClass:"weui-cell__ft"},[n("input",{directives:[{name:"model",rawName:"v-model",value:t.inputtwo,expression:"inputtwo"}],staticClass:"cell_input",attrs:{placeholder:"请输入支付宝账号"},domProps:{value:t.inputtwo},on:{input:function(e){e.target.composing||(t.inputtwo=e.target.value)}}})])]),t._v(" "),t.zsxm?n("div",{staticClass:"weui-cell my_qrcode"},[n("div",{staticClass:"weui-cell__bd"},[t._v("真实姓名")]),t._v(" "),n("div",{staticClass:"weui-cell__ft"},[n("input",{directives:[{name:"model",rawName:"v-model",value:t.inputone,expression:"inputone"}],staticClass:"cell_input",attrs:{placeholder:"请输入真实姓名"},domProps:{value:t.inputone},on:{input:function(e){e.target.composing||(t.inputone=e.target.value)}}})])]):t._e()]),t._v(" "),t.bdzhm?n("div",{staticClass:"cell_bottom",on:{click:t.bdzh}},[n("button",{staticClass:"cell_button"},[t._v("绑定账户")])]):t._e()])},staticRenderFns:[]};var c=n("VU/8")(s,l,!1,function(t){n("07Xq")},"data-v-f74aff38",null);e.default=c.exports}});
//# sourceMappingURL=18.d516722da71a09fb518b.js.map