webpackJsonp([33],{ZFk2:function(t,i,s){"use strict";Object.defineProperty(i,"__esModule",{value:!0});var e=s("mtWM"),c=s.n(e),a={data:function(){var t=window.localStorage.getItem("userName"),i=window.localStorage.getItem("friendNick");return{userName:t,myId:this.$route.params.id,friendNick:i,hasPic:!1}},mounted:function(){var t=this;null!==this.myId&&c.a.get("http://121.42.177.97:8080/red/use/pic_id.do?user_id="+this.myId).then(function(i){t.hasPic=!0}).catch(function(t){throw t})}},l={render:function(){var t=this,i=t.$createElement,e=t._self._c||i;return e("div",[e("header",{attrs:{id:"sub-header"}},[e("div",{staticClass:"center"},[e("div",{staticClass:"iconfont icon-fanhui",on:{click:function(i){t.$router.back()}}},[e("span",[t._v("返回")])]),t._v(" "),e("span",[t._v("聊天详情")])])]),t._v(" "),e("div",{staticClass:"member"},[e("ul",{staticClass:"chat-dialogue-entry-collect"},[e("router-link",{attrs:{tag:"li",to:{name:"userDetails",params:{id:t.myId}}}},[e("div",[t.hasPic?e("img",{attrs:{src:"http://121.42.177.97:8080/red/use/pic_id.do?user_id="+this.myId,alt:"头像"}}):e("img",{attrs:{src:s("c5Or"),alt:"默认头像"}})]),t._v(" "),e("p",[t._v(t._s(t.friendNick))])])],1)]),t._v(" "),t._m(0),t._v(" "),t._m(1),t._v(" "),t._m(2)])},staticRenderFns:[function(){var t=this.$createElement,i=this._self._c||t;return i("div",{staticClass:"weui-cells"},[i("div",{staticClass:"weui-cell weui-cell_switch"},[i("div",{staticClass:"weui-cell__bd"},[this._v("置顶聊天")]),this._v(" "),i("div",{staticClass:"weui-cell__ft"},[i("input",{staticClass:"weui-switch",attrs:{type:"checkbox"}})])]),this._v(" "),i("div",{staticClass:"weui-cell weui-cell_switch"},[i("div",{staticClass:"weui-cell__bd"},[this._v("消息免打扰")]),this._v(" "),i("div",{staticClass:"weui-cell__ft"},[i("input",{staticClass:"weui-switch",attrs:{type:"checkbox"}})])])])},function(){var t=this.$createElement,i=this._self._c||t;return i("div",{staticClass:"weui-cells"},[i("div",{staticClass:"weui-cell weui-cell_access"},[i("div",{staticClass:"weui-cell__bd"},[this._v("设置当前聊天背景")]),this._v(" "),i("div",{staticClass:"weui-cell__ft"})])])},function(){var t=this.$createElement,i=this._self._c||t;return i("div",{staticClass:"weui-cells"},[i("div",{staticClass:"weui-cell weui-cell_access"},[i("div",{staticClass:"weui-cell__bd"},[this._v("清空聊天记录")]),this._v(" "),i("div",{staticClass:"weui-cell__ft"})])])}]};var r=s("VU/8")(a,l,!1,function(t){s("bwX0")},"data-v-0def2741",null);i.default=r.exports},bwX0:function(t,i){}});
//# sourceMappingURL=33.c78bf55bf24189fa9b84.js.map