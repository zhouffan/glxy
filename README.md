# glxy
learn springboot/vue

## java

1 项目（spring initializr）-> 新建module（maven）

2 swagger:  .paths(Predicates.not(PathSelectors.regex("/admin/.*")))  //拦截了该地址，不会显示

3 请求参数

```java
//http://localhost:8101/admin/edu/teacher/1/5?begin=&end=&level=1&name=xx
@GetMapping("{page}/{limit}")
public R pageQuery(@PathVariable Long page,  @PathVariable Long limit,  TeacherQuery teacherQuery){
        log.info(teacherQuery.toString());
        Page<Teacher> pageParam = new Page<>(page, limit);
        teacherService.pageQuery(pageParam, teacherQuery);
        List<Teacher> records = pageParam.getRecords();
}
```

4 图片存储方式： 阿里云oss对象存储

5 表格操作： easyExcel













## 前端

#### vue

1 ECMAScript 6.0（以下简称 ES6）是 JavaScript 语言的下一代标准。编写复杂的大型应用程序。

2 ECMAScript 和 JavaScript 的关系是，前者是后者的规格，后者是前者的一种实现。

3 ES标准中不包含 DOM 和 BOM的定义，只涵盖基本数据类型、关键字、语句、运算符、内建对象、内建函数等通用语法。

4 语法：

```js
<!-- {{}} 插值表达式，绑定vue中的data数据 -->
{{ message }}
=======================================分割线=============================================v-bind:title=  :title=
<!-- 如果要将模型数据绑定在html属性中，则使用 v-bind 指令此时title中显示的是模型数据-->
<h1 v-bind:title="message">
 {{content}}
</h1>
<!-- v-bind 指令的简写形式： 冒号（:） -->
<h1 :title="message">
 {{content}}
</h1>
=======================================分割线==============================================v-bind:value=  v-model= 输入框
<!-- v-bind:value只能进行单向的数据渲染 -->
<input type="text" v-bind:value="searchMap.keyWord">
<!-- v-model 可以进行双向的数据绑定 -->
<input type="text" v-model="searchMap.keyWord">
=======================================分割线==============================================v-on:click=    @click=
<!-- v-on 指令绑定事件，click指定绑定的事件类型，事件发生时调用vue中methods节点中定义的方法 -->
<button v-on:click="search()">查询</button>

<!-- v-on 指令的简写形式 @ -->
<button @click="search()">查询</button>
=======================================分割线===============================================v-on:submit.prevent=
<!-- 修饰符用于指出一个指令应该以特殊方式绑定。这里的 .prevent 修饰符告诉 v-on 指令对于触发的事件调用js的event.preventDefault()：即阻止表单提交的默认行为 -->
<form action="save" v-on:submit.prevent="onSubmit"> 
  <label for="username"> 
    <input type="text" id="username" v-model="user.username"> 
    <button type="submit">保存</button>
  </label>
</form>

=======================================分割线===============================================v-if=
<input type="checkbox" v-model="ok">同意许可协议
<!-- v:if条件指令：还有v-else、v-else-if 切换开销大 -->
<h1 v-if="ok">if：Lorem ipsum dolor sit amet.</h1> 
<h1 v-else>no</h1>

<!-- v:show 条件指令 初始渲染开销大 -->
<h1 v-show="ok">show：Lorem ipsum dolor sit amet.</h1> 
<h1 v-show="!ok">no</h1>
=======================================分割线===============================================v-for="(n, index) in 5"
<!-- 1、简单的列表渲染 -->
<ul>
	<li v-for="n in 10">{{ n }} </li>
</ul> 
<ul>
  <!-- 如果想获取索引，则使用index关键字，注意，圆括号中的index必须放在后面 -->
  <li v-for="(n, index) in 5">{{ n }} - {{ index }} </li>
</ul>
=======================================分割线================================================Vue.component('Navbar', {
// 定义全局组件
Vue.component('Navbar', {
	template: '<ul><li>首页</li><li>学员管理</li><li>讲师管理</li></ul>'
})
// 引用
<div id="app"> 
	<Navbar></Navbar>
</div> 
<script src="vue.min.js"></script> 
<script src="components/Navbar.js"></script> 
<script>
  var app = new Vue({
  el: '#app'
   })
</script>
=======================================分割线=======================================================================

```

5 Vue.js 路由需要载入 vue-router 库。

6 axios是独立于vue的一个项目，基于promise用于浏览器和node.js的http客户端。

 eg:

```javascript
//http://127.0.0.1:8201/admin/edu/course/1/10?subjectParentId=1178214681181483010&subjectId=1178214681210843137&title=ss&teacherId=1189390295668469762     
getPageList(page, limit, searchObj) { //searchObj:  {p1:"v1", p2:"v2"}
        debugger
        return request({
            url: `${api_name}/${page}/${limit}`,
            method: 'get',
            params: searchObj //此处为 params
        })
    }
//对应java接收
@GetMapping("{page}/{limit}")
public R pageQuery(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "courseQuery", value = "查询对象", required = false)
                    CourseQuery courseQuery){ //此处接收 params
        Page<Course> pageParam = new Page<>(page, limit);
        courseService.pageQuery(pageParam, courseQuery);
        List<Course> records = pageParam.getRecords();
        long total = pageParam.getTotal();
        return R.ok().data("total", total).data("rows", records);
}
==========================================================================================分割线
//http://127.0.0.1:8201/admin/edu/course/1/10    
getPageList(page, limit, searchObj) { //searchObj:  {p1:"v1", p2:"v2"}
        debugger
        return request({
            url: `${api_name}/${page}/${limit}`,
            method: 'post',
            data: searchObj //此处为    ================data
        })
    }
//对应java接收
@PostMapping("{page}/{limit}")=======================================post请求  传 RequestBody
public R pageQuery(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "courseQuery", value = "查询对象", required = false)
            @RequestBody TeacherQuery teacherQuery){ //此处接收 data==================@RequestBody接收
        Page<Course> pageParam = new Page<>(page, limit);
        courseService.pageQuery(pageParam, courseQuery);
        List<Course> records = pageParam.getRecords();
        long total = pageParam.getTotal();
        return R.ok().data("total", total).data("rows", records);
}

```





#### node   

1 Node.js 就是运行在服务端的 JavaScript。

   Node.js是一个事件驱动I/O服务端JavaScript环境，基于Google的V8引擎，V8引擎执行Javascript的速度非常快，性能非常好。

- LTS：长期支持版本

- Current：最新版

2 浏览器的内核包括两部分核心：

- DOM渲染引擎；

- js解析器（js引擎）

js运行在浏览器中的内核中的js引擎内部。Node.js是脱离浏览器环境运行的JavaScript程序，基于V8 引擎（Chrome 的 JavaScript的引擎）

3 NPM全称Node Package Manager，是Node.js包管理工具；相当于前端的Maven 。 

4 Babel是一个广泛使用的转码器，可以将ES6代码转为ES5代码，从而在现有环境执行执行。

- 可以现在就用 ES6 编写程序，而不用担心现有环境是否支持。

- Babel提供babel-cli工具，用于命令行转码。

- Babel的配置文件是.babelrc，存放在项目的根目录下，该文件用来设置转码规则和插件

5 Webpack 是一个前端资源加载/打包工具。它将根据模块的依赖关系进行静态分析，然后将这些模块按照指定的规则生成对应的静态资源。

- Webpack 可以将多种静态资源 js、css、less 转换成一个静态文件，减少了页面的请求。

- 安装：npm install -g webpack webpack-cli 

  ```js
  //读取当前项目目录下src文件夹中的main.js（入口文件）内容，分析资源依赖，把相关的js文件打包，打包后的文件放入当前目录的dist文件夹下，打包后的js文件名为bundle.js
  
  const path = require("path"); //Node.js内置模块
  module.exports = {
    entry: './src/main.js', //配置入口文件
    output: {
      path: path.resolve(__dirname, './dist'), //输出路径，__dirname：当前文件所在路径
      filename: 'bundle.js' //输出文件
     }
  }
  ```

- npm run dev









## mysql

1、**库名与应用**名称尽量一致

2、<u>表名、字段名</u>必须使用**小写字母**或数字，禁止出现数字开头，

3、表名**不**使用**复数**名词

4、表的命名最好是加上“**业务名称_表**的作用”。如，edu_teacher

5、表必备三字段：**id, gmt_create, gmt_modified**

说明：

其中 id 必为主键，类型为 **bigint unsigned、单表时自增、步长为 1**。（如果使用**分库分表**集群部署，则id类型为**varchar，非自增**，业务中使用分布式id生成器）

gmt_create, gmt_modified 的类型均为 datetime 类型，前者现在时表示主动创建，后者过去分词表示被动更新。

6、单表**行数超过 500 万行**或者单表**容量超过 2GB**，才**推荐进行分库分表**。 说明：如果预计三年后的数据量根本达不到这个级别，请不要在创建表时就分库分表。

7、表达是**与否**概念的字段，必须使用 **is_xxx** 的方式命名，数据类型是 **unsigned tinyint** （**1 表示是，0 表示否**）。

说明：任何字段如果为**非负数**，必须是 **unsigned**。

注意：**POJO 类**中的任何布尔类型的变量，都**不要加 is** 前缀。数据库表示是与否的值，使用 tinyint 类型，坚持 is_xxx 的 命名方式是为了明确其取值含义与取值范围。

正例：表达逻辑删除的字段名 is_deleted，1 表示删除，0 表示未删除。

8、小数类型为 **decimal**，禁止使用 float 和 double。 说明：float 和 double 在存储的时候，存在精度损失的问题，很可能在值的比较时，得到不 正确的结果。如果存储的数据范围超过 decimal 的范围，建议将数据**拆成整数和小数**分开存储。

9、如果存储的字符串**长度几乎相等**，使用 **char** 定长字符串类型。

10、varchar 是可变长字符串，不预先分配存储空间，长度不要**超过 5000**，如果存储长度大于此值，定义字段类型为 **text**，**独立出来一张表，用主键来对应**，避免影响其它字段索 引效率。

11、唯一索引名为 **uk**_字段名；普通索引名则为 idx_字段名。

说明：uk_ 即 unique key；idx_ 即 index 的简称

12、**不得使用外键与级联**，一切外键概念必须**在应用层解决**。外键与级联更新适用于单机低并发，不适合分布式、高并发集群；**级联更新是强阻塞**，存在数据库更新风暴的风险；外键影响数据库的插入速度。





## 命令



1 vs code : **Shift + Option + F**

2 nginx   :   nginx -s reload

```
mac 使用brew安装nginx 各种命令
安装：brew install nginx      或者      sudo brew install nginx
启动：brew services start nginx       或者         sudo brew services start nginx
重启：brew services restart nginx        或者         sudo brew services restart nginx
停止：brew services stop nginx           或者            sudo brew services stop nginx
查看：cat usr/local/etc/nginx/nginx.conf
编辑：vi usr/local/etc/nginx/nginx.conf
```

3 打开文件： open -a /Applications/Sublime\ Text.app/ nginx.conf  （自动补全的 /Application/ 后输入 V 然后 tab，根据提示接着 输入&tab 组合）



















