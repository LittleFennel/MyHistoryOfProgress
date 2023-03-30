import Vue from 'vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import App from './App.vue'
import { mixin } from "./components/mixin";
// 引入插件
import plugins from "./plugins";

Vue.use(ElementUI);
Vue.config.productionTip = false
Vue.mixin(mixin)
Vue.use(plugins)

new Vue({
  render: h => h(App),
}).$mount('#app')
