#  Webpack的基本使用

## 创建各行列表变色项目

1. 新建项目空白目录
2. 新建src源代码目录
3. 新建src -> index.html 首页和src -> index.js脚本
4. 初始化首页基本的结构
5. 运行npm install jquery -s 命令，安装Jquery
6. 通过ES6模块化的方式导入jQuery，实现隔行变色效果

```html
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  <script src="./index.js"></script>
</head>
<body>
  <ul>
    <li>这是第 1 个li</li>
    <li>这是第 2 个li</li>
    <li>这是第 3 个li</li>
    <li>这是第 4 个li</li>
    <li>这是第 5 个li</li>
    <li>这是第 6 个li</li>
    <li>这是第 7 个li</li>
    <li>这是第 8 个li</li>
    <li>这是第 9 个li</li>
  </ul>
</body>
</html>
```

```js
// 使用ES6导入jQuery
import $ from 'jquery'

// 定义jQuery的入口函数
$(function() {
  // 实现奇数行隔行变色
  // 奇数行为红色
  $('li:odd').css('background-color', 'red')
  $('li:even').css('background-color', 'pink')
})
```

## Webpack基本安装和配置

- 在项目中安装

```shell
npm install webpack@5.42.1 webpack-cli@4.7.2 --save-dev # -D 是--save-dev的缩写
```

- 在项目根目录下创建webpack.config.js的Webpack配置文件，并初始化如下的基本配置

```js
module.exports = {
  mode: 'development' // mode 用来指定模式，可选值有development 和 production
}
```

- 在package.json 的 script 节点下，新增dev脚本

```json
{
  "name": "change-rows-color",
  "version": "1.0.0",
  "description": "",
  "main": "index.js",
  "scripts": {
    "test": "echo \"Error: no test specified\" && exit 1",
    "dev": "webpack" // script 节点下的脚本，可以通过 npm run 执行， 例如 npm run dev
  },
  "keywords": [],
  "author": "",
  "license": "ISC",
  "dependencies": {
    "jquery": "^3.6.4"
  },
  "devDependencies": {
    "webpack": "^5.42.1",
    "webpack-cli": "^4.7.2"
  }
}
```

- 在终端中运行 `npm run dev`  命令， 启动webpack进行项目的打包构建

## webpack中的默认约定

在 webpack 4.x 和  webpack 5.x 的版本中，有如下的默认约定

1. 默认的打包入口文件为：src/index.js
2. 默认的输出文件路径为：dist/main.js
3. 可以在 webpack.config.js 中修改打包的默认约定

## 自定义打包的入口和出口

在 webpack.config.js 配置文件中，通过 entry 节点指定打包的入口。通过 output 节点指定打包的出口

```js
const path = require('path') // 导入 node.js 中专门操作路径的模块

module.exports = {
    entry: path.join(_dirname, './src/index.js'), // 打包入口文件的路径
    output: {
        path: path.join(__diename, './dist'), // 输出文件的存放路径
        filename: 'bundle.js' // 输出文件的名称
    }
}
```

## 安装和配置 webpack-dev-serve 这个插件

1. 通过安装和配置第三方插件，可以拓展 webpack 的能力， 从而让 webpack 用起来更方便。最常用的 webpack 插件有如下两个：

	1. webpack-dev-server
		- 类似于node.js 阶段用到的 nodemon 工具
		- 每当修改了源代码， webpack 会自动进行项目的打包和构建
	2. html-webpack-plugin
		- webpack 中的 html 插件（类似于一个模板引擎插件）
		- 可以通过此插件自定制 index.html 页面的内容

2. 安装 webpack-dev-server

	```shell
	npm install webpack-dev-server@3.11.2 -D
	```

3. 配置 webpack-dev-server

	```json
	"scripts": {
		"test": "echo \"Error: no test specified\" && exit 1",
		"dev": "webpack serve"
	}
	```

4. 安装 html-webpack-plugin

	```shell
	npm install html-webpack-plugin@5.3.2 -D
	```

5. 在 webpack.config.js 配置 html-webpack-plugin

	```js
	const path = require('path')
	
	// 1.导入 html-webpack-plugin 插件，得到插件构造函数
	const HtmlPlugin = require('html-webpack-plugin')
	// 2.new 构造函数，创建插件实例对象
	const htmlPlugin = new HtmlPlugin({
	  // 指定要复制那个页面
	  template: './src/index.html',
	  // 指定复制出来的文件名和存放路径
	  filename: './index.html'
	})
	
	module.exports = {
	  mode: 'development',
	  plugins: [htmlPlugin]
	}
	```

	

