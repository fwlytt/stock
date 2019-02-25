if (layui === undefined) {
  console.error('请先引用layui.js文件.');
} else {

  var modules = {
    admin: 'lib/layui/lay/kit_modules/admin',
    axios: 'lib/layui/lay/kit_modules/axios',
    lodash: 'lib/layui/lay/kit_modules/lodash',
    menu: 'lib/layui/lay/kit_modules/menu',
    mockjs: 'lib/layui/lay/kit_modules/mockjs',
    mockjsbase: 'lib/layui/lay/kit_modules/mockjsbase',
    route: 'lib/layui/lay/kit_modules/route',
    tabs: 'lib/layui/lay/kit_modules/tabs',
    utils: 'lib/layui/lay/kit_modules/utils',
    component:'lib/layui/lay/kit_modules/component',
    nprogress:'lib/layui/lay/kit_modules/nprogress',
    kit:'lib/layui/lay/kit_modules/kit',
    sidebar:'lib/layui/lay/kit_modules/sidebar',
    select:'lib/layui/lay/kit_modules/select',
    echarts:'lib/layui/lay/kit_modules/echarts'
  };

  layui.injectModules(modules);
}