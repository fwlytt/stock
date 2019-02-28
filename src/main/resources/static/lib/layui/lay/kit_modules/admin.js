/** kitadmin-v2.1.0 MIT License By http://kit.zhengjinfan.cn Author Van Zheng */
;"use strict";
var mods = ["element", "sidebar", "mockjs", "select", "tabs", "menu", "route", "utils", "component", "kit"];
layui.define(mods, function (e) {
    layui.element;
    var t = layui.utils, a = layui.jquery, n = (layui.lodash, layui.route), i = layui.tabs, l = layui.layer,
        o = layui.menu, m = layui.component, s = layui.kit, p = function () {
            this.config = {elem: "#app", loadType: "SPA"}, this.version = "1.0.0"
        };
    p.prototype.ready = function (e) {
        var i = this.config, o = (0, t.localStorage.getItem)("KITADMIN_SETTING_LOADTYPE");
        null !== o && void 0 !== o.loadType && (i.loadType = o.loadType), s.set({type: i.loadType}).init(), u.routeInit(i), u.menuInit(i), "TABS" === i.loadType && u.tabsInit(), "" === location.hash && t.setUrlState("主页", "#/"), layui.sidebar.render({
            elem: "#ccleft",
            title: "这是左侧打开的栗子",
            shade: !0,
            direction: "left",
            dynamicRender: !0,
            url: "pages/views/table/teble2.html",
            width: "50%"
        }), a("#cc").on("click", function () {
            layui.sidebar.render({
                elem: this,
                title: "这是表单盒子",
                shade: !0,
                dynamicRender: !0,
                url: "pages/views/form/index.html",
                width: "50%"
            })
        }), m.on("nav(header_right)", function (e) {
            var t = e.elem.attr("kit-target");
            "setting" === t && layui.sidebar.render({
                elem: e.elem,
                title: "设置",
                shade: !0,
                dynamicRender: !0,
                url: "pages/views/setting.html"
            }), "help" === t && l.alert("这么简单还需要帮助吗!?")
        }), layui.mockjs.inject(APIs), "SPA" === i.loadType && n.render(), "function" == typeof e && e()
    };
    var u = {
        routeInit: function (e) {
            var t = this, a = {
                r: [{
                    path: "/user/index",
                    component: "/pages/views/user/index.html",
                    name: "用户列表",
                    children: [{
                        path: "/user/create",
                        component: "pages/views/user/create.html",
                        name: "新增用户"
                    }, {path: "/user/edit", component: "pages/views/user/edit.html", name: "编辑用户"}]
                }],
                routes: [
                    {path: "/user/index", component: "/pages/views/user/index.html", name: "用户列表"},
                    {path: "/user/create", component: "pages/views/user/create.html", name: "新增用户"},
                    {path: "/user/edit", component: "pages/views/user/edit.html", name: "编辑用户"},
                    {path: "/cascader", component: "pages/views/cascader/index.html", name: "Cascader"},
                    {path: "/", component: "pages/views/app.html", name: "主页"},
                    {path: "/user/my", component: "pages/views/profile.html", name: "用户中心"},

                    {path: "/stock/list", component: "pages/stock/list.html", name: "库存列表"},
                    {path: "/stock/recordList", component: "pages/stock/recordList.html", name: "库存详情"},

                    {path: "/customer/list", component: "pages/customer/list.html", name: "客户列表"},
                    {path: "/customer/workshopList", component: "pages/customer/workshopList.html", name: "加工坊列表"},
                    {path: "/customer/info_workshop", component: "pages/customer/info_workshop.html", name: "客户详情"},
                    {path: "/customer/info_bill", component: "pages/customer/info_bill.html", name: "客户账单详情"},
                    {path: "/customer/info_order", component: "pages/customer/info_order.html", name: "客户订单详情"},

                    {path: "/orderInfo/list", component: "pages/orderInfo/list.html", name: "订单列表"},
                    {path: "/orderInfo/billList", component: "pages/orderInfo/billList.html", name: "账单列表"},
                    {path: "/orderInfo/info_billList", component: "pages/orderInfo/info_billList.html", name: "账单详情"},

                    {path: "/purchase/list", component: "pages/purchase/list.html", name: "进货列表"},
                    {path: "/purchase/billList", component: "pages/purchase/billList.html", name: "账单列表"},
                ]
            };
            return "TABS" === e.loadType && (a.onChanged = function () {
                i.existsByPath(location.hash) ? i.switchByPath(location.hash) : t.addTab(location.hash, (new Date).getTime())
            }), n.setRoutes(a), this
        }, addTab: function (e, t) {
            var a = n.getRoute(e);
            a && i.add({id: t, title: a.name, path: e, component: a.component, rendered: !1, icon: "&#xe62e;"})
        }, menuInit: function (e) {
            var t = this;
            return o.set({
                dynamicRender: !1, isJump: "SPA" === e.loadType, onClicked: function (a) {
                    if ("TABS" === e.loadType && !a.hasChild) {
                        var n = a.data, i = n.href, l = n.layid;
                        t.addTab(i, l)
                    }
                }, elem: "#menu-box", remote: {url: "/api/getmenus", method: "post"}, cached: !1
            }).render(), t
        }, tabsInit: function () {
            i.set({
                onChanged: function (e) {
                }
            }).render(function (e) {
                e.isIndex && n.render("#/")
            })
        }
    };
    (new p).ready(function () {
        console.log("Init successed.")
    }), e("admin", {})
});
//# sourceMappingURL=admin.js.map
