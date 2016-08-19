## 项目介绍
#### 简要概述：
AdminSJMS(Admin Spark Job Manager System) 是一个基于Spark的Job管理软件，你可以通过它管理Spark平台的任务和所有节点。
本软件可以给所有用户分配HDFS(Hadoop Distributed File System)空间，用户可以操作自己的HDFS文件，并将文件的位置作为Spark任务的数据输入路径。
本项目是SJMS(Spark Job Manager System)的一个子项目，SJMS项目还包括一个提供给用户的一个Job的提交软件，以及提供Spark和HDFS相关操作的接口的软件。
本项目所有软件基于MIT协议开源，开源的相关细节可以在最下面的"许可"看到。

#### 项目功能及特点：
>* 直观简洁的页面
>* 管理Spark集群的状态
>* 管理任务列表
>* 管理集群的所有节点
>* 完备的HDFS操作
>* 轻松管理用户及用户的任务
>* 提供相应的系统设置

#### 详细介绍：
软件的相关截图和描述:

集群概览：显示集群当前的概览情况
![cmd-markdown-logo](/SCREENSHOT/screenshot001.png)

节点列表：显示所有节点状态信息
![cmd-markdown-logo](/SCREENSHOT/screenshot002.png)

任务管理：显示所有任务状态信息
![cmd-markdown-logo](/SCREENSHOT/screenshot003.png)

通告通知：所有通告通知列表
![cmd-markdown-logo](/SCREENSHOT/screenshot004.png)

个人信息：显示和修改个人信息
![cmd-markdown-logo](/SCREENSHOT/screenshot005.png)

节点管理：显示节点状态信息
![cmd-markdown-logo](/SCREENSHOT/screenshot006.png)

## 项目构建

本软件基于了一些开源的软件，在这里感谢开源社区的力量。下面是我们使用的一部分开源软件:

> [Jquery](https://github.com/jquery/jquery) by [jQuery Foundation](https://github.com/jquery)
> [Flot](https://github.com/flot/flot) by [Flot](https://github.com/flot)
> [admin-lte](https://github.com/almasaeed2010/AdminLTE) by [Abdullah Almsaeed](https://github.com/almasaeed2010)
> [sigar](https://github.com/hyperic/sigar) by [Hyperic](https://github.com/hyperic)
> [JFinal](https://git.oschina.net/jfinal/jfinal) by [JFinal](https://git.oschina.net/jfinal)

注意: 如果你希望使用上面的开源软件，请仔细阅读他们的开源许可。


## 开源许可

AdminSJMS是一个基于 [MIT license](http://opensource.org/licenses/MIT) 的开源软件，在下面你可以看到本软件的完整的开源协议

        The MIT License (MIT)

        Copyright (c) 2016 bigdata.ahu.edu.cn

        Permission is hereby granted, free of charge, to any person obtaining a copy
        of this software and associated documentation files (the "Software"), to deal
        in the Software without restriction, including without limitation the rights
        to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
        copies of the Software, and to permit persons to whom the Software is
        furnished to do so, subject to the following conditions:

        The above copyright notice and this permission notice shall be included in all
        copies or substantial portions of the Software.

        THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
        IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
        FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
        AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
        LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
        OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
        SOFTWARE.
							