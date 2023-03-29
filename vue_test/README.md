# ref属性
  1. 被用来给元素或子组件注册引用信息（id 的替代者）
  2. 应用在 html 标签上获取的是真实的 DOM 元素，应用在组件标签上是组件实例对象（vc）
  3. 使用方式

    打标识：<h1 ref="xxx">....</h1> 或 <School ref="xxx"></School>
    获取：this.$refs.xxx

# 配置项 props
  功能：让组件接收外部传过来的数据
  （1）传递数据
      <Demo name="xxx"/>
  （2）接收数据
      第一种方式（只接收）：props: ['name']
      第二种方式（限制类型）：
        props: {
          name: Number
        }
      第三种方式（限制类型，限制必要性，限制默认值）：
        props: {
          name: {
            type: String,
            required: true,
            default: '老王'
          }
        }
  备注：props 是只读的，Vue 底层会监测你对 props 的修改，如果进行了修改，就会发出警告，
      若业务需求确实需要修改，那么请复制 props 的内容到 data 中一份，然后去修改 data 中的数据

# mixin(混入)
  功能：可以把多个组件共用的配置提取成一个混入对象
  使用方式：
    第一步：定义混合，例如：
    {
      data(){...},
      methods(){...},
      ...
    }
    第二步：使用混合，例如：
    （1）全局混合：Vue.mixin(xxx)
    （2）局部混合：mixins: ['xxx']

# 插件
  功能：用于增强 Vue
  本质：包含 install 方法的一个对象，install 的第一个参数是 Vue，第二个以后的参数是插件使用者传递的数据
  定义插件：
    对象.install = function(Vue, options) {
      // 1. 添加全局过滤器
      Vue.filter(...)

      // 2. 添加全局指令
      Vue.directive(...)
    
      // 3. 配置全局混入(合)
      Vue.mixin(xxx)
    
      // 4. 添加实例方法
      Vue.prototype.$myMethod = function() {...}
      Vue.prototype.$myProperty = xxx
    }
    使用插件：Vue.use()

# 总结 TodoList 案例
  1. 组件化编码流程：

    （1）拆分静态组件：组件要按功能点拆分，命名不要与 HTML 元素冲突
    （2）实现动态组件：考虑好数据存放的位置、数据是一个组件在用还是一些组件在用
      - 一个组件在用：放在组件自身即可
      - 一些组件在用：放在他们共同的父组件上（状态提升）
    （3）实现交互：从绑定事件开始
  2. props 适用于

    （1）父组件 ==> 子组件 通信
    （2）子组件 ==> 父组件 通信（要求父先给与一个函数）
  3. 使用 v-model 时要切记：v-model 绑定的值不能是 props 传递的值，因为 props 是不可以修改的
  4. props 传过来的若是对象类型的值，修改对象中的属性时 Vue 不会报错，但不推荐这么做

# 全局事件总线
  1. 一种组件间的通信方式，适用于任意组件间通信
  2. 在 main.js 文件中安装全局事件总线

	```js
	new Vue({
	  render: h => h(App),
	  // 安装全局事件总线
	  beforeCreate() {
	  Vue.prototype.$bus = this
	  }
	}).$mount('#app')
	```

	

3. 使用事件总线：

	1. 接收数据：A 组件想接收数据，则在 A 组件中给 $bus 绑定自定义事件，事件的回调留在A 组件自身

		```js
		methods: {
		    demo(data) {...}
		},
		... ,
		mounted() {
			this.$bus.$on('sendStudentName', (data) => {
		      console.log('School组件收到了数据', data);
		    })
		},
		```

	2. 提供数据：`this.$bus.$emit('xxx', data)`

	3. 最好在`beforeDestroy`钩子中，用`$off`去解绑当前组件所用到的事件

		```js
		beforeDestroy() {
		    this.$bus.$off('sendStudentName')
		  }
		```

		

